package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupCreationTests {
  private WebDriver wd;


  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    //  переход на страницу и авторизация
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    initGroupCreation("user");
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    initGroupCreation("pass");
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testGroupCreation() throws Exception {

    gotoGroupPage("groups");
    initGroupCreation("new");
    fillGroupForm(new GroupDate("TestGroup", "TestHeaderFroup", "TestComment"));
    submitGroupCreation();
    returnToGroupPage();
  }

  private void returnToGroupPage() {
    gotoGroupPage("Logout");
  }

  private void submitGroupCreation() {
    wd.findElement(By.name("submit")).click();
  }

  private void fillGroupForm(GroupDate groupDate) {
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys(groupDate.getGroupName());
    wd.findElement(By.name("group_header")).sendKeys(groupDate.getGroupHeader());
    wd.findElement(By.name("group_footer")).sendKeys(groupDate.getGroupCommmet());
  }

  private void initGroupCreation(String s) {
    wd.findElement(By.name(s)).click();
  }

  private void gotoGroupPage(String groups) {
    wd.findElement(By.linkText(groups)).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();

  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }


}
