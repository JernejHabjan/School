package predavanje13;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Demonstracija delovanja mnozice. Ko dodajamo elemente v mnozico,
 * se za primerjavo "enakosti" uporabi metoda "compareTo", ki je 
 * definirana v razredu Oseba. Ker imata "Blaz Rogelj" in "Joze Mraz" 
 * isto starost (in sta zato skladno z razultatom metode compareTo
 * enaka), se "Joze Mraz" v mnozico ne bo dodal.
 * 
 * 
 * @author tomaz
 */
public class MnozicaOseb {

  public static void main(String[] args) {
    Oseba o1 = new Oseba("Miha", "Novak", 50);
    Oseba o2 = new Oseba("Micka", "Juric", 20);
    Oseba o3 = new Oseba("Blaz", "Rogelj", 19);

    Oseba o4 = new Oseba("Blaz", "Rogelj", 19);
    Oseba o5 = new Oseba("Joze", "Mraz", 19);
    
    Set<Oseba> osebe = new TreeSet();
    osebe.add(o1);osebe.add(o2);osebe.add(o3);
    osebe.add(o4);
    osebe.add(o5);
    
    for (Oseba oseba : osebe) {
      System.out.println(oseba);
    }
  }
}
