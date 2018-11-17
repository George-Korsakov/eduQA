package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;
// статический импорт методов для проверок (улучшеине читаемости кода)
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Contacts before = app.contact().all();
    ContactShortData deletedContact = before.iterator().next();

    app.contact().delete(deletedContact);

    // подстраховака если не отобразился список контактов, то переходим на страницу с контактами
    if (!app.contact().isThereAContact()) {
      app.goTo().homePage();
    }

    Contacts after = app.contact().all();
    // проверка числа группы в списке до и после +1
    Assert.assertEquals(before.size(), after.size()-1 );

    // проверки в fluent-стиле, используя Hamcrest
    assertThat(after, equalTo(before.withOut(deletedContact)));

  }

}

