package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;
import ru.stqa.pft.addressbook.model.GroupDate;

public class ContactAddToGroupTests extends TestBase {

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
  }

  @Test
  public void testContactAddToGroup() {
    //Set<Integer> groups = new ArrayList<>(app.db().groups().stream().mapToInt((g) -> g.getGroupID()));
    //выборка контакта и группы
    ContactShortData contact = app.db().contacts().iterator().next();
    // поилучение id не привязанной к конаткату группы
    int addGroupId = app.db().isContactHasNotLinkGroup(contact.getContactID());

    // добавление контакта в группу
    app.contact().addConatctToGroup(contact.getContactID(), addGroupId);

    // необязательный переход на страницу группы для визульаного контроля
    app.goTo().contactsListInGroup(addGroupId);
    //app.contact().gotoGroupListContacts(additionGroupName);

    // проверка наличия связи по БД
    Assert.assertTrue(app.db().isContactHasLinkGroup(contact.getContactID(),addGroupId));

  }


}


