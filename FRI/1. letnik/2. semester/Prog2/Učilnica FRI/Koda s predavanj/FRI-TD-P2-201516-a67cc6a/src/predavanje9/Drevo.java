package predavanje9;

/**
 *
 * @author tomaz
 */
public class Drevo {
  // stevec ustvarjenih dreves
  static int steviloDreves=0;
  
  String ime;      // ime drevesa
  int    starost;  // starost drevesa
  double visina;   // visina drevesa  
  
  Drevo() {
    // za vsako ustvarjeno drevo
    // povecam vrednost tega stevca
    steviloDreves++;
    
    ime = "?";
    starost = 0;
    visina  = 0; 
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
  
}
