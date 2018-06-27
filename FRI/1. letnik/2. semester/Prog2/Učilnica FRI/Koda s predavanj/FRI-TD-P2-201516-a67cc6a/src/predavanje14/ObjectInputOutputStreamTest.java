package predavanje14;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import predavanje13.Oseba;

/**
 * Branje in pisanje objektov. Objekt, ki ga zelimo zapisati v datoteko,
 * (v tem primeru gre za objekt razreda Oseba) mora implementirati vmesnik 
 * Serializable.
 *
 * @author tomaz
 */
public class ObjectInputOutputStreamTest {

  
  static void zapisi() {
    Oseba o1 = new Oseba("Miha", "NovakXYZ", 50);
    Oseba o2 = new Oseba("Micka", "Juric", 20);
    Oseba o3 = new Oseba("Blaz", "Rogelj", 19);
    
    ObjectOutputStream oos=null;
    try {
      FileOutputStream fos = new FileOutputStream("viri/osebe.dat");
      oos = new ObjectOutputStream(fos);
  
      oos.writeObject(o1);
      oos.writeObject(o2);
      oos.writeObject(o3);
      
    } catch (IOException ex1) {
      System.out.println("Napaka: " + ex1.toString());
    } finally {
      if (oos != null)
      try {
        oos.close();
      } catch (Exception e) {
        System.out.println("Napaka pri zapiranju!");
      }
    }
  }
  
  static void preberi() {
    try (FileInputStream fis = new FileInputStream("viri/osebe.dat");
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis)) {
      
      Oseba o1 = (Oseba) ois.readObject();
      Oseba o2 = (Oseba) ois.readObject();
      Oseba o3 = (Oseba) ois.readObject();
      
      System.out.println(o1);
      System.out.println(o2);
      System.out.println(o3);
      
    } catch (Exception e) {
      
    }
  }
  
  public static void main(String[] args) {
    //zapisi();
    preberi();
  }
  
}
