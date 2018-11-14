package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SimpleTestRunner extends TestBase {

  WebDriver wd;

  /*public simpleTestRunner(WebDriver wd) {
    this.wd = wd;
  }
  */

  @Test(enabled = false)
  public void test() {

    app.goTo().homePage();

    int sum = (int) app.contact().getContactCount();
    System.out.println("Contacts = " + sum);


  }
}
