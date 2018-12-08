package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.util.List;

public class DbHelper {

  private ApplicationManager app;

  private final SessionFactory sessionFactory;

  public DbHelper() {
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public DbHelper(ApplicationManager app) {
    this.app = app;
  }


  // получения пользователя из БД
  public void getUserFromDb(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<Integer> userList =  session.createNativeQuery("SELECT id  " + " FFROM mantis_user_table " + "username != 'administrator'  ").list();
    // перебор значений для сравнения
    for(int i=0; i < userList.size(); i++){
      System.out.println(userList.get(i));
      }
    session.getTransaction().commit();
    session.close();
    return;
  }

}
