package studentadmin;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
/**
 * Representeert een Student.
 * @author Erwin Engel
 */
public class Student extends Leerling {
	private double aantalStudiepunten = 0.0;
	private Opleiding opleiding = null;

	public Student (String naam, Opleiding opleiding){
		super(naam);
		this.opleiding = opleiding;
	}

	@Override
	public boolean isGeslaagd() {
		return this.aantalStudiepunten >= opleiding.getTotStudiepunten();
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("####0.00", new DecimalFormatSymbols(Locale.US));
		String geslaagd = "niet geslaagd";
		if (isGeslaagd()){
			geslaagd = "geslaagd";
		}
		return (""+ super.getNaam() + "," + opleiding.getNaam() + ","
				+ df.format(this.aantalStudiepunten) 
				+ " studiepunten," + geslaagd);
	}

	/**
	 * Verhoog huidige aantal studiepunten met gegeven verhoging
	 * @param verhoging , aantal studieputen verhoging
	 */
	public void verhoogStudiepunten(double verhoging) {
		this.aantalStudiepunten = this.aantalStudiepunten + verhoging;    
	}

	public void setOpleiding(Opleiding opleiding) {
		this.opleiding = opleiding;
	}   

	public Opleiding getOpleiding() {
		return opleiding;
	}

	public double getAantalStudiepunten() {
		return aantalStudiepunten;
	}

	public void setAantalStudiepunten(double aantalStudiepunten) {
		this.aantalStudiepunten = aantalStudiepunten;
	}
}
