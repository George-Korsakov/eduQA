package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactShortData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {

    app.getNavigationHelper().gotoHomePage();
    int Before = app.getGroupHelper().getGgroupCount();
    // проверка на выполнение предуслоовия, создание контакта
    if(! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createShortContact(new ContactShortData("NameTest",  "LastNameTest", "Ru.Msk", "+74991234567", "t@t.t"));
    }
        // редактирование полей первого контакта в списке
    app.getContactHelper().selectContact(Before-1);
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("NameTest1", "MiddleNameTest0e", "LastNameTest0e", "NickNameTest0e", "C:\\Temp\\04.jpg", "Test edit", "CoTest", "Russian, Spb, Nevskaya str, 12", "+78121230907", "test1@test.test", "25", "November", "1987", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().retutnHomePage();
    app.getNavigationHelper().gotoExit();
    int After = app.getGroupHelper().getGgroupCount();
    // проверка числа группы в списке до и после
    Assert.assertEquals(Before, After);

  }

}
