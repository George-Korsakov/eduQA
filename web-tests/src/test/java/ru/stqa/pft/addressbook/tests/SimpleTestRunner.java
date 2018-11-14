package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class SimpleTestRunner extends TestBase {

  WebDriver wd;

  /*public simpleTestRunner(WebDriver wd) {
    this.wd = wd;
  }
  */

  @Test(enabled = false)
  public void test() {

    app.getNavigationHelper().gotoHomePage();

    int sum = (int) app.getContactHelper().getContactCount();
    System.out.println("Contacts = " + sum);


  }
}
