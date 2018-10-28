package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    // не обязательное действие по прееходу на додмаашнюю страницу для проверки
    app.getNavigationHelper().gotoHomePage();

    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("NameTest3", "MiddleNameTest3", "LastNameTest3", "NickNameTest3", "C:\\Temp\\03.jpg", "Test123", "CoTest", "Russian, Moscow, Red Square, 1", "+74951230007", "test1@test.test", "5", "September", "1987", "TestGroup1"), true);
    app.getContactHelper().submitContactCreation();

    app.getContactHelper().retutnHomePage();
    app.getNavigationHelper().gotoExit();


  }

}
