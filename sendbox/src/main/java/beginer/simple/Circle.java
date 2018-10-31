package beginer.simple;

import javafx.beans.property.SimpleObjectProperty;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Circle {

  public static void main(String[] args) {
    // пример  работы с массивами
    String massiv[] = new String[5];
    massiv[0] = "Java";
    massiv[1] = "PLSQL";
    massiv[2] = "JavaScript";
    massiv[3] = "Bash";
    massiv[4] = "Pyton";

    for (String l : massiv) {
      System.out.println(" I can learn " + l);
    }


    // пример  работы с списком
    List<String> testList = new ArrayList<>();
    testList.add("test1");
    testList.add("test2");
    testList.add("test3");

    for (Integer l = 0; l < testList.size(); l++) {
      System.out.println(testList.get(l));
    }


    List<String> testBList = Arrays.asList("testA", "TestB", "TeestC");

    for (Integer l = 0; l < testBList.size(); l++) {
      System.out.println(testBList.get(l));
    }


  }
}