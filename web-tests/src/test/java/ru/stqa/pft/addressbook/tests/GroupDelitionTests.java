package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;

public class GroupDelitionTests extends TestBase {


  @Test
  public void testGroupDelition()  {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.returnToGroupPage();
  }


}
