package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;

import org.openqa.selenium.WebDriver;


public class HelperBase {
  WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd = wd;
  }
// нажатие на элемент найденный по локалтору
  protected void click(By locator) {
    wd.findElement(locator).click();
  }
// нажатие, очитска поля и ввод текста поиск по локатору
  protected void type(By locator, String text) {
    click(locator);
    if(text != null) {
      String existngText = wd.findElement(locator).getAttribute("value");
      if (! text.equals(existngText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }
  protected void confirmAlert(){
    wd.switchTo().alert().accept();
  }

// проверка исключений
  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }


}
