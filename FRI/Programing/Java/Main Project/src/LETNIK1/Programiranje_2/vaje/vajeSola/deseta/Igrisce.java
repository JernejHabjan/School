package LETNIK1.Programiranje_2.vaje.vajeSola.deseta;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Igrisce extends JPanel implements ActionListener, KeyListener {

    private final String KONEC_SPOROCILO = "Konec igre"; // sporočilo ob koncu igre
    private final int VEL_CELICE = 10; // velikost posamezne celice (v pikslih)
    private final int SIRINA = 30; // širina okna (v celicah)
    private final int VISINA = 30; // višina okna (v celicah)
    private final int POCAKAJ = 150; // časovni interval med preverjanjem akcij uporabnika (v nanosekundah)
    private final int ZAC_ST_CLENOV = 3; // začetno število členov, ki jih ima kača (glava + 2 člena repa)

    // koordinate glave kače (x[0] in y[0]) in njenega repa ((x[i] in y[i]), i > 0)
    // koordinate so koordinate celice (NE koordinate pikslov)
    // kača je lahko dolga največ toliko, kot je vseh celic na igralnem polju
    private int x[]; // x koordinate
    private int y[]; // y koordinate

    private int stClenov; // trenutna velikost kače (število členov)
    private int hrana_x;  // koordinata x celice, v kateri je postavljena hrana
    private int hrana_y;  // koordinata y celice, v kateri je postavljena hrana

    private boolean levo, desno, gor, dol; // smer premika; vedno je lahko TRUE le ena od naštetih smeri
    private boolean igra; // ali smo še v igri (true) ali smo že končali (false)

    private Timer timer; // časovnik, ki omogoča izvajanje animacije

    private BufferedImage ozadje; // slika, s katero podložimo igrišče (ozadje igrišča)

    public Igrisce() {
        // začetne nastavitve nove igre
        addKeyListener(this); // dodamo poslišalca za dogodke s tipkovnice
        setBackground(Color.lightGray); // barva ozadja igralne površine je siva
        setFocusable(true); // igralna površina je lahko v fokusu
        zacniIgro(); // začnemo novo igro
    }

    public final void zacniIgro() {
        // koncam prejsno igro (ce se vedno tece)
        if (timer != null)
          timer.stop();
      
        // začnemo novo igro: inicializiramo spremenljivke

        x = new int[SIRINA*VISINA]; // največja velikost kače je enaka številu celic na igrišču
        y = new int[SIRINA*VISINA];

        stClenov = 10;
        x[0] = 10;
        y[0] = 10;
        
        for (int i = 0; i < stClenov; i++) {
          x[i]= x[0];
          y[i]= y[0];
        }

        // kača se na začetku premika proti desni
        gor = false;
        dol = false;
        levo = false;
        desno = true;

        postaviHrano(); // postavimo hrano na igralno površino
        igra = true;    // smo v igri

        // postavimo časovnik: v podanem časevnem intervalu se sproži
        // dogodek ActionEvent, ki ga obravnava ta razred (this)
        timer = new Timer(POCAKAJ, this);
        timer.start();
    }

    private void narisiClen(Graphics g, int x, int y, Color c) {
      g.setColor(c);
      g.fillOval(x*VEL_CELICE, y*VEL_CELICE, VEL_CELICE, VEL_CELICE);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        // izriši igralno površino
        // če igramo, izriši kačo in hrano
        // če je igre konec, izpiši končno sporočilo na sredino igralne površine

        super.paintComponent(g);
        if(igra) {
            // izrisi hrano za kaco - zlatnik
            // TODO

          // izrisi rdeco glavo kace
          narisiClen(g, x[0], y[0], Color.red);

          // izrisi zelen rep kace
          for (int i = 1; i < stClenov; i++) {
            narisiClen(g, x[i], y[i], Color.green);
          }

          narisiClen(g, hrana_x, hrana_y, Color.yellow);
          
            // poskrbimo za posodobitev izrisa (potrebno za animacijo)
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        } else {
            // izpiši sporočilo ob koncu igre
            Font pisava = new Font("Helvetica", Font.BOLD, 18);
            FontMetrics metr = this.getFontMetrics(pisava);
            int dolzinaNiza = metr.stringWidth(KONEC_SPOROCILO);
            g.setColor(Color.black);
            g.setFont(pisava);
            g.drawString("KONEC", SIRINA*VEL_CELICE/2, VISINA*VEL_CELICE/2);

        }
    }

    public void preveriHrano() {
      if (x[0] == hrana_x && y[0] == hrana_y) {
        stClenov++;
        x[stClenov-1] = x[stClenov-2];
        y[stClenov-1] = y[stClenov-2];

        postaviHrano();
      }
    }

    public void premakni() {
        for (int i = stClenov-2; i>=0; i--) {
          x[i+1] = x[i];
          y[i+1] = y[i];
        }  
        // premakni glavo kače
        if(levo)    
             x[0]--;
        else if (desno)
          x[0]++;
        else if (gor)
          y[0]--;
        else if (dol)
          y[0]++;
        
    }

    public void preveriTrk() {
        // preverimo, ali je kača zadela rob igrišča ali samo sebe

        // TODO
         if((x[0] < 0) || (x[0] > SIRINA)|| (y[0] < 0)|| (y[0] > VISINA))       
             igra = false;  // končamo igro
         
         for (int i = 1; i < stClenov; i++) {
           if (x[0]==x[i] && y[0]==y[i])
             igra=false;
         }
    }

    public void postaviHrano() {
       Random rnd = new Random();
       hrana_x = rnd.nextInt(SIRINA);
       hrana_y = rnd.nextInt(VISINA);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // glavna zanka - kaj delamo
        if(igra) { // če smo še v igri
            preveriHrano();
            premakni();
            preveriTrk();
        }
        if(!igra) // igra se je zaključila, ustavimo timer
            timer.stop();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // pogledamo, katero tipko je pritisnil uporabnik in ustrezno ukrepamo
        // zanimajo nas puščice (gor, dol, levo, desno) in Enter (začetek igre)
        // ESC je zaključek programa

        int key = e.getKeyCode();
        if( key == KeyEvent.VK_LEFT && !desno ) {
             levo = true;
             gor = false;
             dol = false;
        } else if( key == KeyEvent.VK_RIGHT && !levo ) {
             desno = true;
             gor = false;
             dol = false;
        } else if( key == KeyEvent.VK_UP && !dol ) {
             gor = true;
             levo = false;
             desno = false;
        } else if( key == KeyEvent.VK_DOWN && !gor ) {
             dol = true;
             levo = false;
             desno = false;
        } else if(igra && key == KeyEvent.VK_ESCAPE ) {
           igra=false;
        } else if (key == KeyEvent.VK_ENTER ) {
           zacniIgro();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

}
