package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupDate;

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

  public void selectGroupbyId(int id) {

    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

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
  public void createGroup(GroupDate group) {
  initGroupCreation();
  fillGroupForm(group);
  submitGroupCreation();
  returnToGroupPage();
  }
// изменение группы
  public void modifyGroup(int index, GroupDate group) {
    selectGroup(index);
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    returnToGroupPage();
  }
// удаление группыё
public void deleteGroup(int index) {
  selectGroup(index);
  deleteSelectedGroups();
  returnToGroupPage();
}

  public void deleteGroup(GroupDate group) {
    selectGroupbyId(group().getId);
    deleteSelectedGroups();
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
    List<GroupDate> groups  = new ArrayList<GroupDate>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    // в цикле заполняется список полученными именами групп
    for(WebElement element: elements){
      String name = element.getText();
      int groupID = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupDate().withtGroupID(groupID).withGroupName(name));
    }
    return groups;
  }
  // метод получения множества

  public Set<GroupDate> all() {
    Set<GroupDate> groups  = new HashSet<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    // в цикле заполняется список полученными именами групп
    for(WebElement element: elements){
      String name = element.getText();
      int groupID = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      groups.add(new GroupDate().withtGroupID(groupID).withGroupName(name));
    }
    return groups;
  }
}
