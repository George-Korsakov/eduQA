package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {

    app.goTo().homePage();
    // проверка на выполнение предуслоовия, создание контакта
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactShortData().withFname("NewTest").withLname("LastNameTest"));
    }
   /* if (app.contact().all().size() == 0) {
      app.contact().create(new ContactShortData().withFname("NewTest").withLname("LastNameTest"));
    }*/
  }

  @Test
  public void testContactModification() {
    int r = (int)(Math.random()*1000000);
    //Contacts before = app.contact().all();
    Contacts before = app.db().contacts();
    ContactShortData modifyContact = before.iterator().next();

    ContactShortData contact = new ContactShortData().withContactID(modifyContact.getContactID()).withFname("NameTest1").withLname("LastNameTestEdit")
            .withPhoneNumHome(String.valueOf(r*1)).withAddress("Ru, Spb, Nevskaya str, " + r ).withEmail("tests@tests.local");
    // редактирование полей выбранного контакта в списке
    app.contact().modify(contact);
    //Contacts after = app.contact().all();
    Contacts after = app.db().contacts();
    // проверка числа контаков в списке до и после
    Assert.assertEquals(before.size(), after.size());

    // проверки в fluent-стиле, используя Hamcrest
    assertThat(after, equalTo(before.withOut(modifyContact).withAdded(contact)));
    // дополнительная отключаемая проверка


  }

}