package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

  @Test (enabled = false)
  public void testContactEmail(){
    app.goTo().homePage();
    ContactShortData contact = app.contact().all().iterator().next();
    ContactShortData contactInfoFormEditFrom = app.contact().infoFormEditForm(contact);

    // сравнение email методом обратных проверок, вызов функции чистки нуже для старого способа склейки строк
    assertThat(cleaned(contact.getAllEmails()), equalTo(cleaned(mergeEmails(contactInfoFormEditFrom))));

   /*
    // для отладки
    System.out.println("Test get email in form");
    System.out.println(contact.getEmail());
    System.out.println(contact.getEmail2());
    System.out.println(contact.getEmail3());
    System.out.println("Test email from table");
    System.out.println(cleaned(contact.getAllEmails()));*/


  }

  private String mergeEmails(ContactShortData contact) {

    /* // простой старый способ
    String result = "";
  if(contact.getEmail() != null) {
    result = result + contact.getEmail();
  }
    if(contact.getEmail2() != null) {
      result = result + contact.getEmail2();
    }
    if(contact.getEmail3() != null) {
      result = result + contact.getEmail3();
    }
  return result;*/

    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> !s.equals("")).map(ContactEmailTests::cleaned)
            .collect(Collectors.joining("\n"));
  }


  public static String cleaned(String email){

    return email.replaceAll("\\s","").replaceAll("[-()]", "").replaceAll("\n", "");
  }
}
