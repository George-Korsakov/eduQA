package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.Set;

public class GroupDelitionTests extends TestBase {


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
  public void testGroupDelition() {

    Set<GroupDate> before = app.group().all();
    GroupDate deletedGroup = before.iterator().next();
    //int index = before.size() - 1;
    app.group().delete(deletedGroup);
    Set<GroupDate> after = app.group().all();

    // проврека рутем срправнения размеров списков
    Assert.assertEquals(after.size(), before.size() - 1);

    // проверка путем сравнение списков по элементно
    before.remove(deletedGroup);
    Assert.assertEquals(before, after);


  }


}
