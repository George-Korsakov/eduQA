package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactShortData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    int r = (int)(Math.random()*1000000);
    app.getNavigationHelper().gotoHomePage();
    // проверка на выполнение предуслоовия, создание контакта
    if(! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createShortContact(new ContactShortData("NewTest"+ r,  "LastNameTest", "Ru.Msk", "+74991234567", "t@t.t"));
    }
    int before = app.getContactHelper().getContactCount();
    // редактирование полей первого контакта в списке
    app.getContactHelper().selectContact(before-1);
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("NameTest4"+r, "MiddleNameTest0e", "LastNameTest0e"+r, "NickNameTest0e", "C:\\Temp\\04.jpg", "Test edit", "CoTest", "Russian, Spb, Nevskaya str, 12", "+78121230907", "test1@test.test", "25", "November", "1987", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().retutnHomePage();
    int after = app.getContactHelper().getContactCount();
    // проверка числа группы в списке до и после
    Assert.assertEquals(before, after);
    app.getNavigationHelper().gotoExit();

  }

}
