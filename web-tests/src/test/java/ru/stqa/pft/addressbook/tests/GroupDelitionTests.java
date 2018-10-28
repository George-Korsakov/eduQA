package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

public class GroupDelitionTests extends TestBase {


  @Test
  public void testGroupDelition() {

    app.getNavigationHelper().gotoGroupPage();
    // проверка наличия группы, создание при необходимости
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupDate("TestGroup1", "null", null));
    };
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage(); // намерено оставленный метод вне класса помошника
  }


}
