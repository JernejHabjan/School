package predavanje8; 

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author tomaz
 */
public class Vaje6 {

  public static void odkodiraj(String kodirnaKnjiga, String koda) throws Exception {
    String besede[] = new String[1000];
    int steviloBesed = 0;

    Scanner sc = new Scanner(new File(kodirnaKnjiga));
    while (sc.hasNext()) {
      besede[steviloBesed++] = sc.next();
    }
    sc.close();

    int steviloNicel = 0;

    sc = new Scanner(new File(koda));
    while (sc.hasNextInt()) {
      int b = sc.nextInt();
      if (b == 0) {
        steviloNicel++;
      } else {
        if (steviloNicel == 2) {
          System.out.print(" ");
        } else if (steviloNicel == 3) {
          System.out.println();
        }

        if (sc.hasNextInt()) {
          int c = sc.nextInt();

          if (b <= steviloBesed && c <= besede[b - 1].length()) {
            char crka = Character.toLowerCase(besede[b - 1].charAt(c - 1));
            if (steviloNicel == 1) {
              crka = Character.toUpperCase(crka);
            }
            System.out.print(crka);
          } else {
            System.out.print("?");
          }
        } else {
          System.out.print("?");
        }

        steviloNicel = 0;
      }
    }
    sc.close();
    System.out.println("");
  }
  
  static void kodiraj(String kodirnaKnjiga, String niz) throws Exception {
    Scanner sc = new Scanner(new File("viri/pesem.txt"));
    
//    Besedo po besedi lahko berem s pomocjo metode next();
//    vsako prebrano besedo shranim v tabelo
//
//    String [] besede = new String[1000];
//    int stBesed=0;
//    while (sc.hasNext()) {
//      besede[stBesed++] = sc.next();
//    }
//    sc.close();

    // Druga moznost za branje vseh besed pa je, da preberem 
    // datoteko z enim samim ukazom, nato pa prebrano
    // vsebino splitam na besede
    sc.useDelimiter("\\Z");
    String vsebinaDatoteke = sc.next();
    sc.close();    
    String besede[] = vsebinaDatoteke.split("[, \n]+");
    
    // Ce zelim besede spreminjati, namesto razreda String
    // potrebujem razred StringBuffer.
    // V ta namen izdelam se eno tabelo StringBufferjev
    // enake velikosti, kot je tabela Stringov
    StringBuffer besedeB[] = new StringBuffer[besede.length];
    for (int i = 0; i < besede.length; i++) {
      besedeB[i] = new StringBuffer(besede[i]);
    }
   
    for (int i = 0; i < niz.length(); i++) {
      char crka = niz.charAt(i);
      if (crka==' ') {
        System.out.print("0 0 ");
        continue;
      }
      if (Character.isUpperCase(crka))
        System.out.print("0 ");
      
      boolean nasel = false;
      crka = Character.toLowerCase(crka);
      for (int b = 0; b < besedeB.length; b++) {
        for (int c = 0; c < besedeB[b].length(); c++) {
          if (besedeB[b].charAt(c) == crka) {            
            System.out.printf("%d %d ", b+1, c+1);
            besedeB[b].setCharAt(c, ' ');
            nasel=true;
            break;
          }
        }
        if (nasel)
          break;
      }
      if (!nasel)
        System.out.print("99 99 ");
    }
    
  }
  
  public static void main(String[] args) throws Exception {
    //odkodiraj("viri/pesem.txt", "viri/kodirano.txt");
    kodiraj("viri/pesem.txt", "Pomlad pomlad");
  }
}
