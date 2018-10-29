package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.appmanager.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.tests.GroupCreationTests;

public class SimpleTestRunner  extends TestBase {


  @Test
  public void test1() { new GroupCreationTests(); return; };

  @Test
  public void test2() { new ContactCreationTests(); return;};

    /*ContactModificationTests step3 = new ContactModificationTests();
     ContactDelitionTests step4 = new ContactDelitionTests();
     GroupModificationTest step5 = new GroupModificationTest();
     GroupDelitionTests step6 = new GroupDelitionTests();*/




}
