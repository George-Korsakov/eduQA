package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {
    int r = (int) (Math.random() * 1000);
    app.goTo().groupPage();

    GroupDate group = new GroupDate().withGroupName("TestGroup").withGroupHeader("TestHeaderFroup" + r);
    List<GroupDate> before = app.group().list();

    app.group().initGroupCreation();
    app.group().fillGroupForm(group);
    app.group().submitGroupCreation();
    app.group().returnToGroupPage(); // переход на страницу спсика групп

    List<GroupDate> after = app.group().list();
    // проверка сравнением размеров списков до  и после
    Assert.assertEquals(before.size(), after.size() - 1);

    // проверка сравнением списков по содержанию
    before.add(group);
    Comparator<? super GroupDate> byID = (g1, g2) -> Integer.compare(g1.getGroupID(), g2.getGroupID());
    before.sort(byID);
    after.sort(byID);

    // сравнение списков
    Assert.assertEquals(before, after);
  }


}
