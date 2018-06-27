package predavanje15;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;

/**
 *
 * @author tomaz
 */
public class BorderLayoutTest {
  public static void main(String[] args) {
    JFrame okno = new JFrame("Naslov");
    okno.setBounds(100,100,400,400);
    
    JPanel gumbiPanel = new JPanel();
    gumbiPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    gumbiPanel.setPreferredSize(new Dimension(100, 1));
    JButton okGumb = new JButton("OK"); 
    JButton cancelGumb = new JButton("Cancel");
    gumbiPanel.add(okGumb);
    gumbiPanel.add(cancelGumb);
    
    JTextArea textArea = new JTextArea();
    
    okno.getContentPane().add(textArea, BorderLayout.CENTER); // isto kot okno.getContentPane().add(textArea); 
    okno.getContentPane().add(gumbiPanel, BorderLayout.LINE_END);
    
    okno.setVisible(true);
  }
}
