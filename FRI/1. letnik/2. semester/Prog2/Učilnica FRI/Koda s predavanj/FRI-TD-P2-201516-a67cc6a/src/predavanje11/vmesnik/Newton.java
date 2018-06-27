package predavanje11.vmesnik;

/**
 * Demonstracija uporabe abstraktnega razreda: v metodi poisciNiclo() 
 * uporabljamo abstraktni razred Funkcija, saj nam v tej fazi ni pomembno,
 * kaksne so dejanske vrednosti in odvod funkcije, pomembno je le, da
 * imamo na razpolago objekt z definiranima metodama vrednost() in odvod().
 * 
 */
public class Newton {
  
  /**
   * Racunanje nicle funkcije po Newtnovi metodi. 
   */
  static double poisciNiclo(int n, double x0, Funkcija f) {
    for (int i = 0; i < n; i++) { 
      x0 = x0 - f.vrednost(x0) / f.odvod(x0);
    }
    return x0;
  }
  
  /** 
   * Racunanje nicle funkcije sin(x) okoli tocke 3.
   */
  public static void main(String[] args) {    
    double nicla = poisciNiclo(10, 3, new Sinus());
    System.out.println(nicla); // priblizek stevila Pi
    
    
  }

}
