package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification() {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupDate("TestGroup1_edit", "TestHeaderFroup_e", "TestComment_e"));
    app.getGroupHelper().submitGroupModification();
    app.returnToGroupPage(); // намерено оставленный метод вне класса помошника
  }

}
