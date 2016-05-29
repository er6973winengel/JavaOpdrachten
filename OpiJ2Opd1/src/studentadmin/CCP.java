package studentadmin;
/**
 * Representeert een CPP
 * @author erwin engel
 */
public class CCP extends Leertraject implements Cloneable{
  private int totModules = 0;
/**
 * Constructor voor CCP
 * @param naam
 * @param totModules: totaal aantal modules waaruit CCP bestaat
 */
  public CCP(String naam, int totModules){
    super(naam);
    this.totModules = totModules;
  }
  public int getTotModules() {
    return totModules;
  } 
  public Object clone() throws CloneNotSupportedException{
	  return super.clone();
  }
}
