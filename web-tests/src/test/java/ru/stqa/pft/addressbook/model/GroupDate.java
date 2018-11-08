package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class GroupDate {
  private final String groupID;
  private final String groupName;
  private final String groupHeader;
  private final String groupCommmet;

  public GroupDate(String groupID, String groupName, String groupHeader, String groupCommmet) {
    this.groupID = groupID;
    this.groupName = groupName;
    this.groupHeader = groupHeader;
    this.groupCommmet = groupCommmet;
  }

  public GroupDate( String groupName, String groupHeader, String groupCommmet) {
    this.groupID = null;
    this.groupName = groupName;
    this.groupHeader = groupHeader;
    this.groupCommmet = groupCommmet;
  }



  @Override
  public String toString() {
    return "GroupDate{" + "groupID='" + groupID + '\'' + ", groupName='" + groupName + '\'' + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupDate groupDate = (GroupDate) o;
    return Objects.equals(groupID, groupDate.groupID) && Objects.equals(groupName, groupDate.groupName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(groupID, groupName);
  }

  public String getGroupID() {  return groupID; }

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
