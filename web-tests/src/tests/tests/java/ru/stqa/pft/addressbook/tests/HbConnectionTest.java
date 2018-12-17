package ru.stqa.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class HbConnectionTest {

  private SessionFactory sessionFactory;

  @BeforeClass
  // метод подключения к БД
  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    } catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy(registry);
    }
  }

  @Test (enabled = false)
  public void testHbConnection() {
    // создание подключение к БД
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    // запросом начитывается в цикле значения из БД
    // для выполнения запроса в классе модели данных нужно указать анотации с именем таблицы и именами столюцов
    // для групп
  /*  List<GroupDate> result = session.createQuery( "from GroupDate" ).list();
    for ( GroupDate group : result ) {
      System.out.println(group);
    }*/
    // для контактов
    List<ContactShortData> result = session.createQuery("from ContactShortData where deprecated = '0000-00-00' ").list();

   // List<ContactShortData> result2 = session.createQuery("from ContactShortData where exists group_id and deprecated = '0000-00-00' and id = '213' ").list();
   // пример выборки id групп по id контакта
    int t = 213;
    List<String> result2 = session.createNativeQuery("SELECT group_id " + " FROM address_in_groups " + " WHERE id = " + t).list();
    System.out.println("Test = " + result2);



    session.getTransaction().commit();
    session.close();
    for (ContactShortData contact : result) {
      //System.out.println(contact);
      System.out.println(contact.getGroups());
    }

  }

}
