package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import sun.plugin2.util.BrowserType;


public class TestBase {

  protected ApplicationManager app = new ApplicationManager(BrowserType.INTERNET_EXPLORER);

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }


  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();

  }

}

