package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation()  {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupDate("TestGroup1", "TestHeaderFroup", "TestComment"));
    app.getGroupHelper().submitGroupCreation();
    app.returnToGroupPage();
  }


}
