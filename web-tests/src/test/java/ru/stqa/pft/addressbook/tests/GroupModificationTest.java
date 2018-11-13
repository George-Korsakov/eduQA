package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTest extends TestBase {



  @BeforeMethod
  public void ensurePrecondition(){
    int r = (int) (Math.random() * 1000);
// редактирование полей первой группы в списке
    app.getNavigationHelper().gotoGroupPage();
    // проверка наличия группы, создание при необходимости
    if (!app.group().isThereAGroup()) {
      app.group().createGroup(new GroupDate().withGroupName("TestGroup"));
    };
  }

  @Test
  public void testGroupModification() {

    Set<GroupDate> before = app.group().all();
    GroupDate modifiedGroup = before.iterator().next();
    GroupDate group = new GroupDate()
            .withtGroupID(modifiedGroup.getGroupID()).withGroupName("TestEGroup1").withGroupHeader("TestHeaderFroup_e").withGroupCommmet("TestComment_e");
    app.group().modifyGroup( group);
    Set<GroupDate> after = app.group().all();
    // проверка числа группы в списке до и после
    Assert.assertEquals(before.size(), after.size());

    // проверка сравнением списков
    before.remove(modifiedGroup);
    before.add(group);


    // проверка сравнением списков
    Assert.assertEquals(before, after);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }



}