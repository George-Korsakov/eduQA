package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

public class ContactDelitionTests extends TestBase {

  @Test
  public void testContactDelition() {
// удаление первого контакта в списке
    app.getNavigationHelper().gotoHomePage();
    if(! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createShortContact(new ContactShortData("NameTest",  "LastNameTest", "test", "00991234567", "t@t.t"));
      app.getNavigationHelper().gotoHomePage();
    }
    int Before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(Before-1);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().submitContactDelete();
    int After = app.getContactHelper().getContactCount();
    // проверка числа группы в списке до и после
    Assert.assertEquals(Before, After +1);
    app.getNavigationHelper().gotoExit();
  }

}
