package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.util.List;

public class ContactDelitionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition(){
    //int r = (int)(Math.random()*1000000);
    app.goTo().homePage();
    // проверка на выполнение предуслоовия, создание контакта
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactShortData("NewTest", "LastNameTest"));
    }
  }


  @Test
  public void testContactDelition() {

    // удаление выбранного контакта в списке
    List<ContactShortData> before = app.contact().list();
    int index = before.size() - 1;

    app.contact().delete(index);

    // подстраховака если не отобразился спсиок контактов, то переходим на страницу с контактами
    if (!app.contact().isThereAContact()) {
      app.goTo().homePage();
    }
    ;
    //app.goTo().homePage();
    List<ContactShortData> after = app.contact().list();
    // проверка числа группы в списке до и после +1
    Assert.assertEquals(before.size(), after.size() + 1);

// сравнение списков по содержанию
    before.remove(index);

    for (int i = 0; i < after.size(); i++) {
      Assert.assertEquals(before.get(i), after.get(i));
    }
   // app.goTo().gotoExit();
  }


}
