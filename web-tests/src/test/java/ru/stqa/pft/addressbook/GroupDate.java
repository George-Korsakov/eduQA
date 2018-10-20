package ru.stqa.pft.addressbook;

public class GroupDate {
  private final String groupName;
  private final String groupHeader;
  private final String groupCommmet;

  public GroupDate(String groupName, String groupHeader, String groupCommmet) {
    this.groupName = groupName;
    this.groupHeader = groupHeader;
    this.groupCommmet = groupCommmet;
  }

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
