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
  * maakt clone van leerling (diepe kopie, eigenlijk niet nodig omdat Opleiding en CPP
  * niet wijzigbaar zijen) 
  */
  public Object clone() throws CloneNotSupportedException{
	  Leerling l = (Leerling)super.clone();
	  if (this instanceof Student) {
	  	((Student) l).setOpleiding((Opleiding)((Student) l).getOpleiding().clone());
	  }
	  if (this instanceof Scholer) {
	  	((Scholer) l).setCpp((CPP)((Scholer) l).getCcp().clone());
	  }
	  return l;
  }
  
  public String getNaam(){
    return this.naam;
  }
  
  public void setNaam(String naam){
    this.naam = naam;
  }
}
