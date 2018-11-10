package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    //int r = (int)(Math.random()*1000000);
    app.getNavigationHelper().gotoHomePage();
    // проверка на выполнение предуслоовия, создание контакта
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createShortContact(new ContactShortData("NewTest", "LastNameTest"));
    }
    //int before = app.getContactHelper().getContactCount();
    List<ContactShortData> before = app.getContactHelper().getContactList();
    // редактирование полей первого контакта в списке
    app.getContactHelper().selectContact(before.size() - 1);
    ContactShortData contact = new ContactShortData(before.get(before.size() - 1).getContactID(), "NameTestEdit", "LastNameTestEdit");
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillShortContactForm(contact);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().retutnHomePage();
    //int after = app.getContactHelper().getContactCount();
    List<ContactShortData> after = app.getContactHelper().getContactList();
    // проверка числа контаков в списке до и после
    Assert.assertEquals(before.size(), after.size());

    // проверка сравнением списков по значению
    before.remove(before.size() - 1);

    before.add(contact);
    // сортировка перед сравнением
    Comparator<? super ContactShortData> byID = (c1, c2) -> Integer.compare(c1.getContactID(), c2.getContactID());
    before.sort(byID);
    after.sort(byID);
    // проверка сравнением списков
    // Assert.assertEquals(before,after);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    // выход из приложения
   // app.getNavigationHelper().gotoExit();

  }

}
