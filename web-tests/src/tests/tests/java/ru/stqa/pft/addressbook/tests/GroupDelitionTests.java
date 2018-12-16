package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;
// статический импорт методов для проверок (улучшеине читаемости кода)
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupDelitionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    int r = (int) (Math.random() * 1000);
// редактирование полей первой группы в списке
    app.goTo().groupPage();
    if (app.db().groups().size() == 0){
      app.group().create(new GroupDate().withGroupName("TestGroup0" + r));
    }
    // проверка наличия группы, создание при необходимости
    /*if (app.group().all().size() == 0) {
      app.group().create(new GroupDate().withGroupName("TestGroup0" + r));
    }*/
  }

  @Test
  public void testGroupDelition() {

    //Groups before = app.group().all();
    Groups before = app.db().groups();
    GroupDate deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    //Groups after = app.group().all();
    Groups after = app.db().groups();
    // проврека рутем срправнения размеров списков
    Assert.assertEquals(before.size(), app.group().getGroupCount()+1 );


    // проверки в fluent-стиле, используя Hamcrest
    assertThat(after, equalTo(before.withOut(deletedGroup)));

  }
}
