package predavanje14;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Uporaba razreda BufferedInputStream za hitro branje datoteke
 * @author tomaz
 */
public class BufferedInputStreamTest {

  public static void main(String[] args) {
    File f = new File("viri/e");
    try (
         FileInputStream fis = new FileInputStream(f);
         BufferedInputStream bis = new BufferedInputStream(fis);
        ) {
      
      int vsota=0;
      int c;
      
      do {
        c = bis.read();
        if (c != -1)
          vsota = (vsota + c) % 1024;        
      } while (c != -1);
      
      System.out.println("Kontrolna vsota: " + vsota);
      
    } catch (FileNotFoundException ex1) {
      System.out.println("Datoteka ne obstaja!");
    } catch (IOException ex2) {
      System.out.println("Napaka: " + ex2.toString());
    }
  }
  
}
