package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

public class ContactDelitionTests extends TestBase {

  @Test
  public void testContactDelition() {
// удаление первого контакта в списке
    app.getNavigationHelper().gotoHomePage();
    if(! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createShortContact(new ContactShortData("NameTest",  "LastNameTest", "Ru.Msk", "+74991234567", "t@t.t"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().submitContactDelete();
    app.getNavigationHelper().gotoExit();

  }

}
