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
  public void startMailServer() {
    app.mail().start();
    // проверка и обеспечение предуслвоия
    if (app.db().getUsers().size() < 2 ) {

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

