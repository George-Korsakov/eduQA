import java.time.LocalDateTime;

public class FirstProg1 {

	public static void main ( String[] args) {
// простая программа для проверки сборщика и системы контроля версий
		LocalDateTime testtime = LocalDateTime.now();
// выводить сообщение

		System.out.println ( "******_TEST_START*************");
		System.out.println ( "   "  + testtime + "    " );
		System.out.println ("     " + "Hello, world !"  + "     ");
		System.out.println ("******_TEST_END*************");

	}

}