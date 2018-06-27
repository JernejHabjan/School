package predavanje15;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

/**
 *
 * @author tomaz
 */
public class GridBagLayoutTest {
  public static void main(String[] args) {
    JFrame okno = new JFrame("Naslov");
    okno.setBounds(100,100,400,400);

    JLabel imeL = new JLabel("Ime:");
    JLabel priimekL = new JLabel("Priimek:");
    
    JTextField imeTF = new JTextField();
    JTextField priimekTF = new JTextField();
    
    JTextArea vsebinaTA = new JTextArea();
    
    JButton g1 = new JButton("OK");
    JButton g2 = new JButton("Cancel");
    JButton g3 = new JButton("Help");    
    JPanel gumbiP = new JPanel();        
    gumbiP.add(g1);
    gumbiP.add(g2);
    gumbiP.add(g3);
    
    
    JPanel sredina = new JPanel(new GridBagLayout());
    //sredina.setBackground(Color.red);
    
    
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx=0; 
    gbc.gridy=0; 
    gbc.insets = new Insets(5, 5, 0, 0);
    gbc.anchor = GridBagConstraints.WEST;
    sredina.add(imeL, gbc);
    
    gbc = new GridBagConstraints();
    gbc.gridx=1; 
    gbc.gridy=0; 
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx=1;
    gbc.insets = new Insets(5, 5, 0, 5);
    sredina.add(imeTF, gbc);
    
    gbc = new GridBagConstraints();
    gbc.gridx=0; 
    gbc.gridy=1; 
    gbc.insets = new Insets(5, 5, 0, 0);
    gbc.anchor = GridBagConstraints.WEST;
    sredina.add(priimekL, gbc);
    
    gbc = new GridBagConstraints();
    gbc.gridx=1; 
    gbc.gridy=1; 
    gbc.weightx=1;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 0, 5);
    sredina.add(priimekTF, gbc);
    
    gbc = new GridBagConstraints();
    gbc.gridx=0; 
    gbc.gridy=2; 
    gbc.weightx=1;
    gbc.weighty=1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.gridwidth = 2;
    gbc.insets = new Insets(5, 5, 5, 5);
    sredina.add(vsebinaTA, gbc);
    
    okno.getContentPane().add(sredina, BorderLayout.CENTER);
    okno.getContentPane().add(gumbiP, BorderLayout.PAGE_END);
    
    

    
    
    okno.setVisible(true);
  }
}
