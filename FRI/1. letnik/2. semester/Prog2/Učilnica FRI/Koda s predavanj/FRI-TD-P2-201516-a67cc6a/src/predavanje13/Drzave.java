package predavanje13;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author tomaz
 */
public class Drzave {
  
  HashMap<String, Drzava> drzave;
  
  void preberi(String imeDatoteke) {
    drzave = new HashMap();
    
    try (Scanner sc=new Scanner(new File(imeDatoteke));) {
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        String deliVrstice[] = line.split(":");
                        
        int prebivalci;
        try {
          prebivalci = Integer.parseInt(deliVrstice[2]);
        } catch (Exception e) {
          prebivalci=0;
        }
        
        
        Drzava d = new Drzava(deliVrstice[0], deliVrstice[1], prebivalci);
        drzave.put(deliVrstice[0], d);
      }
      
    } catch (Exception e) {
      System.out.println(e.toString());
    }
  }
  
  void izpisi() {
    Set<String> kljuci = drzave.keySet();
    Iterator<String> it = kljuci.iterator();
    while (it.hasNext()) {
      String kljuc = it.next();
      Drzava d = drzave.get(kljuc);
      
      String glavnoMesto = drzave.get(kljuc).getGlavnoMesto();
      
      System.out.println(d.getGlavnoMesto());
    }
  }
  
  public static void main(String[] args) {
    Drzave drzaveOI = new Drzave();    
    drzaveOI.preberi("viri/drzave.txt");
      
    drzaveOI.izpisi();
    
  }

}
