package beginer.simple;

import java.time.LocalDateTime;


public class FirstProg1 {

	public static void main ( String[] args) {
// простая программа для проверки сборщика и системы контроля версий
		hello("world");
		hello("test");
		char a = 25;
		double b = 33.0;
		// пример вызова функции с неявным преобразованием типов
		System.out.println( area( b, a));
	}



	public static void hello (String somebody) {
		// заполненени переменой значением текущей даты и времени
		LocalDateTime testtime = LocalDateTime.now();
		// вывод сообщения в консоль
		System.out.println ( "******_TEST_START*************");
		System.out.println ( "   "  + testtime + "    " );
		System.out.println ("     " + "Hello, "+ somebody + " !"  + "     ");
		System.out.println ("******_TEST_END*************");
	}
	// расчет площади
	public static double area (double l1, double l2){
		double summ = l1 * l2;
		return summ;
	}



}
