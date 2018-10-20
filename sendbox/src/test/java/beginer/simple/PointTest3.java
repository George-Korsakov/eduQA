package beginer.simple;

import org.testng.annotations.Test;
import org.testng.Assert;

public class PointTest3 {


  @Test
  public void testPointZero() {
    // простой тест для проверки значения
    Point p1 = new Point(0, 0);
    Point p2 = new Point(0, 0);
    Assert.assertEquals(p1.distancep2p(p2), 0.0);
  }

}