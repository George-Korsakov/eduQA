package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    // вызов драйвера, переход на страницу и авторизация
    wd = new FirefoxDriver();
//  wd = new ChromeDriver();
//  wd = new InternetExplorerDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    //  переход на страницу и авторизация
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }



  private void login(String username, String password) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }
  // возврат на страницу списка групп
  protected void returnToGroupPage() {
    wd.findElement(By.linkText("Logout")).click();;
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

  // подтверждение оперции в форме группы
  protected void submitGroupCreation() {
    wd.findElement(By.name("submit")).click();
  }
  // заполнение полей формы группы
  protected void fillGroupForm(GroupDate groupDate) {
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys(groupDate.getGroupName());
    wd.findElement(By.name("group_header")).sendKeys(groupDate.getGroupHeader());
    wd.findElement(By.name("group_footer")).sendKeys(groupDate.getGroupCommmet());
  }
  // создание новой группы вызов формы
  protected void initGroupCreation() {
    wd.findElement(By.name("new")).click();
  }
  // переход к списку групп
  protected void gotoGroupPage() {
    wd.findElement(By.linkText("groups")).click();
  }

  // удаление выбранной группы
  protected void deleteSelectedGroups() {
    wd.findElement(By.name("delete")).click();
  }
  // выбор группы для удаления
  protected void selectGroup() {
    wd.findElement(By.name("selected[]")).click();
  }
  // возврат на основную страницу
  protected void retutnHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }
  // подтверждение действия в форме контакта
  protected void submitContactCreation() {
    wd.findElement(By.name("submit")).click();
  }
  // заполнение полей формы контакта
  protected void fillContactForm(ContactData contactData) {
    wd.findElement(By.name("firstname")).sendKeys(contactData.getFname());
    wd.findElement(By.name("middlename")).sendKeys(contactData.getMname());
    wd.findElement(By.name("lastname")).sendKeys(contactData.getLname());
    wd.findElement(By.name("nickname")).sendKeys(contactData.getNname());
    wd.findElement(By.name("photo")).sendKeys(contactData.getPhotoPathToFile());
    wd.findElement(By.name("title")).sendKeys(contactData.getTitle());
    wd.findElement(By.name("company")).sendKeys(contactData.getCompany());
    wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
    wd.findElement(By.name("home")).sendKeys(contactData.getPhoneNumHome());
    wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getbDay());
    wd.findElement(By.name("bmonth")).click();
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getbMonth());
    wd.findElement(By.name("byear")).sendKeys(contactData.getbYear());
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getChosenGroup());
    // альтернативный сопособ выбора группы
    //wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Group:'])[1]/following::option[6]")).click();
  }
  // добавление нового контакта вызов формы
  protected void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }
}

