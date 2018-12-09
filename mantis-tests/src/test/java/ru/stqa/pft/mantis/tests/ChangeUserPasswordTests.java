package ru.stqa.pft.mantis.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.models.MailMessage;
import ru.stqa.pft.mantis.models.User_data;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.UUID;

import static org.testng.Assert.assertTrue;

public class ChangeUserPasswordTests extends TestBase {

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

  @Test
  public void testChangeUserPassword () throws IOException, MessagingException {
  // подготовка тестовых данных , адимнистратор первый с id = 1 , последующие созданные пользователи
    String newPass = UUID.randomUUID().toString();
    String aLogin = app.getProperty("web.adminLogin");
    String aPassword = app.getProperty("web.adminPassword");
    // получаем пользователя из БД с заданным id
    User_data user = app.db().getOneUser(2).get(0);
    //String user = app.db().getOneUser(2).get(0).toString();
    //String email = app.db().getOneUser(2).get(1).toString();

  app.manageUsers().loginAdmin(aLogin, aPassword);
  app.manageUsers().resetUserPassword(user.getUsername());
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String link = findChangePWDLink(mailMessages, user.getEmail());
  app.manageUsers().changeUserPassword(link, newPass);

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

