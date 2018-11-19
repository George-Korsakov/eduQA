package ru.stqa.pft.addressbook.Generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactShortData;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names  = "-c", description = "Contact count" )
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;


  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactShortData> contacts = generateContacts(count);
    save(contacts, new File(file));
  }

  private  void save(List<ContactShortData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactShortData contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;\n", contact.getFname(),contact.getLname(),contact.getPhoneNumHome(),contact.getEmail(), contact.getAddress() ));
    }
  }

  private  List<ContactShortData> generateContacts(int count) {
  List<ContactShortData> contacts = new ArrayList<ContactShortData>();
    int r = (int)(Math.random()*1000000);
    for (int i = 0; i < count; i++){
      contacts.add(new ContactShortData().withFname(String.format("FName %s", r+i)).withLname(String.format("LName %s", r+i))
              .withPhoneNumHome(String.format("%s",r*i)).withEmail(String.format("%s-@test.t",r-i))
              .withAddress(String.format("Ru, Msc, Main str, %s", i)));
    }
    return contacts;
  }


}
