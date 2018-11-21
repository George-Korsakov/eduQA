package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactShortData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// статический импорт методов для проверок (улучшеине читаемости кода)
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContactsFromXml() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
   try ( BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")));) {
     String line = reader.readLine();
     // чтение тестовых данных из xml файла
     String xml = "";
     while (line != null) {
       xml += line;
       line = reader.readLine();
     }
     XStream xstream = new XStream();
     xstream.processAnnotations(ContactShortData.class);
     List<ContactShortData> contacts = (List<ContactShortData>) xstream.fromXML(xml);
     return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
   }
    /*// чтение файла csv
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null){
      String[] testdata = line.split(";");
      list.add(new Object[]{new ContactShortData().withFname(testdata[0]).withLname(testdata[1]).withPhoneNumHome(testdata[2])});
      line = reader.readLine();
    }
      return list.iterator();*/
  }

  @DataProvider
  public Iterator<Object[]> validContactsFromJson() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
   try ( BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));) {
     String line = reader.readLine();
     String json = "";
     while (line != null) {
       json += line;
       line = reader.readLine();
     }
     Gson gson = new Gson();
     List<ContactShortData> contacts = gson.fromJson(json, new TypeToken<List<ContactShortData>>() {
     }.getType());
     ;
     return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
   }
  }



    @Test(dataProvider = "validContactsFromJson")
  public void testContactCreation(ContactShortData contact) {
    int r = (int)(Math.random()*1000000);
    // не обязательное действие по прееходу на страницу контактов для подстраховки
    app.goTo().homePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/photo01.jpg");
    //ContactShortData contact = new ContactShortData().withFname("NameTest1" +r).withLname("LastNameTest1").withPhoneNumHome(String.valueOf(r*1)).withPhoneNumMobile(String.valueOf(r*2)).withPhoneNumWork(String.valueOf(r*3)).withPhoto(photo);

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
