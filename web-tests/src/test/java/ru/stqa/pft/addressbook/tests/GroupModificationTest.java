package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTest extends TestBase {


  @BeforeMethod
  public void ensurePrecondition() {
    int r = (int) (Math.random() * 1000);
// редактирование полей первой группы в списке
    if (app.db().groups().size() == 0){
      app.group().create(new GroupDate().withGroupName("TestGroup0" + r));
    }
    app.goTo().groupPage();
    // проверка наличия группы, создание при необходимости
    /*if (app.group().all().size() == 0) {
      app.group().create(new GroupDate().withGroupName("TestGroup0" + r));
    }*/

  }

  @Test
  public void testGroupModification() {

    Groups before = app.db().groups();
            //Groups before = app.group().all();
    GroupDate modifyedGroup = before.iterator().next();
        GroupDate group = new GroupDate().withGroupId(modifyedGroup.getGroupID()).withGroupName("TestEGroup1").withGroupHeader("TestHeaderFroup_e").withGroupCommmet("TestComment_e");
    app.group().modify(group);
    //Groups after = app.group().all();
    Groups after = app.db().groups();
       // проверка числа группы в списке до и после
    Assert.assertEquals(before.size(), after.size());

    // проверки в fluent-стиле, используя Hamcrest
    assertThat(after, equalTo(before.withOut(modifyedGroup).withAdded(group)));
    verifyGroupListInUI();
  }


}