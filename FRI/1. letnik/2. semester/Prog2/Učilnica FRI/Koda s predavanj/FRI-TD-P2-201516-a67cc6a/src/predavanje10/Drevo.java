package predavanje10;

public class Drevo extends Object {
  // stevec ustvarjenih dreves
  static int steviloDreves=0;
  
  // Atribute ime, starost in visina smo skrili (dolocilo private in 
  // protected). Za dostop do vrednosti in za spreminjanje vrednosti 
  // teh atributov smo na koncu dodali getterje in setterje.
  
  private String ime;      // ime drevesa
  private int    starost;  // starost drevesa
  protected double visina;   // visina drevesa  
  
  Drevo() {
    // za vsako ustvarjeno drevo
    // povecam vrednost tega stevca
    steviloDreves++;
    
    ime = "?";
    starost = 0;
    visina  = 0; 
  }
  
  Drevo(String ime) {
    this();  // klic konstriktorja Drevo()
    
    this.ime = ime;
  }
  
  // prva tri leta drevo zraste za 1 m, potem za 20cm
  void rast() {
    if (this.starost <= 3)
      this.visina += 1;
    else
      this.visina += 0.2;
  }
    
  void pomlad() {
    this.starost = this.starost + 1;
    rast();
  }
  
  void spremeniIme(String ime) {
    this.ime = ime;
  }
  
  double povprecnaRast() {
    if (this.starost == 0)
      return 0;
    else
      return this.visina / this.starost;
  }
  
  void izpisi() {
    System.out.printf("%s, %d, %.2f\n", ime, starost, visina);
    
  }
  
  static String navodilaZaObrezovanje() {
    return "Drevo se obrezuje spomladi ali jeseni, ....";
  }

  @Override
  public String toString() {
    return "Jaz sem " + ime;
  }
  
  public String getIme() {
    return this.ime;
  }
  
  public int getStarost() {
    return this.starost;
  }
  
  public double getVisina() {
    return this.visina;
  }
  
  public void setStarost(int starost){
    this.starost = starost;
    
    if (starost > 3)
      this.visina  =  3 + (starost-3)*0.2;
    else
      this.visina = starost;
  }
  
}
