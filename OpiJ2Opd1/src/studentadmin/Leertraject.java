package studentadmin;
/**
 * Representeert een lijst met leertrajecten.
 * @author Erwin Engel
 */
public abstract class Leertraject {
  private String naam = null;
  
  public Leertraject(String naam){
    this.naam = naam;
  }
  
  public String getNaam(){
    return this.naam;
  }
}
