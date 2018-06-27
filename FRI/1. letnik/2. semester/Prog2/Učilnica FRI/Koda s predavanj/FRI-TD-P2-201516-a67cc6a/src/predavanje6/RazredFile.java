package predavanje6;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Razred je namenjen demonstraciji metod, ki jih ponuja razred
 * java.io.File in jih boste potrebovali pri resevanjeu 6. domace 
 * naloge.
 * 
 * @author tomaz
 */
public class RazredFile {
  
  public static void main(String[] args) throws Exception {
    File f = new File("viri/CHF2015.txt");  // tekstovna datoteka
    File f2 = new File("viri");             // mapa
    File f3 = new File("viri/graf.png");    // binarna datoteka

   
    // uporaba metod exists(), isDirectory(), length() in lastModified()
    System.out.println(f.exists());
    System.out.println(f.getAbsolutePath());
    System.out.println(f.isDirectory());
    System.out.println(f2.isDirectory());
    System.out.println(f.length());
    System.out.println(f.lastModified());

    // ce zelimo datum, ki ga dobimo z metodo lastModified() pretvoriti
    // v cloveku lepo berljivo obliko, potrebujemo razreda
    // Date in SimpleDateFormat
    Date datumSpremembe = new Date(f.lastModified());
    System.out.println(datumSpremembe);    
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd. MM. yyyy hh:mm:ss");
    System.out.println(dateFormat.format(datumSpremembe));
    
    // branje datoteke znak-po-znaku
    Scanner sc = new Scanner(f);
    
    // nastavim locilo med zetoni - "" (prazen niz)
    // po tej nastavitvi, bo zeton ena crka datoteke
    sc.useDelimiter(""); 
    
    while (sc.hasNext()) {
      // metoda next(), ki bere "zeton-po-zetonu" bo sedaj vracala
      // crko-po-crki
      String prebrano = sc.next();
      System.out.println(prebrano);
    }
    sc.close();
    
    
    // Branje binarne datoteke s scannerjem ni mogoce - scanner je
    // namenjen le branju tekstovnih datotek
    System.out.println("Binarna datoteka f3:");
    sc = new Scanner(f3);
    // pri binarnih datotekah metoda hasNext() vrdno vrne false
    while (sc.hasNext()) {
      String prebrano = sc.next();
      System.out.println(prebrano);
    }
    sc.close();
    
    // Seznam vseh datotek nekega direktorija dobim z metodo list()
    System.out.println("Vse datoteke:");
    String vseDatoteke[] = f2.list();
    for (int i = 0; i < vseDatoteke.length; i++) {
      System.out.println(vseDatoteke[i]);
    }    
  }

}
