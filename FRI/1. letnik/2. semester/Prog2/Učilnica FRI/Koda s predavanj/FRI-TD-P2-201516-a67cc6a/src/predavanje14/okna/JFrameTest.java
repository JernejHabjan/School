package predavanje14.okna;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Primer programa, ki uporablja JFrame okno.
 * 
 * @author tomaz
 */
public class JFrameTest {
  
  public static void main(String[] args) {
    JFrame okno = new JFrame();
    
    okno.setBounds(100,100, 200, 200);
    
    okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    okno.setTitle("Prvi program");
    
    okno.setResizable(true);

    JPanel panel = new JPanel();
    //panel.setBackground(Color.red);
    panel.setBorder(BorderFactory.createTitledBorder("To je moj okvir"));
    okno.add(panel);
    
    okno.setVisible(true);
  }

}
