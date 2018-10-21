package ru.stqa.pft.addressbook;

import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation()  {

    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupDate("TestGroup0", "TestHeaderFroup", "TestComment"));
    submitGroupCreation();
    returnToGroupPage();
  }


}
