package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
// редактирование полей первого контакта в списке
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("NameTest1", "MiddleNameTest0e", "LastNameTest0e", "NickNameTest0e", "C:\\Temp\\04.jpg", "Test edit", "CoTest", "Russian, Spb, Nevskaya str, 12", "+78121230907", "test1@test.test", "25", "November", "1987", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoExit();


  }

}
