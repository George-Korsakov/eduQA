package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class GroupDate {
  public void setGroupID(int groupID) {
    this.groupID = groupID;
  }

  private  int groupID;
  private final String groupName;
  private final String groupHeader;
  private final String groupCommmet;

  public GroupDate(int groupID, String groupName, String groupHeader, String groupCommmet) {
    this.groupID = groupID;
    this.groupName = groupName;
    this.groupHeader = groupHeader;
    this.groupCommmet = groupCommmet;
  }

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

  public GroupDate(String groupName, String groupHeader, String groupCommmet) {
    this.groupID = Integer.MAX_VALUE;
    this.groupName = groupName;
    this.groupHeader = groupHeader;
    this.groupCommmet = groupCommmet;
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
