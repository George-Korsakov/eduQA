package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupModificationTest extends TestBase {


  @BeforeMethod
  public void ensurePrecondition() {
    int r = (int) (Math.random() * 1000);
// редактирование полей первой группы в списке
    app.goTo().groupPage();
    // проверка наличия группы, создание при необходимости
    if (app.group().all().size() == 0) {
      app.group().create(new GroupDate().withGroupName("TestGroup0" + r));
    }

  }

  @Test
  public void testGroupModification() {

    Set<GroupDate> before = app.group().all();
    GroupDate modifyedGroup = before.iterator().next();
    //int index = before.size() - 1;
    GroupDate group = new GroupDate()
            .withGroupId(modifyedGroup.getGroupID()).withGroupName("TestEGroup1").withGroupHeader("TestHeaderFroup_e").withGroupCommmet("TestComment_e");
    app.group().modify(group);
    Set<GroupDate> after = app.group().all();
    // проверка числа группы в списке до и после
    Assert.assertEquals(before.size(), after.size());

    // проверка сравнением списков
    before.remove(modifyedGroup);
    before.add(group);


    // проверка сравнением списков
    Assert.assertEquals(before, after);
    //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }


}