import java.awt.Color;
import java.util.Random;

/**
 * Razred za demonstracijo uporabe knjiznice stdlib.jar
 * @author tomaz
 */
public class Risanje {  
  
  // risanje trikotnika
  static void test1() {
    StdDraw.line(0,0,50,50);
    StdDraw.line(50,50, 100, 0);
    StdDraw.line(0,0, 100, 0);
  }
  
  // risanje kvadratov in krogov s centrom v (0,0)
  static void test2() {
    for (int i = 0; i < 30; i++) {
      Random rnd = new Random();
      // barvo likov dolocim nakljucno
      Color c = new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
      StdDraw.setPenColor(c);
      StdDraw.square(0, 0, 3*i);
      StdDraw.circle(0, 0, 3*i);
    }  
  }

  // risanje preproste spirale: vrtim se v krogu (na vsakem koraku se
  // zavrtim za kot deltaFi) in vecam razdaljo od centra (na vsakem 
  // koraku se od centra oddaljim za dodatnih deltaR).
  static void test3() {
    double r  = 1;
    double fi = 0;
    double deltaR = 1.02;
    double deltaFi = 10;
    
    double pX = 0;
    double pY = 0;
    for (int i = 0; i < 1000; i++) {
      double x = r * Math.cos(2*Math.PI * fi / 360);
      double y = r * Math.sin(2*Math.PI * fi / 360);
      
      StdDraw.line(pX, pY, x, y);
      pX = x;
      pY = y;
      
      r = r + deltaR;
      fi = fi + deltaFi;
    }
    
  }

  public static void main(String[] args) {
    StdDraw.setScale(-100, 100);
    
    //test1();
    //test2();
    test3();    
  }
  
}
