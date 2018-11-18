package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// статический импорт методов для проверок (улучшеине читаемости кода)
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {



  @Test
  public void testContactCreation() {
    int r = (int)(Math.random()*1000000);
    // не обязательное действие по прееходу на страницу контактов для подстраховки
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/photo01.jpg");
    ContactShortData contact = new ContactShortData().withFname("NameTest1" +r).withLname("LastNameTest1")
            .withPhoneNumHome(String.valueOf(r*1)).withPhoneNumMobile(String.valueOf(r*2)).withPhoneNumWork(String.valueOf(r*3)).withPhoto(photo);

    app.contact().create(contact);

    Contacts after = app.contact().all();

    // проверка сравнением размеров спсисков
    Assert.assertEquals(before.size(), after.size()-1);

    // проверка сравнением множеств
    // анониманая функция принимающая в качесвте атрибута объект типа ContactShortData выделяет id целые числа и находит максимальное
   /* contact.withContactID(after.stream().mapToInt( (c) -> c.getContactID()).max().getAsInt() );
    before.add(contact);
    Assert.assertEquals(before, after);*/


    // проверки в fluent-стиле, используя Hamcrest
    assertThat(after, equalTo(before.withAdded(
            contact.withContactID(after.stream().mapToInt( (g) -> g.getContactID()).max().getAsInt() ))));

  }

  @Test(enabled = false)
  public void testCurrentDir (){
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/photo01.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println("Filsr exist = " + photo.exists());
    System.out.println("It is file = " +  photo.isFile());
    System.out.println("Size = " + photo.getTotalSpace());
  }

}
