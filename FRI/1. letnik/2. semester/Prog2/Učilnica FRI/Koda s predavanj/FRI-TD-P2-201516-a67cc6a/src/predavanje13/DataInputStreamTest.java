package predavanje13;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Z razredom DataInputStream beremo podatke (int, short, byte, double, ...)
 * iz binarnih datotek.
 * 
 * @author tomaz
 */
public class DataInputStreamTest {
  public static void main(String[] args) {
    try {
      FileInputStream fis = new FileInputStream(new File("viri/podatki.bin"));
      DataInputStream dis = new DataInputStream(fis);
      
      short v = dis.readShort();
      System.out.println(v);
              
    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
    
    
  }
}
