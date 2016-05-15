package studentadmin;
/**
 * Klasse die verantwoordelijk is voor een Opleiding
 * @author erwin
 */
public class Opleiding extends Leertraject implements Cloneable{
  private double totStudiepunten = 0;
  /**
   * Constructor voor Oplieding
   * @param naam
   * @param totStudiepunten: totaal aantal studiepunten waaruit Opleiding bestaat
   */
  public Opleiding(String naam, double totStudiepunten){
   super(naam);
   this.totStudiepunten = totStudiepunten;
  }
  
  public double getTotStudiepunten() {
    return totStudiepunten;
  } 
  
  public Object clone() throws CloneNotSupportedException{
	  return super.clone();
  }
}
