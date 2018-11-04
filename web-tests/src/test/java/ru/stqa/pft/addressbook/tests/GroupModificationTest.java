package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification() {
    int r = (int)(Math.random()*1000);
// редактирование полей первой группы в списке
    app.getNavigationHelper().gotoGroupPage();
    int Before = app.getGroupHelper().getGroupCount();
    // проверка наличия группы, создание при необходимости
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupDate("TestGroup0"+r, "null", null));
    };
    app.getGroupHelper().selectGroup(Before-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupDate("TestEGroup1"+r, "TestHeaderFroup_e" +r, "TestComment_e"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    int After = app.getGroupHelper().getGroupCount();
    // проверка числа группы в списке до и после
    Assert.assertEquals(Before, After);
  }
}