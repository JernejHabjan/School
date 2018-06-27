package predavanje12;

import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @author tomaz
 */
public class BranjePPM {
  
  /**
   * Primer branja binarne datoteke s pomocjo razreda FileInputStream. 
   */
  public static void main(String[] args) throws Exception {
    File dat = new File("viri/sah9x9.ppm");

    // ustvarimo ravno prav veliko tabelo bajtov
    byte vsebina[] = new byte[(int) dat.length()];
    
    // odpremo datoteko ...
    FileInputStream fis = new FileInputStream(dat);
    // ... in z enim ukazom preberemo vse bajte iz datoteke v tabelo
    fis.read(vsebina);
    fis.close();
    
    // i-ti bajt datoteke je zapisan na i-tem mestu v tabeli
    System.out.printf("%c%c\n", vsebina[0], vsebina[1]);
  }

}
