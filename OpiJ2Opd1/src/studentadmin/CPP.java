package studentadmin;
/**
 * Representeert een CPP
 * @author erwin engel
 */
public class CPP extends Leertraject implements Cloneable{
  private int totModules = 0;
/**
 * Constructor voor CPP
 * @param naam
 * @param totModules: totaal aantal modules waaruit CPP bestaat
 */
  public CPP(String naam, int totModules){
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
