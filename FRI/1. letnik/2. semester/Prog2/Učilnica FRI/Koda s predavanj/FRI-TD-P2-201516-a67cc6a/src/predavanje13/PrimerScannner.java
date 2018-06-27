package predavanje13;

import java.util.Scanner;

/**
 *
 * @author tomaz
 */
public class PrimerScannner {
  
  // Z razredom Scanner lahko berem tudi iz pomnilnika. V spodnjem primeru 
  // se "sprehodim" po nizu in z metodo "next()" preberem vseh 5 besed.
  public static void main(String[] args) {
    Scanner sc = new Scanner("To je vhodni niz podatkov");
    while (sc.hasNext())
      System.out.println(sc.next());
  }

}
