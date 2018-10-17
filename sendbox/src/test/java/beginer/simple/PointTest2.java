package beginer.simple;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest2 {

  @Test
  public void testPointAlt() {
    // тест с использованием псевдослучайного значения
    Point r1 = new Point((1 + (int) (Math.random() * 999)), (1 + (int) (Math.random() * 999)));
    Point r2 = new Point((1 + (int) (Math.random() * 999)), (1 + (int) (Math.random() * 999)));
    // альтернативнsq расчитывает ответ для сравнения
    double d = Math.sqrt(Math.pow((r1.x - r2.x), 2) + Math.pow((r1.y - r2.y), 2));
    // проверка не равенства занчению
    Assert.assertNotEquals(r1.distance(r2.x, r2.y), d-1);
  }
}
