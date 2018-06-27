package predavanje9;

/**
 *
 * @author tomaz
 */
public class Drevesnica {
  public static void main(String[] args) {
    System.out.println(Drevo.navodilaZaObrezovanje());
    
    Drevo d1 = new Drevo();
    d1.spremeniIme("Lipa");
    d1.pomlad();
    d1.pomlad();
    d1.pomlad();
    d1.pomlad();    
    
    Drevo d2   = new Drevo();
    d2.spremeniIme("Hrast"); 
    d2.pomlad();
    d1.izpisi();
    d2.izpisi();
    
    System.out.println("Stevilo vseh dreves: " + Drevo.steviloDreves);
    
    System.out.printf("Povprecna rast za drevo2: %.2f\n", 
            d2.povprecnaRast());
  }
}
