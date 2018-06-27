package DN03;

import java.awt.Color;
import java.awt.List;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Domaca03 extends javax.swing.JFrame {
    Map<Integer, String> kraji = new HashMap<Integer, String>();

    public Domaca03() {
        initComponents();
        
        readParseCSV();
        readCars();
        zavarovanjec_field_posStEventListener();
        vozilo_colorDisplay.setBackground(Color.WHITE);     
        dat_rojstva_picker.setDate(new Date());     
        keyListeners();
    }
    
    void keyListeners(){   
        zavarovanjec_field_priimek.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {zavarovanjec_field_priimek.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {zavarovanjec_field_priimek.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {zavarovanjec_field_priimek.setBackground(Color.WHITE);}
        });
        zavarovanjec_field_ime.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {zavarovanjec_field_ime.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {zavarovanjec_field_ime.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {zavarovanjec_field_ime.setBackground(Color.WHITE);}
        });
        zavarovanjec_field_posSt.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {zavarovanjec_field_posSt.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {zavarovanjec_field_posSt.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {zavarovanjec_field_posSt.setBackground(Color.WHITE);}
        });        
        zavarovanjec_field_kraj.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {zavarovanjec_field_kraj.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {zavarovanjec_field_kraj.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {zavarovanjec_field_kraj.setBackground(Color.WHITE);}
        });
        zavarovanjec_field_ulica.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {zavarovanjec_field_ulica.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {zavarovanjec_field_ulica.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {zavarovanjec_field_ulica.setBackground(Color.WHITE);}
        });
        registracija_field_kfajPrveReg.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {registracija_field_kfajPrveReg.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {registracija_field_kfajPrveReg.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {registracija_field_kfajPrveReg.setBackground(Color.WHITE);}
        });
        registracija_field_regOznacba.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {registracija_field_regOznacba.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {registracija_field_regOznacba.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {registracija_field_regOznacba.setBackground(Color.WHITE);}
        });
        vozilo_field_Sifra.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {vozilo_field_Sifra.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {vozilo_field_Sifra.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {vozilo_field_Sifra.setBackground(Color.WHITE);}
        });
        vozilo_field_oznaka.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {vozilo_field_oznaka.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {vozilo_field_oznaka.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {vozilo_field_oznaka.setBackground(Color.WHITE);}
        });
        vozilo_field_prostornina.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {vozilo_field_prostornina.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {vozilo_field_prostornina.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {vozilo_field_prostornina.setBackground(Color.WHITE);}
        });
       zavarovanjec_field_hst.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {zavarovanjec_field_hst.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {zavarovanjec_field_hst.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {zavarovanjec_field_hst.setBackground(Color.WHITE);}
        });
       
        PropertyChangeListener listener = new PropertyChangeListener() {
             public void propertyChange(PropertyChangeEvent e) {
                 dat_rojstva_picker.setBackground(Color.WHITE);
             }
         };
        
        dat_rojstva_picker.addPropertyChangeListener(listener);
        
    }
        
    void zavarovanjec_field_posStEventListener(){

        zavarovanjec_field_posSt.getDocument().addDocumentListener(new DocumentListener() {
            public void check(){
                String text = zavarovanjec_field_posSt.getText();
                if(text.length() < 4) return;
                else if(text.length() > 4) statusnaVrstica.setText("Veljavna poštna številka ima 4 znake.");
                try{
                    Integer pstevilka = Integer.parseInt(text);
                    String value = kraji.get(pstevilka);
                    if (value != null) 
                        zavarovanjec_field_kraj.setText(kraji.get(pstevilka));
                    else{
                        statusnaVrstica.setText("Kraja ni mogoče najti");
                        zavarovanjec_field_posSt.setBackground(Color.YELLOW);
                    }
                }catch (Exception a){
                    statusnaVrstica.setText("Vpisali ste neštevilski znak");
                    zavarovanjec_field_posSt.setBackground(Color.RED);
                    return;
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {check();}
            @Override
            public void removeUpdate(DocumentEvent e) {statusnaVrstica.setText("");
                zavarovanjec_field_posSt.setBackground(Color.WHITE);}
            @Override
            public void changedUpdate(DocumentEvent e) {statusnaVrstica.setText("");
                zavarovanjec_field_posSt.setBackground(Color.WHITE);
            }
            });
    }
    
    
    void zavarovanjec_field_posStspinnerEventListener(){
        zavarovanjec_field_posSt.getDocument().addDocumentListener(new DocumentListener() {
            public void check(){
                String text = zavarovanjec_field_posSt.getText();

                //System.out.println(text);
                if(text.length() < 4) return;
                else if(text.length() > 4) statusnaVrstica.setText("Veljavna poštna številka ima 4 znake.");
                try{
                    Integer pstevilka = Integer.parseInt(text);
                    String value = kraji.get(pstevilka);
                    if (value != null) {
                        zavarovanjec_field_kraj.setText(kraji.get(pstevilka));
                    }else{
                        statusnaVrstica.setText("Kraja ni mogoče najti");
                        zavarovanjec_field_posSt.setBackground(Color.YELLOW);
                        
                    }
                  
                }catch (Exception a){
                    statusnaVrstica.setText("Vpisali ste neštevilski znak");
                    zavarovanjec_field_posSt.setBackground(Color.RED);
                    return;
                }

            }
            @Override
            public void insertUpdate(DocumentEvent e) {check();}
            @Override
            public void removeUpdate(DocumentEvent e) {statusnaVrstica.setText("");
            zavarovanjec_field_posSt.setBackground(Color.WHITE);}
            @Override
            public void changedUpdate(DocumentEvent e) {statusnaVrstica.setText("");
            zavarovanjec_field_posSt.setBackground(Color.WHITE);
            }
        });
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        starostVoznikaGroup = new javax.swing.ButtonGroup();
        osnovnoZavarovanjeGroup = new javax.swing.ButtonGroup();
        kaskoGroup = new javax.swing.ButtonGroup();
        panel_main = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        panel_zavarovanec = new javax.swing.JPanel();
        zavarovanjec_label_title = new javax.swing.JLabel();
        zavarovanjec_label_priimek = new javax.swing.JLabel();
        zavarovanjec_label_ime = new javax.swing.JLabel();
        zavarovanjec_label_hSt = new javax.swing.JLabel();
        zavarovanjec_label_ulica = new javax.swing.JLabel();
        zavarovanjec_label_datRoj = new javax.swing.JLabel();
        zavarovanjec_label_kraj = new javax.swing.JLabel();
        zavarovanjec_label_posSt = new javax.swing.JLabel();
        zavarovanjec_field_priimek = new javax.swing.JTextField();
        zavarovanjec_field_ime = new javax.swing.JTextField();
        zavarovanjec_field_kraj = new javax.swing.JTextField();
        zavarovanjec_field_posSt = new javax.swing.JTextField();
        zavarovanjec_field_ulica = new javax.swing.JTextField();
        zavarovanjec_label_izkusnje = new javax.swing.JLabel();
        zavarovanjec_rad_mlad = new javax.swing.JRadioButton();
        zavarovanjec_rad_izkusen = new javax.swing.JRadioButton();
        dat_rojstva_picker = new org.jdesktop.swingx.JXDatePicker();
        zavarovanjec_field_hst = new javax.swing.JTextField();
        panel_zavarovanje = new javax.swing.JPanel();
        zavarovanje_label_title = new javax.swing.JLabel();
        zavarovanje_label__osnovno = new javax.swing.JLabel();
        zavarovanje_rad_AO = new javax.swing.JRadioButton();
        zavarovanje_rad_AOPLUS = new javax.swing.JRadioButton();
        zavarovanje_label_kasko = new javax.swing.JLabel();
        zavarovanje_rad_polno = new javax.swing.JRadioButton();
        zavarovanje_rad_odbFran = new javax.swing.JRadioButton();
        zavarovanje_rad_brez = new javax.swing.JRadioButton();
        zavarovanje_label_dodatno = new javax.swing.JLabel();
        zavarovanje_check_stekla = new javax.swing.JCheckBox();
        zavarovanje_check_parkirisce = new javax.swing.JCheckBox();
        zavarovanje_check_potniki = new javax.swing.JCheckBox();
        zavarovanje_check_tretjaOs = new javax.swing.JCheckBox();
        zavarovanje_check_gume = new javax.swing.JCheckBox();
        zavarovanje_check_kraja = new javax.swing.JCheckBox();
        zavarovanje_check_toca = new javax.swing.JCheckBox();
        panel_registracija = new javax.swing.JPanel();
        registracija_label_title = new javax.swing.JLabel();
        registracija_label_regOzn = new javax.swing.JLabel();
        registracija_label_krajPrveReg = new javax.swing.JLabel();
        registracija_field_regOznacba = new javax.swing.JTextField();
        registracija_field_kfajPrveReg = new javax.swing.JTextField();
        registracija_label_datPrveReg = new javax.swing.JLabel();
        panel_vozilo = new javax.swing.JPanel();
        vozilo_label_title = new javax.swing.JLabel();
        vozilo_label_moc = new javax.swing.JLabel();
        vozilo_spinner_moc = new javax.swing.JSpinner();
        vozilo_label_oznaka = new javax.swing.JLabel();
        vozilo_label_sifra = new javax.swing.JLabel();
        vozilo_field_oznaka = new javax.swing.JTextField();
        vozilo_field_Sifra = new javax.swing.JTextField();
        vozilo_label_barva = new javax.swing.JLabel();
        vozilo_label_prostornina = new javax.swing.JLabel();
        vozilo_field_prostornina = new javax.swing.JTextField();
        vozilo_label_znamka = new javax.swing.JLabel();
        vozilo_label_gorivo = new javax.swing.JLabel();
        vozilo_combo_znamka = new javax.swing.JComboBox<>();
        vozilo_combo_gorivo = new javax.swing.JComboBox<>();
        vozilo_button_izberiBarvo = new javax.swing.JButton();
        vozilo_colorDisplay = new javax.swing.JPanel();
        scrollPane_StatusnaVrstica = new javax.swing.JScrollPane();
        statusnaVrstica = new javax.swing.JLabel();
        toolBar = new javax.swing.JToolBar();
        tool_Odpri = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        tool_Shrani = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        button_clear = new javax.swing.JButton();
        menuBar = new javax.swing.JMenuBar();
        menu_datoteka = new javax.swing.JMenu();
        datoteka_odpri = new javax.swing.JMenuItem();
        datoteka_shrani = new javax.swing.JMenuItem();
        datoteka_izhod = new javax.swing.JMenuItem();
        menu_popravi = new javax.swing.JMenu();
        popravi_item1 = new javax.swing.JMenuItem();
        menu_pomoc = new javax.swing.JMenu();
        pomoc_Avtor = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabbedPane.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        zavarovanjec_label_title.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        zavarovanjec_label_title.setText("Podatki o zavezancu");

        zavarovanjec_label_priimek.setText("Priimek");

        zavarovanjec_label_ime.setText("Ime");

        zavarovanjec_label_hSt.setText("Hišna številka");

        zavarovanjec_label_ulica.setText("Ulica");

        zavarovanjec_label_datRoj.setText("Datum rojstva");

        zavarovanjec_label_kraj.setText("Kraj");

        zavarovanjec_label_posSt.setText("Poštna številka");

        zavarovanjec_field_posSt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zavarovanjec_field_posStActionPerformed(evt);
            }
        });
        zavarovanjec_field_posSt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                zavarovanjec_field_posStKeyTyped(evt);
            }
        });

        zavarovanjec_field_ulica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zavarovanjec_field_ulicaActionPerformed(evt);
            }
        });

        zavarovanjec_label_izkusnje.setText("Izkušnje");

        starostVoznikaGroup.add(zavarovanjec_rad_mlad);
        zavarovanjec_rad_mlad.setText("Mlad voznik");

        starostVoznikaGroup.add(zavarovanjec_rad_izkusen);
        zavarovanjec_rad_izkusen.setSelected(true);
        zavarovanjec_rad_izkusen.setText("Izkušen voznik");

        zavarovanjec_field_hst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zavarovanjec_field_hstActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_zavarovanecLayout = new javax.swing.GroupLayout(panel_zavarovanec);
        panel_zavarovanec.setLayout(panel_zavarovanecLayout);
        panel_zavarovanecLayout.setHorizontalGroup(
            panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_zavarovanecLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_zavarovanecLayout.createSequentialGroup()
                        .addComponent(zavarovanjec_label_title, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 463, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_zavarovanecLayout.createSequentialGroup()
                        .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(zavarovanjec_label_ime)
                            .addGroup(panel_zavarovanecLayout.createSequentialGroup()
                                .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(zavarovanjec_label_priimek)
                                    .addComponent(zavarovanjec_label_kraj)
                                    .addComponent(zavarovanjec_label_posSt))
                                .addGap(33, 33, 33)
                                .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(zavarovanjec_field_priimek)
                                    .addComponent(zavarovanjec_field_ime)
                                    .addComponent(zavarovanjec_field_kraj)
                                    .addComponent(zavarovanjec_field_posSt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_zavarovanecLayout.createSequentialGroup()
                                .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(zavarovanjec_label_hSt, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(zavarovanjec_label_ulica))
                                .addGap(41, 41, 41)
                                .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(zavarovanjec_field_ulica, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(zavarovanjec_field_hst)))
                            .addGroup(panel_zavarovanecLayout.createSequentialGroup()
                                .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(zavarovanjec_label_datRoj)
                                    .addComponent(zavarovanjec_label_izkusnje))
                                .addGap(38, 38, 38)
                                .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_zavarovanecLayout.createSequentialGroup()
                                        .addComponent(zavarovanjec_rad_mlad)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(zavarovanjec_rad_izkusen))
                                    .addComponent(dat_rojstva_picker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(28, 28, 28)))
                .addGap(286, 286, 286))
        );
        panel_zavarovanecLayout.setVerticalGroup(
            panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_zavarovanecLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(zavarovanjec_label_title)
                .addGap(26, 26, 26)
                .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_zavarovanecLayout.createSequentialGroup()
                        .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(zavarovanjec_label_priimek)
                            .addComponent(zavarovanjec_field_priimek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(zavarovanjec_label_ime)
                            .addComponent(zavarovanjec_field_ime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addComponent(zavarovanjec_label_posSt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(zavarovanjec_label_kraj))
                    .addGroup(panel_zavarovanecLayout.createSequentialGroup()
                        .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(zavarovanjec_label_ulica)
                            .addComponent(zavarovanjec_field_ulica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(zavarovanjec_label_hSt)
                            .addComponent(zavarovanjec_field_hst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(zavarovanjec_field_posSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(zavarovanjec_label_datRoj)
                                .addComponent(dat_rojstva_picker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_zavarovanecLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(zavarovanjec_field_kraj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_zavarovanecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(zavarovanjec_label_izkusnje)
                                .addComponent(zavarovanjec_rad_mlad)
                                .addComponent(zavarovanjec_rad_izkusen)))))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Zavarovanec", panel_zavarovanec);

        zavarovanje_label_title.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        zavarovanje_label_title.setText("Podatki o zavarovanju");

        zavarovanje_label__osnovno.setText("Osnovno zavarovanje");

        osnovnoZavarovanjeGroup.add(zavarovanje_rad_AO);
        zavarovanje_rad_AO.setSelected(true);
        zavarovanje_rad_AO.setText("AO");
        zavarovanje_rad_AO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zavarovanje_rad_AOActionPerformed(evt);
            }
        });

        osnovnoZavarovanjeGroup.add(zavarovanje_rad_AOPLUS);
        zavarovanje_rad_AOPLUS.setText("AO+");

        zavarovanje_label_kasko.setText("Kasko");

        kaskoGroup.add(zavarovanje_rad_polno);
        zavarovanje_rad_polno.setSelected(true);
        zavarovanje_rad_polno.setText("Polno");

        kaskoGroup.add(zavarovanje_rad_odbFran);
        zavarovanje_rad_odbFran.setText("Odbitna franšiza");

        kaskoGroup.add(zavarovanje_rad_brez);
        zavarovanje_rad_brez.setText("Brez");

        zavarovanje_label_dodatno.setText("Dodatno zavarovanje");

        zavarovanje_check_stekla.setText("zavarovanje stekel");

        zavarovanje_check_parkirisce.setText("zavarovanje na parkirišču");

        zavarovanje_check_potniki.setText("zavarovanje potnikov");

        zavarovanje_check_tretjaOs.setText("zavarovanje tretje osebe");

        zavarovanje_check_gume.setText("zavarovanje gum");

        zavarovanje_check_kraja.setText("zavarovanje proti kraji");

        zavarovanje_check_toca.setText("zavarovanje proti toči");

        javax.swing.GroupLayout panel_zavarovanjeLayout = new javax.swing.GroupLayout(panel_zavarovanje);
        panel_zavarovanje.setLayout(panel_zavarovanjeLayout);
        panel_zavarovanjeLayout.setHorizontalGroup(
            panel_zavarovanjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_zavarovanjeLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panel_zavarovanjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_zavarovanjeLayout.createSequentialGroup()
                        .addComponent(zavarovanje_label_title, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(580, 580, 580))
                    .addGroup(panel_zavarovanjeLayout.createSequentialGroup()
                        .addGroup(panel_zavarovanjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(zavarovanje_label_dodatno)
                            .addComponent(zavarovanje_label__osnovno)
                            .addComponent(zavarovanje_label_kasko))
                        .addGap(18, 18, 18)
                        .addGroup(panel_zavarovanjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_zavarovanjeLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(panel_zavarovanjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(zavarovanje_check_stekla)
                                    .addComponent(zavarovanje_check_parkirisce)
                                    .addComponent(zavarovanje_check_potniki))
                                .addGap(31, 31, 31))
                            .addGroup(panel_zavarovanjeLayout.createSequentialGroup()
                                .addGroup(panel_zavarovanjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panel_zavarovanjeLayout.createSequentialGroup()
                                        .addComponent(zavarovanje_rad_AO)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(zavarovanje_rad_AOPLUS))
                                    .addGroup(panel_zavarovanjeLayout.createSequentialGroup()
                                        .addComponent(zavarovanje_rad_polno)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(zavarovanje_rad_odbFran)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(zavarovanje_rad_brez)))
                                .addGap(201, 201, 201)))
                        .addGroup(panel_zavarovanjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(zavarovanje_check_kraja)
                            .addComponent(zavarovanje_check_gume)
                            .addGroup(panel_zavarovanjeLayout.createSequentialGroup()
                                .addComponent(zavarovanje_check_tretjaOs)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(zavarovanje_check_toca)))))
                .addContainerGap(265, Short.MAX_VALUE))
        );
        panel_zavarovanjeLayout.setVerticalGroup(
            panel_zavarovanjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_zavarovanjeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(zavarovanje_label_title)
                .addGap(22, 22, 22)
                .addGroup(panel_zavarovanjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zavarovanje_label__osnovno)
                    .addComponent(zavarovanje_rad_AO)
                    .addComponent(zavarovanje_rad_AOPLUS))
                .addGap(18, 18, 18)
                .addGroup(panel_zavarovanjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(zavarovanje_label_kasko)
                    .addGroup(panel_zavarovanjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(zavarovanje_rad_polno)
                        .addComponent(zavarovanje_rad_odbFran)
                        .addComponent(zavarovanje_rad_brez)))
                .addGap(67, 67, 67)
                .addGroup(panel_zavarovanjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zavarovanje_label_dodatno)
                    .addComponent(zavarovanje_check_stekla)
                    .addComponent(zavarovanje_check_tretjaOs)
                    .addComponent(zavarovanje_check_toca))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_zavarovanjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zavarovanje_check_parkirisce)
                    .addComponent(zavarovanje_check_gume))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_zavarovanjeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(zavarovanje_check_kraja)
                    .addComponent(zavarovanje_check_potniki))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Zavarovanje", panel_zavarovanje);

        registracija_label_title.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        registracija_label_title.setText("Podatki o registraciji");

        registracija_label_regOzn.setText("Registrska označba");

        registracija_label_krajPrveReg.setText("Kraj prve registracije");

        registracija_label_datPrveReg.setText("Datum prve registracije");

        javax.swing.GroupLayout panel_registracijaLayout = new javax.swing.GroupLayout(panel_registracija);
        panel_registracija.setLayout(panel_registracijaLayout);
        panel_registracijaLayout.setHorizontalGroup(
            panel_registracijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_registracijaLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panel_registracijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(registracija_label_title, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel_registracijaLayout.createSequentialGroup()
                        .addGap(259, 259, 259)
                        .addComponent(registracija_field_regOznacba, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_registracijaLayout.createSequentialGroup()
                        .addComponent(registracija_label_datPrveReg)
                        .addGap(278, 278, 278)
                        .addGroup(panel_registracijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(registracija_label_krajPrveReg)
                            .addComponent(registracija_label_regOzn)))
                    .addComponent(registracija_field_kfajPrveReg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(574, Short.MAX_VALUE))
        );
        panel_registracijaLayout.setVerticalGroup(
            panel_registracijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_registracijaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(registracija_label_title)
                .addGap(31, 31, 31)
                .addGroup(panel_registracijaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(registracija_label_regOzn)
                    .addComponent(registracija_label_datPrveReg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registracija_field_regOznacba, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(registracija_label_krajPrveReg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(registracija_field_kfajPrveReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(134, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Registracija", panel_registracija);

        vozilo_label_title.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        vozilo_label_title.setText("Podatki o vozilu");

        vozilo_label_moc.setText("Moč (kW)");

        vozilo_spinner_moc.setModel(new javax.swing.SpinnerNumberModel());

        vozilo_label_oznaka.setText("Oznaka");

        vozilo_label_sifra.setText("Šifra");

        vozilo_label_barva.setText("Barva");

        vozilo_label_prostornina.setText("Prostornina");

        vozilo_label_znamka.setText("Znamka");

        vozilo_label_gorivo.setText("Gorivo");

        vozilo_combo_znamka.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        vozilo_combo_gorivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "bencin", "diesel", "hibridni pogon", "bencin + plin", "e-pogon" }));

        vozilo_button_izberiBarvo.setText("Izberi barvo");
        vozilo_button_izberiBarvo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vozilo_button_izberiBarvoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout vozilo_colorDisplayLayout = new javax.swing.GroupLayout(vozilo_colorDisplay);
        vozilo_colorDisplay.setLayout(vozilo_colorDisplayLayout);
        vozilo_colorDisplayLayout.setHorizontalGroup(
            vozilo_colorDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        vozilo_colorDisplayLayout.setVerticalGroup(
            vozilo_colorDisplayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel_voziloLayout = new javax.swing.GroupLayout(panel_vozilo);
        panel_vozilo.setLayout(panel_voziloLayout);
        panel_voziloLayout.setHorizontalGroup(
            panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_voziloLayout.createSequentialGroup()
                .addGroup(panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_voziloLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vozilo_label_title, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vozilo_label_znamka)))
                    .addGroup(panel_voziloLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(vozilo_combo_znamka, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_voziloLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(vozilo_label_moc)
                        .addGap(18, 18, 18)
                        .addComponent(vozilo_spinner_moc, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(102, 102, 102)
                .addGroup(panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(vozilo_label_oznaka)
                    .addComponent(vozilo_label_barva)
                    .addGroup(panel_voziloLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(vozilo_field_oznaka, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_voziloLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vozilo_label_gorivo)
                            .addComponent(vozilo_combo_gorivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_voziloLayout.createSequentialGroup()
                                .addComponent(vozilo_colorDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(vozilo_button_izberiBarvo)))))
                .addGap(27, 27, 27)
                .addGroup(panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(vozilo_label_prostornina)
                    .addComponent(vozilo_label_sifra)
                    .addComponent(vozilo_field_Sifra)
                    .addComponent(vozilo_field_prostornina, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(237, Short.MAX_VALUE))
        );
        panel_voziloLayout.setVerticalGroup(
            panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_voziloLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(vozilo_label_title)
                .addGap(18, 18, 18)
                .addGroup(panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_voziloLayout.createSequentialGroup()
                        .addGroup(panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vozilo_label_oznaka)
                            .addComponent(vozilo_label_sifra))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vozilo_field_Sifra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vozilo_field_oznaka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vozilo_label_barva)
                            .addComponent(vozilo_label_prostornina))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vozilo_colorDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(vozilo_field_prostornina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(vozilo_button_izberiBarvo))))
                    .addGroup(panel_voziloLayout.createSequentialGroup()
                        .addComponent(vozilo_label_znamka)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vozilo_combo_znamka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_voziloLayout.createSequentialGroup()
                        .addComponent(vozilo_label_gorivo)
                        .addGap(18, 18, 18)
                        .addComponent(vozilo_combo_gorivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_voziloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(vozilo_label_moc)
                        .addComponent(vozilo_spinner_moc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Vozilo", panel_vozilo);

        javax.swing.GroupLayout panel_mainLayout = new javax.swing.GroupLayout(panel_main);
        panel_main.setLayout(panel_mainLayout);
        panel_mainLayout.setHorizontalGroup(
            panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );
        panel_mainLayout.setVerticalGroup(
            panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane)
        );

        tabbedPane.getAccessibleContext().setAccessibleDescription("");

        statusnaVrstica.setText(" ");
        scrollPane_StatusnaVrstica.setViewportView(statusnaVrstica);

        toolBar.setRollover(true);

        tool_Odpri.setMnemonic('O');
        tool_Odpri.setText("Odpri");
        tool_Odpri.setFocusable(false);
        tool_Odpri.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Odpri.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Odpri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_OdpriActionPerformed(evt);
            }
        });
        toolBar.add(tool_Odpri);
        toolBar.add(jSeparator1);

        tool_Shrani.setMnemonic('S');
        tool_Shrani.setText("Shrani");
        tool_Shrani.setFocusable(false);
        tool_Shrani.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Shrani.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Shrani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_ShraniActionPerformed(evt);
            }
        });
        toolBar.add(tool_Shrani);
        toolBar.add(jSeparator2);
        toolBar.add(jSeparator6);

        button_clear.setMnemonic('P');
        button_clear.setText("Počisti polja");
        button_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_clearActionPerformed(evt);
            }
        });
        toolBar.add(button_clear);

        menu_datoteka.setText("Datoteka");

        datoteka_odpri.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        datoteka_odpri.setMnemonic('O');
        datoteka_odpri.setText("Odpri");
        datoteka_odpri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datoteka_odpriActionPerformed(evt);
            }
        });
        menu_datoteka.add(datoteka_odpri);

        datoteka_shrani.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        datoteka_shrani.setMnemonic('S');
        datoteka_shrani.setText("Shrani");
        datoteka_shrani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datoteka_shraniActionPerformed(evt);
            }
        });
        menu_datoteka.add(datoteka_shrani);

        datoteka_izhod.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        datoteka_izhod.setMnemonic('I');
        datoteka_izhod.setText("Izhod");
        datoteka_izhod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datoteka_izhodActionPerformed(evt);
            }
        });
        menu_datoteka.add(datoteka_izhod);

        menuBar.add(menu_datoteka);

        menu_popravi.setText("Orodja");

        popravi_item1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        popravi_item1.setMnemonic('P');
        popravi_item1.setText("Počisti polja");
        popravi_item1.setToolTipText("");
        popravi_item1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popravi_item1ActionPerformed(evt);
            }
        });
        menu_popravi.add(popravi_item1);

        menuBar.add(menu_popravi);

        menu_pomoc.setText("Pomoč");
        menu_pomoc.setToolTipText("");

        pomoc_Avtor.setMnemonic('A');
        pomoc_Avtor.setText("Avtor");
        pomoc_Avtor.setToolTipText("");
        pomoc_Avtor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pomoc_AvtorActionPerformed(evt);
            }
        });
        menu_pomoc.add(pomoc_Avtor);

        menuBar.add(menu_pomoc);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panel_main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPane_StatusnaVrstica))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panel_main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(scrollPane_StatusnaVrstica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void zavarovanje_rad_AOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zavarovanje_rad_AOActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_zavarovanje_rad_AOActionPerformed
    void readCars(){
        String userDir = System.getProperty("user.dir");
        String filePath = userDir +File.separator + "src" +File.separator + "sources" +File.separator + "cars.txt";
        BufferedReader reader;
        Vector comboBoxItems=new Vector();
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line= reader.readLine().trim();
            while(!line.equals("")){
                String car = line;
                comboBoxItems.add(car);  
                line = reader.readLine().trim();
            }
        }catch (Exception e){}
        vozilo_combo_znamka.setModel(new DefaultComboBoxModel(comboBoxItems.toArray()));
    }  
    File f;
    private void chooseFile() throws ParseException{
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        f = chooser.getSelectedFile();
        if(f !=null && f.getName().endsWith(".txt")){
            try {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));) {
                    String col = br.readLine().trim();

                    vozilo_spinner_moc.setValue(Integer.parseInt(br.readLine().trim()));
             
                    zavarovanjec_field_priimek.setText(br.readLine().trim());
                    zavarovanjec_field_ime.setText(br.readLine().trim());
                    zavarovanjec_field_posSt.setText(br.readLine().trim());
                    zavarovanjec_field_kraj.setText(br.readLine().trim());
                    zavarovanjec_field_ulica.setText(br.readLine().trim());

                    registracija_field_kfajPrveReg.setText(br.readLine().trim());
                    registracija_field_regOznacba.setText(br.readLine().trim());
          


                    vozilo_field_Sifra.setText(br.readLine().trim());
                    vozilo_field_oznaka.setText(br.readLine().trim());
                    vozilo_field_prostornina.setText(br.readLine().trim());
                    
                    zavarovanje_check_gume.setSelected(Boolean.parseBoolean(br.readLine().trim()));
                    zavarovanje_check_kraja.setSelected(Boolean.parseBoolean(br.readLine().trim()));
                    zavarovanje_check_parkirisce.setSelected(Boolean.parseBoolean(br.readLine().trim()));
                    zavarovanje_check_potniki.setSelected(Boolean.parseBoolean(br.readLine().trim()));
                    zavarovanje_check_stekla.setSelected(Boolean.parseBoolean(br.readLine().trim()));
                    zavarovanje_check_toca.setSelected(Boolean.parseBoolean(br.readLine().trim()));
                    zavarovanje_check_tretjaOs.setSelected(Boolean.parseBoolean(br.readLine().trim()));
                    
                    
                    zavarovanjec_rad_izkusen.setSelected(Boolean.parseBoolean(br.readLine().trim()));
                    zavarovanjec_rad_mlad.setSelected(Boolean.parseBoolean(br.readLine().trim()));
       
                    zavarovanje_rad_AO.setSelected(Boolean.parseBoolean(br.readLine().trim()));
                    zavarovanje_rad_AOPLUS.setSelected(Boolean.parseBoolean(br.readLine().trim()));
                    zavarovanje_rad_brez.setSelected(Boolean.parseBoolean(br.readLine().trim()));
                    zavarovanje_rad_odbFran.setSelected(Boolean.parseBoolean(br.readLine().trim()));
                    zavarovanje_rad_polno.setSelected(Boolean.parseBoolean(br.readLine().trim()));
   

                    zavarovanjec_field_hst.setText(br.readLine().trim());

                    
                    vozilo_combo_gorivo.setSelectedItem(br.readLine().trim());
                    vozilo_combo_znamka.setSelectedItem(br.readLine().trim());
   
    
                    String d_S = br.readLine().trim();
                    //SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
                       
                        //convert Java String to Date using parse method of SimpleDateFormat
                    //Date date = sdf.parse(d_S);
                    //System.out.println(date.toString());
                    //dat_rojstva_picker.setDate(date);
           
                
                    //textPane.requestFocus();--------------------
                    
                    statusnaVrstica.setText("Datoteka je uspešno prebrana v program.");
                }
            }catch (IOException ex) {
                System.out.println("Ni datoteke.");
            }
        }
    }
    
    private void datoteka_odpriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datoteka_odpriActionPerformed
        try {
            chooseFile();
        } catch (ParseException ex) {
            Logger.getLogger(Domaca03.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_datoteka_odpriActionPerformed
    
    boolean checkValues(){
        Boolean ok = true;

        String _zavarovanjec_field_priimek = zavarovanjec_field_priimek.getText().trim();
        String _zavarovanjec_field_ime = zavarovanjec_field_ime.getText().trim();
        String _zavarovanjec_field_posSt = zavarovanjec_field_posSt.getText().trim();
        String _zavarovanjec_field_kraj = zavarovanjec_field_kraj.getText().trim();
        String _zavarovanjec_field_ulica = zavarovanjec_field_ulica.getText().trim();
        String _zavarovanjec_field_hst = zavarovanjec_field_hst.getText().trim();

        String _registracija_field_kfajPrveReg = registracija_field_kfajPrveReg.getText().trim();
        String _registracija_field_regOznacba = registracija_field_regOznacba.getText().trim();

        String _vozilo_field_Sifra = vozilo_field_Sifra.getText().trim();
        String _vozilo_field_oznaka = vozilo_field_oznaka.getText().trim();
        String _vozilo_field_prostornina = vozilo_field_prostornina.getText().trim();
        
        if(_zavarovanjec_field_priimek.equals("")){
            zavarovanjec_field_priimek.setBackground(Color.red);
            ok=  false;
        }
        if(_zavarovanjec_field_ime.equals("")){
            zavarovanjec_field_ime.setBackground(Color.red);
            ok= false;
        }
        if(_zavarovanjec_field_posSt.equals("")){
            zavarovanjec_field_posSt.setBackground(Color.red);
            ok= false;
        }
        if(_zavarovanjec_field_kraj.equals("")){
            zavarovanjec_field_kraj.setBackground(Color.red);
            ok= false;
        }
        if(_zavarovanjec_field_ulica.equals("")){
            zavarovanjec_field_ulica.setBackground(Color.red);
            ok= false;
        }
        if(_zavarovanjec_field_hst.equals("")){
            zavarovanjec_field_hst.setBackground(Color.red);
            ok= false;
        }
        
        
        if(_registracija_field_kfajPrveReg.equals("")){
            registracija_field_kfajPrveReg.setBackground(Color.red);   
            ok= false;
        }
        if(_registracija_field_regOznacba.equals("")){
            registracija_field_regOznacba.setBackground(Color.red);
            ok= false;
        }
                
        if(_vozilo_field_Sifra.equals("")){
            vozilo_field_Sifra.setBackground(Color.red);    
            ok= false;
        }
        if(_vozilo_field_oznaka.equals("")){
            vozilo_field_oznaka.setBackground(Color.red);
            ok= false;
        }
        if(_vozilo_field_prostornina.equals("")){
            vozilo_field_prostornina.setBackground(Color.red);
            ok= false;
        }
            
        

        if (dat_rojstva_picker.getDate() == null){
            dat_rojstva_picker.setBackground(Color.red);
            ok= false;
        }
        return ok; //cej nakonc use true -> vrn true
 
    }
    
    private void datoteka_shraniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datoteka_shraniActionPerformed

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Ali želite imeti shranjene vse podatke?","Pozor!",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            if (!checkValues()) {
                JOptionPane.showMessageDialog(null,"Niste vnesli vseh polj- nevnešena so pobarvana z barvo","Neizpolnjena polja",JOptionPane.WARNING_MESSAGE);
                return;
            }
            else JOptionPane.showMessageDialog(null,"Vnesli ste vsa polja.","Izpolnjena polja",JOptionPane.INFORMATION_MESSAGE);
        }
        
        
        String filename = JOptionPane.showInputDialog("Poimenujte datoteko");

        if(filename != null){
            JFileChooser savefile = new JFileChooser();
            savefile.setSelectedFile(new File(filename));
            BufferedWriter writer;
            int sf = savefile.showSaveDialog(null);
            if(sf == JFileChooser.APPROVE_OPTION){
                try {
                    writer = new BufferedWriter(new FileWriter(savefile.getSelectedFile()));

                    Color c = vozilo_colorDisplay.getBackground();
                    if(c!=null){
                        String _vozilo_colorDisplay = Integer.toString(c.getRGB());

                        writer.write(_vozilo_colorDisplay);
                        writer.write("\n");

                    }

                    String _vozilo_spinner_moc = (Integer)vozilo_spinner_moc.getValue() + "";
                    writer.write(_vozilo_spinner_moc);
                    writer.write("\n");

                    String _zavarovanjec_field_priimek = zavarovanjec_field_priimek.getText();
                    String _zavarovanjec_field_ime = zavarovanjec_field_ime.getText();
                    String _zavarovanjec_field_posSt = zavarovanjec_field_posSt.getText();
                    String _zavarovanjec_field_kraj = zavarovanjec_field_kraj.getText();
                    String _zavarovanjec_field_ulica = zavarovanjec_field_ulica.getText();
                    writer.write(_zavarovanjec_field_priimek);
                    writer.write("\n");
                    writer.write(_zavarovanjec_field_ime);
                    writer.write("\n");
                    writer.write(_zavarovanjec_field_posSt);
                    writer.write("\n");
                    writer.write(_zavarovanjec_field_kraj);
                    writer.write("\n");
                    writer.write(_zavarovanjec_field_ulica);
                    writer.write("\n");

                    String _registracija_field_kfajPrveReg = registracija_field_kfajPrveReg.getText();
                    String _registracija_field_regOznacba = registracija_field_regOznacba.getText();
                    writer.write(_registracija_field_kfajPrveReg);
                    writer.write("\n");
                    writer.write(_registracija_field_regOznacba);
                    writer.write("\n");


                    String _vozilo_field_Sifra = vozilo_field_Sifra.getText();
                    String _vozilo_field_oznaka = vozilo_field_oznaka.getText();
                    String _vozilo_field_prostornina = vozilo_field_prostornina.getText();
                    writer.write(_vozilo_field_Sifra);
                    writer.write("\n");
                    writer.write(_vozilo_field_oznaka);
                    writer.write("\n");
                    writer.write(_vozilo_field_prostornina);
                    writer.write("\n");

                    Boolean _zavarovanje_check_gume = zavarovanje_check_gume.isSelected();
                    Boolean _zavarovanje_check_kraja = zavarovanje_check_kraja.isSelected();
                    Boolean _zavarovanje_check_parkirisce = zavarovanje_check_parkirisce.isSelected();
                    Boolean _zavarovanje_check_potniki = zavarovanje_check_potniki.isSelected();
                    Boolean _zavarovanje_check_stekla = zavarovanje_check_stekla.isSelected();
                    Boolean _zavarovanje_check_toca = zavarovanje_check_toca.isSelected();
                    Boolean _zavarovanje_check_tretjaOs = zavarovanje_check_tretjaOs .isSelected();
                    writer.write(_zavarovanje_check_gume+"");
                    writer.write("\n");
                    writer.write(_zavarovanje_check_kraja+"");
                    writer.write("\n");
                    writer.write(_zavarovanje_check_parkirisce+"");
                    writer.write("\n");
                    writer.write(_zavarovanje_check_potniki+"");
                    writer.write("\n");
                    writer.write(_zavarovanje_check_stekla+"");
                    writer.write("\n");
                    writer.write(_zavarovanje_check_toca+"");
                    writer.write("\n");
                    writer.write(_zavarovanje_check_tretjaOs+"");
                    writer.write("\n");

                    Boolean _zavarovanjec_rad_izkusen = zavarovanjec_rad_izkusen.isSelected();
                    Boolean _zavarovanjec_rad_mlad = zavarovanjec_rad_mlad.isSelected();
                    writer.write(_zavarovanjec_rad_izkusen+"");
                    writer.write("\n");
                    writer.write(_zavarovanjec_rad_mlad+"");
                    writer.write("\n");

                    Boolean _zavarovanje_rad_AO = zavarovanje_rad_AO.isSelected();
                    Boolean _zavarovanje_rad_AOPLUS = zavarovanje_rad_AOPLUS.isSelected();
                    Boolean _zavarovanje_rad_brez = zavarovanje_rad_brez.isSelected();
                    Boolean _zavarovanje_rad_odbFran = zavarovanje_rad_odbFran.isSelected();
                    Boolean _zavarovanje_rad_polno = zavarovanje_rad_polno.isSelected();
                    writer.write(_zavarovanje_rad_AO+"");
                    writer.write("\n");
                    writer.write(_zavarovanje_rad_AOPLUS+"");
                    writer.write("\n");
                    writer.write(_zavarovanje_rad_brez+"");
                    writer.write("\n");
                    writer.write(_zavarovanje_rad_odbFran+"");
                    writer.write("\n");
                    writer.write(_zavarovanje_rad_polno+"");
                    writer.write("\n");

                    String _zavarovanjec_field_hst = (String)zavarovanjec_field_hst.getText();
                    writer.write(_zavarovanjec_field_hst);
                    writer.write("\n");

                    String _vozilo_combo_gorivo = (String)vozilo_combo_gorivo.getSelectedItem();
                    String _vozilo_combo_znamka = (String)vozilo_combo_znamka.getSelectedItem();
                    writer.write(_vozilo_combo_gorivo);
                    writer.write("\n");
                    writer.write(_vozilo_combo_znamka);
                    writer.write("\n");

                    if (dat_rojstva_picker.getDate() != null){
                        String _dat_rojstva_picker =  dat_rojstva_picker.getDate().toString();
                        
                        
                        writer.write(_dat_rojstva_picker);
                        writer.write("\n"); 
                    }

                    writer.close();
                    JOptionPane.showMessageDialog(null, "Datoteka je shranjena","Uspešno shranjeno",JOptionPane.INFORMATION_MESSAGE);
                                  } catch (IOException e) {}
            }else if(sf == JFileChooser.CANCEL_OPTION)
                JOptionPane.showMessageDialog(null, "Shranjevanje datoteke je bilo prekinjeno");
        }else
            statusnaVrstica.setText("Za shranjevanje morate določiti ime datoteke.");
                
    }//GEN-LAST:event_datoteka_shraniActionPerformed

    private void datoteka_izhodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datoteka_izhodActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Ali res želite zapustiti program?","Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION)
            System.exit(0);
    }//GEN-LAST:event_datoteka_izhodActionPerformed
    private void popravi_item1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_popravi_item1ActionPerformed
        clear();
    }//GEN-LAST:event_popravi_item1ActionPerformed
    private void pomoc_AvtorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pomoc_AvtorActionPerformed
        String userDir = System.getProperty("user.dir");
        String filePath = userDir + File.separator + "src"+ File.separator + "sources"+ File.separator + "Jernej.jpg";
        BufferedImage image;
        try {
            image = ImageIO.read(new File(filePath));
            ImageIcon imIcon = new ImageIcon(image);
            JOptionPane.showMessageDialog(this,
                                        "Jernej Habjan\n63150106",
                                        "Avtor",
                                        JOptionPane.INFORMATION_MESSAGE,
                                        imIcon);
        } catch (IOException ex) {
            System.out.println("cannot read picture");
        }
    }//GEN-LAST:event_pomoc_AvtorActionPerformed

    private void zavarovanjec_field_posStKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_zavarovanjec_field_posStKeyTyped
    }//GEN-LAST:event_zavarovanjec_field_posStKeyTyped
    private void vozilo_button_izberiBarvoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vozilo_button_izberiBarvoActionPerformed
        Color textColor = Color.BLACK;
        Color color = JColorChooser.showDialog(null, "Zamenjajte barvo izbranega besedila", textColor);
        if(color != null) {
            vozilo_colorDisplay.setBackground(color);
            statusnaVrstica.setText("Spremenjena barva");
        }
    }//GEN-LAST:event_vozilo_button_izberiBarvoActionPerformed
    private void tool_OdpriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_OdpriActionPerformed
        datoteka_odpriActionPerformed(evt);
    }//GEN-LAST:event_tool_OdpriActionPerformed

    private void tool_ShraniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_ShraniActionPerformed
        datoteka_shraniActionPerformed(evt);
    }//GEN-LAST:event_tool_ShraniActionPerformed
    private void button_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_clearActionPerformed
            clear();
    }//GEN-LAST:event_button_clearActionPerformed

    private void zavarovanjec_field_posStActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zavarovanjec_field_posStActionPerformed
    }//GEN-LAST:event_zavarovanjec_field_posStActionPerformed
    private void zavarovanjec_field_ulicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zavarovanjec_field_ulicaActionPerformed
    }//GEN-LAST:event_zavarovanjec_field_ulicaActionPerformed
    private void zavarovanjec_field_hstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zavarovanjec_field_hstActionPerformed
    }//GEN-LAST:event_zavarovanjec_field_hstActionPerformed

    void readParseCSV(){
        String userDir = System.getProperty("user.dir");
        String csvFile = userDir + File.separator + "src"+ File.separator + "sources"+ File.separator + "PostneStevilke.csv";
        BufferedReader br = null;
        String line = "";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] country = line.split(";");
                kraji.put(Integer.parseInt(country[0]), country[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    void clear(){
        vozilo_colorDisplay.setBackground(Color.WHITE);
 
        vozilo_spinner_moc.setValue(0);

        zavarovanjec_field_priimek.setText("");
        zavarovanjec_field_ime.setText("");
        zavarovanjec_field_posSt.setText("");
        zavarovanjec_field_kraj.setText("");
        zavarovanjec_field_ulica.setText("");

        registracija_field_kfajPrveReg.setText("");
        registracija_field_regOznacba.setText("");

        vozilo_field_Sifra.setText("");
        vozilo_field_oznaka.setText("");
        vozilo_field_prostornina.setText("");

        zavarovanje_check_gume.setSelected(false);
        zavarovanje_check_kraja.setSelected(false);
        zavarovanje_check_parkirisce.setSelected(false);
        zavarovanje_check_potniki.setSelected(false);
        zavarovanje_check_stekla.setSelected(false);
        zavarovanje_check_toca.setSelected(false);
        zavarovanje_check_tretjaOs.setSelected(false);

        zavarovanjec_rad_izkusen.setSelected(true);
        zavarovanjec_rad_mlad.setSelected(false);

        zavarovanje_rad_AO.setSelected(true);
        zavarovanje_rad_AOPLUS.setSelected(false);
        zavarovanje_rad_brez.setSelected(false);
        zavarovanje_rad_odbFran.setSelected(false);
        zavarovanje_rad_polno.setSelected(true);

        zavarovanjec_field_hst.setText("");

        //zavarovanjec_combo_hSt.setSelectedItem("bencin");

        vozilo_combo_gorivo.setSelectedItem("bencin");
        //vozilo_combo_znamka.setSelectedItem(br.readLine().trim());

        dat_rojstva_picker.setDate(new Date());

        statusnaVrstica.setText("");
    }
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Domaca03().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_clear;
    private org.jdesktop.swingx.JXDatePicker dat_rojstva_picker;
    private javax.swing.JMenuItem datoteka_izhod;
    private javax.swing.JMenuItem datoteka_odpri;
    private javax.swing.JMenuItem datoteka_shrani;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.ButtonGroup kaskoGroup;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menu_datoteka;
    private javax.swing.JMenu menu_pomoc;
    private javax.swing.JMenu menu_popravi;
    private javax.swing.ButtonGroup osnovnoZavarovanjeGroup;
    private javax.swing.JPanel panel_main;
    private javax.swing.JPanel panel_registracija;
    private javax.swing.JPanel panel_vozilo;
    private javax.swing.JPanel panel_zavarovanec;
    private javax.swing.JPanel panel_zavarovanje;
    private javax.swing.JMenuItem pomoc_Avtor;
    private javax.swing.JMenuItem popravi_item1;
    private javax.swing.JTextField registracija_field_kfajPrveReg;
    private javax.swing.JTextField registracija_field_regOznacba;
    private javax.swing.JLabel registracija_label_datPrveReg;
    private javax.swing.JLabel registracija_label_krajPrveReg;
    private javax.swing.JLabel registracija_label_regOzn;
    private javax.swing.JLabel registracija_label_title;
    private javax.swing.JScrollPane scrollPane_StatusnaVrstica;
    private javax.swing.ButtonGroup starostVoznikaGroup;
    private javax.swing.JLabel statusnaVrstica;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JButton tool_Odpri;
    private javax.swing.JButton tool_Shrani;
    private javax.swing.JButton vozilo_button_izberiBarvo;
    private javax.swing.JPanel vozilo_colorDisplay;
    private javax.swing.JComboBox<String> vozilo_combo_gorivo;
    private javax.swing.JComboBox<String> vozilo_combo_znamka;
    private javax.swing.JTextField vozilo_field_Sifra;
    private javax.swing.JTextField vozilo_field_oznaka;
    private javax.swing.JTextField vozilo_field_prostornina;
    private javax.swing.JLabel vozilo_label_barva;
    private javax.swing.JLabel vozilo_label_gorivo;
    private javax.swing.JLabel vozilo_label_moc;
    private javax.swing.JLabel vozilo_label_oznaka;
    private javax.swing.JLabel vozilo_label_prostornina;
    private javax.swing.JLabel vozilo_label_sifra;
    private javax.swing.JLabel vozilo_label_title;
    private javax.swing.JLabel vozilo_label_znamka;
    private javax.swing.JSpinner vozilo_spinner_moc;
    private javax.swing.JCheckBox zavarovanje_check_gume;
    private javax.swing.JCheckBox zavarovanje_check_kraja;
    private javax.swing.JCheckBox zavarovanje_check_parkirisce;
    private javax.swing.JCheckBox zavarovanje_check_potniki;
    private javax.swing.JCheckBox zavarovanje_check_stekla;
    private javax.swing.JCheckBox zavarovanje_check_toca;
    private javax.swing.JCheckBox zavarovanje_check_tretjaOs;
    private javax.swing.JLabel zavarovanje_label__osnovno;
    private javax.swing.JLabel zavarovanje_label_dodatno;
    private javax.swing.JLabel zavarovanje_label_kasko;
    private javax.swing.JLabel zavarovanje_label_title;
    private javax.swing.JRadioButton zavarovanje_rad_AO;
    private javax.swing.JRadioButton zavarovanje_rad_AOPLUS;
    private javax.swing.JRadioButton zavarovanje_rad_brez;
    private javax.swing.JRadioButton zavarovanje_rad_odbFran;
    private javax.swing.JRadioButton zavarovanje_rad_polno;
    private javax.swing.JTextField zavarovanjec_field_hst;
    private javax.swing.JTextField zavarovanjec_field_ime;
    private javax.swing.JTextField zavarovanjec_field_kraj;
    private javax.swing.JTextField zavarovanjec_field_posSt;
    private javax.swing.JTextField zavarovanjec_field_priimek;
    private javax.swing.JTextField zavarovanjec_field_ulica;
    private javax.swing.JLabel zavarovanjec_label_datRoj;
    private javax.swing.JLabel zavarovanjec_label_hSt;
    private javax.swing.JLabel zavarovanjec_label_ime;
    private javax.swing.JLabel zavarovanjec_label_izkusnje;
    private javax.swing.JLabel zavarovanjec_label_kraj;
    private javax.swing.JLabel zavarovanjec_label_posSt;
    private javax.swing.JLabel zavarovanjec_label_priimek;
    private javax.swing.JLabel zavarovanjec_label_title;
    private javax.swing.JLabel zavarovanjec_label_ulica;
    private javax.swing.JRadioButton zavarovanjec_rad_izkusen;
    private javax.swing.JRadioButton zavarovanjec_rad_mlad;
    // End of variables declaration//GEN-END:variables
}
