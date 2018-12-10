package ru.stqa.pft.mantis.models;
import org.hibernate.annotations.Type;
import com.google.gson.annotations.Expose;
import javax.persistence.*;

@Entity
@Table(name =  "mantis_user_table")
public class User_data {
  @Override
  public String toString() {
    return "User_data{" +
            "username='" + username + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  @Id
  @Column(name = "id")
  public int id;

  @Expose
  @Column(name = "username")
  @Type(type = "string")
  public String username;

  @Expose
  @Column(name = "email")
  @Type(type = "string")
  public String email;

  public User_data(){
    this.username = "";
    this.email = "";
  }

  public User_data(String username, String email, String password){
    this.username = username;
    this.email = email;
  }
  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public User_data withUsername(String username) {
    this.username = username;
    return this;
  }

  public User_data withEmail(String email) {
    this.email = email;
    return this;
  }
}