package predavanje13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Demonstracija urejanja seznama (ArrayList) program dela povsem enako kot
 * program Osebe.java, razlika je v "polnjenju strukture" (osede.add(...)) in 
 * pa v nacinu urejanja: pri Tabeli uporabimo Arrays.sort, pri seznamu pa
 * Collections.sort. 
 * 
 * @author tomaz
 */
public class SeznamOseb {
  
  public static void main(String[] args) {
    Oseba o1 = new Oseba("Miha", "NovakXYZ", 50);
    Oseba o2 = new Oseba("Micka", "Juric", 20);
    Oseba o3 = new Oseba("Blaz", "Rogelj", 19);
    
    ArrayList<Oseba> osebe = new ArrayList();
    osebe.add(o1);
    osebe.add(o2);
    osebe.add(o3);
    
    Collections.sort(osebe);     
    for(Oseba o : osebe) {
      System.out.println(o);
    }
    
    Collections.sort(osebe, new Comparator<Oseba>() {
      @Override
      public int compare(Oseba o1, Oseba o2) {        
        return o1.priimek.length()-o2.priimek.length();
      }      
    });
    System.out.println("Urejeno po dolzini priimka");
    for(Oseba o : osebe) {
      System.out.println(o);
    }
    
    Collections.sort(osebe, new Comparator<Oseba>() {
      @Override
      public int compare(Oseba o1, Oseba o2) {        
        return o1.priimek.compareTo(o2.priimek);
      }      
    });
    System.out.println("Urejeno po abecedi priimka");
    for(Oseba o : osebe) {
      System.out.println(o);
    }
    
    
  }

}
