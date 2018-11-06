package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  // возврат на основную страницу
  public void retutnHomePage() {
    click(By.linkText("home page"));
  }

  // подтверждение действия в форме контакта
  public void submitContactCreation() {
    click(By.name("submit"));
  }

  // заполнение полей полной формы контакта
  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFname());
    type(By.name("middlename"), contactData.getMname());
    type(By.name("lastname"), contactData.getLname());
    type(By.name("nickname"), contactData.getNname());
    // выбор файла для контактов
    wd.findElement(By.name("photo")).sendKeys(contactData.getPhotoPathToFile());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getPhoneNumHome());
    type(By.name("email"), contactData.getEmail());

    // задание даты рожения (позже вынести в отдельный класс и объект)
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getbDay());
    wd.findElement(By.name("bmonth")).click();
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getbMonth());
    wd.findElement(By.name("byear")).click();
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys(contactData.getbYear());
    // проверка типа операции над контактом для выбора группы
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getChosenGroup());
    } else {
      Assert.assertFalse(isElementPresents(By.name("new_group")));
    }


  }

  //  заполнение сокращенной формы контакта

  public void fillShortContactForm(ContactShortData contactShortData) {
    type(By.name("firstname"), contactShortData.getFname());
    type(By.name("lastname"), contactShortData.getLname());

  }

  // добавление нового контакта вызов формы
  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void submitContactDelete() {
    confirmAlert();

  }

  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  // вспомогательный мететод, для создание предусловия другим тестам
  public void createShortContact(ContactShortData contact) {
    initContactCreation();
    fillShortContactForm(contact);
    submitContactCreation();
  }

  public boolean isThereAContact() {
    return isElementPresents(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactShortData> getContactList() {
    List<ContactShortData> contacts = new ArrayList<ContactShortData>();
    {
      //int maxRow = getContactCount();
      WebElement table_element = wd.findElement(By.id("maintable"));
      // получаем список строк таблицы

      List<WebElement> allRows = table_element.findElements(By.xpath(".//tbody/tr"));
      //String aa = table_element.findElement(By.xpath("//tr[4]/td[3]"))
      for(WebElement row : allRows){
        String name1 = row.findElement(By.xpath("//td[2]")).getText();
        String name2 = row.findElement(By.xpath("//td[3]")).getText();
        ContactShortData contact = new ContactShortData(name1, name2);
        // добавлем объект контакт в список
        contacts.add(contact);
      }

      // проходим все строки
      //for (WebElement row : allRows) {
        // получаем ячейки из строк
        //List<WebElement> cells = row.findElements(By.tagName("td"));
        // полчаем значение из нужных строк
        //String valueA = row.findElements(By.cssSelector("tr")).get(1).toString();
        //String valueB = row.findElements(By.cssSelector("tr")).get(2).toString();
        //String cellB = cells.get(2).toString();
        // осздаем новый объект контакт
        //ContactShortData contact = new ContactShortData(valueA, valueB);
        // добавлем объект контакт в список
        //contacts.add(contact);
      //}
      return contacts;

      //for (WebElement row : allRows) {
      // if( !row.equals(row.findElement(By.className("fdTableSortTrigger")))){
      //List<WebElement> cells = row.findElements(By.tagName("td"));


    }
  }
}