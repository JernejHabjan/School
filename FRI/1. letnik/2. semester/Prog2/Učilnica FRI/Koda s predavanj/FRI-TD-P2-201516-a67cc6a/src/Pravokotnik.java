
import java.awt.Color;

/**
 * * Implementacija Lika. Poleg x, y in barva pravokotnik pozna se velikost (a, b).
 * @author tomaz
 */
public class Pravokotnik  extends Lik {

  private int a, b; // sirina in visina pravokotnika

  public Pravokotnik(int a, int b, int x, int y, Color barva) {
    super(x, y, barva);
    this.a = a;
    this.b = b;
  }

  @Override
  void narisiSe() {
    StdDraw.setPenColor(getBarva());
    StdDraw.filledRectangle(getX(), getY(), a, b);
  }
  

  public int getA() {
    return a;
  }

  public int getB() {
    return b;
  }
  
  
  
}
