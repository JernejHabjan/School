package predavanje12.izjeme;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tomaz
 */
public class DeljenjeZNic {
  
  static int deli(int a, int b) {
    if (b != 0)
      return a/b;
    else
      // ce bo prislo do izjeme, ustvarimo in vrzemo izjemo tipa 
      // DeljenjeZNicIzjema. Ker gre za nepreverljivo izjemo, nam
      // v glavi metode tega ni treba napovedati
      throw new DeljenjeZNicIzjema();
  }
  
  
static int deliMaloDrugace(int a, int b) throws Exception {
    if (b != 0)
      return a/b;
    else
      // ce bo prislo do izjeme, ustvarimo in vrzemo izjemo tipa 
      // Exception. Ker gre za preverljivo izjemo, moramo v glavi
      // metode to napovedati z ukazom "throws"
      throw new Exception("Deljenje z nic!");
  }
    

  public static void main(String[] args) {
    // metodo deli(), ki v primeru napake vrze nepreverljivo izjemo, lahko
    // klicemo, ne da bi uporabili try-catch blok
    System.out.println(deli(10, 0));        
    
    try {
      // metode deliMaloDrugace, ki vrze preverljivo izjemo tipa Exception
      // ne moremo klicati brez try-catch bloka
      System.out.println(deliMaloDrugace(10, 0));
    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
  }
}
