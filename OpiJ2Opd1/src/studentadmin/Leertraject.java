package studentadmin;
/**
 * Superklasse voor de verschillende soorten leertrajecten.
 * @author Erwin Engel
 */
public abstract class Leertraject implements Cloneable {
  private String naam = null;
  
  public Leertraject(String naam){
    this.naam = naam;
  }
  
  public String getNaam(){
    return this.naam;
  }
  
  public Object clone() throws CloneNotSupportedException{
	  return super.clone();
  }
}
