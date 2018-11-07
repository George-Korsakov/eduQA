package beginer.simple;

import org.openqa.selenium.By;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class TestTable {
  public static void main(String[] args) {


    WebDriver wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
    wd = new FirefoxDriver();
    // проимер выборки значений их простейшей таблицы
   /* String baseUrl = "file://C:/Temp/testTable.html";
    wd.get(baseUrl);
    String cellText = wd.findElement(By.xpath(" //table/tbody/tr[2]/td[2]")).getText();
            System.out.println("Text in Cell is: " +cellText);
            wd.quit();*/


    // пример2 работа с динамической таблицей
    String baseUrl = "file://C:/Temp/testTable2.html";
    wd.get(baseUrl);
    //найти таблицу
    WebElement mytable = wd.findElement(By.xpath("html/body/table/tbody"));

    // получить строки таблицы
    List < WebElement > rows_table = mytable.findElements(By.tagName("tr"));

    //получить число строк  в таблице
    int rows_count = rows_table.size();


    //цикл по всем строкам таблицы
    for (int row = 0; row < rows_count; row++) {

      //получение столбцов из строк (cells)
      List< WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));

      //получить число  ячеек в строке
      int columns_count = Columns_row.size();
      System.out.println("Number of cells In Row " + row + " are " + columns_count);

      //цикл выборки значений по ячейкам строки
      for (int column = 0; column < columns_count; column++) {
        //получить содержимое ячейки
        String celltext = Columns_row.get(column).getText();
        System.out.println("Cell Value Of row number " + row + " and column number " + column + " Is " + celltext);
      }
    }



  }

}
