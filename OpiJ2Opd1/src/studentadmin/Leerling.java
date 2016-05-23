package studentadmin;
/**
 * Superklasse voor de verschillende soorten leerlingen.
 * @author erwin
 */
public abstract class Leerling implements Cloneable{
  private String naam = null;
  
  public Leerling(String naam){
    this.naam = naam;
  }
  
 /**
  * Laat zien of leerling geslaagd is 
  * @return
  */
  public abstract boolean isGeslaagd();

  public abstract String toString();
 /**
  * maakt clone van leerling 
  */
  public Object clone() throws CloneNotSupportedException{
	  Leerling l = (Leerling)super.clone();
	  return l;
  } 
  
  public String getNaam(){
    return this.naam;
  }
  
  public void setNaam(String naam){
    this.naam = naam;
  }
}
