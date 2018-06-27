package predavanje11;

import java.util.Scanner;

public class OblikovanjeBesedila {
  
  static void beriInOblikuj(OblikovalecBesedila oblikovalec) {
    Scanner sc = new Scanner(System.in);
    
    while(true) {
      String besedilo = sc.nextLine();
      System.out.println(oblikovalec.oblikujBesedilo(besedilo));
    }
  }
  
  public static void main(String[] args) {
    // OblikovalecBesedila oblikovalec = new OblikujVVelikeCrke();
    // beriInOblikuj(oblikovalec);    
    
    beriInOblikuj(new OblikovalecBesedila() {
      @Override
      public String oblikujBesedilo(String besedilo) {
        return besedilo.toLowerCase();
      }      
    });
  }

}
