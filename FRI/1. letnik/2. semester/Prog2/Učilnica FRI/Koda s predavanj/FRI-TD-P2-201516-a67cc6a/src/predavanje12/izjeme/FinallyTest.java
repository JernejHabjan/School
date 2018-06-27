package predavanje12.izjeme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author tomaz
 */
public class FinallyTest {
  
  
  // Primer pravilnega rokovanja z datotekami. V try bloku datoteko odpremo,
  // v catch bloku obravnavamo morebitne izjeme, ki nastanejo pri odpiranju, 
  // v finally bloku pa poskrbimo za pravilno zapiranje datoteke. 
  public static void main(String[] args) {
    Scanner sc=null;
    
    try {
      sc = new Scanner(new File("ime.txt"));
      while (sc.hasNext())
        System.out.println(sc.next());
      
    } catch (FileNotFoundException eFNF) {
    
      System.out.println("Napaka pri odpiranju datoteke!");
    
    } catch (IOException eIO) {
      System.out.println("Napaka pri branju datoteke!");
    
    } finally {
      
      if (sc != null)
        try {
          sc.close();
        } catch (Exception e1) {
          System.out.println("Napaka pri zapiranju");
        }
    }
  }

}
