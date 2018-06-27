package predavanje14.okna;

import javax.swing.JWindow;

/**
 *
 * @author tomaz
 */
public class JWindowTest {
  
  public static void main(String[] args) {
    JWindow okno = new JWindow();
    
    okno.setBounds(100,100, 200, 200);
    
    okno.setVisible(true);
  }

}
