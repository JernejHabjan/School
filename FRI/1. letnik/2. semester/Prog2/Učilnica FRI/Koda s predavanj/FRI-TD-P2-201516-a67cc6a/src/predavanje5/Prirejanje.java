package predavanje5;

/**
 *
 * @author tomaz
 */
public class Prirejanje {

  public static void main(String[] args) {
    int a=3;
    // Primer, iz katerega vidimo, da prirejanje v javi
    // vrne rezultat (v spodnjem primeru 5)
    System.out.println(a=5);
    System.out.println(a);

    // Vprašanje: kako se bo izraz izracunal, kot (8/4)/2
    // ali kot 8 / (4/2). Odgovor: kot (8/4)/2, saj je 
    // operator / levo asociativen (glej tabelo asociativnosti
    // operatorjev na prosojnicah)
    System.out.println( 8 / 4 / 2);
    
//    while ((c = fis.read()) != -1) {
//      printf(c);
//    }
//    
//    do {
//      c=fis.read();
//      if (c!=-1) {
//        printf(c);
//      }
//    } while (c!=-1);
    
  }
  
}
