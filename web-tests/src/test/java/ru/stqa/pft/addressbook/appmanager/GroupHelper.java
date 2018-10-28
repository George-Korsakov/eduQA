package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import ru.stqa.pft.addressbook.model.GroupDate;

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

  // выбор группы для удаления
  public void selectGroup() {
    click(By.name("selected[]"));
  }

  public void returnToGroupPage() {
    wd.findElement(By.linkText("groups")).click();
    ;
  }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void createGroup(GroupDate group) {
  initGroupCreation();
  fillGroupForm(new GroupDate("TestGroup1", null, null));
  submitGroupCreation();
  returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresents(By.name("selected[]"));
  }
}
