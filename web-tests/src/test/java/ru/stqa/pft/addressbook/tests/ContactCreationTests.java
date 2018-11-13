package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    // int r = (int)(Math.random()*1000000);
    // не обязательное действие по прееходу на додмаашнюю страницу для проверки
    app.getNavigationHelper().gotoHomePage();
    List<ContactShortData> before = app.getContactHelper().list();

    ContactShortData contact = new ContactShortData().withFname("NameTest1").withLname("LastNameTest1");
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillShortContactForm(contact);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().retutnHomePage();
    List<ContactShortData> after = app.getContactHelper().list();
    // проверка сравнением размеров спсисков
    Assert.assertEquals(before.size(), after.size() - 1);

    Comparator<? super ContactShortData> byId = new Comparator<ContactShortData>() {
      @Override
      public int compare(ContactShortData o1, ContactShortData o2) {
        return Integer.compare(o1.getContactID(), o2.getContactID());
      }
    };
    int max = after.stream().max(byId).get().getContactID();
    contact.withContactID(max);
    before.add(contact);
    // проверка сравнением списков
    // полиск максимального занчения id с использованием лябда-выражения
    Comparator<? super ContactShortData> byID = (c1, c2) -> Integer.compare(c1.getContactID(), c2.getContactID());
    before.sort(byID);
    after.sort(byID);

    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    // выход для повторной авторизации
    // app.getNavigationHelper().gotoExit();


  }

}
