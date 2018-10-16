package beginer.simple;

import java.time.LocalDateTime;

public class FirstProg1 {

  public static void main(String[] args) {
// простая программа для проверки сборщика и системы контроля версий
    hello("world");
    // создаем обхкты точки и задаем им значение параметров
    Point p1 = new Point(9, 8);
    Point p2 = new Point(6, 4);

    // вызываем функцию расчета расстояние между точками и выводим результат вычислений
    System.out.println("Расстояние моежду nочками " + "(" + p1.x + ", " + p1.y + ") и (" + p2.x + ", "+ p2.y + ") равнo " + distance(p1, p2));
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

  // функция которая вычисляет расстояение между двумя точками
  public static double distance(Point p1, Point p2) {
    return Math.sqrt(Math.pow((p1.x-p2.x),2)+Math.pow((p1.y-p2.y),2));
  }

}
