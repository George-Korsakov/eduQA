package beginer.simple;

import java.time.LocalDateTime;

public class FirstProg1 {

  public static void main(String[] args) {
// простая программа для проверки сборщика и системы контроля версий
    hello("world");
    // создаем обхкты точки и задаем им значение параметров
    Point p1 = new Point(5, 7);
    Point p2 = new Point(8, 3);

    // вызываем функцию расчета расстояние между точками и выводим результат вычислений
    System.out.println("Расстояние моежду nочками " + "(" + p1.x + ", " + p1.y + ") и (" + p2.x + ", "+ p2.y + ") равнo " + p1.distance(p2.x, p2.y));
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

}
