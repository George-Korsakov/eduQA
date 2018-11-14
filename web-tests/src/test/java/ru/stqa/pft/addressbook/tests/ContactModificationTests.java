package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    //int r = (int)(Math.random()*1000000);
    app.goTo().homePage();
    // проверка на выполнение предуслоовия, создание контакта
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactShortData("NewTest", "LastNameTest"));
    }
  }

  @Test
  public void testContactModification() {

    List<ContactShortData> before = app.contact().list();
    int index = before.size() - 1;
    ContactShortData contact = new ContactShortData(before.get(index).getContactID(), "NameTest1", "LastNameTestEdit");
    // редактирование полей выбранного контакта в списке
    app.contact().modify(index, contact);
    List<ContactShortData> after = app.contact().list();

    // проверка числа контаков в списке до и после
    Assert.assertEquals(before.size(), after.size());

    // проверка сравнением списков по значению
    before.remove(index);
    before.add(contact);
    // сортировка перед сравнением
    Comparator<? super ContactShortData> byID = (c1, c2) -> Integer.compare(c1.getContactID(), c2.getContactID());
    before.sort(byID);
    after.sort(byID);
    // проверка
    Assert.assertEquals(before, after);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    // выход из приложения
    // app.goTo().gotoExit();

  }


}
