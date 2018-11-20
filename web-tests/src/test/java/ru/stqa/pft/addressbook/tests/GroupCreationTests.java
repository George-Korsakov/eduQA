package ru.stqa.pft.addressbook.tests;

        import org.hamcrest.CoreMatchers;
        import org.hamcrest.MatcherAssert;
        import org.testng.Assert;
        import org.testng.annotations.DataProvider;
        import org.testng.annotations.Test;
        import ru.stqa.pft.addressbook.model.GroupDate;
        import ru.stqa.pft.addressbook.model.Groups;


// статический импорт методов для проверок (улучшеине читаемости кода)
        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;

        import static org.hamcrest.CoreMatchers.equalTo;
        import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
  // провайдер данных содержит список тестовых данных
  @DataProvider
  public Iterator<Object[]> validGroups() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new GroupDate().withGroupName("test1").withGroupHeader("test2").withGroupCommmet("test3")});
    list.add(new Object[] {new GroupDate().withGroupName("test10").withGroupHeader("test20").withGroupCommmet("test3")});
    list.add(new Object[] {new GroupDate().withGroupName("test100").withGroupHeader("test200").withGroupCommmet("test3")});
    return list.iterator();
  }

  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupDate group) {
    int r = (int) (Math.random() * 1000);

      app.goTo().groupPage();
      //GroupDate group = new GroupDate().withGroupName(name + r).withGroupHeader(header).withGroupCommmet(footer);
      Groups before = app.group().all();
      app.group().create(group);

      Groups after = app.group().all();

      // проверка сравнением размеров списков до  и после
      assertThat(before.size(), equalTo(after.size() - 1));

      // проверки в fluent-стиле, используя Hamcrest
      assertThat(after, equalTo(before.withAdded(group.withGroupId(after.stream().mapToInt((g) -> g.getGroupID()).max().getAsInt()))));

  }


}
