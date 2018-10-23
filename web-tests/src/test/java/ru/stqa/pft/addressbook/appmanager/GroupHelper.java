package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupDate;

public class GroupHelper {
FirefoxDriver wd;

public GroupHelper(FirefoxDriver wd) {
    this.wd=wd;
  }

  // подтверждение оперции в форме группы
  public void submitGroupCreation() {
    wd.findElement(By.name("submit")).click();
  }

  // заполнение полей формы группы
  public void fillGroupForm(GroupDate groupDate) {
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys(groupDate.getGroupName());
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).clear();
    wd.findElement(By.name("group_header")).sendKeys(groupDate.getGroupHeader());
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).clear();
    wd.findElement(By.name("group_footer")).sendKeys(groupDate.getGroupCommmet());
  }

  // создание новой группы вызов формы
  public void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }

  // удаление выбранной группы
  public void deleteSelectedGroups() {
    wd.findElement(By.name("delete")).click();
  }

  // выбор группы для удаления
  public void selectGroup() {
    wd.findElement(By.name("selected[]")).click();
  }
}
