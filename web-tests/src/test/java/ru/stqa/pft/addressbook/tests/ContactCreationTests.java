package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    int r = (int)(Math.random()*1000000);
    // не обязательное действие по прееходу на додмаашнюю страницу для проверки
    app.getNavigationHelper().gotoHomePage();
    List<ContactShortData> before = app.getContactHelper().getContactList();
    //int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContactCreation();
    // заполнение полей конатактов, для пропуска выбора группы указать значение [none]
    app.getContactHelper().fillContactForm(new ContactData("NameTest1"+r, "MiddleNameTest1", "LastNameTest1"+r, "NickNameTest1", "C:\\Temp\\03.jpg", "Test123", "CoTest", "Russian, Moscow, Red Square, 1", "+74951230007", "test1@test.test", "5", "September", "1987", "[none]"), true);
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().retutnHomePage();
    List<ContactShortData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(before.size(), after.size()+1);
    app.getNavigationHelper().gotoExit();

//    System.out.println(app.getContactHelper().getContactList());
  }

}
