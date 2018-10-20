package beginer.simple;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest4 {
  @Test
  public void testPointExp()
  {
    // простой тест сравнение расчетов растояние до псевдослучайной точки
    double a = (1 + (Math.random() * 999));
    double b = (1 + (Math.random() * 999));
    Point p1 = new Point(1, 1);
    Point p2 = new Point( a, b);
    double dif = Math.sqrt( (a - 1)*(a - 1) + (b - 1)*(b - 1) );
    Assert.assertEquals(p1.distancep2p(p2), dif);

  }


}
