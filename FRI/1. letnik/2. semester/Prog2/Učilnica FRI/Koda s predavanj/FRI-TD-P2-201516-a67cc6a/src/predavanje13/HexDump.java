package predavanje13;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Program prebere datoteko bajt-po-bajtu in izpise na podoben nacin
 * kot program hexdump.
 * @author tomaz
 */
public class HexDump {
  
  public static void main(String[] args) {
    String imeDatoteke = args[0];
    File f = new File(imeDatoteke);
    
    try {
      FileInputStream fis = new FileInputStream(f);

      BufferedInputStream bis = new BufferedInputStream(fis);
      
      String znakiDesno="";
      int koliko = 0;
      
      int bajt = bis.read();
      while (bajt != -1) {
        System.out.printf("%x ", bajt);
        if (bajt >=32 && bajt <= 127)
          znakiDesno += String.format("%c", bajt);
        else 
          znakiDesno += ".";
        
        koliko++;
        
        if (koliko==16) {
          System.out.println("  " + znakiDesno);
          znakiDesno="";
          koliko=0;
        }
        
        bajt=bis.read();
      }
      
      for(; koliko <16; koliko++)
        System.out.printf("   ");
      System.out.println("  " + znakiDesno);

      
      bis.close();
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    
  }

}
