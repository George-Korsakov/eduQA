package beginer.simple;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest4 {
  @Test
  public void testPointExp()
  {
    // для воспроизведения ошибки с граничным значении
    Point p1 = new Point(1, 1);
    Point p2 = new Point(250*Math.pow(10, 485), 1);
    Assert.assertEquals(p1.distance(p2.x, p2.y), 1);

  }


}
