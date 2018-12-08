package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

  @BeforeMethod
  public void startMailServer() { app.mail().start(); }


  @Test
  public void testRegistration() throws IOException, MessagingException {
    long now= System.currentTimeMillis();
    String user = String.format("user%s" , now);
    String password = "password";
    String email = String.format("user%s@loclahost.localdomain", now);
    // добавление пользовтаеля на почтовом сервере James
    app.james().createUser(user, password);
    // создание нового пользователя
    app.registration().start(user, email);
    app.mail().waitForMail(2, 10000);
    // обравботка письма и подьвержение прегичтарции
    // обработка для встроенного почтонового сервера
     List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);

    // обработка для внешнего почтового сервера
   // List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    findConfirmationLink(mailMessages, email);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink,  password);
    // проверка авторизацей
    assertTrue(app.newSession().login(user, password));
  }
  // поиск ссылки подтверждения регичтрации в тексте письма
  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}
