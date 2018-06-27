package predavanje12.izjeme;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author tomaz
 */
public class TryWithResourcesTest {
  
  public static void main(String[] args) {
    
    // koncept try-with-resources: vsi viri (resources), ki jih odpremo v glavi
    // try bloka, se bodo avtomatsko zaprli po koncu izvajanja try bloka; 
    // za te viri nam ni treba klicati metode close()!
    try (Scanner sc = new Scanner(new File("ime.txt"))) {
      
      while (sc.hasNext())
        System.out.println(sc.next());
      
    } catch (FileNotFoundException e) {
    
      System.out.println("Napaka pri odpiranju!");
    
    }  
  }

}
