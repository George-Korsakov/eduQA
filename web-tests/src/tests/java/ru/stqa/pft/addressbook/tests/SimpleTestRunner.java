package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

public class SimpleTestRunner extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    // проверка на выполнение предуслоовия, создание контакта
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactShortData().withFname("NewTest").withLname("LastNameTest"));
    }
  }

  /*public simpleTestRunner(WebDriver wd) {
    this.wd = wd;
  }
  */

  @Test(enabled = false)
  public void test() {

    app.goTo().homePage();


    System.out.println(app.db().groups().stream().mapToInt((g) -> g.getGroupID()));


  }
}
