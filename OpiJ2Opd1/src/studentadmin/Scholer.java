package studentadmin;
/**
 * Representeert een Scholer.
 * @author Erwin Engel
 */
public class Scholer extends Leerling{
	private int aantalModules = 0;
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
	 * @param verhoging , aantal modules verhoging
	 */
	public void verhoogModules(int verhoging) {
		this.aantalModules = this.aantalModules + verhoging;    
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


}
