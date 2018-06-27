package predavanje14;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Uporaba razreda BufferedInputStream za hitro branje datoteke. Ce namesto 
 * bajt-po-bajt (ukaz read()) beremo blok podatkov (ukaz read(byte[] b)),
 * branje dodatno pohitrimo!
 * 
 * @author tomaz
 */
public class BufferedInputStreamWithArray {

  static final int N = 4096;
  
  public static void main(String[] args) {
    File f = new File("viri/f");
    try (
         FileInputStream fis = new FileInputStream(f);
         BufferedInputStream bis = new BufferedInputStream(fis);
        ) {
      
      int vsota=0;
      int steviloPrebranih;
      
      byte podatki[] = new byte[N];
      
      do {
        steviloPrebranih = bis.read(podatki);
        for (int i = 0; i < steviloPrebranih; i++) {
          vsota = (vsota + podatki[i]) % 1024;
        }
      } while (steviloPrebranih != -1);
      
      System.out.println("Kontrolna vsota: " + vsota);
      
    } catch (FileNotFoundException ex1) {
      System.out.println("Datoteka ne obstaja!");
    } catch (IOException ex2) {
      System.out.println("Napaka: " + ex2.toString());
    }
  }
  
}
