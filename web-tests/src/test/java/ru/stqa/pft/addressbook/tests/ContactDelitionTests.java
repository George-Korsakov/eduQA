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
    app.getNavigationHelper().gotoHomePage();
    // проверка на выполнение предуслоовия, создание контакта
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createShortContact(new ContactShortData("NewTest", "LastNameTest"));
    }
  }


  @Test
  public void testContactDelition() {

    // удаление выбранного контакта в списке
    List<ContactShortData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    app.getContactHelper().selectContact(index);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().submitContactDelete();
    // подстраховака если не отобразился спсиок контактов, то переходим на страницу с контактами
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoHomePage();
    }
    ;
    //app.getNavigationHelper().gotoHomePage();
    List<ContactShortData> after = app.getContactHelper().getContactList();
    // проверка числа группы в списке до и после +1
    Assert.assertEquals(before.size(), after.size() + 1);

// сравнение списков по содержанию
    before.remove(index);

    for (int i = 0; i < after.size(); i++) {
      Assert.assertEquals(before.get(i), after.get(i));
    }
   // app.getNavigationHelper().gotoExit();
  }

}
