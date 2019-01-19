package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
//import sun.plugin2.util.BrowserType;

@Listeners(MyTestListener.class)
public class TestBase {
  Logger logger = LoggerFactory.getLogger(TestBase.class);

 // protected static ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);
  public static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite(alwaysRun = true)
  public void setUp(ITestContext context) throws Exception {
    app.init();
    context.setAttribute("app", app);
  }


  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();

  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start tests  "  + m.getName() + "with paramentrs " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m){
    logger.info("Stop tests "  + m.getName());
  }


  public void verifyGroupListInUI() {
    // для включения проверки указать параметр -DverifyUI=true
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbgroups = app.db().groups();
      Groups uigroups = app.group().all();
      assertThat(uigroups, equalTo(dbgroups.stream()
              .map((g)-> new GroupDate().withGroupId(g.getGroupID()).withGroupName(g.getGroupName()))
              .collect(Collectors.toSet())));
    }

  }

}

