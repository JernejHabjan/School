package predavanje5;

/**
 * Uporaba metod kodiraj() in odkodiraj() razreda Kodiranje
 * @author tomaz
 */
public class TestKodiranje {
  
  public static void main(String[] args) {
    String niz = "PONEDELJEK";
    
    // niz zakodiram ...
    String kodirano   = Kodiranje.kodiraj(niz);
    // ... zakodiran niz nato odkodiram
    String odkodirano = Kodiranje.odkodiraj(kodirano);
    
    // kodiran niz ima "cudno" obliko (SRQHGHOMHN)
    System.out.println(kodirano);
    // odkodiran niz se mora ujemati z originalnim (PONEDELJEK)
    System.out.println(odkodirano);
  }
}
