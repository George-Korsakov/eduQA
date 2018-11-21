package ru.stqa.pft.addressbook.tests;

        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;
        import com.thoughtworks.xstream.XStream;
        import org.hamcrest.CoreMatchers;
        import org.hamcrest.MatcherAssert;
        import org.testng.Assert;
        import org.testng.annotations.DataProvider;
        import org.testng.annotations.Test;
        import ru.stqa.pft.addressbook.model.GroupDate;
        import ru.stqa.pft.addressbook.model.Groups;


// статический импорт методов для проверок (улучшеине читаемости кода)
        import java.io.*;
        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;
        import java.util.stream.Collectors;

        import static org.hamcrest.CoreMatchers.equalTo;
        import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {
  // провайдер данных содержит список тестовых данных
  @DataProvider
  public Iterator<Object[]> validGroupsFormXml() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));) {
      String line = reader.readLine();

      // чтение тестовых данных из xml файла
      String xml = "";
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(GroupDate.class);
      List<GroupDate> groups = (List<GroupDate>) xstream.fromXML(xml);
      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
    /*// чтение файла csv
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups2.csv")));
    String line = reader.readLine();
    while (line != null){
      String[] split = line.split(";");
      list.add(new Object[]{new GroupDate().withGroupName(split[0]).withGroupHeader(split[1]).withGroupCommmet(split[2])});
      line = reader.readLine(); }
    return list.iterator();*/
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFormJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
   try ( BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));) {
     String line = reader.readLine();

     String json = "";
     while (line != null) {
       json += line;
       line = reader.readLine();
     }
     Gson gson = new Gson();
     List<GroupDate> groups = gson.fromJson(json, new TypeToken<List<GroupDate>>() {
     }.getType());
     return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
   }
  }

    @Test(dataProvider = "validGroupsFormJson")
  public void testGroupCreation(GroupDate group) {
    int r = (int) (Math.random() * 1000);

      app.goTo().groupPage();
      Groups before = app.group().all();
      app.group().create(group);
      Groups after = app.group().all();

      // проверка сравнением размеров списков до  и после
      assertThat(before.size(), equalTo(after.size() - 1));

      // проверки в fluent-стиле, используя Hamcrest
      assertThat(after, equalTo(before.withAdded(group.withGroupId(after.stream().mapToInt((g) -> g.getGroupID()).max().getAsInt()))));

  }


}
