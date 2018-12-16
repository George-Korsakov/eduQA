package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  // подтверждение оперции в форме группы
  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  // заполнение полей формы группы
  public void fillGroupForm(GroupDate groupDate) {
    type(By.name("group_name"), groupDate.getGroupName());
    type(By.name("group_header"), groupDate.getGroupHeader());
    type(By.name("group_footer"), groupDate.getGroupCommmet());
  }

  // создание новой группы вызов формы
  public void initGroupCreation() {
    click(By.name("new"));
  }

  // удаление выбранной группы
  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

  // выбор группы для удаления по номеру в списке
  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
  }


  // переход к списку групп
  public void returnToGroupPage() {
    wd.findElement(By.linkText("groups")).click();

  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  // создание группы
  public void create(GroupDate group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    groupCahe = null;
    returnToGroupPage();
  }


  public void modify(GroupDate group) {
    selectGroupById(group.getGroupID());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCahe = null;
    returnToGroupPage();
  }

  // удаление группы

  public void delete(GroupDate group) {
    selectGroupById(group.getGroupID());
    deleteSelectedGroups();
    groupCahe = null;
    returnToGroupPage();
  }
  // для проверки групп

  public boolean isThereAGroup() {
    return isElementPresents(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  // метод получение списка
  public List<GroupDate> list() {
    List<GroupDate> groups = new ArrayList<GroupDate>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    // в цикле заполняется список полученными именами групп
    for (WebElement element : elements) {
      String name = element.getText();
      int ID = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      //GroupDate group = new GroupDate().withGroupId(groupID).withGroupName(name);
      groups.add(new GroupDate().withGroupId(ID).withGroupName(name));
    }
    return groups;
  }

  private Groups groupCahe = null;

  // метод получения списка с кешированием
  public Groups all() {
    if (groupCahe != null) {
      return new Groups(groupCahe);
    } else {
      groupCahe = new Groups();
      List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
      // в цикле заполняется список полученными именами групп
      for (WebElement element : elements) {
        String name = element.getText();
        int ID = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
        //GroupDate group = new GroupDate().withGroupId(groupID).withGroupName(name);
        groupCahe.add(new GroupDate().withGroupId(ID).withGroupName(name));
      }
      return new Groups(groupCahe);
    }
  }
}
