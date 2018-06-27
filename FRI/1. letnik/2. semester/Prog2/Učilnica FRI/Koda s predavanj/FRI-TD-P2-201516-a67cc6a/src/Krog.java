
import java.awt.Color;

/**
 * Implementacija Lika. Poleg x, y in barva krog pozna se radij (r).
 * @author tomaz
 */
public class Krog extends Lik{

  int r; // radij kroga

  public Krog(int r, int x, int y, Color barva) {
    super(x, y, barva);
    this.r = r;
  }

  @Override
  void narisiSe() {
    StdDraw.setPenColor(getBarva());
    StdDraw.filledCircle(getX(), getY(), r);
  }
  
}
