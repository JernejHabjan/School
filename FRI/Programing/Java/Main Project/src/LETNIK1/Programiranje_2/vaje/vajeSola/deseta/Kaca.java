package LETNIK1.Programiranje_2.vaje.vajeSola.deseta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

/**
 * Implementacija igrice LETNIK1.Programiranje_2.vaje.vajeSola.deseta.Kaca
 *
 * @author alenka
 */
public class Kaca {

  private final static int VEL = 315; // velikost okna (okno naj bi bilo kvadratne oblike)
  private final static int VEL_NASLOVA = 20; // velikost naslovne vrstice, ki jo zavzame okvir okna

  // ustvarjanje uporabniškega vmesnika
  private static void ustvariGUI() {
    // ustvarimo novo okno z naslovom "Kača"
    final JFrame okno = new JFrame("Kača");
    // velikost okna nastavi na določeno velikost
    okno.setSize(VEL, VEL + VEL_NASLOVA);
    // onemogoči spreminjanje velikosti okna
    okno.setResizable(false);
    // če okno zapremo (klik na x), se program konča
    okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // v okno postavimo nov objekt razreda LETNIK1.Programiranje_2.vaje.vajeSola.deseta.Igrisce (ta razred je bistvo našega programa)

    final Igrisce igrisce = new Igrisce();
    
    JMenuBar mBar = new JMenuBar();
    JMenu item1 = new JMenu("Datoteka");
    JMenuItem item3 = new JMenuItem("Nova igra");
    JMenuItem item4 = new JMenuItem("Zapri");
     
    item4.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);  
      }
    });

    item3.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
          igrisce.zacniIgro();
      }
    });
    
    mBar.add(item1);
    item1.add(item3);
    item1.add(item4);

    okno.setJMenuBar(mBar);

    okno.add(igrisce);
    // okno prikažemo na zaslonu
    okno.setVisible(true);
  }

  public static void main(String[] args) {
    // v ustrezni niti pokličemo metodo, ki ustvari GUI
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        ustvariGUI();
      }
    });
  }
}
