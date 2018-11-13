package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {
    int r = (int) (Math.random() * 1000);
    app.getNavigationHelper().gotoGroupPage();

    GroupDate group = new GroupDate().withGroupName("TestGroup");
    Set<GroupDate> before = app.group().all();

    app.group().initGroupCreation();
    app.group().fillGroupForm(group);
    app.group().submitGroupCreation();
    app.group().returnToGroupPage(); // переход на страницу спсика групп

    Set<GroupDate> after = app.group().all();
    // проверка сравнением размеров списков до  и после
    Assert.assertEquals(before.size(), after.size() - 1);

    // проверка сравнением списков по содержанию
    group.withtGroupID(after.stream().mapToInt((g) -> g.getGroupID().max().getAsInt()));
    before.add(group);
    Assert.assertEquals(before, after);

  }


}
