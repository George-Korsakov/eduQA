package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.models.User_data;

import java.util.List;

public class DbHelper extends HelperBase {
  private final SessionFactory sessionFactory;

  public DbHelper(ApplicationManager app) {
    super(app);
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public List<User_data> getUsers() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<User_data> result = session.createQuery( "from User_data where enabled = true" ).list();
    for ( User_data user : result ) {
      System.out.println(user);
    }
    session.getTransaction().commit();
    session.close();
    return result;
  }

  public List<User_data> getOneUser(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<User_data> result = session.createQuery( "from User_data where id = " + id ).list();

    //  System.out.println(" SQL query return = " + result.get(0).toString()+  " and " + result.get(1).toString());
     // User_data user = new User_data().withUsername(result.get(0)).withEmail(result.get(1));

    session.getTransaction().commit();
    session.close();
    return result;
  }

  public User_data getChosenUser(int id) {

    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<String> userList = session.createNativeQuery("SELECT username, email " + " FROM mantis_user_table " + " WHERE id = " + id).list();
    User_data user = new User_data().withUsername(userList.get(0).toString()).withEmail(userList.get(1).toString());
    session.getTransaction().commit();
    session.close();
    return user;
  }
}
