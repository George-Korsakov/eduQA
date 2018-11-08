package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTest extends TestBase {

  @Test
  public void testGroupModification() {
    int r = (int)(Math.random()*1000);
// редактирование полей первой группы в списке
    app.getNavigationHelper().gotoGroupPage();
    // проверка наличия группы, создание при необходимости
    if(! app.getGroupHelper().isThereAGroup()){
      app.getGroupHelper().createGroup(new GroupDate("TestGroup0"+r, "null", null));
    };
    List<GroupDate> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    GroupDate group = new GroupDate(before.get(before.size()-1).getGroupID(),"TestEGroup1"+r, "TestHeaderFroup_e" +r, "TestComment_e");
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupDate> after = app.getGroupHelper().getGroupList();
    // проверка числа группы в списке до и после
    Assert.assertEquals(before.size(), after.size());

    // проверка сравнением списков
    before.remove(before.size()-1);
    before.add(group);

    // сортировка перед сравнением
    Comparator<? super GroupDate> byID = (g1, g2) -> Integer.compare(g1.getGroupID(), g2.getGroupID());
    before.sort(byID);
    after.sort(byID);
    // проверка сравнением списков
    Assert.assertEquals(before,after);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}