package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation()  {

    initContactCreation();
    fillContactForm(new ContactData("NameTest6", "MiddleNameTest7", "LastNameTest7", "NickNameTest7", "C:\\Temp\\04.jpg", "Test123", "CoTest", "Russian, Moscow, Red Square, 1", "+74951230007", "test1@test.test", "5", "September", "1987", "TestGroup2"));
    submitContactCreation();
    retutnHomePage();
  }

}
