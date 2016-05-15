
package studentadmin;

import java.util.ArrayList;
/**
 * Klasse die verantwoordelijk is voor de leerlingen administratie: leerlingen en de leertrajecten die zij (kunnen) volgen
 * @author erwin
 */
public class StudentenAdmin {
  private ArrayList<Leerling> leerlingLijst = new ArrayList<Leerling>();
  private ArrayList<Leertraject> leertrajectLijst = new ArrayList<Leertraject>();  

  public StudentenAdmin(){
    init();
  }
  
  private void init(){
    leertrajectLijst.add(new Opleiding("Wiskunde", 160));
    leertrajectLijst.add(new Opleiding("Informatica", 200));
    leertrajectLijst.add(new CPP("Java", 6));
    leertrajectLijst.add(new CPP("Software architect", 4));
    leertrajectLijst.add(new CPP("Systeem ontwikkelaar", 3));
  }
 /**
  * Methode om studenten toe te voegen. Bij toevoegen van student die al bestaat wordt bestaande student 
  * overschreven.  
  * @param naam , naam van student
  * @param opleidingKeuze , naam van gekozen opleiding
  */
  public void maakStudent(String naam, String opleidingKeuze){
    // zoek gevraagde opleiding object
    Opleiding opleiding = zoekOpleidingObject(opleidingKeuze);
    // zoek leering in lijst, indien aanwezig: gegevens van deze leerling overschrijven
    if (zoekLeerlingObject(naam) != null && opleiding != null){
      Student s = (Student)zoekLeerlingObject(naam);
      s.setAantalStudiepunten(0);     
      s.setOpleiding(opleiding);
    } else {
    // indien nieuwe student dan toevoegen
      leerlingLijst.add(new Student(naam, opleiding));
    } 
  }
  
 /**
   * Methode om scholers toe te voegen. Bij toevoegen van scholer die al bestaat wordt bestaande student 
   * overschreven.  
   * @param naam , naam van scholer
   * @param CcpKeuze , naam van gekozen CPP
   */
  public void maakScholer(String naam, String CcpKeuze){
    // zoek gevraagde ccp object
    CPP cpp = zoekCCPObject(CcpKeuze);
    // zoek scholer in lijst, indien aanwezig: gegevens van deze scholer overschrijven
    if (zoekLeerlingObject(naam) != null && cpp != null){
      Scholer s = (Scholer)zoekLeerlingObject(naam);
      s.setAantalModules(0);     
      s.setCpp(cpp);
    } else {
    // indien nieuwe scholer dan toevoegen
      leerlingLijst.add(new Scholer(naam, cpp));
    }
  }
  /**
   * Methode die leerlingen opzoekt voor gegeven naam. Indien leerling wordt gevonden wordt een clone van deze leerling 
   * teruggegeven
   * @param naam , naam van gezochte leerling
   * @return Leerling (kopie van leerling uit administratie)
   * @throws CloneNotSupportedException , indien actuele type geen implementatie van clone() heeft
   */
  public Leerling geefLeerling(String naam) throws CloneNotSupportedException{
    Leerling leerling = zoekLeerlingObject(naam);
    if (leerling != null){
      return (Leerling) leerling.clone();
    } else {
      return null;
    }
  }
  
  /**
   * Methode die de behaalde studiepunten van een gegeven student verhoogt met gegeven verhoging
   * @param leerling , Leerling object (kopie uit administratie)
   * @param verhoging , getal waarmee aantal studiepunten verhoogt moet worden
   */
  public void verhoogStudiepunten(Leerling leerling, double verhoging){
    if (zoekLeerlingObject(leerling.getNaam()) != null && leerling instanceof Student){
      Student s = (Student) zoekLeerlingObject(leerling.getNaam());
     	s.verhoogStudiepunten(verhoging);
    }
  }
  /**
   * Methode die het aantal behaalde modules van een gegeven scholer verhoogt met gegeven verhoging
   * @param leerling, Leerling object (kopie uit administratie)
   * @param verhoging , getal waarme aantal modules verhoogt moet worden
   */
  public void verhoogModules(Leerling leerling, int verhoging){
    if (zoekLeerlingObject(leerling.getNaam()) != null && leerling instanceof Scholer){
      Scholer s = (Scholer)zoekLeerlingObject(leerling.getNaam());
      s.verhoogModules(verhoging);
    }
  }
  /**
   * Methode die een lijst geeft met informatie over alle leerlingen, elke string in de lijst
   * bevat de informatie over 1 leerling
   * @return ArrayList met strings, per student in de administratie een string met alle student informatie 
   */
  public ArrayList<String> geefLeerlingenInfo(){
    ArrayList<String> leerlingenInfoLijst = new ArrayList<String>();
    for (Leerling leerling : leerlingLijst){
      leerlingenInfoLijst.add(leerling.toString());
    }
    return leerlingenInfoLijst;
  }
  /**
   * Methode die lijst geeft met kopieen van alle aangeboden CCPs.
   * @return Lijst met kopieen van alle CCPs
   * @throws CloneNotSupportedException indien actuele type van het object in de lijst clone() niet implementeert
   */
  public ArrayList<CPP> geefCcpLijst() throws CloneNotSupportedException{
	ArrayList<CPP> CcpLijst = new ArrayList<CPP>();
	for (Leertraject leertraject : leertrajectLijst){
	  if (leertraject instanceof CPP){
		CcpLijst.add( (CPP)leertraject.clone() );
	  }
	}
	return CcpLijst;
  }
  /**
   * Methode de lijst geeft met kopieen van alle aangeboden opleidingen
   * @return Lijst met kopieen van alle Opleidingen
   * @throws CloneNotSupportedException indien actuele type van het object in de lijst clone() niet implementeert
   */
  public ArrayList<Opleiding> geefOpleidingLijst() throws CloneNotSupportedException{
	ArrayList<Opleiding> opleidingLijst = new ArrayList<Opleiding>();
	for (Leertraject leertraject : leertrajectLijst) {
	 if (leertraject instanceof Opleiding){
	   opleidingLijst.add( (Opleiding)leertraject.clone() );
	  }
	}
	return opleidingLijst;
  }
  /**
   * hulpmethode voor het opvragen van een Opleiding object uit de leertrajectLijst op basis van gegeven naam
   * @param opleidingNaam , naam van gezochte opleiding
   * @return Opleiding object
   */
  private Opleiding zoekOpleidingObject(String opleidingNaam){
    for (Leertraject leertraject : leertrajectLijst){
      if (leertraject.getNaam().equals(opleidingNaam) && leertraject instanceof Opleiding ){
        return (Opleiding)leertraject;
      }
    }
    return null;
  }
  /**
   * hulpmethode voor het opvragen van een CPP uit de leertrajectLijst op basis van gegeven naam
   * @param CCPNaam , naam van gezochte CPP
   * @return CPP object
   */
  private CPP zoekCCPObject(String CCPNaam){
    for (Leertraject leertraject : leertrajectLijst){
      if (leertraject.getNaam().equals(CCPNaam) && leertraject instanceof CPP){
        return (CPP)leertraject;
      }
    }
    return null;
  }
  /**
   * hulpmethode voor het opvragen van een Leerling uit de leerlingLijst met behulp van gegeven naam
   * @param naam , naam van gezochte leerling
   * @return Leerling object
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
