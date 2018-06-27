package predavanje13;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Z razredom DataOutputStream pisemo podatke (int, short, byte, double, ...)
 * v binarne datoteke.
 *
 * @author tomaz
 */
public class DataOutputStreamTest {

  public static void main(String[] args) {
    try {
      FileOutputStream fos = new FileOutputStream(new File("viri/podatki.bin"));      
      DataOutputStream dos = new DataOutputStream(fos);
      
      dos.writeByte(30);
      dos.writeInt(30);
      
      dos.writeBoolean(true);
      dos.writeBoolean(false);
      
      
      dos.close();
    } catch (Exception ex) {
      System.out.println(ex.toString());
    }
  }
}
