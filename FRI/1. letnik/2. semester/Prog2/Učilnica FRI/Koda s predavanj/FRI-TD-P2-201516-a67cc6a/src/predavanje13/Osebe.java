package predavanje13;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author tomaz
 */
public class Osebe {
  
  public static void main(String[] args) {
    Oseba o1 = new Oseba("Miha", "NovakXYZ", 50);
    Oseba o2 = new Oseba("Micka", "Juric", 20);
    Oseba o3 = new Oseba("Blaz", "Rogelj", 19);
    
//    Oseba osebe[] = new Oseba[3];
//    osebe[0] = o1;
//    osebe[1] = o2;
//    osebe[2] = o3;
    
    // polnjenje tabele na hitrejsi nacin:
    Oseba osebe[] = {o1, o2, o3};
    
    Arrays.sort(osebe);     
    for(Oseba o : osebe) {
      System.out.println(o);
    }
    
    Arrays.sort(osebe, new Comparator<Oseba>() {
      @Override
      public int compare(Oseba o1, Oseba o2) {        
        return o1.priimek.length()-o2.priimek.length();
      }      
    });
    System.out.println("Urejeno po dolzini priimka");
    for(Oseba o : osebe) {
      System.out.println(o);
    }
    
    Arrays.sort(osebe, new Comparator<Oseba>() {
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
