package predavanje11.vmesnik;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author tomaz
 */
public class Sinus implements Funkcija {

  @Override
  public double vrednost(double x) {
    return Math.sin(x);
  }
  
  @Override
  public double odvod(double x) {
    return Math.cos(x);
  }

  
}
