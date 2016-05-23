package studentadmin;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
/**
 * Representeert een Student.
 * @author Erwin Engel
 */
public class Student extends Leerling  {
	private double aantalStudiepunten = 0.0;
	private final double MAXSTUDIEPUNTEN = 250.0;
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
	 * @param verhoging
	 * @throws StudentAdminException : indien maximale aantal modules overschreden
	 */
	public void verhoogStudiepunten(double verhoging) throws StudentAdminException {
		if (this.aantalStudiepunten + verhoging <= MAXSTUDIEPUNTEN) {
			this.aantalStudiepunten = this.aantalStudiepunten + verhoging;
		} 
		else {
			throw new StudentAdminException("aantal studiepunten kan nog met maximaal "  
																			+ (MAXSTUDIEPUNTEN- this.aantalStudiepunten) 
																			+ " verhoogd worden");
		}
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
	
	public Object clone() throws CloneNotSupportedException{
		Student s = (Student)super.clone();
		s.opleiding = (Opleiding) this.opleiding.clone();
		return s;
	}
}
