package predavanje12.zbirke;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Program je popolnoma enak programu Dnevi.java, edina razlika je v uporabljenih 
 * podatkovnih strukturah; tam smo uporabili mnozice (HashSet) tu seznam (ArrayList).
 * 
 * Uporabljam iste metode v istem vrstnem redu, a je zaradi razlicne logike 
 * delovanja mnozice in seznama rezultat na koncu razlicen. V seznamu se
 * pojavijo duplikati, v mnozici pa tega ni bilo.
 * 
 * @author tomaz
 */
public class DneviArrayList {
  public static void main(String[] args) {    
    ArrayList<String> dnevi = new ArrayList();
    
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
