package studentadmin;
/**
 * Klasse die verantwoordelijk is voor de studenten administratie
 * @author erwin
 */
public abstract class Leerling {
  private String naam = null;
  
  public Leerling(String naam){
    this.naam = naam;
  }
  
  public abstract Leertraject getLeertraject();
  
  public abstract double geefScore();
 /**
  * Laat zien of leerling geslaagd is 
  * @return
  */
  public abstract boolean isGeslaagd();
 /**
  * verhoogt de score van een leerling
  * @param score
  */
  public abstract void verhoogScore(double score);

  public abstract String toString();
  
  public abstract Leerling clone();
  
  public String getNaam(){
    return this.naam;
  }
  
  public void setNaam(String naam){
    this.naam = naam;
  }
}
