package predavanje5;

import java.util.Scanner;

/**
 * Naloga: preberi oceno in jo izpisi v opisno obliki 
 * (ODLICNO, PRAV DOBRO, ...). 
 * @author tomaz
 */
public class Ocena {
  
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String ocena = sc.nextLine();
    int ocenaS   = Integer.parseInt(ocena);
    
    // Nalogo lahko resimo na dva nacina: z uporabo 
    // zaporedja if-else stavkov...
//    if (ocenaS == 10)
//      System.out.println("ODLICNO");
//    else if (ocenaS == 9)
//      System.out.println("PRAV DOBRO");
//    else if (ocenaS == 8)
//      System.out.println("PRAV DOBRO");
//    else if (ocenaS == 7)
//      System.out.println("DOBRO");
//    else if (ocenaS == 6)
//      System.out.println("ZADOSTNO");    
//    else 
//      System.out.println("DRUGIC VEC SRECE!");
    
    // ... ali (kar je lepse) z uporabo ukaza switch
    switch (ocenaS) {
      case 10: 
        System.out.println("ODLICNO");
        // vsaka veja ukaza switch se mora praviloma koncati 
        // z "break", sicer se tok programa nadaljuje na 
        // naslednji veji (v tem primeru pri oceno 9)
        break; 
      case 9 : 
        System.out.println("PRAV DOBRO");
        break;        
      case 8 : 
        System.out.println("PRAV DOBRO");
        break;
      case 7 : 
        System.out.println("DOBRO");
        break;
      case 6 : 
        System.out.println("ZADOSTNO");
        break;        
      default: 
        System.out.println("DRUGIC VEC SRECE!");
    }   
  }
  

}
