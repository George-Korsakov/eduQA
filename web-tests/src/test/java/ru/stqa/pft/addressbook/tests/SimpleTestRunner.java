package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class SimpleTestRunner  extends TestBase {


    @Test
            public void testEmpty(){
     System.out.println("Тут будет какой то тест !");
      app.getGroupHelper().returnToGroupPage();
  }



}
