package beginer.simple;

import java.time.LocalDateTime;

public class FirstProg1 {

  public static void main(String[] args) {
// простая программа для проверки сборщика и системы контроля версий
    int a = 25;
    double b = 33.0;
    hello("world");
    System.out.println("Плошадь прямоугольнка со сторанми " + a + " и " + b + " равна " + area(b, a));
    // пример вызова функции с неявным преобразованием типов
  }

  public static void hello(String somebody) {
    // заполненени переменой значением текущей даты и времени
    LocalDateTime testtime = LocalDateTime.now();
    // вывод сообщения в консоль
    System.out.println("******_TEST_START*************");
    // выводить текущую дату и время
    System.out.println("   " + testtime + "    ");
    System.out.println("     " + "Hello, " + somebody + " !" + "     ");
    System.out.println("******_TEST_END*************");
  }

  // расчет площади
  public static double area(double l1, double l2) {
    return l1 * l2;
  }

}
