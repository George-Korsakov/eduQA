package beginer.simple;

//import java.awt.*;

public class Point {
 public double x, y;
 // конструктор
 public Point(double x, double y){
   this.x = x;
   this.y = y;
 }


  // вычисление расстояение между двумя точками
//  public double distance(int x2, int y2) {
//  double result = Math.sqrt(Math.pow((this.x - x2), 2) + Math.pow((this.y - y2), 2));
//   return (int)result;
//    //return this.x*this.y;
//  }
 //private Event p1 ;
 //private Event p2 ;
  public double distancep2p(double x, double y) {
   // double result = Math.sqrt( (p1.x - p1.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y) );
   double dx = this.x-x;
   double dy = this.y-y;
   return Math.sqrt(dx*dx+dy*dy);
    //return this.x*this.y;
  }
 public double distancep2p(Point p){
   return distancep2p(p.x, p.y);
 }



}
