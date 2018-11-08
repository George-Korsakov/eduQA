package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
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
   /* int max = 0;
    for(GroupDate g: after){
      if(g.getGroupID()> max ){
        max = g.getGroupID();
      }
      group.setGroupID(max);
    }*/

    // другой способ с использованием компаратора
  /*  Comparator<? super GroupDate> byiD = new Comparator<GroupDate>() {
      @Override
      public int compare(GroupDate o1, GroupDate o2) {
        return Integer.compare(o1.getGroupID(),o2.getGroupID());
      }
    };
    int max = after.stream().max(byiD).get().getGroupID();*/

  // способ с использованием лябда-выражений , дает зависимость от версии java 8
    //group.setGroupID(after.stream().max((Comparator<GroupDate>) (o1, o2) -> Integer.compare(o1.getGroupID(),o2.getGroupID())).get().getGroupID());

    before.add(group);

    Comparator<? super GroupDate> byID = (g1, g2) -> Integer.compare(g1.getGroupID(), g2.getGroupID());
    before.sort(byID);
    after.sort(byID);

    // сравнение списков
    Assert.assertEquals(before,after);
  }


}
