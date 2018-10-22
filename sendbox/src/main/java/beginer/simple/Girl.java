package beginer.simple;

public class Girl {

  public double mind_lvl, beauty_lvl;
  // объект
  public Girl(double mind_lvl , double beauty_lvl){
    this.mind_lvl = mind_lvl;
    this.beauty_lvl= beauty_lvl;
  }
  // функция расчета
  public double choice_girlfriend ( double mind_lvl, double beauty_lvl ){
    double difA = this.mind_lvl-mind_lvl;
    double difB = this.beauty_lvl-beauty_lvl;
    return difA*difB ;
  }

  public double choice_girlfriend ( Girl a){
    return choice_girlfriend (a.mind_lvl, a.beauty_lvl);
  }

}
