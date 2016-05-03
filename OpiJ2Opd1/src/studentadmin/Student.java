package studentadmin;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
/**
 * Representeert een Student.
 * @author Erwin Engel
 */
public class Student extends Leerling{
  private double aantalStudiepunten = 0.0;
  private Opleiding opleiding = null;
  
  public Student (String naam, Leertraject leertraject){
    super(naam);
    this.opleiding = (Opleiding)leertraject;
  }

  @Override
  public Leertraject getLeertraject() {
    return this.opleiding;
  }

  @Override
  public double geefScore() {
    return aantalStudiepunten;
  }

  @Override
  public boolean isGeslaagd() {
    return this.aantalStudiepunten >= opleiding.getTotStudiepunten();
  }

  @Override
  public void verhoogScore(double score) {
    this.aantalStudiepunten = this.aantalStudiepunten + score;    
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
  
  public Student clone(){
    Opleiding opleidingCopy = new Opleiding(this.opleiding.getNaam(), this.opleiding.getTotStudiepunten());
    Student studentClone = new Student(super.getNaam(), opleidingCopy);
    studentClone.setAantalStudiepunten(this.aantalStudiepunten);
    return studentClone;
  }

  public void setAantalStudiepunten(int aantalStudiepunten) {
    this.aantalStudiepunten = aantalStudiepunten;
  }
  
  public void setOpleiding (Opleiding opleiding){
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
