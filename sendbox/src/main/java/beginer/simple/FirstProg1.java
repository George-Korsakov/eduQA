package beginer.simple;

import java.time.LocalDateTime;


public class FirstProg1 {

	public static void main ( String[] args) {
// простая программа для проверки сборщика и системы контроля версий
		hello();
	}

	public static void hello () {
		// заполненени переменой значением текущей даты и времени
		LocalDateTime testtime = LocalDateTime.now();
		// вывод сообщения в консоль
		System.out.println ( "******_TEST_START*************");
		System.out.println ( "   "  + testtime + "    " );
		System.out.println ("     " + "Hello, world !"  + "     ");
		System.out.println ("******_TEST_END*************");
	}
}