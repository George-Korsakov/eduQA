package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactShortData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    // запросом начитывается в цикле значения из БД
    // для выполнения запроса в классе модели данных нужно указать анотации с именем таблицы и именами столюцов
    // для групп
    List<GroupDate> result = session.createQuery("from GroupDate where deprecated = '0000-00-00'").list();
    for (GroupDate group : result) {
      System.out.println(group);
    }
    session.getTransaction().commit();
    session.close();
    return new Groups(result);

  }

  //  метод возвращающий список контактов
  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactShortData> result = session.createQuery("from ContactShortData where deprecated = '0000-00-00' ").list();
    for (ContactShortData contact : result) {
      System.out.println(contact);
    }
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }
}
