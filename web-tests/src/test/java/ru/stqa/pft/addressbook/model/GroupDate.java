package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class GroupDate {
  public void setGroupID(int groupID) {
    this.groupID = groupID;
  }

  private int groupID = Integer.MAX_VALUE;
  private String groupName;
  private String groupHeader;
  private String groupCommmet;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupDate groupDate = (GroupDate) o;
    return groupID == groupDate.groupID && Objects.equals(groupName, groupDate.groupName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupID, groupName);
  }

  @Override
  public String toString() {
    return "GroupDate{" + "groupID='" + groupID + '\'' + ", groupName='" + groupName + '\'' + '}';
  }

  public int getGroupID() {
    return groupID;
  }
// сеттеры измененные

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
}
