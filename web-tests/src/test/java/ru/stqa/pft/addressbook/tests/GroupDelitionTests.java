package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.util.List;

public class GroupDelitionTests extends TestBase {


  @BeforeMethod
  public void ensurePrecondition() {
    int r = (int) (Math.random() * 1000);
// редактирование полей первой группы в списке
    app.getNavigationHelper().gotoGroupPage();
    // проверка наличия группы, создание при необходимости
    if (app.group().list().size()==0) {
      app.group().createGroup(new GroupDate().withGroupName("TestGroup"));
    }
    ;
  }


  @Test
  public void testGroupDelition() {

    List<GroupDate> before = app.group().list();
    int index =  before.size() - 1;
    app.group().deleteGroup(index);
    List<GroupDate> after = app.group().list();

    // проврека рутем срправнения размеров списков
    Assert.assertEquals(after.size(), before.size() - 1);
    // проверка путем сравнение списков по элементно
    before.remove(before.size() - 1);
    for (int i = 0; i < after.size(); i++) {
      Assert.assertEquals(before.get(i), after.get(i));
    }

  }




}
