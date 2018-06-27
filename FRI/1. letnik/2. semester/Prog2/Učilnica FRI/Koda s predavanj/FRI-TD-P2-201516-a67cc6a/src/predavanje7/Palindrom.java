package predavanje7;

/**
 * Napisi tri razlicne metode za ugotavljanje ali je podani 
 * niz palindrom. Metode naj delajo a) iterativno, b) rekurzivno 
 * in c) z uporabo razreda StringBuffer in metode reverse().
 * @author tomaz
 */
public class Palindrom {

  // Iterativno ugotavljanje, ali je dani niz palindrom: 
  // sprehodim se po vseh znakih od zacetka do sredina in
  // primerjam "istolezne" znake iz zacetka in konca
  // (to je znake, ki so enako oddaljeni od zacetka in od konca)
  static boolean jePalindromI(String niz) {
    niz = niz.replaceAll(" ", "");
    
    for (int i = 0; i < niz.length() / 2; i++) { 
      if (niz.charAt(i) != niz.charAt(niz.length() - i - 1))
        return false;
    }
    return true;
  }
  
  // Rekurzivno ugotavljanje, ali je dani niz palindrom: 
  // uporabim naslednjo "definicijo" palindromskosti: dani
  // niz je palindrom, ce sta prva in zadnja crka enaki IN
  // ce je preostanek (niz brez prva in zadnje crke) 
  // palindrom.
  static boolean jePalindromR(String niz) {
    // obvezen izstopni pogoj: rekurzijo koncam
    // pri dovolj kratkih nizih
    if (niz.length() <= 1)
      return true;
         
            // prva in zadnja crka se ujemama IN ...
    return (niz.charAt(0) == niz.charAt(niz.length()-1)) &&
            // sredisnki del je palindrom
            jePalindromR(niz.substring(1, niz.length()-1));
  }
  
  // Z uporabo raazreda StringBuffer (obrnemo niz in ga primerjamo z originalnim)
  static boolean jePalindromSB(String niz) {
    // obrnemo niz ...
    StringBuffer obrnjenNiz = new StringBuffer(niz).reverse();    
    
    // .. in ga primerjamo z originalnim nizom
    return niz.toString().equals(obrnjenNiz.toString());
  }

  
  public static void main(String[] args) {
    String beseda = "CEPEC";
    
//    Spodnja (zakomentirana) koda naredi ISTO kot koda v vrstici,
//    ki sled. Zakomentirana koda je daljsa, a morda bolj razumljiva. 
//    if (jePalindromR(beseda))
//      System.out.printf("Beseda %s JE palindrom\n", beseda);
//    else
//      System.out.printf("Beseda %s NI palindrom\n", beseda);
    
    System.out.printf("Beseda %s %s palindrom\n", beseda,
            jePalindromR(beseda) ? "JE" : "NI"
    );
  }
  
}
