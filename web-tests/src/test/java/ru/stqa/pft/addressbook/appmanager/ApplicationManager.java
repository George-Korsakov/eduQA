package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ApplicationManager {
private final Properties properties;
  WebDriver wd;
  private NavigationHelper navigationHelper;
  private SessionHelper sessionHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser){

    this.browser = browser;
    properties = new Properties();

  }

  public void init() throws IOException {
    // параметриазция, для использования конфигураицонного файла
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    // вызов метода подключения к БД
    dbHelper = new DbHelper();
    // выбор браузера
 /*
    if("".equals(properties.getProperty("selenium.server"))) {
    if("local".equals(properties.getProperty("selenium.server"))) {
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      } else if (browser.equals(BrowserType.GOOGLECHROME)) {
        wd = new ChromeDriver();
      }
    } }else {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName(browser);
      //capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "Win7")));
      wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
    }
*/
    if("".equals(properties.getProperty("selenium.server"))) {
      wd = new FirefoxDriver();
      if (browser.equals(BrowserType.FIREFOX)) {
             wd = new FirefoxDriver();
      } else  if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      }
      else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      } else if(browser.equals(BrowserType.GOOGLECHROME)) {
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.GOOGLECHROME)) {
      }	         wd = new ChromeDriver();
    }
   else {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setBrowserName(browser);
    wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
  }
      // ожидание пояявление элемента на странице 5c для подстраховки
    wd.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
    //  переход на страницу и авторизация
    wd.get(properties.getProperty("web.baseUrl"));
    navigationHelper = new NavigationHelper(wd);
    groupHelper = new GroupHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    // параметризован ввод логина и пароля
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }

  public void stop() {
    wd.close();
   // wd.quit();

  }
// проверка
  public boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public ContactHelper contact() {
    return contactHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public DbHelper db() {
    return dbHelper;
  }

  // возвращает скриншот сделаный webdriver-ом
  public byte[] takeScreenshot() {
    return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
  }

}
