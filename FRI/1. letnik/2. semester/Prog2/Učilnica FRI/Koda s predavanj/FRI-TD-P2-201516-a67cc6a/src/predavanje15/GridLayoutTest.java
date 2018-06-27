package predavanje15;

import java.awt.GridLayout;
import javax.swing.*;

/**
 *
 * @author tomaz
 */
public class GridLayoutTest {
  
  public static void main(String[] args) {
    JFrame okno = new JFrame("Naslov");
    okno.setBounds(100,100,400,400);
    
    // preprecim pomoc razporejevalnika
    okno.getContentPane().setLayout(new GridLayout(3, 3)); 
    
    for (int i = 1; i <= 9; i++) {
      JButton gumb = new JButton(Integer.toString(i));
      okno.getContentPane().add(gumb);
    }
    
    okno.setVisible(true);
  }

}
