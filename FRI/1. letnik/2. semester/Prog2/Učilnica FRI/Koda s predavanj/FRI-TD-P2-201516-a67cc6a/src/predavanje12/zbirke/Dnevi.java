package predavanje12.zbirke;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Prikaz uporabe mnozice
 * @author tomaz
 */
public class Dnevi {
  
  public static void main(String[] args) {
    // ustvarim mnozico
    Set<String> dnevi = new HashSet();
    
    // v mnozico dam elemente
    dnevi.add("PON");
    dnevi.add("TOR");
    dnevi.add("SRE");
    dnevi.add("CET");
    dnevi.add("PET");
    dnevi.add("SO");
    dnevi.add("NE");
    
    System.out.println(dnevi.size());
    
    // v mnozico dam iste elemente; ker so ze v mnozici,
    // se vsebina mnozice ne bo spremenila
    dnevi.add("PON");
    dnevi.add("TOR");
    dnevi.add("SRE");
    System.out.println(dnevi.size());
    
    // iz mnozice odstranim element "PON"
    dnevi.remove("PON");
    System.out.println(dnevi.size());
    
    
    // "sprehod" cez elemente mnozice in izpis na zaslon
    System.out.println("Izpis s pomocjo iteratorja");
    Iterator it = dnevi.iterator();
    while (it.hasNext())
      System.out.println(it.next());
    
    // 2. moznost za sprehod: for each zanka
    System.out.println("Izpis s for-each zanko");
    for (String dan : dnevi) {
      System.out.println(dan);
    }    
  }

}
