package predavanje7;

/**
 * Uporabi razred StringBuffer za demonstracijo spreminjajocega niza.
 * @author tomaz
 */
public class StringBufferTest {
  
  public static void main(String[] args) {
    // niz, ki je zapisan v StringBufferju lahko spreminjamo
    StringBuffer sb = new StringBuffer("pomlad je X");
    
    sb.replace(10,11, "tu!"); // crko X zamenjam z "tu!"
    sb.replace(0, 1, "P");    // crko p spremenim v veliko crko P
    sb.append(" Juhuhu!");    // na koncu dodam nov niz
    
    System.out.println(sb);
  }

}
