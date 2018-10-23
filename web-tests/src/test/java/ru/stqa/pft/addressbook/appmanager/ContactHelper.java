package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {


  public ContactHelper(FirefoxDriver wd) {
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

  // заполнение полей формы контакта
  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.getFname());
    type(By.name("middlename"),contactData.getMname());
    type(By.name("lastname"),contactData.getLname());
    type(By.name("nickname"),contactData.getNname());
   //  удалить или заменить type(By.name("photo"),contactData.getPhotoPathToFile());
    // выбор файла для контактов
    wd.findElement(By.name("photo")).sendKeys(contactData.getPhotoPathToFile());
    type(By.name("title"),contactData.getTitle());
    type(By.name("company"),contactData.getCompany());
    type(By.name("address"),contactData.getAddress());
    type(By.name("home"),contactData.getPhoneNumHome());
    type(By.name("email"),contactData.getEmail());

    // задание даты рожения (позже вынести в отдельный класс и объект)
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getbDay());
    wd.findElement(By.name("bmonth")).click();
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getbMonth());
    wd.findElement(By.name("byear")).click();
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys(contactData.getbYear());
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getChosenGroup());
    // альтернативный сопособ выбора группы
    //wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Group:'])[1]/following::option[6]")).click();
  }

  // добавление нового контакта вызов формы
  public void initContactCreation() {
    click(By.linkText("add new"));
  }

}
