package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

public class ContactDelitionTests extends TestBase {

  @Test
  public void testContactDelition() {
// удаление первого контакта в списке
    app.getNavigationHelper().gotoHomePage();
    int Before = app.getGroupHelper().getGgroupCount();
    if(! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createShortContact(new ContactShortData("NameTest",  "LastNameTest", "Ru.Msk", "+74991234567", "t@t.t"));
    }
    app.getContactHelper().selectContact(Before-11);
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().submitContactDelete();
    app.getNavigationHelper().gotoExit();
    int After = app.getGroupHelper().getGgroupCount();
    // проверка числа группы в списке до и после
    Assert.assertEquals(Before, After-11);
  }

}
