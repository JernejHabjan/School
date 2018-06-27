package predavanje15;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;



class MojPoslusalec implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("Pritisk - " + e.getActionCommand());
  }  
}

/**
 *
 * @author tomaz
 */
public class Listenertest {
  
  public static void main(String[] args) {
    final JFrame okno = new JFrame("Naslov");
    okno.setBounds(100,100,400,400);
    
    // preprecim pomoc razporejevalnika
    okno.getContentPane().setLayout(null); 
    
    JButton gumb = new JButton("OK");
    gumb.setBounds(50,50, 100, 20);    
    okno.getContentPane().add(gumb);

    JButton gumb2 = new JButton("Cancel");
    gumb2.setBounds(50,150, 100, 20);    
    okno.getContentPane().add(gumb2);
    
    JButton gumb3 = new JButton("Spremeni naslov");
    gumb3.setBounds(50,250, 100, 20);    
    okno.getContentPane().add(gumb3);

    gumb3.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
         okno.setTitle("Nekaj"); 
      }     
    });

    okno.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseMoved(MouseEvent e) {
        System.out.println(e.getX() + "," + e.getY());
      }
    });
    
    okno.addComponentListener(new ComponentAdapter() {
      
      public void componentResized(ComponentEvent e) {
        System.out.println("Sprememba velikosti");
      }
    });
    
    okno.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent e) {
        int i = JOptionPane.showConfirmDialog(okno, "Zapiranje", "Koncam program?", JOptionPane.YES_NO_OPTION);
        if (i==JOptionPane.YES_OPTION)
          System.exit(0);
      }
    });
    
    MojPoslusalec poslusalec = new MojPoslusalec();
    gumb.addActionListener(poslusalec); 
    gumb2.addActionListener(poslusalec);
    
    okno.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    okno.setVisible(true);
  }

}
