package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;
import ru.stqa.pft.addressbook.model.GroupDate;

public class ContactRemoveFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().homePage();
    int r = (int) (Math.random() * 1000000);
    // проверка на выполнение предуслоовия, создание контакта
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactShortData().withFname("NewTest_" +r).withLname("LastNameTest_" +r));
    }
    // проверка на выполениения предусловия, создания группы
    if(app.db().groups().size() == 0){
      app.goTo().groupPage();
      app.group().create(new GroupDate().withGroupName("TestGroup_" +r).withGroupCommmet("TestComment_"+r).withGroupHeader("Test_" +r) );
    }
    // проверка на выполения предусловий, наличие связанной группы с контакта
    if(app.db().isAnyGroupIncludeContact() == 0){
      app.contact().addConatctToGroup(app.db().contacts().iterator().next().getContactID(), app.db().groups().iterator().next().getGroupID());
    }
  }

  @Test
  public void testContactRemoveFromGroup(){
    app.goTo().homePage();
    int groupId = app.db().isAnyGroupIncludeContact();
    int contactId = app.db().groupIncludeContact(groupId);
    // удаление контакта из группы
    app.goTo().homePageChosenGroup(groupId);
    app.contact().selectContactById(contactId);
    app.contact().submitRemoveContactFromGroup();

    // проверка наличия связи по БД
    Assert.assertFalse(app.db().isContactHasLinkGroup(contactId, groupId));

  }

}
