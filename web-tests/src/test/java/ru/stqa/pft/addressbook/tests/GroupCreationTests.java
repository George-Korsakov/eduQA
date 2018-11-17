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

public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() {
    int r = (int) (Math.random() * 1000);
    app.goTo().groupPage();

    GroupDate group = new GroupDate().withGroupName("TestGroup" +r).withGroupHeader("TestHeaderFroup" + r);
    Groups before = app.group().all();
    app.group().create(group);

    Groups after = app.group().all();

    // проверка сравнением размеров списков до  и после
    assertThat(before.size(), equalTo(after.size()-1));

    // проверки в fluent-стиле, используя Hamcrest
    assertThat(after, equalTo(before.withAdded(
            group.withGroupId(after.stream().mapToInt( (g) -> g.getGroupID()).max().getAsInt() ))));

  }


}
