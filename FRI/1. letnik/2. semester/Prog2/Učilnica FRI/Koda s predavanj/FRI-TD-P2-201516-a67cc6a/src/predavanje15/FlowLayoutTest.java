package predavanje15;

import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author tomaz
 */
public class FlowLayoutTest {
  public static void main(String[] args) {
    JFrame okno = new JFrame("Naslov");
    okno.setBounds(100,100,400,400);
    
    JButton g1 = new JButton("Prvi");
    JButton g2 = new JButton("Drugi");
    JButton g3 = new JButton("Tretji");
    JButton g4 = new JButton("Cetrti");
    JButton g5 = new JButton("Peti");
    
    Container vsebnik = okno.getContentPane();
    
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.LEFT,0,0)); 
        
    panel.add(g1);
    panel.add(g2);
    panel.add(g3);
    panel.add(g4);
    panel.add(g5);
    
    vsebnik.add(panel);

    okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    okno.setVisible(true);
  }

}
