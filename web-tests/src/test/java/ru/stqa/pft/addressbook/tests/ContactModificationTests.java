package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {

    app.goTo().homePage();
    // проверка на выполнение предуслоовия, создание контакта
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactShortData().withFname("NewTest").withLname("LastNameTest"));
    }
  }

  @Test
  public void testContactModification() {

    Set<ContactShortData> before = app.contact().all();
    ContactShortData modifyContact = before.iterator().next();


    ContactShortData contact = new ContactShortData().withContactID(modifyContact.getContactID()).withFname("NameTest1").withLname("LastNameTestEdit");
    // редактирование полей выбранного контакта в списке
    app.contact().modify(contact);
    Set<ContactShortData> after = app.contact().all();

    // проверка числа контаков в списке до и после
    Assert.assertEquals(before.size(), after.size());

    // проверка сравнением списков по значению
    before.remove(modifyContact);
    before.add(contact);

    Assert.assertEquals(before, after);


  }


}
