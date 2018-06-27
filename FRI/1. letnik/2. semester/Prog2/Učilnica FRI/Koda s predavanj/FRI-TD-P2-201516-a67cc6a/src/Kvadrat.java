
import java.awt.Color;



/**
 * Implementacija Lika. Poleg x, y in barva kvadrat pozna se velikost (a).
 * @author tomaz
 */
public class Kvadrat extends Lik {

  int a; // velikost kvadrata

  public Kvadrat(int a, int x, int y, Color barva) {
    super(x, y, barva);
    this.a = a;
  }
  
  @Override
  void narisiSe() {
    StdDraw.setPenColor(getBarva());
    StdDraw.filledRectangle(getX(), getY(), a, a);
  }  
}
