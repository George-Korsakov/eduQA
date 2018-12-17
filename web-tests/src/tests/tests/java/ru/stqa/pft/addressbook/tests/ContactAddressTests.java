package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

  @Test (enabled = false)
  public void testContactAddress(){
    app.goTo().homePage();
    ContactShortData contact = app.contact().all().iterator().next();
    ContactShortData contactInfoFormEditFrom = app.contact().infoFormEditForm(contact);
    // сравнение адреса путем методом обратных проверок
    assertThat(cleaned(contact.getAddress()), equalTo((mergeAddress(contactInfoFormEditFrom))));

  }


  private String mergeAddress(ContactShortData contact) {

    return Arrays.asList(contact.getAddress()).stream().filter((s) -> !s.equals("")).map(ContactAddressTests::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String address){

    return address.replaceAll("\\s","").replaceAll("\n", "");
  }

}
