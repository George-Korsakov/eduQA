package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(FirefoxDriver wd) {
    super(wd);
  }

  // переход к списку групп
  public void gotoGroupPage() {
    click(By.linkText("groups"));
  }

  // есть основание подозревать дублирование кода с returnGroupPage, но будет оставлено воимя аутентичности :-))
  public void gotoExit() {
    click(By.linkText("Logout"));
  }

}
