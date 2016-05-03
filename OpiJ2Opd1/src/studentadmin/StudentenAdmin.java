
package studentadmin;

import java.util.ArrayList;
/**
 * Klasse die verantwoordelijk is voor de studenten administratie
 * @author erwin
 */
public class StudentenAdmin {
  private ArrayList<Leerling> leerlingLijst = new ArrayList<Leerling>();
  private ArrayList<Leertraject> leertrajectLijst = new ArrayList<Leertraject>();  
  public enum OpleidingKeuze {WISKUNDE, INFORMATICA};
  public enum CCPKeuze {JAVA, SOFTWAREARCHITECT, SYSTEEMONTWIKKELAAR};
 
  public StudentenAdmin(){
    init();
  }
  
  private void init(){
    leertrajectLijst.add(new Opleiding("Wiskunde", 160));
    leertrajectLijst.add(new Opleiding("Informatica", 200));
    leertrajectLijst.add(new CCP("Java", 6));
    leertrajectLijst.add(new CCP("Software architect", 4));
    leertrajectLijst.add(new CCP("Systeem ontwikkelaar", 3));
  }
 /**
  * Methode om studenten toe te voegen. Bij toevoegen van student die al bestaat wordt bestaande student 
  * overschreven.  
  * @param naam
  * @param keuze
  */
  public void maakStudent(String naam, OpleidingKeuze keuze){
    boolean nieuweStudent = true;
    // bepaal de naam van de gevraagde opleiding
    String opleidingNaam = geefOpleidingNaam(keuze);
    // zoek gevraagde opleiding object
    Opleiding opleiding = zoekOpleidingObject(opleidingNaam);
    // zoek leering in lijst, indien aanwezig: gegevens van deze leerling overschrijven
    if (zoekLeerlingObject(naam) != null && opleidingNaam != null){
      Student s = (Student)zoekLeerlingObject(naam);
      s.setAantalStudiepunten(0);     
      s.setOpleiding(opleiding);
      nieuweStudent = false;
    }
    // indien nieuwe student dan toevoegen
    if (nieuweStudent){
      leerlingLijst.add(new Student(naam, opleiding));
      System.out.println(""+ leerlingLijst.get(0).getNaam());
    }
  }
  
 /**
   * Methode om scholers toe te voegen. Bij toevoegen van scholer die al bestaat wordt bestaande student 
   * overschreven.  
   * @param naam
   * @param keuze
   */
  public void maakScholer(String naam, CCPKeuze keuze){
    boolean nieuweScholer = true;
    // bepaal de naam van de gevraagde ccp
    String CCPNaam = geefCCPNaam(keuze);
    // zoek gevraagde ccp object
    CCP ccp = zoekCCPObject(CCPNaam);
    // zoek scholer in lijst, indien aanwezig: gegevens van deze scholer overschrijven
    if (zoekLeerlingObject(naam) != null && CCPNaam != null){
      Scholer s = (Scholer)zoekLeerlingObject(naam);
      s.setAantalModules(0);     
      s.setCCP(ccp);
      nieuweScholer = false;
    }
    // indien nieuwe scholer dan toevoegen
    if (nieuweScholer){
      leerlingLijst.add(new Scholer(naam, ccp));
      System.out.println(""+ leerlingLijst.get(0).getNaam());
    }
  }
  /**
   * Methode die leerlingen opzoekt voor gegeven naam. Indien leerling wordt gevonden wordt een clone van deze leerling 
   * teruggegeven
   * @param naam
   * @return
   */
  public Leerling geefLeerling(String naam){
    Leerling leerling = zoekLeerlingObject(naam);
    if (leerling != null){
      return leerling.clone();
    } else {
      return null;
    }
  }
  /**
   * Methode die de behaalde studiepunten van een gegeven student verhoogt met gegeven verhoging
   * @param leerling
   * @param verhoging
   */
  public void verhoogStudiepunten(Leerling leerling, double verhoging){
    if (zoekLeerlingObject(leerling.getNaam()) != null && leerling instanceof Student){
      zoekLeerlingObject(leerling.getNaam()).verhoogScore(verhoging);
    }
  }
  /**
   * Methode die het aantal behaalde modules van een gegeven scholer verhoogt met gegeven verhoging
   * @param leerling
   * @param verhoging
   */
  public void verhoogModules(Leerling leerling, double verhoging){
    if (zoekLeerlingObject(leerling.getNaam()) != null && leerling instanceof Scholer){
      zoekLeerlingObject(leerling.getNaam()).verhoogScore(verhoging);
    }
  }
  /**
   * Methode die een lijst geeft met informatie over alle leerlingen, elke string in de lijst
   * bevat de informatie over 1 leerling
   * @return ArrayList<string> leerlingen informatie
   */
  public ArrayList<String> geefLeerlingenInfo(){
    ArrayList<String> leerlingenInfoLijst = new ArrayList<String>();
    for (Leerling leerling : leerlingLijst){
      leerlingenInfoLijst.add(leerling.toString());
    }
    return leerlingenInfoLijst;
  }
  /**
   * Hulp methode om de enum Opleiding te converteren naar opleiding naam
   * @param keuze
   * @return String opleidingnaam
   */
  private String geefOpleidingNaam(OpleidingKeuze keuze){
    if (keuze == OpleidingKeuze.WISKUNDE){
      return "Wiskunde";
    }
    if (keuze == OpleidingKeuze.INFORMATICA){
      return "Informatica";
    }
    return null;   
  }
  /**
   * Hulp methode om de enum CCP te converteren naar CCP naam
   * @param keuze
   * @return String CPPgnaam
   */  
  private String geefCCPNaam(CCPKeuze keuze){
    if (keuze == CCPKeuze.JAVA){
      return "Java";
    }
    if (keuze == CCPKeuze.SOFTWAREARCHITECT){
      return "Software architect";
    }
    if (keuze == CCPKeuze.SYSTEEMONTWIKKELAAR){
      return "Systeem ontwikkelaar";
    }
    return null;   
  }
  /**
   * hulpmethode voor het opvragen van een Opleiding uit de leertrajectLijst met behulp van gegeven naam
   * @param opleidingNaam
   * @return Opleiding
   */
  private Opleiding zoekOpleidingObject(String opleidingNaam){
    for (Leertraject leertraject : leertrajectLijst){
      if (leertraject.getNaam().equals(opleidingNaam)){
        return (Opleiding)leertraject;
      }
    }
    return null;
  }
  /**
   * hulpmethode voor het opvragen van een CCP uit de leertrajectLijst met behulp van gegeven naam
   * @param CCPNaam
   * @return CCP
   */
  private CCP zoekCCPObject(String CCPNaam){
    for (Leertraject leertraject : leertrajectLijst){
      if (leertraject.getNaam().equals(CCPNaam)){
        return (CCP)leertraject;
      }
    }
    return null;
  }
  /**
   * hulpmethode voor het opvragen van een Leerling uit de leerlingLijst met behulp van gegeven naam
   * @param naam
   * @return Leerling
   */ 
  private Leerling zoekLeerlingObject(String naam){
    for (Leerling leerling : leerlingLijst) {
      if (leerling.getNaam().equals(naam)){
        return leerling;
      }
    }
    return null;
  }
}
