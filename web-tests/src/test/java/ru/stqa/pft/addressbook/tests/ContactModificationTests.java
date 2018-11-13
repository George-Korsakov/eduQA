package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition(){
    //int r = (int)(Math.random()*1000000);
    app.getNavigationHelper().gotoHomePage();
    // проверка на выполнение предуслоовия, создание контакта
    if (app.getContactHelper().list().size()==0) {
      app.getContactHelper().createShortContact(new ContactShortData().withFname("NewTest").withLname("LastNameTest"));
    }
  }

  @Test
  public void testContactModification() {

    Set<ContactShortData> before = app.getContactHelper().allContacts();
    GroupDate modifiedContact = before.iterator().next();
    ContactShortData contact = new ContactShortData().withContactID(modifiedGroup.getContactID()).withFname("NameTest1").withLname("LastNameTestEdit");
    // редактирование полей выбранного контакта в списке
    app.getContactHelper().modify(index, contact);
    Set<ContactShortData> after = app.getContactHelper().allContacts();

    // проверка числа контаков в списке до и после
    Assert.assertEquals(before.size(), after.size());

    // проверка сравнением списков по значению
    before.remove(modifiedGroup);
    before.add(contact);

    // проверка
     Assert.assertEquals(before,after);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    // выход из приложения
   // app.getNavigationHelper().gotoExit();

  }



}
