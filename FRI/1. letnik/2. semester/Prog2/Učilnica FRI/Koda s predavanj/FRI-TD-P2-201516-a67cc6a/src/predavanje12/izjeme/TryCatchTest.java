package predavanje12.izjeme;

/**
 *
 * @author tomaz
 */
public class TryCatchTest {
  
  static void deljenje() {
    int i=0;
    
    int a;
    try {
      a = 5/i;
    } catch (ArithmeticException e) {
      // ce bo pri deljenju z i prislo do izjeme, se bo izvedel ta blok:
      
      a = 0;
      System.out.printf("Prislo je do tezave: '%s'\n", 
              e.getMessage());
      
      System.out.println("Daljse obvestilo: " + e.toString());
      
      e.printStackTrace();
    }
    System.out.println(a);
    
  }
  
  public static void main(String[] args) {
    // klicem metodo, pri kateri bo morda prislo do izjeme, 
    // vendar bo za izjemo poskrbljeno v try-catch bloku v metodi
    deljenje();
  }

}
