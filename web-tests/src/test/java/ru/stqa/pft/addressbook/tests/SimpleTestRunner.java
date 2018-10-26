package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class SimpleTestRunner  extends TestBase {

  public static void main (){
    @Test
            ContactCreationTests.testContactCreation(1);


  }



}
