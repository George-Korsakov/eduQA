package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class SimpleTestRunner  extends TestBase {


    @Test
            public void testEmpty(){
     System.out.println("Последовательное выполнение тестов !");

     GroupCreationTests step1 = new GroupCreationTests();
     ContactCreationTests step2 = new ContactCreationTests();
     ContactModificationTests step3 = new ContactModificationTests();
     ContactDelitionTests step4 = new ContactDelitionTests();
     GroupModificationTest step5 = new GroupModificationTest();
     GroupDelitionTests step6 = new GroupDelitionTests();
  }



}
