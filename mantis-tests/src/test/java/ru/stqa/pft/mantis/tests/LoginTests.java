package ru.stqa.pft.mantis.tests;


import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import javax.xml.rpc.ServiceException;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

  @Test
  public void testLogin() throws IOException, ServiceException {
    // toDo сделать параметром issueId
    int issueId = 9;
    if ( !skipIfNotFixed(issueId)) {
      HttpSession session = app.newSession();
      //session.login("administrator", "root");
      assertTrue(session.login("administrator", "root"));
      assertTrue(session.isLoggedInAs("administrator"));
    } else {System.out.println("Ignored because of issue " + issueId + " not fixed !");}
  }
}
