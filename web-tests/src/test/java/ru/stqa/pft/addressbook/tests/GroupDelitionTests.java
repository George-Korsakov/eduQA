package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.List;

public class GroupDelitionTests extends TestBase {


  @Test
  public void testGroupDelition() {

    app.getNavigationHelper().gotoGroupPage();
    // проверка наличия группы, создание при необходимости
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupDate("TestGroup02", "null", null));
    };
    List<GroupDate> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
    List<GroupDate> after = app.getGroupHelper().getGroupList();

    // проврека рутем срправнения размеров списков
    Assert.assertEquals(after.size(),before.size()-1 );
    // проверка путем сравнение списков по элементно
    before.remove(before.size() -1);
    for(int i=0; i< after.size(); i++){
      Assert.assertEquals(before.get(i), after.get(i));
    }

  }



}
