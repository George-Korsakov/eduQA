package ru.stqa.pft.mantis.tests;


import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.models.MailMessage;
import ru.stqa.pft.mantis.models.User_data;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.testng.Assert.assertTrue;

public class ChangeUserPasswordTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validUsersFormJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/test_user.json")));) {
      String line = reader.readLine();

      String json = "";
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<User_data> user_test = gson.fromJson(json, new TypeToken<List<User_data>>() {
      }.getType());
      return user_test.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }


  @BeforeMethod
  public void startMailServer() throws IOException, MessagingException {
    app.mail().start();
    // проверка и обеспечение предусловия
    if (app.db().getUsers().size() < 2 ) {
      // создание пользователя
      long now= System.currentTimeMillis();
      String user = String.format("user%s" , now);
      String password = "password";
      String email = String.format("user%s@loclahost.localdomain", now);
      app.registration().start(user, email);
      app.mail().waitForMail(2, 10000);
      List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
      findChangePWDLink(mailMessages, email);
      String confirmationLink = findChangePWDLink(mailMessages, email);
      app.registration().finish(confirmationLink,  password);
    }
  }

  @Test(dataProvider = "validUsersFormJson")
  public void testChangeUserPassword (User_data user_test) throws IOException, MessagingException {
  // подготовка тестовых данных , адимнистратор первый с id = 1 , последующие созданные пользователи
    String newPass = UUID.randomUUID().toString();
    String aLogin = app.getProperty("web.adminLogin");
    String aPassword = app.getProperty("web.adminPassword");

    // проверк существования заданного в файле тестовых данных пользователя по имени в БД
    if(!app.db().checkUserExsist(user_test.username)){
      // если пользователя нет, то он создается
      String user = String.format(user_test.username);
      String password = "password";
      String email = String.format(user_test.email);
      app.registration().start(user, email);
      app.mail().waitForMail(2, 10000);
      List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
      findChangePWDLink(mailMessages, email);
      String confirmationLink = findChangePWDLink(mailMessages, email);
      app.registration().finish(confirmationLink,  password);
    }

    // использование пользователя заданного в файле тестовых данных test_user.json
    User_data user = app.db().getOneUser(user_test.username).get(0);
      // пользователя из БД с заданным id = 2 , т.к. id=1 администратор
      // User_data user = app.db().getOneUser(2).get(0);

  // тест сброса пароля
  app.manageUsers().loginAdmin(aLogin, aPassword);
  app.manageUsers().resetUserPassword(user.getUsername());
    // получение email с текстом подтверждением
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String link = findChangePWDLink(mailMessages, user.getEmail());
    // смена ппароля
  app.manageUsers().changeUserPassword(link, newPass);
     // проверка путем авторизации с новым паролем
    HttpSession session = app.newSession();
    assertTrue(session.login(user.getUsername(), newPass));
    assertTrue(session.isLoggedInAs(user.getUsername()));
  }

  private String findChangePWDLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}

