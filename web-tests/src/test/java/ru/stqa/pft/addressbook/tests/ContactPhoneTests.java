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

    assertThat(contact.getPhoneNumHome(), equalTo(contactInfoFormEditFrom.getPhoneNumHome()));
    assertThat(contact.getPhoneNumMobile(), equalTo(contactInfoFormEditFrom.getPhoneNumMobile()));
    assertThat(contact.getPhoneNumWork(), equalTo(contactInfoFormEditFrom.getPhoneNumWork()));
  }

}
