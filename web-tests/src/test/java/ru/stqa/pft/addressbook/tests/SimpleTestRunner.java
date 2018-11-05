package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.appmanager.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.tests.GroupCreationTests;

import java.util.List;

public class SimpleTestRunner  extends TestBase {


  @Test
  public void test1() {
    app.getNavigationHelper().gotoHomePage();
    int sum = (int)app.getContactHelper().getContactCount();

    List<ContactShortData> contactAll = app.getContactHelper().getContactList();
        for(ContactShortData cont : contactAll){
      System.out.print("test " + cont.getFname() + " " + cont.getLname() );
    }
    System.out.print("test " + contactAll.get(1) + " " + contactAll.get(2) + " " + contactAll.get(3));
    System.out.print(" test contact summ is " + sum);

    Assert.assertEquals(contactAll.size(), -99);
  //  return;
  };

  //@Test
 // public void test2() { new ContactCreationTests(); return;};

    /*ContactModificationTests step3 = new ContactModificationTests();
     ContactDelitionTests step4 = new ContactDelitionTests();
     GroupModificationTest step5 = new GroupModificationTest();
     GroupDelitionTests step6 = new GroupDelitionTests();*/


}
