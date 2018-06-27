package predavanje10;

/**
 * Testiranje delovanja razredov Drevo in Bonsai
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
    
    Drevo d3 = new Drevo("Bor");
    
    System.out.println("Stevilo vseh dreves: " + Drevo.steviloDreves);
    
    System.out.printf("Povprecna rast za drevo2: %.2f\n", 
            d2.povprecnaRast());
    
    System.out.println(d1 instanceof Drevo);
    
    Bonsai b = new Bonsai();
    b.spremeniIme("Hrastek");
    b.pomlad();
    b.pomlad();
    b.pomlad();
    System.out.println(b.getVisina());
    
    System.out.println(b.toString());
    System.out.println(d1.toString());
    
    System.out.println(b);
    
  }
}
