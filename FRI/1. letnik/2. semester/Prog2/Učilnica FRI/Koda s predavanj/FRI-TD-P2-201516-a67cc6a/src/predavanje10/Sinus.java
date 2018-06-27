package predavanje10;

import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;

/**
 * Funkcija sinus. Vrednost funkcije je enaka Math.sin(x), 
 * odvod pa Math.cos(x).
 */
public class Sinus extends Funkcija  {

  @Override
  double vrednost(double x) {
    return Math.sin(x);
  }

  @Override
  double odvod(double x) {
    return Math.cos(x);
  }
  
}
