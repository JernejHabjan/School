package predavanje14.okna;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * Primer programa, ki uporablja JDialog. Ker je JDialog samo pomozno okno,
 * mora program vsebovati se glavno okno tipa JFrame.
 * @author tomaz
 */
public class JDialogTest {
  
  public static void main(String[] args) {
    JFrame okno = new JFrame();
    
    okno.setBounds(100,100, 200, 200);
    
    okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    okno.setTitle("Prvi program");
        
    JDialog dialog = new JDialog(okno, false);
    dialog.setTitle("Moj dialog");
    dialog.setBounds(500, 500, 300, 300);
    
    okno.setVisible(true);
    dialog.setVisible(true);
  }

}
