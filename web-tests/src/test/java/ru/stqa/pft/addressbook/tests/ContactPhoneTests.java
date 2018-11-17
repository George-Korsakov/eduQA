package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @Test
  public void testContactPhone() {
    app.goTo().homePage();
    ContactShortData contact = app.contact().all().iterator().next();
    ContactShortData contactInfoFormEditFrom = app.contact().infoFormEditForm(contact);

    assertThat(contact.getPhoneNumHome(), equalTo(cleaned(contactInfoFormEditFrom.getPhoneNumHome())));
    assertThat(contact.getPhoneNumMobile(), equalTo(cleaned(contactInfoFormEditFrom.getPhoneNumMobile())));
    assertThat(contact.getPhoneNumWork(), equalTo(cleaned(contactInfoFormEditFrom.getPhoneNumWork())));
  }

  public String cleaned(String phone){

    return phone.replaceAll("\\s","").replaceAll("[-()]", "");
  }


}
