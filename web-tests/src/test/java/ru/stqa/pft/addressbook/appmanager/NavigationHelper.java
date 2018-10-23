package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {
  private FirefoxDriver wd;

  public NavigationHelper(FirefoxDriver wd) {
    this.wd=wd;
  }

  // переход к списку групп
  public void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }
}
