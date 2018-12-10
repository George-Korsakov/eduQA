package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.pft.mantis.models.User_data;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;



public class ApplicationManager {
  private final Properties properties;
  private WebDriver wd;

  private String browser;
  private RegistrationHelper registrationHelper;
  private String key;
  private String content;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private JamesHelper jamesHelper;
  private DbHelper dbHelper;
  private ManageUsersHelper manageUsers;


  public ApplicationManager(String browser) {

    this.browser = browser;
    properties = new Properties();

  }

  public void init() throws IOException {
    // параметриазция, для использования конфигураицонного файла
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));


    // выбор браузера
   /* if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    } else if (browser.equals(BrowserType.GOOGLECHROME)) {
      wd = new ChromeDriver();
    }

    // ожидание пояявление элемента на странице 5c для подстраховки
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    //  переход на страницу и авторизация
    wd.get(properties.getProperty("web.baseUrl"));
  } */
  }
  public void stop() {
    if(wd != null){
      wd.quit();
      //wd.close();
    }
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

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public void setPropertyUsername( String content) {
    this.properties.setProperty("web.username", content);
  }

 public RegistrationHelper registration() {
    if(registrationHelper == null){
      registrationHelper = new RegistrationHelper(this);
    }
  return registrationHelper;
  }

  public FtpHelper ftp(){
    if(ftp == null) {
      ftp = new FtpHelper(this);
    }
    return ftp;
  }
  
  public MailHelper mail(){
    if(mailHelper == null){
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public WebDriver getDriver() {
    if(wd == null) {
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      } else if (browser.equals(BrowserType.GOOGLECHROME)) {
        wd = new ChromeDriver();
      }
      // ожидание пояявление элемента на странице 5c для подстраховки
      wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
      //  переход на страницу и авторизация
      wd.get(properties.getProperty("web.baseUrl"));
    }
      return wd;
    }
  // ленивая инициализация для почтового сервера James
  public JamesHelper james() {
    if (jamesHelper == null) {
      jamesHelper = new JamesHelper(this);
    }
    return jamesHelper;
  }

  public DbHelper db(){
    if(dbHelper == null) {
       dbHelper = new DbHelper(this);
    }
    return dbHelper;
  }

  public ManageUsersHelper manageUsers() {
    if (manageUsers == null) {
      manageUsers = new ManageUsersHelper(this);
    }
    return manageUsers;
  }
  
}



