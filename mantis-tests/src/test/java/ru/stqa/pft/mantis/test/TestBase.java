package ru.stqa.pft.mantis.test;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;


public class TestBase {


  // protected static ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
  public static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.GOOGLECHROME));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }


  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();

  }


}

