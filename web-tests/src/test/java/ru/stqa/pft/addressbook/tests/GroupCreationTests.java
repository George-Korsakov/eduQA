package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {
    int r = (int)(Math.random()*1000);
    app.getNavigationHelper().gotoGroupPage();
    List<GroupDate> before = app.getGroupHelper().getGroupList();
    //int before = app.getGroupHelper().getGroupCount();
    GroupDate group = new GroupDate("TestGroup", "TestHeaderFroup"+r, null);
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroupPage(); // переход на страницу спсика групп
    List<GroupDate> after = app.getGroupHelper().getGroupList();
    //int after = app.getGroupHelper().getGroupCount();
    Assert.assertEquals(before.size(), after.size() - 1);

    // проверка сравнением списков
    // поиск максимального id
    int max = 0;
    for(GroupDate g: after){
      if(g.getGroupID()> max ){
        max = g.getGroupID();
      }
      group.setGroupID(max);
    }
    before.add(group);
    // сравнение списков
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }


}
