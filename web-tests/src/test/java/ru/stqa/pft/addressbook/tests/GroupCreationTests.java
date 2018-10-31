package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    int Before = app.getGroupHelper().getGgroupCount();
    // вариант использованиея app.getGroupHelper().createGroup(new GroupDate("TestGroup1", "TestHeaderFroup", null));
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupDate("TestGroup1", "TestHeaderFroup", null));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage(); // намерено  оставлена
    int After = app.getGroupHelper().getGgroupCount();
    Assert.assertEquals(Before, After + 1);
  }


}
