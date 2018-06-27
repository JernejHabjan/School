package predavanje10;

/**
 * Testiranje delovanja razreda Drevo - demonstracija uporabe 
 * skritega atributa starost
 */
public class Drevesnica1 {  
  
  public static void main(String[] args) {
    Drevo d1 = new Drevo("Hrast");
    
    d1.pomlad();
    d1.pomlad();
    d1.pomlad();    
    d1.pomlad();    
    
    // to ne gre, saj je atribut starost skrit!
    //d1.starost=7;
    // namesto tega starost nastavimo preko metode setStarost()
    d1.setStarost(7);
    
    System.out.printf("Ime: %s, starost: %d, visina: %.2f\n", 
            d1.getIme(), d1.getStarost(), d1.getVisina());
  }
}
