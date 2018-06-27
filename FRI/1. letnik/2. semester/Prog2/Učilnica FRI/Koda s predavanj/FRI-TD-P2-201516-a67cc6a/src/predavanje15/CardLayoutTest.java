package predavanje15;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author tomaz
 */
public class CardLayoutTest {
  public static void main(String[] args) {
    JFrame okno = new JFrame("Naslov");
    okno.setBounds(100,100,400,400);
    
    final JPanel panel = new JPanel(new CardLayout());
    JLabel l1 = new JLabel(new ImageIcon("viri/s1.jpeg"));
    JLabel l2 = new JLabel(new ImageIcon("viri/s2.jpeg"));
    JLabel l3 = new JLabel(new ImageIcon("viri/s3.jpeg"));
    JLabel l4 = new JLabel(new ImageIcon("viri/s4.jpeg"));
    
    panel.add(l1);panel.add(l2);panel.add(l3);panel.add(l4);
    
    JButton nextGumb = new JButton("Naslednja slika");
    
    okno.getContentPane().add(panel, BorderLayout.CENTER);
    okno.getContentPane().add(nextGumb, BorderLayout.PAGE_START);
    
    
    nextGumb.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // kaj naj se zgodi, ko uporabnik pritisne gumb
        LayoutManager razporejevalnik =  panel.getLayout();
        if (razporejevalnik instanceof CardLayout) {
          ((CardLayout)razporejevalnik).next(panel);
         }
      }
    });
    
    
    okno.setVisible(true);
  }
}
