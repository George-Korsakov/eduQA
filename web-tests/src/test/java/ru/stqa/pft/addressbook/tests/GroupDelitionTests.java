package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

public class GroupDelitionTests extends TestBase {


  @Test
  public void testGroupDelition() {

    app.getNavigationHelper().gotoGroupPage();
    int Before = app.getGroupHelper().getGgroupCount();
    // проверка наличия группы, создание при необходимости
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupDate("TestGroup1", "null", null));
    };
    app.getGroupHelper().selectGroup(Before-1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    int After = app.getGroupHelper().getGgroupCount();
    Assert.assertEquals(Before, After - 1);
  }


}
