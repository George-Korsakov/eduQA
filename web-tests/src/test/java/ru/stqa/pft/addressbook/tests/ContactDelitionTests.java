package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.util.Set;

public class ContactDelitionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {

    app.goTo().homePage();
    // проверка на выполнение предуслоовия, создание контакта
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactShortData().withFname("NewTest").withLname("LastNameTest"));
    }
  }


  @Test
  public void testContactDelition() {
    // удаление выбранного контакта в списке
    Set<ContactShortData> before = app.contact().all();
    ContactShortData deletedContact = before.iterator().next();

    app.contact().delete(deletedContact);

    // подстраховака если не отобразился список контактов, то переходим на страницу с контактами
    if (!app.contact().isThereAContact()) {
      app.goTo().homePage();
    }

    //app.goTo().homePage();
    Set<ContactShortData> after = app.contact().all();
    // проверка числа группы в списке до и после +1
    Assert.assertEquals(before.size(), after.size() + 1);

// сравнение списков по содержанию
    before.remove(deletedContact);
    Assert.assertEquals(before, after);

  }

}

