package beginer.simple;

import org.testng.annotations.Test;
import org.testng.Assert;

public class PointTest1 {


 @Test
  public void testPoint() {
   // простой тест для проверки значения
   Point p1 = new Point(5, 7);
   Point p2 = new Point(8, 3);
   Assert.assertEquals(p1.distance(p2.x, p2.y), 5.0);
   }

}

