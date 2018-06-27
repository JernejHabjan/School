package predavanje7;

import java.io.File;
import java.util.Scanner;

/**
 * Demonstracija uporabe metode split.
 * @author tomaz
 */
public class IzpisStudentov {
  public static void main(String[] args) throws Exception  {
    // Iz datoteke "studenti.txt" preberi podatke, vrstico po 
    // vrstici. Posamezna vrstica je oblike
    // ID:Ime:Priimek:ocena:visina:barva_las
    // Primer:
    // 63000006:Stefka:Drobtina:6:178:BLOND
    
    Scanner sc = new Scanner(new File("viri/studenti.txt")); 
    
    while(sc.hasNextLine()) {
      String vrstica = sc.nextLine();
      
      // z metodo split vrstico oblike 
      // ID:Ime:Priimek:ocena:visina:barva_las
      // razbijemo na tabelo nizov (prvi niz je "ID",
      // drugi "Ime", tretji "Priimek", ...)
      String deli[] = vrstica.split(":");
      
      double visina = Double.parseDouble(deli[4]);
      if (visina > 180)      
        System.out.println(deli[1]);
      
      System.out.println(vrstica);
    }
    
    sc.close();
  }
}
