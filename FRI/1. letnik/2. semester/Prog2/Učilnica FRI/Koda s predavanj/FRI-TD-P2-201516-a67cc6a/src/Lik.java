

import java.awt.Color;

/**
 * Abstraktni razred, ki predstavlja en lik na risalni povrsini. Metoda
 * narisiSe() je abstraktna, saj ne vemo, kako se bo posamezen lik 
 * (kvadrat, krog, ...) narisal, metoda premakniSe() pa ni abstraktna,
 * saj se vsi liki premaknejo na enak nacin (x+=dx, y+=dy)
 * @author tomaz
 */
abstract public class Lik {
  private int x;
  private int y;
  private Color barva;

  public Lik(int x, int y, Color barva) {
    this.x = x;
    this.y = y;
    this.barva = barva;
  }
  
  abstract void narisiSe();
  
  void premakniSe(int dx, int dy) {
    x = x + dx;
    y = y + dy;
  }
  
  // abstract void povecajSe(int dX, int dY);

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Color getBarva() {
    return barva;
  }
  
    
}
