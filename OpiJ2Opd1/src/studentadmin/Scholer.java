package studentadmin;
/**
 * Representeert een Scholer.
 * @author Erwin Engel
 */
public class Scholer extends Leerling{
  private int aantalModules = 0;
  private CCP ccp = null;
  
  public Scholer(String naam, Leertraject leertraject){
    super(naam);
    this.ccp = (CCP)leertraject;
  }
  
  @Override
  public Scholer clone(){
    CCP CCPCopy = new CCP(this.ccp.getNaam(), this.ccp.getTotModules());
    Scholer scholerClone = new Scholer(super.getNaam(), CCPCopy);
    scholerClone.setAantalModules(this.aantalModules);
    return scholerClone;
  }

  @Override
  public Leertraject getLeertraject() {
    return this.ccp;
  }

  @Override
  public double geefScore() {
    return aantalModules;
  }

  @Override
  public boolean isGeslaagd() {
    return this.aantalModules >= ccp.getTotModules();
  }

  @Override
  public void verhoogScore(double score) {
    this.aantalModules = this.aantalModules + (int)score;    
  }

  @Override
  public String toString() {
    String geslaagd = "niet geslaagd";
    if (isGeslaagd()){
      geslaagd = "geslaagd";
    }
    return (""+ super.getNaam() + "," + ccp.getNaam() + ","
              + this.aantalModules 
              + " modules," + geslaagd);
  }
  
  public void setAantalModules(int aantalModules) {
    this.aantalModules = aantalModules;
  }
  
  public void setCCP (CCP ccp){
    this.ccp = ccp;
  }

  public int getAantalModules() {
    return aantalModules;
  }

  public CCP getCCP() {
    return ccp;
  }

}
