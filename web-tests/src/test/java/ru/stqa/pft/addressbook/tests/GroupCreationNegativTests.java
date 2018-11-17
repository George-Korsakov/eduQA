package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;


// статический импорт методов для проверок (улучшеине читаемости кода)
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationNegativTests extends TestBase {


  @Test
  public void testGroupCreation() {
    int r = (int) (Math.random() * 1000);
    app.goTo().groupPage();
    // негатив в данных - в наименовнии таблицы используется символ одинарной кавычки (апостроф)
    GroupDate group = new GroupDate().withGroupName("TestGroup'").withGroupHeader("TestHeaderFroup" + r);
    Groups before = app.group().all();

    app.group().initGroupCreation();
    app.group().fillGroupForm(group);
    app.group().submitGroupCreation();
    app.group().returnToGroupPage(); // переход на страницу спсика групп
    assertThat(before.size(), equalTo(app.group().getGroupCount()));

    Groups after = app.group().all();

    // проверка сравнением размеров списков до и после\
    // воспроизводит подтверждение бага, группа не была создана, т.к. списки равны

    // проверки в fluent-стиле, используя Hamcrest
    assertThat(after, equalTo(before));

  }


}
