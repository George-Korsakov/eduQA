package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.appmanager.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.tests.GroupCreationTests;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.util.ArrayList;
import java.util.List;

public class SimpleTestRunner  extends TestBase {

  WebDriver wd;

  /*public simpleTestRunner(WebDriver wd) {
    this.wd = wd;
  }
  */
  @Test
  public void test () {
    //app.init();
    app.getNavigationHelper().gotoHomePage();

    int sum = (int) app.getContactHelper().getContactCount();
    System.out.println("Contacts = " + sum);

    app.getContactHelper().getContactList().get(1);

    List<WebElement> allR = wd.findElements(By.tagName("entry"));

    //String valueA = allR.stream().findElements(By.tagName("td")).get(1).toString();
    //System.out.println("Test = " + valueA);

    }
}
