
import java.awt.Color;

/**
 * V Slikarju izdelamo in narisemo nekaj likov, nato uporabniku omogocimo, da 
 * s pomocjo tipkovnice like premika in jim spreminja velikost
 * @author tomaz
 */
public class Slikar {
  static int dX = 5;
  static int dY = 5;
  
  public static void main(String[] args) {
    StdDraw.setXscale(0,1000);
    StdDraw.setYscale(0,1000);
    
    Lik liki[] = new Lik[4];
    liki[0] = new Pravokotnik(50, 80, 500, 500, Color.blue);
    liki[1] = new Kvadrat(30, 200,200, Color.green);
    liki[2] = new Krog(100, 510, 70, Color.red);

    // pri tem liku s pomocjo anonimnega notranjega razreda
    // "popravimo" metodo narisiSe(); 
    liki[3] = new Krog(100, 210, 70, Color.red){
      @Override
      void narisiSe() {      
        StdDraw.circle(getX(), getY(), 120);
      }      
    };

    
    while (true) {
      StdDraw.clear();
      for (int i = 0; i < liki.length; i++) {
        liki[i].narisiSe();
      }
      
      // pocakam, da uporabnik pritisne eno tipko
      while (!StdDraw.hasNextKeyTyped());

      char tipka = StdDraw.nextKeyTyped();
      switch (tipka) {        
        case 'w': // w ... gor
          for (int i = 0; i < liki.length; i++) {
           liki[i].premakniSe(0, dY);
          }
          break;
        case 's': // s... dol
          for (int i = 0; i < liki.length; i++) {
           liki[i].premakniSe(0, -dY);
          }
          break;          

        case 'a': // a ... levo
          for (int i = 0; i < liki.length; i++) {
           liki[i].premakniSe(-dX, 0);
          }
          break;
        case 'd': // d ... desno
          for (int i = 0; i < liki.length; i++) {
           liki[i].premakniSe(dX, 0);
          }
          break;                              
      }
    }
  }
}
