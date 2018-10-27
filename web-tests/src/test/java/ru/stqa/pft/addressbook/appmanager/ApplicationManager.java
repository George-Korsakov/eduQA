package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

//import sun.plugin2.util.BrowserType;


//import sun.plugin2.util.BrowserType;

import java.util.concurrent.TimeUnit;


public class ApplicationManager {

  WebDriver wd;
  private NavigationHelper navigationHelper;
  private SessionHelper sessionHelper;
  private GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private String browser;

  public ApplicationManager(String browser) {

    this.browser = browser;
  }

  public void init() {
  // выбор браузера
  if(browser.equals(BrowserType.FIREFOX)){
    wd = new FirefoxDriver();
  } else if (browser.equals(BrowserType.IE)) {
    wd = new InternetExplorerDriver();
  } else if(browser.equals(BrowserType.GOOGLECHROME)) {
    wd = new ChromeDriver();
  }

    wd = new FirefoxDriver();
    // вызов драйвера, переход на страницу и авторизация
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    //  переход на страницу и авторизация
    wd.get("http://localhost/addressbook/");
    navigationHelper = new NavigationHelper(wd);
    groupHelper = new GroupHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login("admin", "secret");
  }


  // возврат на страницу списка групп временно оставлена вне класса помошника
  public void returnToGroupPage() {
    wd.findElement(By.linkText("Logout")).click();
    ;
  }

  public void stop() {
    wd.quit();
  }

  public boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }
}
