package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

//import org.hibernate.annotations.Entity;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupDate {
  //@XStreamOmitField
  @Id // специальная антоация для индентификатора
  @Column(name = "group_id")
  private int groupID = Integer.MAX_VALUE;
  @Expose
  @Column(name = "group_name")
  private String groupName;
  @Expose
  @Column(name = "group_header")
  @Type(type = "text") // антотация для преобразования типов
  private String groupHeader;
  @Expose
  @Column(name = "group_footer")
  @Type(type = "text")
  private String groupCommmet;

  @Override
  public String toString() {
    return "GroupDate{" + "groupID='" + groupID + '\'' + ", groupName='" + groupName + '\'' + '}';
  }

  public int getGroupID() {
    return groupID;
  }

  // сеттеры измененные
  public void setGroupID(int groupID) {
    this.groupID = groupID;
  }

  public GroupDate withGroupId(int groupID) {
    this.groupID = groupID;
    return this;
  }

  public GroupDate withGroupName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  public GroupDate withGroupHeader(String groupHeader) {
    this.groupHeader = groupHeader;
    return this;
  }

  public GroupDate withGroupCommmet(String groupCommmet) {
    this.groupCommmet = groupCommmet;
    return this;
  }
// геттеры

  public String getGroupName() {
    return groupName;
  }

  public String getGroupHeader() {
    return groupHeader;
  }

  public String getGroupCommmet() {
    return groupCommmet;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupDate groupDate = (GroupDate) o;
    return groupID == groupDate.groupID && Objects.equals(groupName, groupDate.groupName) && Objects.equals(groupHeader, groupDate.groupHeader) && Objects.equals(groupCommmet, groupDate.groupCommmet);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupID, groupName, groupHeader, groupCommmet);
  }
}
