package predavanje12.zbirke;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Prikaz uporabe slovarja (HashMap)
 * @author tomaz
 */
public class Meseci {

  public static void main(String[] args) {
    Map<String, Integer> meseci = new HashMap();
    
    // dodajanj parov (kljuc, vrednost) v slovar
    meseci.put("Januar", 31);
    meseci.put("Feb", 28);
    meseci.put("Marec", 31);
    meseci.put("April", 30);
    meseci.put("Maj", 31);
    meseci.put("Junij", 30);
    meseci.put("Julij", 31);
    meseci.put("Avgust", 31);
    
    
    System.out.println(meseci.size());
    
    // ponovno dodajanje vrednosti pri ze obstojecem kljucu
    // prepise prejsnjo vrednost kljuca
    meseci.put("Marec", 28);
    System.out.println(meseci.size());
    System.out.println(meseci.get("Marec"));
    
    // odstranjevanje vrednosti pri kljucu "Januar"
    meseci.remove("Januar");
    System.out.println(meseci.size());
    
    // Izpis vseh parov kljuc - vrednost
    Set<String> kljuci = meseci.keySet();
    for (String mesec : kljuci) {
      System.out.printf("%s - %d\n", mesec, meseci.get(mesec));
    }
    
    
  }
}
