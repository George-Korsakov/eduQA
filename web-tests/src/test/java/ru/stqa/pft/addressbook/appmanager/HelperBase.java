package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;

import org.openqa.selenium.NoSuchElementException;
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
    // проверка на наличие значения в поле ввода и сравнение его на совпадение
    if (text != null) {
      String existngText = wd.findElement(locator).getAttribute("value");
      if (!text.equals(existngText)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    }
  }

  protected void confirmAlert() {
    wd.switchTo().alert().accept();
    // обход проблемы для Fierfox
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  // проверка всплывающего сообщения на странице
  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

// проверка наличие элемента на странице
  protected boolean isElementPresents(By locator) {
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
