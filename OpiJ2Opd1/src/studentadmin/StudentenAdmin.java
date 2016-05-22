
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
	 * @throws StudentAdminException
	 */
	public void maakStudent(String naam, String opleidingKeuze)throws StudentAdminException{
		// controleer gevraagde opleiding object, indien opleiding niet aanwezig: exception 
		if (zoekOpleidingObject(opleidingKeuze) == null){
			throw new StudentAdminException("kies een bestaande opleiding");
		}
		// controleer op lengte naam minimaal 2 karakters, indien te kort: exception
		if (naam.length() < 2 ){
			throw new StudentAdminException("naam moet minimaal 2 posities lang zijn");
		}
		// controleer naam leerling, indien naam al aanwezig: exception
		if (zoekLeerlingObject(naam) != null ){
			throw new StudentAdminException("kies een nog niet bestaande leerling");
		}
		// indien controles goed: nieuwe student toevoegen
		leerlingLijst.add(new Student(naam, zoekOpleidingObject(opleidingKeuze)));
	}

	/**
	 * Methode om scholers toe te voegen. Bij toevoegen van scholer die al bestaat wordt bestaande student 
	 * overschreven.  
	 * @param naam , naam van scholer
	 * @param  , naam van gekozen CPP
	 * @throws StudentAdminException
	 */
	public void maakScholer(String naam, String cppKeuze)throws StudentAdminException{
		// controleer gevraagde opleiding object, indien opleiding niet aanwezig: exception 
		if (zoekCppObject(cppKeuze) == null){
			throw new StudentAdminException("kies een bestaande CPP");
		}
		// controleer op lengte naam minimaal 2 karakters, indien te kort: exception
		if (naam.length() < 2 ){
			throw new StudentAdminException("naam moet minimaal 2 posities lang zijn");
		}
		// controleer naam leerling, indien naam al aanwezig: exception 
		if (zoekLeerlingObject(naam) != null ){
			System.out.println("naam bestaat al: " + naam);
			throw new StudentAdminException("kies een nog niet bestaande leerling");
		}
		// indien nieuwe scholer dan toevoegen
		leerlingLijst.add(new Scholer(naam, zoekCppObject(cppKeuze)));
	}
	/**
	 * Methode die leerlingen opzoekt voor gegeven naam. Indien leerling wordt gevonden wordt een clone van deze leerling 
	 * teruggegeven. Indien geen leerling wordt gevonden dan exceptie: leerling niet gevonden
	 * @param naam , naam van gezochte leerling
	 * @return Leerling (kopie van leerling uit administratie)
	 * @throws StudentAdminException
	 */
	public Leerling geefLeerling(String naam) throws StudentAdminException {
		Leerling leerling = zoekLeerlingObject(naam);
		// probeer clone van leerling te maken, indien mislukt: exception,
		//                                      indien leerling niet betaat: exception.
		if (leerling != null){
			try {
				return (Leerling) leerling.clone();
			} 
			catch (CloneNotSupportedException CloneNtSpprtEx){
				throw new StudentAdminException("fout in administratie: neem contact op met de beheerder");
			}
		} else {
			throw new StudentAdminException("kies een bestaande leerling");
		}
	}

	/**
	 * Methode die de behaalde studiepunten van een gegeven student verhoogt met gegeven verhoging
	 * @param leerling , Leerling object (kopie uit administratie)
	 * @param verhoging , getal waarmee aantal studiepunten verhoogt moet worden
	 * @throws StudentAdminException
	 */
	public void verhoogStudiepunten(Leerling leerling, double verhoging) throws StudentAdminException {
		// controleer op bestaan leerling, indien niet bestaand: exceptions 
		if (zoekLeerlingObject(leerling.getNaam()) == null){
			throw new StudentAdminException("kies een bestaande leerling");
		}
		// indien leerling geen student: exception
		if (!(leerling  instanceof Student)){
			throw new StudentAdminException("studiepunten verhogen kan alleen bij een student");
		}
		// verhogen studiepunten als leerling bestaat
		((Student) zoekLeerlingObject(leerling.getNaam())).verhoogStudiepunten(verhoging);
	}
	/**
	 * Methode die het aantal behaalde modules van een gegeven scholer verhoogt met gegeven verhoging
	 * @param leerling, Leerling object (kopie uit administratie)
	 * @param verhoging , getal waarme aantal modules verhoogt moet worden
	 * @throws StudentAdminException
	 */
	public void verhoogModules(Leerling leerling, int verhoging) throws StudentAdminException {
		// controleer op bestaan leerling, indien niet bestaand: exceptions 
		if (zoekLeerlingObject(leerling.getNaam()) == null){
			throw new StudentAdminException("kies een bestaande leerling");
		}
		// indien leerling geen scholer: exception
		if (!(leerling  instanceof Scholer)){
			throw new StudentAdminException("module verhogen kan alleen bij een student");
		}
		// verhogen studiepunten als leerling bestaat
		((Scholer) zoekLeerlingObject(leerling.getNaam())).verhoogModules(verhoging);
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
	 * Methode die lijst geeft met kopieen van alle aangeboden CPPs.
	 * @return Lijst met kopieen van alle CPPs
	 * @throws StudentAdminException indien actuele type van het object in de lijst clone() niet implementeert
	 */
	public ArrayList<CPP> geefCppLijst() throws StudentAdminException{
		ArrayList<CPP> cppLijst = new ArrayList<CPP>();
		for (Leertraject leertraject : leertrajectLijst){
			if (leertraject instanceof CPP){
				try {
					cppLijst.add( (CPP)leertraject.clone() );
				} catch (CloneNotSupportedException CloneNtSpprtEx){
					throw new StudentAdminException("fout in administratie: neem contact op met de beheerder");
				}
			}
		}
		return cppLijst;
	}
	/**
	 * Methode de lijst geeft met kopieen van alle aangeboden opleidingen
	 * @return Lijst met kopieen van alle Opleidingen
	 * @throws StudentAdminException indien actuele type van het object in de lijst clone() niet implementeert
	 */
	public ArrayList<Opleiding> geefOpleidingLijst() throws StudentAdminException{
		ArrayList<Opleiding> opleidingLijst = new ArrayList<Opleiding>();
		for (Leertraject leertraject : leertrajectLijst) {
			if (leertraject instanceof Opleiding){
				try {
					opleidingLijst.add( (Opleiding)leertraject.clone() );
				} catch (CloneNotSupportedException CloneNtSpprtEx){
					throw new StudentAdminException("fout in administratie: neem contact op met de beheerder");
				}
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
	 * @param CPPNaam , naam van gezochte CPP
	 * @return CPP object
	 */
	private CPP zoekCppObject(String cppNaam){
		for (Leertraject leertraject : leertrajectLijst){
			if (leertraject.getNaam().equals(cppNaam) && leertraject instanceof CPP){
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
