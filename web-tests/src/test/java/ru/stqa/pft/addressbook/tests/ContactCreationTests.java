package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {



  @Test
  public void testContactCreation() {
    // int r = (int)(Math.random()*1000000);
    // не обязательное действие по прееходу на страницу контактов для подстраховки
    app.goTo().homePage();
    Set<ContactShortData> before = app.contact().all();
    ContactShortData contact = new ContactShortData().withFname("NameTest1").withLname("LastNameTest1");

    app.contact().initContactCreation();
    app.contact().fillShortContactForm(contact);
    app.contact().submitContactCreation();
    app.contact().retutnHomePage();

    Set<ContactShortData> after = app.contact().all();
    // проверка сравнением размеров спсисков
    Assert.assertEquals(before.size(), after.size() - 1);


    // проверка сравнением множеств
    // анониманая функция принимающая в качесвте атрибута объект типа ContactShortData выделяет id целые числа и находит максимальное
    contact.withContactID(after.stream().mapToInt( (c) -> c.getContactID()).max().getAsInt() );
    before.add(contact);
    Assert.assertEquals(before, after);


     // для одиночного теста, удалить
    // app.goTo().gotoExit();

  }
}
