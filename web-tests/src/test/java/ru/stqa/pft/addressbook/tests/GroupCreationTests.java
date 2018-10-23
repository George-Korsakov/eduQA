package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation()  {

    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupDate("TestGroup1", "TestHeaderFroup", "TestComment"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }


}
