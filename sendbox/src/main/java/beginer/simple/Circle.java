package beginer.simple;

import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Circle{

  public static void main(String[] args) {
    // пример  работы с массивами
    String massiv[] = new String[5];
    massiv[0] = "Java";
    massiv[1] = "PLSQL";
    massiv[2] = "JavaScript";
    massiv[3] = "Bash";
    massiv[4] = "Pyton";

    for (int i = 0; i < massiv.length; i++) {
      System.out.println(" I can learn " + massiv[i]);
    }



  // пример  работы с списком
    ArrayList<String> testList = new ArrayList<String>();
    testList.add("test1");
    testList.add("test2");
    testList.add("test3");

    for(Integer l = 0; l < testList.size(); l++){
    System.out.println(testList.get(l));
    }
  }
}