package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    // не обязательное действие по прееходу на додмаашнюю страницу для проверки
    app.getNavigationHelper().gotoHomePage();
    int Before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("NameTest2", "MiddleNameTest2", "LastNameTest2", "NickNameTest2", "C:\\Temp\\03.jpg", "Test123", "CoTest", "Russian, Moscow, Red Square, 1", "+74951230007", "test1@test.test", "5", "September", "1987", "TestGroup1"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().retutnHomePage();
    int After = app.getContactHelper().getContactCount();
    Assert.assertEquals(Before, After +1);
    app.getNavigationHelper().gotoExit();


  }

}
