package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.ContactShortData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

import static ru.stqa.pft.addressbook.tests.TestBase.app;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  // возврат на основную страницу
  public void goToHomePage() {
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
    // todo добавить сюда остальные поля позже
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

  //  заполнение не полной формы контакта

  public void fillShortContactForm(ContactShortData contactShortData) {
    type(By.name("lastname"), contactShortData.getLname());
    type(By.name("firstname"), contactShortData.getFname());

    type(By.name("home"), contactShortData.getPhoneNumHome());
    type(By.name("mobile"), contactShortData.getPhoneNumMobile());
    type(By.name("work"), contactShortData.getPhoneNumWork());
    type(By.name("phone2"), contactShortData.getPhoneNumHome());
    type(By.name("address"), contactShortData.getAddress());
    type(By.name("address2"), contactShortData.getAddress2());
    type(By.name("email"), contactShortData.getEmail());
    type(By.name("email2"), contactShortData.getEmail2());
    type(By.name("email3"), contactShortData.getEmail3());

    //attach(By.name("photo"), contactShortData.getPhoto());
    //type(By.name("photo"), contactShortData.getPhoto());
  }

  // добавление нового контакта вызов формы
  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }
  public void selectContactAny() {
    wd.findElements(By.name("selected[]")).iterator().next().click();
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value = '" + id + "']")).click();
  }


  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  // кнопка добавления контакта в выбранную группу
  public void confirmAddToGroup() {
    click(By.name("add"));
    //click(By.xpath("//input[@value='Add to']"));
  }
  // выбор группы для добавления конетакта по id
  public void selectGroupToAddContact(String groupName){
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupName);
  }
  public void selectGroupToAddContactById(int id_group)
  {
    new Select(wd.findElement(By.name("to_group"))).selectByValue(Integer.toString(id_group));
   // new Select(wd.findElement(By.xpath("//a[contains( './?group= '" + id_group + "')]")));
   //click((By.name("to_group"))).selectByVisibleText("TestEGroup1"));
  }



  public void submitContactDelete() {
    confirmAlert();
  }

  /*
  // отставлен для примера использования xpath
  public void initContactModification() {
    click(By.xpath("//img[@alt='Edit']"));
  }*/
  public void gotoGroupListContacts(String name){
    wd.findElement(By.linkText("group page \"" + name +"\"")).click();
  }

  public void gotoGroupListContacts2(int id){
    wd.findElement(By.cssSelector("a[href='./?group=" + id + "']")).click();
  }

  public void initContactModificationById(int id) {
    // поиск по индентификатору кнопки редактирования
    wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();

    // альтренативные способы поиска кнопки редактирования, демонстрация String.format для склеивания строк
    // поиск чекбоса с последующим переходм к родительскому эелменту, что бы получить строку в который выбирается нужная ячейка
    //wd.findElement(By.xpath(String.format("//input[@value='$s']/../../td[8]/a",id))).click();
    // с использованием подзапроса для поиска строки в которой выбирается нужная ячейка
    //wd.findElement(By.xpath(String.format("//tr[.//input[@value='$s']]//td[8]/a", id)))

   /* // длинный способ поиска кнопки методом последовательного приближения
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='$s']",id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells =row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();*/

  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  // вспомогательный мететод, для создание предусловия другим тестам
  public void create(ContactShortData contact) {
    initContactCreation();
    fillShortContactForm(contact);
    submitContactCreation();
    contactCahe = null;
    goToHomePage(); // возвращение на страницу контактов для подстраховки
  }


  public void modify(ContactShortData contact) {
    initContactModificationById(contact.getContactID());
    fillShortContactForm(contact);
    submitContactModification();
    contactCahe = null;
    goToHomePage(); // возвращение на страницу контактов для подстраховки
  }


  public void delete(ContactShortData contact) {
    selectContactById(contact.getContactID());
    deleteSelectedContact();
    submitContactDelete();
    contactCahe = null;
  }

  public boolean isThereAContact() {
    return isElementPresents(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  // метод получения спсика контаков
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

  private Contacts contactCahe = null;

  // метод получения множества контактов из таблицы списка контактов
  public Contacts all() {
    Contacts contacts = new Contacts();
    {
      if (contactCahe != null) {
        return new Contacts(contactCahe);
      } else {

        contactCahe = new Contacts();
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
          // получение номеров телефонов в виде массива
          //String[] phones = Columns_row.get(5).getText().split("\n");
          // для метода обратных проверок получаем все телефоны в строку
          String address = Columns_row.get(3).getText();
          String allemails =  Columns_row.get(4).getText();
          String allphones = Columns_row.get(5).getText();
          // получение занчени ID и преобразования тип в целое число
          int ID = Integer.parseInt(Columns_row.get(0).findElement(By.tagName("input")).getAttribute("value"));
          // добавлем объект контакт в список
          contactCahe.add(new ContactShortData().withContactID(ID).withFname(name1).withLname(name2).withAllPhones(allphones).withAddress(address).withAllEmails(allemails));

                  //  .withPhoneNumHome(phones[0]).withPhoneNumMobile(phones[1]).withPhoneNumWork(phones[2]));
        }

        return new Contacts(contactCahe);
      }
    }
  }

  // метод получения значений из формы редактирования контакта
  public ContactShortData infoFormEditForm(ContactShortData contact) {
    initContactModificationById(contact.getContactID());
    String fname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    //String fax = wd.findElement(By.name("fax")).getAttribute("value");
    //String home2 = wd.findElement(By.name("phone2")).getAttribute("value");

    String address = wd.findElement(By.name("address")).getAttribute("value");
    String address2 = wd.findElement(By.name("address2")).getAttribute("value");

    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");

    wd.navigate().back();
    return new ContactShortData().withContactID(contact.getContactID()).withFname(fname).withLname(lname)
            .withPhoneNumHome(home).withPhoneNumMobile(mobile).withPhoneNumWork(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);

  }

  public void addConatctToGroup(int contactId, int addGroupId) {
    app.goTo().homePage();
    app.contact().selectContactById(contactId);
    app.contact().selectGroupToAddContactById(addGroupId);
    app.contact().confirmAddToGroup();
  }


  public void submitRemoveContactFromGroup() {
    click(By.name("remove"));
  }
}
