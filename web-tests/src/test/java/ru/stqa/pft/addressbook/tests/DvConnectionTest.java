package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DvConnectionTest {

  @Test
  public void testDbConnection() {
    Connection conn = null;

    try {
      // подключение к БД
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=&serverTimezone=UTC");
      Statement st = conn.createStatement();
      // SQL запрос для получения данных о группе
      ResultSet rs = st.executeQuery("select group_id, group_name, group_header, group_footer from group_list ");
      Groups groups = new Groups();
      // цикл начитки из БД данных о группах в списко
      while (rs.next()){
        groups.add(new GroupDate().withGroupId(rs.getInt("group_id")).withGroupName(rs.getString("group_name"))
                .withGroupHeader(rs.getString("group_header")).withGroupCommmet(rs.getString("group_footer")));
      }
      rs.close();
      st.close();
      conn.close();
      System.out.println(groups);


    } catch (SQLException ex) {
      // для вывода сообщений об ошибках
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }

  }
}
