package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class GroupDate {
  private final String groupName;
  private final String groupHeader;
  private final String groupCommmet;

  public GroupDate(String groupName, String groupHeader, String groupCommmet) {
    this.groupName = groupName;
    this.groupHeader = groupHeader;
    this.groupCommmet = groupCommmet;
  }

/*
  public GroupDate(GroupDate group) {
    this.groupName = GroupDate.this.getGroupName();
    this.groupHeader = GroupDate.this.getGroupHeader();
    this.groupCommmet = GroupDate.this.getGroupCommmet();
  }
*/

// сравнение объектов грпп
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
// преобразование элемента списка групп в строку для вывода
  @Override
  public String toString() {
    return "GroupDate{" + "groupName='" + groupName + '\'' + '}';
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
