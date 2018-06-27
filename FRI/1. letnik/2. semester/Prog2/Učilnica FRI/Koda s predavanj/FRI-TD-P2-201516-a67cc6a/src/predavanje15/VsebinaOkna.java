package predavanje15;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author tomaz
 */
public class VsebinaOkna {
  
  public static void main(String[] args) {
    JFrame okno = new JFrame("Naslov");
    okno.setBounds(100,100,400,400);
    
    // preprecim pomoc razporejevalnika
    okno.getContentPane().setLayout(null); 
    
    JButton gumb = new JButton("OK");
    gumb.setBounds(50,50, 100, 20);
    
    okno.getContentPane().add(gumb);
    
    okno.setVisible(true);
  }

}
