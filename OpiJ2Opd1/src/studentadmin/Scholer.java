package studentadmin;
/**
 * Representeert een Scholer.
 * @author Erwin Engel
 */
public class Scholer extends Leerling{
	private int aantalModules = 0;
	private final int MAXMODULES = 8;
	private CPP cpp = null;

	public Scholer(String naam, CPP cpp){
		super(naam);
		this.cpp = cpp;
	}

	@Override
	public boolean isGeslaagd() {
		return this.aantalModules >= cpp.getTotModules();
	}

	@Override
	public String toString() {
		String geslaagd = "niet geslaagd";
		if (isGeslaagd()){
			geslaagd = "geslaagd";
		}
		return (""+ super.getNaam() + "," + cpp.getNaam() + ","
				+ this.aantalModules 
				+ " modules," + geslaagd);
	}

	/**
	 * Verhoog huidige aantal modules met gegeven verhoging
	 * @param verhoging
	 * @throws StudentAdminException : indien maximum aantal modules overschreden
	 */
	public void verhoogModules(int verhoging) throws StudentAdminException {
		if (this.aantalModules + verhoging <= MAXMODULES){
			this.aantalModules = this.aantalModules + verhoging;
		}
		else {
			throw new StudentAdminException("aantal studiepunten kan nog met maximaal "  
																			+ (MAXMODULES - this.aantalModules) 
																			+ " verhoogd worden");			
		}
	}

	public void setCpp(CPP cpp) {
		this.cpp = cpp;
	}

	public CPP getCcp() {
		return cpp;
	}  

	public void setAantalModules(int aantalModules) {
		this.aantalModules = aantalModules;
	}

	public int getAantalModules() {
		return aantalModules;
	}

	public Object clone() throws CloneNotSupportedException{
		Scholer s = (Scholer)super.clone();
		s.cpp = (CPP) this.cpp.clone();
		return s;
	}

}
