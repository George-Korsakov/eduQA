package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class GroupDate {

  int groupID = Integer.MAX_VALUE;
  String groupName;
  String groupHeader;
  String groupCommmet;


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupDate groupDate = (GroupDate) o;
    return Objects.equals(groupName, groupDate.groupName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupName);
  }




  public GroupDate withtGroupID(int groupID) {
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


  @Override
  public String toString() {
    return "GroupDate{" + "groupID='" + groupID + '\'' + ", groupName='" + groupName + '\'' + '}';
  }

  public int getGroupID() {  return groupID; }

  public String getGroupName() {
    return groupName;
  }

  public String getGroupHeader() {
    return groupHeader;
  }

  public String getGroupCommmet() {
    return groupCommmet;
  }
}
