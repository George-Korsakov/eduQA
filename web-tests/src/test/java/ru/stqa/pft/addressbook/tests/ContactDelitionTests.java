package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.util.List;

public class ContactDelitionTests extends TestBase {

  @Test
  public void testContactDelition() {
// удаление первого контакта в списке
    app.getNavigationHelper().gotoHomePage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createShortContact(new ContactShortData("NameTest", "LastNameTest"));
      app.getNavigationHelper().gotoHomePage();
    }
    //int beforec = app.getContactHelper().getContactCount();
    List<ContactShortData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().submitContactDelete();
    // подстраховака если не отобразился спсиок контактов, то переходим на страницу с контактами
    if (!app.getContactHelper().isThereAContact()) {
      app.getNavigationHelper().gotoHomePage();
    }
    ;
    //app.getNavigationHelper().gotoHomePage();
    List<ContactShortData> after = app.getContactHelper().getContactList();
    // проверка числа группы в списке до и после
    Assert.assertEquals(before.size(), after.size() + 1);

    // проверка сравнением
    before.remove(before.size() - 1);
 /* Сравнение в цикле
    for(int i=0; i< after.size(); i++){
      Assert.assertEquals(before.get(i), after.get(i));
      //System.out.println("before " + before.get(i));
      //System.out.println("after" + after.get(i));
    }*/
// сравнение списков
    Assert.assertEquals(before, after);

   // app.getNavigationHelper().gotoExit();
  }

}
