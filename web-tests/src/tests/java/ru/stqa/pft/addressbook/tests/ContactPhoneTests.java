package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @Test
  public void testContactPhone() {
    app.goTo().homePage();
    ContactShortData contact = app.contact().all().iterator().next();
    ContactShortData contactInfoFormEditFrom = app.contact().infoFormEditForm(contact);
    // сравнение номеров телефонов методом обратных проверок, вызов функции чистки нуже для старого способа склейки строк
    assertThat(cleaned(contact.getAllPhones()), equalTo(cleaned(mergePhones(contactInfoFormEditFrom))));
    //assertThat(contact.getPhoneNumHome(), equalTo(cleaned(contactInfoFormEditFrom.getPhoneNumHome())));
    //assertThat(contact.getPhoneNumMobile(), equalTo(cleaned(contactInfoFormEditFrom.getPhoneNumMobile())));
    //assertThat(contact.getPhoneNumWork(), equalTo(cleaned(contactInfoFormEditFrom.getPhoneNumWork())));
  }

  private String mergePhones(ContactShortData contact) {

   /* // простой старый способ
    String result = "";
  if(contact.getPhoneNumHome() != null) {
    result = result + contact.getPhoneNumHome();
  }
    if(contact.getPhoneNumMobile() != null) {
      result = result + contact.getPhoneNumMobile();
    }
    if(contact.getPhoneNumWork() != null) {
      result = result + contact.getPhoneNumWork();
    }
  return result;*/

    return Arrays.asList(contact.getPhoneNumHome(), contact.getPhoneNumMobile(), contact.getPhoneNumWork())
            .stream().filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleaned)
            .collect(Collectors.joining("\n"));

  }


  public static String cleaned(String phone){

    return phone.replaceAll("\\s","").replaceAll("[-()]", "").replaceAll("\n", "");
  }


}