package ru.stqa.pft.addressbook.Generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.GroupDate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupDataGenerator {

  @Parameter(names = "-c", description = "Group count'")
  public int count;
  @Parameter(names = "-f", description = "Target file")
  public String file;
  @Parameter(names = "-d", description = "Data format")
  public String format;

  // функция генерации тестовых данных принмиате арагументы: целое число - кол-во групп, относительный путь к файлу в который будут записаны данные
  public static void main(String[] args) throws IOException {
    GroupDataGenerator generator = new GroupDataGenerator();
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
    List<GroupDate> groups = generateGroups(count);
    if (format.equals("csv")) {
      saveAsCsv(groups, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml (groups, new File(file));
    } else if (format.equals("json")) {
      saveAsJson (groups, new File(file));
    } else {
      System.out.println("Urecognise format " + format);
    }
  }
  // запись данных в файл в формате json
  private void saveAsJson(List<GroupDate> groups, File file) throws IOException {
    //Gson gson = new Gson();
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(groups);
   try(Writer writer = new FileWriter(file);) {
     writer.write(json);
   }
    //writer.close();
  }

  // создание документа в формает xml при помощи XStream
  private void saveAsXml(List<GroupDate> groups, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.alias("group", GroupDate.class);
    //xstream.processAnnotations(GroupDate.class);
    String xml = xstream.toXML(groups);
    try (Writer writer = new FileWriter(file);) {
    writer.write(xml); }
    //writer.close();
  }

  // запись данных в файл в формате csv
  private void saveAsCsv(List<GroupDate> groups, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (GroupDate group : groups) {
      writer.write(String.format("%s;%s;%s;\n", group.getGroupName(), group.getGroupHeader(), group.getGroupCommmet()));
    }
    // закрывает файл, важно !
    writer.close();
  }

  // генерация данных для гурпп
  private List<GroupDate> generateGroups(int count) {
    List<GroupDate> groups = new ArrayList<GroupDate>();
    int r = (int) (Math.random() * 1000000);
    for (int i = 0; i < count; i++) {
      groups.add(new GroupDate().withGroupName(String.format("TestName %s", i + r)).withGroupHeader(String.format("Test %s", i + r)).withGroupCommmet(String.format("TestComment %s", i + r)));
    }
    return groups;
  }

}
