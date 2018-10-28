package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification() {
// редактирование полей первой группы в списке
    app.getNavigationHelper().gotoGroupPage();
    // проверка наличия группы, создание при необходимости
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupDate("TestGroup1", "null", null));
    };
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupDate("TestGroup1", "TestHeaderFroup_e", "TestComment_e"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();

  }
}