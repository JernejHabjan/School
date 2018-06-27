package predavanje12.izjeme;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author tomaz
 */
public class ObravnavanjeIzjem {

  // v metodi odpremo datoteko in pri tem lahko pride do izjeme; morebitno izjemo 
  // obravnavamo v metodi v try-catch bloku
  static void odpri() {
    try {
      Scanner sc = new Scanner(new File("ime.txt"));
    } catch (FileNotFoundException e) {
      System.out.println("Napaka!");
    }
  }

  // v metodi odpremo datoteko in pri tem lahko pride do izjeme; morebitne izjeme 
  // ne obravnavamo, pac pa jo vrzemo naprej (kar napovemo z "throws" v glavi metode
  static void odpriSeEnkrat() throws FileNotFoundException {
     Scanner sc = new Scanner(new File("ime.txt"));
  }
  
  public static void main(String[] args)  {
    // metodo odpri() lahko poklicemo, pri tem pa nam ni treba paziti
    // na morebitno izjemo, saj je za izjemo poskrbljeno ze v try-catch
    // bloku v metodi
    odpri();
    
    
    // metode odpriSeEnkrat() pa ne moremo klicati brez bloka try-catch, saj
    // je to izjemo metoda odpriSeEnkrat() vrgla naprej (throws v glavi metode)
    try {
      odpriSeEnkrat();
    } catch (Exception e) {
      System.out.println("Napaka2!");
    }
  }
  
}



//JVM:
//
//   try {
//      ObravnavanjeIzjem.main(args);
//   } catch (Exception e) {
//     e.printStackTrace();
//   }
//

