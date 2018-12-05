package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;

public class SimpleTestFirst extends TestBase{

  @Test
   public void testSimpleFirst() throws IOException {
     app.init();
    // простейшая проверка
    app.isElementPresent(new By.ByName("username"));
     app.stop();

  }
}
