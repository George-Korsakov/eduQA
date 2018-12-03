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
import java.util.Set;

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

  // проверка наличиея связи группы и контакта
  public boolean isContactHasLinkGroup(int id, int group_id){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Integer> groupsList =  session.createNativeQuery("SELECT group_id " + " FROM address_in_groups " + " WHERE id = " + id).list();
    // перебор значений для сравнения
    for(int i=0; i < groupsList.size(); i++){
      if (groupsList.get(i) == group_id){ return true;  }
      }
    session.getTransaction().commit();
    session.close();
    return false;
  }
// получение id группы не привязанной к  контакту
  public int isContactHasNotLinkGroup(int id){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Integer> allGroups = session.createNativeQuery("SELECT group_id " + " FROM group_list "+ "where deprecated = '0000-00-00'" ).list();
    List<Integer> groupsList =  session.createNativeQuery("SELECT group_id " + " FROM address_in_groups " + " WHERE id = " + id).list();
    for(int i=0; i < allGroups.size(); i++){
      for(int j=0; j < groupsList.size(); j++) {
        if (groupsList.get(j) != allGroups.get(i)) return allGroups.get(i);
      }
    }
    session.getTransaction().commit();
    session.close();
    return allGroups.get(0);
  }
  // проверка налчичия конаткат в группе , возвращает номер группы
  public int isAnyGroupIncludeContact(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Integer> groups=  session.createNativeQuery("SELECT group_id " + " FROM address_in_groups " ).list();
    session.getTransaction().commit();
    session.close();
    if(groups.size() > 0) {
      return groups.iterator().next();
    }
    return 0;
  }
public int groupIncludeContact (int id){
  Session session = sessionFactory.openSession();
  session.beginTransaction();
  List<Integer> contacts =  session.createNativeQuery("SELECT id " + " FROM address_in_groups " + " WHERE group_id = " + id).list();
  session.getTransaction().commit();
  session.close();
  if(contacts.size()> 0 ) {   return contacts.iterator().next();}
  else {return 0;}
}

  // получение значения id по имени группы, но имена могут совпадать у разных групп
  public int findGroupIdbyName(String groupName){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Integer> groupId = session.createNativeQuery("SELECT group_id " + " FROM group_list " + " WHERE group_name = '" + groupName + "'" ).list();
    session.getTransaction().commit();
    session.close();
    return groupId.get(0);
  }

  public List<Integer> groupsIds(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Integer> groupsIds = session.createQuery("SELECT group_id " + "FROM group_list" + "where deprecated = '0000-00-00'" ).list();
            session.getTransaction().commit();
    session.close();
    return groupsIds;
  }
}
