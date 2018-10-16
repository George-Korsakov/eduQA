package beginer.simple;

public class Point {
 public int x, y;
 // конструктор
 public Point(int x, int y){
   this.x = x;
   this.y = y;
 }


  // вычисление расстояение между двумя точками
  public double distance(int x2, int y2) {
   return Math.sqrt(Math.pow((this.x - x2), 2) + Math.pow((this.y - y2), 2));
    //return this.x*this.y;
  }
}
