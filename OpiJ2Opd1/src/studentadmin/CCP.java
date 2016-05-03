package studentadmin;
/**
 * Klasse die verantwoordelijk is voor een CPP
 * @author erwin
 */
public class CCP extends Leertraject {
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
}
