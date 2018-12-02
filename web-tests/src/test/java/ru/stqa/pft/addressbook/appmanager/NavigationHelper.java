package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  // переход к списку групп с проверкой
  public void groupPage() {

    if (isElementPresents(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresents(By.name("New"))) {
      return;
    } else {
      click(By.linkText("groups"));
    }

  }

  // есть основание подозревать дублирование кода с returnGroupPage, но будет оставлено воимя аутентичности :-))
  public void gotoExit() {
    click(By.linkText("Logout"));
  }
// проверка для страницы с контакатами
  public void homePage() {
    if (isElementPresents(By.id("maintable"))) {
      return;
    } else {
      click(By.linkText("home"));
    }
  }

  public void contactsListInGroup(int id){
    wd.findElement(By.cssSelector("a[href='./?group=" + id + "']")).click();
  }

}
