package ru.stqa.pft.addressbook.Generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactShortData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  // объявление переменных дял парметров и задание их опсиания
  @Parameter(names = "-c", description = "Contact count")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;
  @Parameter(names = "-d", description = "Data format")
  public String format;


  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    // если параметр задан не верно, то выводит текст в консоль с описанием параметров
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }
// запуск генератора из двух частей: созданик данных, сохранение в файл.
  private void run() throws IOException {
    List<ContactShortData> contacts = generateContacts(count);
    if (format.equals("csv")){
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml (contacts, new File(file));
    } else if (format.equals("json")){
      saveAsJson (contacts, new File(file));
    } else {
      System.out.println("Urecognise format " + format);
    }
  }
// сохранение в формате json
  private void saveAsJson(List<ContactShortData> contacts, File file) throws IOException {
   //Gson gson = new Gson();
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    // проверка для автоматического закрытия файла
    try(Writer writer = new FileWriter(file);){
      writer.write(json);
    }
    // прямая команда закрытяи заменена на проверку
    // writer.close();
  }

  // сохранение в файл xml
  private void saveAsXml(List<ContactShortData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    //xstream.alias("contact", ContactShortData.class);
    xstream.processAnnotations(ContactShortData.class);
    String xml = xstream.toXML(contacts);
  try (  Writer writer = new FileWriter(file);) {
    writer.write(xml);
  }
    //writer.close();
  }

// сохранение в файл CSV
  private void saveAsCsv(List<ContactShortData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactShortData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;\n", contact.getFname(), contact.getLname(), contact.getPhoneNumHome(), contact.getEmail(), contact.getAddress()));
    }
  }
// генерация данных
  private List<ContactShortData> generateContacts(int count) {
    List<ContactShortData> contacts = new ArrayList<ContactShortData>();
    int r = (int) (Math.random() * 1000000);
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactShortData().withFname(String.format("FName %s", r + i)).withLname(String.format("LName %s", r + i)).withPhoneNumHome(String.format("%s", r * i)).withAddress(String.format("Ru, Msc, Main str, %s", i)).withEmail(String.format("%s-@test.t", r - i)));
    }
    return contacts;
  }

}
