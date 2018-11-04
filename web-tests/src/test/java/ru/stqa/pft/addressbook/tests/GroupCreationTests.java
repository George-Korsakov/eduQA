package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {
    int r = (int)(Math.random()*1000);
    app.getNavigationHelper().gotoGroupPage();
    int Before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupDate("TestGroup", "TestHeaderFroup"+r, null));
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage(); // переход на страницу спсика групп
    int After = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(Before, After - 1);
  }


}
