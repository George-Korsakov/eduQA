package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
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
    type(By.name("lastname"), contactShortData.getLname());
    type(By.name("firstname"), contactShortData.getFname());

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
  public void create(ContactShortData contact) {

    initContactCreation();
    fillShortContactForm(contact);
    submitContactCreation();
  }

  // редактирование контакта
  public void modify(int index, ContactShortData contact) {
    selectContact(index);
    initContactModification();
    fillShortContactForm(contact);
    submitContactModification();
    retutnHomePage();
  }

  public void delete(int index) {
    selectContact(index);
    deleteSelectedContact();
    submitContactDelete();
  }

  public boolean isThereAContact() {
    return isElementPresents(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactShortData> list() {
    List<ContactShortData> contacts = new ArrayList<ContactShortData>();
    {
      // поиск таблицы для последующего получения значений ячеек
      WebElement mytable = wd.findElement(By.xpath("//*[@id=\"maintable\"]"));
      // получаем строки
      List<WebElement> rows_table = mytable.findElements(By.name("entry"));
      int maxRow = rows_table.size();
      for (int row = 0; row < maxRow; row++) {
        //получение столбцов из строк (cells)
        List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
        // получение значений из нужных ячеек по индексу
        // полчеение имени и фамили
        String name2 = Columns_row.get(1).getText();
        String name1 = Columns_row.get(2).getText();
        // получение занчени ID и преобразования тип в целое число
        int ID = Integer.parseInt(Columns_row.get(0).findElement(By.tagName("input")).getAttribute("value"));
       // добавлем объект контакт в список
        contacts.add(new ContactShortData().withContactID(ID).withFname(name1).withLname(name2));
      }

      return contacts;
    }

  }
}
