package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactShortData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    app.getNavigationHelper().gotoHomePage();
    // проверка на выполнение предуслоовия, создание контакта
    if(! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createShortContact(new ContactShortData("NameTest",  "LastNameTest", "Ru.Msk", "+74991234567", "t@t.t"));
    }
    int Before = app.getContactHelper().getContactCount();
    // редактирование полей первого контакта в списке
    app.getContactHelper().selectContact(Before-1);
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("NameTest1", "MiddleNameTest0e", "LastNameTest0e", "NickNameTest0e", "C:\\Temp\\04.jpg", "Test edit", "CoTest", "Russian, Spb, Nevskaya str, 12", "+78121230907", "test1@test.test", "25", "November", "1987", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().retutnHomePage();
    int After = app.getContactHelper().getContactCount();
    app.getNavigationHelper().gotoExit();
    // проверка числа группы в списке до и после
    Assert.assertEquals(Before, After);

  }

}
