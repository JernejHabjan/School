package DN04;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Avtek extends javax.swing.JFrame {
    String username = "";
    String password = "";
    Map<Integer, String> kraji = new HashMap<Integer, String>();
    Map<String, ArrayList> stranke_data = new HashMap<String, ArrayList>();
    
    String kreditna = "";
    String kriptiranaKreditna = "";
    public Avtek() {
        init();
    }
    
    public Avtek(String username, String password) {
        this.username = username;
        this.password = password;
        init();
        
    }
    
    void init(){
        initComponents();
        readStranke();
        
        readParseCSV();
        izposojevalec_field_posStEventListener();
        kreditna_event_listener();
        csv_event_listener();
        gotovinaChangeListener();
        velikostChangeListener();
        
        keyListeners();
        
        showNullView();
        
    }
    
    void showNullView(){
        view_ime_display.setVisible(false);
        view_priimek_display.setVisible(false);
        view_stDni_display.setVisible(false);
        view_avto_display.setVisible(false);
        view_dodatno_display.setVisible(false);
        view_prevzetiV_display.setVisible(false);
        view_oddatiV_display.setVisible(false);
        view_tipPlacila_display.setVisible(false);
        view_kartica_display.setVisible(false);
        view_cenaDan_display.setVisible(false);
        view_label_total.setVisible(false);
        kartica_label.setVisible(false);
        
    }
    
    
    void keyListeners(){   
  
        
        izposojevalec_field_dolVozIzp.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {izposojevalec_field_dolVozIzp.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {izposojevalec_field_dolVozIzp.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {izposojevalec_field_dolVozIzp.setBackground(Color.WHITE);}
        });
        izposojevalec_field_email.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {izposojevalec_field_email.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {izposojevalec_field_email.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {izposojevalec_field_email.setBackground(Color.WHITE);}
        });
        izposojevalec_field_ime.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {izposojevalec_field_ime.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {izposojevalec_field_ime.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {izposojevalec_field_ime.setBackground(Color.WHITE);}
        });
        izposojevalec_field_kraj.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {izposojevalec_field_kraj.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {izposojevalec_field_kraj.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {izposojevalec_field_kraj.setBackground(Color.WHITE);}
        });
        izposojevalec_field_naslov.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {izposojevalec_field_naslov.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {izposojevalec_field_naslov.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {izposojevalec_field_naslov.setBackground(Color.WHITE);}
        });
        izposojevalec_field_postnaSt.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {izposojevalec_field_postnaSt.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {izposojevalec_field_postnaSt.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {izposojevalec_field_postnaSt.setBackground(Color.WHITE);}
        });
        izposojevalec_field_priimek.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {izposojevalec_field_priimek.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {izposojevalec_field_priimek.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {izposojevalec_field_priimek.setBackground(Color.WHITE);}
        });
        izposojevalec_field_telefon.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {izposojevalec_field_telefon.setBackground(Color.WHITE);}
            public void removeUpdate(DocumentEvent e) {izposojevalec_field_telefon.setBackground(Color.WHITE);}
            public void changedUpdate(DocumentEvent e) {izposojevalec_field_telefon.setBackground(Color.WHITE);}
        });

    }
    
    
    void csv_event_listener(){
        placilo_field_CSV.getDocument().addDocumentListener(new DocumentListener() {
           
            public void check(){
                String text = placilo_field_CSV.getText();
                    if (text.length()> 4){
                        statusnaVrstica.setText("Vpisali ste preveč znakov");
                        placilo_field_CSV.setBackground(Color.YELLOW);
                    }
                    
                    try{
                        Integer.parseInt(text);
                    }catch(Exception e){
                        statusnaVrstica.setText("Vpisali ste neštevilski znak");
                        placilo_field_CSV.setBackground(Color.RED);
                    }
                    
            }      
            @Override
            public void insertUpdate(DocumentEvent e) {check();}
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = placilo_field_CSV.getText();
                if(text.length() < 4){
                    statusnaVrstica.setText("");
                    placilo_field_CSV.setBackground(Color.WHITE);
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                String text = placilo_field_CSV.getText();
                if(text.length() < 4){
                    statusnaVrstica.setText("");
                    placilo_field_CSV.setBackground(Color.WHITE);
                }
            }
        });
    }
    
    void kreditna_event_listener(){

        placilo_field_stKartice.getDocument().addDocumentListener(new DocumentListener() {
           
            public void check(){
                String text = placilo_field_stKartice.getText();
                    kreditna += text.substring(text.length() - 1);
                    if (text.length()> 16){
                        statusnaVrstica.setText("Vpisali ste preveč znakov");
                        placilo_field_stKartice.setBackground(Color.YELLOW);
                    }
            }      
            @Override
            public void insertUpdate(DocumentEvent e) {check();}
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = placilo_field_stKartice.getText();
                if(text.length() < 17){
                    statusnaVrstica.setText("");
                    placilo_field_stKartice.setBackground(Color.WHITE);
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                String text = placilo_field_stKartice.getText();
                if(text.length() < 17){
                    statusnaVrstica.setText("");
                    placilo_field_stKartice.setBackground(Color.WHITE);
                }
            }
        });
    }
    
    
    
    void velikostChangeListener(){
        Vector comboBoxItems=new Vector();
        comboBoxItems.add("Clio");
        comboBoxItems.add("Twingo");
        izposoja_combo_naVoljo.setModel(new DefaultComboBoxModel(comboBoxItems.toArray()));
        
        izposoja_combo_velikost.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                int index = izposoja_combo_velikost.getSelectedIndex();
                
                Vector comboBoxItems=new Vector();
                
                switch (index){
                    case 0:
                        comboBoxItems.add("Clio");
                        comboBoxItems.add("Twingo");


                        break;
                    case 1:
                        comboBoxItems.add("Audi A4");
                        comboBoxItems.add("Mitsubushi");

                        break;
                    case 2:
                        comboBoxItems.add("Masserati");
                        comboBoxItems.add("Subaru");

                        break;
                    default:
                        System.out.println("ERROR NO INDEX SELECTED VELIKOST ERROR");

                }
                    
                    
                izposoja_combo_naVoljo.setModel(new DefaultComboBoxModel(comboBoxItems.toArray()));

                
                
            }
        });
    }
    
    
    
    void gotovinaChangeListener(){
        placilo_panel_kartica.setVisible(false); //po defaultu je invisible
        
        placilo_combo_nacinPlacila.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
 
                if(placilo_combo_nacinPlacila.getSelectedIndex() == 0){ //GOTOVINA

                    placilo_panel_kartica.setVisible(false);
                }else{ //KARTICA

                    placilo_panel_kartica.setVisible(true);
                }
                
                
                
            }
        });
    }
    
            
    void izposojevalec_field_posStEventListener(){

        izposojevalec_field_postnaSt.getDocument().addDocumentListener(new DocumentListener() {
            public void check(){
                String text = izposojevalec_field_postnaSt.getText();
                if(text.length() < 4) return;
                else if(text.length() > 4) statusnaVrstica.setText("Veljavna poštna številka ima 4 znake.");
                try{
                    Integer pstevilka = Integer.parseInt(text);
                    String value = kraji.get(pstevilka);
                    if (value != null) 
                        izposojevalec_field_kraj.setText(kraji.get(pstevilka));
                    else{
                        statusnaVrstica.setText("Kraja ni mogoče najti");
                        izposojevalec_field_postnaSt.setBackground(Color.YELLOW);
                    }
                }catch (Exception a){
                    statusnaVrstica.setText("Vpisali ste neštevilski znak");
                    izposojevalec_field_postnaSt.setBackground(Color.RED);
                    return;
                }
            }
            @Override
            public void insertUpdate(DocumentEvent e) {check();}
            @Override
            public void removeUpdate(DocumentEvent e) {

                    statusnaVrstica.setText("");
                    izposojevalec_field_postnaSt.setBackground(Color.WHITE);}
                
                
            @Override
            public void changedUpdate(DocumentEvent e) {
                    statusnaVrstica.setText("");
                    izposojevalec_field_postnaSt.setBackground(Color.WHITE);
                
            }
            });
    }
    
    
    
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
    
   private void readStranke(){
       
        String userDir = System.getProperty("user.dir");
        String csvFile = userDir + File.separator + "src"+ File.separator + "sources"+ File.separator + "strankeData.txt";
   
        BufferedReader br = null;
        String line = "";
    
        try {
            br = new BufferedReader(new FileReader(csvFile));
       
            while ((line = br.readLine()) != null) {

                
                String[] user_pass = line.split(";");
                System.out.println(user_pass.length);
                ArrayList<String> data = new ArrayList<String>();
                for(int i = 1; i < user_pass.length; i++){
                    data.add(user_pass[i]);
                    //System.out.println(user_pass[i]);
                }

                stranke_data.put(user_pass[0], data);
                //System.out.println(user_pass[0]);
                
                
                
            }
        } catch (FileNotFoundException e) {System.out.println("filenotFoundException");} 
        catch (IOException e) {System.out.println("ioException");} 
        finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {System.out.println("ioException");}
            }
        }
       
   }
    
   
   void calcSUMTOTAL(){
       
  
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        String inputString1 = "23 01 1997"; //izposoja_datePicker_od.getDate()
        String inputString2 = "27 04 1997"; //izposoja_datePicker_do.getDate()
        int dni = 0;
        try {
            Date date1 = myFormat.parse(inputString1);
            Date date2 = myFormat.parse(inputString2);
            dni = Math.abs((int) (date2.getTime() - date1.getTime()));
            dni = (int) TimeUnit.DAYS.convert(dni, TimeUnit.MILLISECONDS);
            System.out.println ("Days: " + dni);
        } catch (ParseException e) {
            e.printStackTrace();
        }

       
        view_stDni_display.setText(dni+"");
        view_stDni_display.setVisible(true);
        int cenaAvtomobila =0;
       String avto = izposoja_combo_naVoljo.getSelectedItem().toString();
       if(avto.equals("Clio")){
           cenaAvtomobila = 50;
       }
       if(avto.equals("Twingo")){
           cenaAvtomobila = 50;
       }
       if(avto.equals("Audi A4")){
           cenaAvtomobila = 70;
       }
       if(avto.equals("Mitsubushi")){
           cenaAvtomobila = 80;
       }
       if(avto.equals("Masserati")){
           cenaAvtomobila = 120;
       }
       if(avto.equals("Subaru")){
           cenaAvtomobila = 110;
       }
       
       int cenaDodatnegaZavar = 2;
       
       
       view_cenaDan_display.setVisible(true);
       view_cenaDan_display.setText((cenaAvtomobila+cenaDodatnegaZavar) + "");
       
       int izracun;
       if (izposojevalec_check_dodatno.isSelected()){
           izracun= dni * cenaAvtomobila + dni * cenaDodatnegaZavar;
       }else{
           izracun= dni * cenaAvtomobila;
       }
        
       
       view_label_total.setText(izracun+"€");
       view_label_total.setVisible(true);
   }
   
   private void set_View_Labels(){
        String _izposojevalec_field_ime = izposojevalec_field_ime.getText();
        String _izposojevalec_field_priimek=izposojevalec_field_priimek.getText();

        String avto = izposoja_combo_naVoljo.getSelectedItem().toString();
        String dodatnoDisplay = Boolean.toString(izposojevalec_check_dodatno.isSelected());
        
        if(!_izposojevalec_field_ime.equals("")){
            view_ime_display.setVisible(true);
            view_ime_display.setText(_izposojevalec_field_ime);
        }
       
        if(!_izposojevalec_field_priimek.equals("")){
            view_priimek_display.setVisible(true);
            view_priimek_display.setText(_izposojevalec_field_priimek);
        }
              
        if(avto != null || !avto.equals("")){
            view_avto_display.setVisible(true);
            view_avto_display.setText(avto);
        }
       
       view_dodatno_display.setVisible(true);
       view_dodatno_display.setText(dodatnoDisplay);
       
           
       view_prevzetiV_display.setVisible(true);
       view_prevzetiV_display.setText(izposoja_combo_prevzeti.getSelectedItem().toString());
       
           
       view_oddatiV_display.setVisible(true);
       view_oddatiV_display.setText(izposoja_combo_oddati.getSelectedItem().toString());
       
       String tipPlacila = placilo_combo_nacinPlacila.getSelectedItem().toString();
                  
       view_tipPlacila_display.setVisible(true);
       view_tipPlacila_display.setText(tipPlacila);
       
       if(tipPlacila.equals("Kreditna kartica")){
           kartica_label.setVisible(true);
           
           
           view_kartica_display.setVisible(true);
           
           String karticaKoda = placilo_field_stKartice.getText().toString();
           String kriptirana = "";
           for(int i = 0; i < karticaKoda.length()-4; i++){
               if(i > 0 && i%4 == 0) kriptirana+="-";
               kriptirana +="X";
           }
           kriptirana+="-";
           for(int i = karticaKoda.length()-4; i < karticaKoda.length(); i++){
               kriptirana +=karticaKoda.charAt(i);
           }
           
           view_kartica_display.setText(kriptirana);
       }else{
           kartica_label.setVisible(false);
           view_kartica_display.setVisible(false);
       }
   
        calcSUMTOTAL();
   } 
   
   
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_menjalnik = new javax.swing.ButtonGroup();
        scrollPane_StatusnaVrstica = new javax.swing.JScrollPane();
        statusnaVrstica = new javax.swing.JLabel();
        toolBar = new javax.swing.JToolBar();
        tool_Odpri = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        tool_Shrani = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        button_clear = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        jButton1 = new javax.swing.JButton();
        tabbedPane = new javax.swing.JTabbedPane();
        panel_izposojaAvtomobila = new javax.swing.JPanel();
        izposoja_label_izposojaAvtomobila = new javax.swing.JLabel();
        izposoja_label_izposojaOd = new javax.swing.JLabel();
        izposoja_label_izposojaDo = new javax.swing.JLabel();
        izposoja_label_prevzetiV = new javax.swing.JLabel();
        izposoja_label_oddatiV = new javax.swing.JLabel();
        izposoja_combo_prevzeti = new javax.swing.JComboBox<>();
        izposoja_combo_oddati = new javax.swing.JComboBox<>();
        izposoja_label_velikost = new javax.swing.JLabel();
        izposoja_combo_naVoljo = new javax.swing.JComboBox<>();
        izposoja_label_naVoljo = new javax.swing.JLabel();
        izposoja_rad_menjalnik_rocni = new javax.swing.JRadioButton();
        izposoja_rad_menjalnik_avtomatski = new javax.swing.JRadioButton();
        izposoja_label_menjalnik = new javax.swing.JLabel();
        izposoja_combo_vrstaGoriva = new javax.swing.JComboBox<>();
        izposoja_label_vrstaGoriva = new javax.swing.JLabel();
        izposoja_combo_velikost = new javax.swing.JComboBox<>();
        izposoja_datePicker_od = new org.jdesktop.swingx.JXDatePicker();
        izposoja_datePicker_do = new org.jdesktop.swingx.JXDatePicker();
        panel_izposojevalec = new javax.swing.JPanel();
        izposojevalec_label_izposojevalec = new javax.swing.JLabel();
        izposojevalec_label_ime = new javax.swing.JLabel();
        izposojevalec_label_priimek = new javax.swing.JLabel();
        izposojevalec_field_postnaSt = new javax.swing.JTextField();
        izposojevalec_field_kraj = new javax.swing.JTextField();
        izposojevalec_label_pSt = new javax.swing.JLabel();
        izposojevalec_label_kraj = new javax.swing.JLabel();
        izposojevalec_field_ime = new javax.swing.JTextField();
        izposojevalec_field_priimek = new javax.swing.JTextField();
        izposojevalec_label_naslov = new javax.swing.JLabel();
        izposojevalec_field_naslov = new javax.swing.JTextField();
        izposojevalec_label_email = new javax.swing.JLabel();
        telefon = new javax.swing.JLabel();
        izposojevalec_label_starost = new javax.swing.JLabel();
        izposojevalec_label_dolVozIzp = new javax.swing.JLabel();
        izposojevalec_field_email = new javax.swing.JTextField();
        izposojevalec_field_telefon = new javax.swing.JTextField();
        izposojevalec_field_dolVozIzp = new javax.swing.JTextField();
        izposojevalec_spinner_starost = new javax.swing.JSpinner();
        panel_placilo = new javax.swing.JPanel();
        placilo_combo_nacinPlacila = new javax.swing.JComboBox<>();
        placilo_label_nacinPlacila = new javax.swing.JLabel();
        placilo_panel_kartica = new javax.swing.JPanel();
        placilo_field_stKartice = new javax.swing.JTextField();
        placilo_field_CSV = new javax.swing.JTextField();
        placilo_label_stevilkaKartice = new javax.swing.JLabel();
        placilo_label_CSV = new javax.swing.JLabel();
        placilo_button_placilo = new javax.swing.JButton();
        placilo_label_placilo = new javax.swing.JLabel();
        izposojevalec_check_dodatno = new javax.swing.JCheckBox();
        izposojevalec_label_dodatkoZav = new javax.swing.JLabel();
        panelView = new javax.swing.JPanel();
        view_label_povzetek = new javax.swing.JLabel();
        view_label_ime = new javax.swing.JLabel();
        view_label_priimek = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        kartica_label = new javax.swing.JLabel();
        view_label_total = new javax.swing.JLabel();
        view_ime_display = new javax.swing.JLabel();
        view_priimek_display = new javax.swing.JLabel();
        view_stDni_display = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        view_avto_display = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dgs = new javax.swing.JLabel();
        view_dodatno_display = new javax.swing.JLabel();
        view_cenaDan_display = new javax.swing.JLabel();
        view_prevzetiV_display = new javax.swing.JLabel();
        view_oddatiV_display = new javax.swing.JLabel();
        view_kartica_display = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        view_tipPlacila_display = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menu_datoteka = new javax.swing.JMenu();
        datoteka_odpri = new javax.swing.JMenuItem();
        datoteka_shrani = new javax.swing.JMenuItem();
        datoteka_izhod1 = new javax.swing.JMenuItem();
        datoteka_izhod = new javax.swing.JMenuItem();
        menu_orodja = new javax.swing.JMenu();
        popravi_item1 = new javax.swing.JMenuItem();
        menu_pomoc = new javax.swing.JMenu();
        pomoc_Avtor = new javax.swing.JMenuItem();
        pomoc_pomoc = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        statusnaVrstica.setText(" ");
        scrollPane_StatusnaVrstica.setViewportView(statusnaVrstica);

        toolBar.setRollover(true);

        tool_Odpri.setMnemonic('O');
        tool_Odpri.setText("Naloži podatke");
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
        tool_Shrani.setText("Shrani podatke");
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
        toolBar.add(jSeparator7);
        toolBar.add(jSeparator8);

        jButton1.setText("Odjava");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        toolBar.add(jButton1);

        tabbedPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        izposoja_label_izposojaAvtomobila.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        izposoja_label_izposojaAvtomobila.setText("Izposoja avtomobila");

        izposoja_label_izposojaOd.setText("Izposoja od");

        izposoja_label_izposojaDo.setText("Izposoja do");

        izposoja_label_prevzetiV.setText("Prevzeti v");

        izposoja_label_oddatiV.setText("Oddati v");

        izposoja_combo_prevzeti.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ljubljana", "Maribor", "Celje", "Kranj", "Velenje", "Koper", "Novo Mesto", "Murska Sobota", "Jesenice", "Portorož", "letališče Brnik", "letališče Maribor" }));

        izposoja_combo_oddati.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ljubljana", "Maribor", "Celje", "Kranj", "Velenje", "Koper", "Novo Mesto", "Murska Sobota", "Jesenice", "Portorož", "letališče Brnik", "letališče Maribor" }));

        izposoja_label_velikost.setText("Velikost");

        izposoja_combo_naVoljo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        izposoja_label_naVoljo.setText("Na voljo");

        buttonGroup_menjalnik.add(izposoja_rad_menjalnik_rocni);
        izposoja_rad_menjalnik_rocni.setSelected(true);
        izposoja_rad_menjalnik_rocni.setText("Ročni");

        buttonGroup_menjalnik.add(izposoja_rad_menjalnik_avtomatski);
        izposoja_rad_menjalnik_avtomatski.setText("Avtomatski");

        izposoja_label_menjalnik.setText("Menjalnik");

        izposoja_combo_vrstaGoriva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "bencin", "diesel", "hibridni pogon", "bencin + plin", "e-pogon" }));

        izposoja_label_vrstaGoriva.setText("Vrsta goriva");

        izposoja_combo_velikost.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Majhen", "Srednji", "Velik" }));
        izposoja_combo_velikost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izposoja_combo_velikostActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_izposojaAvtomobilaLayout = new javax.swing.GroupLayout(panel_izposojaAvtomobila);
        panel_izposojaAvtomobila.setLayout(panel_izposojaAvtomobilaLayout);
        panel_izposojaAvtomobilaLayout.setHorizontalGroup(
            panel_izposojaAvtomobilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_izposojaAvtomobilaLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panel_izposojaAvtomobilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_izposojaAvtomobilaLayout.createSequentialGroup()
                        .addGroup(panel_izposojaAvtomobilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_izposojaAvtomobilaLayout.createSequentialGroup()
                                .addGroup(panel_izposojaAvtomobilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(izposoja_label_prevzetiV)
                                    .addComponent(izposoja_label_oddatiV)
                                    .addComponent(izposoja_label_naVoljo)
                                    .addComponent(izposoja_label_menjalnik)
                                    .addComponent(izposoja_label_vrstaGoriva))
                                .addGap(43, 43, 43))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_izposojaAvtomobilaLayout.createSequentialGroup()
                                .addComponent(izposoja_label_izposojaOd)
                                .addGap(45, 45, 45))
                            .addGroup(panel_izposojaAvtomobilaLayout.createSequentialGroup()
                                .addComponent(izposoja_label_velikost)
                                .addGap(65, 65, 65)))
                        .addGroup(panel_izposojaAvtomobilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panel_izposojaAvtomobilaLayout.createSequentialGroup()
                                .addComponent(izposoja_rad_menjalnik_rocni)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(izposoja_rad_menjalnik_avtomatski))
                            .addComponent(izposoja_combo_vrstaGoriva, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_izposojaAvtomobilaLayout.createSequentialGroup()
                                .addGroup(panel_izposojaAvtomobilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(izposoja_datePicker_od, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(izposoja_combo_prevzeti, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(izposoja_combo_oddati, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(izposoja_combo_velikost, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(63, 63, 63)
                                .addComponent(izposoja_label_izposojaDo))
                            .addComponent(izposoja_combo_naVoljo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(izposoja_datePicker_do, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 102, Short.MAX_VALUE))
                    .addGroup(panel_izposojaAvtomobilaLayout.createSequentialGroup()
                        .addComponent(izposoja_label_izposojaAvtomobila)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panel_izposojaAvtomobilaLayout.setVerticalGroup(
            panel_izposojaAvtomobilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_izposojaAvtomobilaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(izposoja_label_izposojaAvtomobila)
                .addGap(24, 24, 24)
                .addGroup(panel_izposojaAvtomobilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(izposoja_label_izposojaOd)
                    .addComponent(izposoja_datePicker_od, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(izposoja_label_izposojaDo)
                    .addComponent(izposoja_datePicker_do, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(panel_izposojaAvtomobilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(izposoja_label_prevzetiV)
                    .addComponent(izposoja_combo_prevzeti, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(panel_izposojaAvtomobilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(izposoja_label_oddatiV)
                    .addComponent(izposoja_combo_oddati, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panel_izposojaAvtomobilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(izposoja_combo_velikost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(izposoja_label_velikost))
                .addGap(30, 30, 30)
                .addGroup(panel_izposojaAvtomobilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(izposoja_combo_naVoljo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(izposoja_label_naVoljo))
                .addGap(30, 30, 30)
                .addGroup(panel_izposojaAvtomobilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(izposoja_rad_menjalnik_rocni)
                    .addComponent(izposoja_rad_menjalnik_avtomatski)
                    .addComponent(izposoja_label_menjalnik))
                .addGap(18, 18, 18)
                .addGroup(panel_izposojaAvtomobilaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(izposoja_combo_vrstaGoriva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(izposoja_label_vrstaGoriva))
                .addContainerGap(141, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Izposoja", panel_izposojaAvtomobila);

        izposojevalec_label_izposojevalec.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        izposojevalec_label_izposojevalec.setText("Izposojevalec");

        izposojevalec_label_ime.setText("Ime");

        izposojevalec_label_priimek.setText("Priimek");

        izposojevalec_field_postnaSt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izposojevalec_field_postnaStActionPerformed(evt);
            }
        });
        izposojevalec_field_postnaSt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                izposojevalec_field_postnaStKeyTyped(evt);
            }
        });

        izposojevalec_label_pSt.setText("Poštna številka");

        izposojevalec_label_kraj.setText("Kraj");

        izposojevalec_field_priimek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izposojevalec_field_priimekActionPerformed(evt);
            }
        });

        izposojevalec_label_naslov.setText("Naslov");

        izposojevalec_field_naslov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izposojevalec_field_naslovActionPerformed(evt);
            }
        });

        izposojevalec_label_email.setText("Email");

        telefon.setText("Telefon");

        izposojevalec_label_starost.setText("Starost");

        izposojevalec_label_dolVozIzp.setText("Dolžina vozniškega izpita");

        izposojevalec_spinner_starost.setModel(new javax.swing.SpinnerNumberModel(18, 18, null, 1));

        javax.swing.GroupLayout panel_izposojevalecLayout = new javax.swing.GroupLayout(panel_izposojevalec);
        panel_izposojevalec.setLayout(panel_izposojevalecLayout);
        panel_izposojevalecLayout.setHorizontalGroup(
            panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_izposojevalecLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_izposojevalecLayout.createSequentialGroup()
                        .addComponent(telefon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 160, Short.MAX_VALUE)
                        .addComponent(izposojevalec_field_telefon, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_izposojevalecLayout.createSequentialGroup()
                        .addComponent(izposojevalec_label_email)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(izposojevalec_field_email, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_izposojevalecLayout.createSequentialGroup()
                        .addGroup(panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(izposojevalec_label_kraj)
                            .addComponent(izposojevalec_label_pSt)
                            .addComponent(izposojevalec_label_priimek)
                            .addComponent(izposojevalec_label_ime)
                            .addComponent(izposojevalec_label_izposojevalec)
                            .addComponent(izposojevalec_label_naslov))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(izposojevalec_field_kraj)
                            .addComponent(izposojevalec_field_postnaSt, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(izposojevalec_field_priimek)
                            .addComponent(izposojevalec_field_ime)
                            .addComponent(izposojevalec_field_naslov)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_izposojevalecLayout.createSequentialGroup()
                        .addGroup(panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(izposojevalec_label_dolVozIzp)
                            .addComponent(izposojevalec_label_starost))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(izposojevalec_field_dolVozIzp, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(izposojevalec_spinner_starost))))
                .addGap(168, 168, 168))
        );
        panel_izposojevalecLayout.setVerticalGroup(
            panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_izposojevalecLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(izposojevalec_label_izposojevalec)
                .addGap(23, 23, 23)
                .addGroup(panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(izposojevalec_label_ime)
                    .addComponent(izposojevalec_field_ime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(izposojevalec_label_priimek)
                    .addComponent(izposojevalec_field_priimek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(izposojevalec_field_postnaSt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(izposojevalec_label_pSt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_izposojevalecLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(izposojevalec_field_kraj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(izposojevalec_label_kraj))
                .addGap(18, 18, 18)
                .addGroup(panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(izposojevalec_label_naslov)
                    .addComponent(izposojevalec_field_naslov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(izposojevalec_label_email)
                    .addComponent(izposojevalec_field_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(telefon)
                    .addComponent(izposojevalec_field_telefon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(izposojevalec_label_starost)
                    .addComponent(izposojevalec_spinner_starost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_izposojevalecLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(izposojevalec_label_dolVozIzp)
                    .addComponent(izposojevalec_field_dolVozIzp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(114, Short.MAX_VALUE))
        );

        tabbedPane.addTab("Izposojevalec", panel_izposojevalec);

        placilo_combo_nacinPlacila.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gotovina", "Kreditna kartica" }));

        placilo_label_nacinPlacila.setText("Način plačila");

        placilo_field_stKartice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placilo_field_stKarticeActionPerformed(evt);
            }
        });

        placilo_label_stevilkaKartice.setText("Številka kartice");

        placilo_label_CSV.setText("CSV");

        javax.swing.GroupLayout placilo_panel_karticaLayout = new javax.swing.GroupLayout(placilo_panel_kartica);
        placilo_panel_kartica.setLayout(placilo_panel_karticaLayout);
        placilo_panel_karticaLayout.setHorizontalGroup(
            placilo_panel_karticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(placilo_panel_karticaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(placilo_panel_karticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(placilo_field_stKartice, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(placilo_label_stevilkaKartice))
                .addGap(62, 62, 62)
                .addGroup(placilo_panel_karticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(placilo_panel_karticaLayout.createSequentialGroup()
                        .addComponent(placilo_label_CSV)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(placilo_panel_karticaLayout.createSequentialGroup()
                        .addComponent(placilo_field_CSV, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(96, Short.MAX_VALUE))))
        );
        placilo_panel_karticaLayout.setVerticalGroup(
            placilo_panel_karticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(placilo_panel_karticaLayout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(placilo_panel_karticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(placilo_label_stevilkaKartice)
                    .addComponent(placilo_label_CSV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(placilo_panel_karticaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(placilo_field_stKartice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(placilo_field_CSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        placilo_button_placilo.setText("Potrdi plačilo");
        placilo_button_placilo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placilo_button_placiloActionPerformed(evt);
            }
        });

        placilo_label_placilo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        placilo_label_placilo.setText("Plačilo");

        izposojevalec_check_dodatno.setText("2€/dan");
        izposojevalec_check_dodatno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izposojevalec_check_dodatnoActionPerformed(evt);
            }
        });

        izposojevalec_label_dodatkoZav.setText("Dodatno zavarovanje");

        javax.swing.GroupLayout panel_placiloLayout = new javax.swing.GroupLayout(panel_placilo);
        panel_placilo.setLayout(panel_placiloLayout);
        panel_placiloLayout.setHorizontalGroup(
            panel_placiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_placiloLayout.createSequentialGroup()
                .addGroup(panel_placiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_placiloLayout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(placilo_button_placilo, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_placiloLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(panel_placiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(placilo_label_placilo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_placiloLayout.createSequentialGroup()
                                .addGroup(panel_placiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(izposojevalec_label_dodatkoZav)
                                    .addComponent(placilo_label_nacinPlacila))
                                .addGap(167, 167, 167)
                                .addGroup(panel_placiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(placilo_combo_nacinPlacila, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(izposojevalec_check_dodatno)))
                            .addComponent(placilo_panel_kartica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        panel_placiloLayout.setVerticalGroup(
            panel_placiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_placiloLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(placilo_label_placilo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_placiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(izposojevalec_label_dodatkoZav, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(izposojevalec_check_dodatno))
                .addGap(18, 18, 18)
                .addGroup(panel_placiloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(placilo_label_nacinPlacila)
                    .addComponent(placilo_combo_nacinPlacila, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(placilo_panel_kartica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(placilo_button_placilo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(76, 76, 76))
        );

        tabbedPane.addTab("Plačilo", panel_placilo);

        view_label_povzetek.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        view_label_povzetek.setText("Povzetek");

        view_label_ime.setText("ime");

        view_label_priimek.setText("priimek");

        jLabel29.setText("Število dni izposoje");

        kartica_label.setText("Kartica");

        view_label_total.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        view_label_total.setText("SKUPNA VSOTA");

        view_ime_display.setText("Privzeta vrednost");

        view_priimek_display.setText("Privzeta vrednost");

        view_stDni_display.setText("Privzeta vrednost");

        jLabel4.setText("Avto");

        view_avto_display.setText("Privzeta vrednost");

        jLabel6.setText("Dodatno zavarovanje");

        jLabel7.setText("Cena izposoje na dan");

        jLabel8.setText("Oddati v");

        dgs.setText("Prevzeti v");

        view_dodatno_display.setText("Privzeta vrednost");

        view_cenaDan_display.setText("Privzeta vrednost");

        view_prevzetiV_display.setText("Privzeta vrednost");

        view_oddatiV_display.setText("Privzeta vrednost");

        view_kartica_display.setText("Privzeta vrednost");

        jLabel15.setText("Tip plačila");

        view_tipPlacila_display.setText("Privzeta vrednost");

        javax.swing.GroupLayout panelViewLayout = new javax.swing.GroupLayout(panelView);
        panelView.setLayout(panelViewLayout);
        panelViewLayout.setHorizontalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(view_label_priimek)
                            .addComponent(view_label_ime)
                            .addComponent(view_label_povzetek)
                            .addComponent(jLabel29)
                            .addComponent(jLabel4))
                        .addGap(84, 84, 84)
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(view_priimek_display)
                            .addComponent(view_ime_display)
                            .addComponent(view_stDni_display)
                            .addComponent(view_avto_display))
                        .addContainerGap(136, Short.MAX_VALUE))
                    .addGroup(panelViewLayout.createSequentialGroup()
                        .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(view_label_total)
                            .addGroup(panelViewLayout.createSequentialGroup()
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(dgs)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel6))
                                .addGap(75, 75, 75)
                                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(view_tipPlacila_display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(view_kartica_display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(view_prevzetiV_display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(view_dodatno_display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(view_cenaDan_display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(view_oddatiV_display, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(kartica_label, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        panelViewLayout.setVerticalGroup(
            panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViewLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(view_label_povzetek)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(view_label_ime)
                    .addComponent(view_ime_display))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(view_label_priimek)
                    .addComponent(view_priimek_display))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(view_stDni_display))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(view_avto_display))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(view_dodatno_display))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dgs)
                    .addComponent(view_prevzetiV_display))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(view_oddatiV_display))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(view_tipPlacila_display))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kartica_label)
                    .addComponent(view_kartica_display))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(view_cenaDan_display))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(view_label_total)
                .addGap(87, 87, 87))
        );

        menu_datoteka.setText("Datoteka");

        datoteka_odpri.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        datoteka_odpri.setMnemonic('O');
        datoteka_odpri.setText("Naloži podatke");
        datoteka_odpri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datoteka_odpriActionPerformed(evt);
            }
        });
        menu_datoteka.add(datoteka_odpri);

        datoteka_shrani.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        datoteka_shrani.setMnemonic('S');
        datoteka_shrani.setText("Shrani podatke");
        datoteka_shrani.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datoteka_shraniActionPerformed(evt);
            }
        });
        menu_datoteka.add(datoteka_shrani);

        datoteka_izhod1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        datoteka_izhod1.setMnemonic('O');
        datoteka_izhod1.setText("Odjava");
        datoteka_izhod1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datoteka_izhod1ActionPerformed(evt);
            }
        });
        menu_datoteka.add(datoteka_izhod1);

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

        menu_orodja.setText("Orodja");

        popravi_item1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        popravi_item1.setMnemonic('P');
        popravi_item1.setText("Počisti polja");
        popravi_item1.setToolTipText("");
        popravi_item1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                popravi_item1ActionPerformed(evt);
            }
        });
        menu_orodja.add(popravi_item1);

        menuBar.add(menu_orodja);

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

        pomoc_pomoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        pomoc_pomoc.setMnemonic('P');
        pomoc_pomoc.setText("Pomoč");
        pomoc_pomoc.setToolTipText("");
        pomoc_pomoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pomoc_pomocActionPerformed(evt);
            }
        });
        menu_pomoc.add(pomoc_pomoc);

        menuBar.add(menu_pomoc);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollPane_StatusnaVrstica)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scrollPane_StatusnaVrstica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Ali se res želite odjaviti?","Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            dispose(); //disposes bank screen
            
            Login info = new Login(username);
            info.setVisible(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void nalozi_podatke(){
        showNullView(); //ZBRISES USE LABLE
        
        JPanel panel = new JPanel();
        JLabel labeluser = new JLabel("Vnesite strankino ime:");
        JTextField text = new JTextField(10);
        JLabel labelPass = new JLabel("Vnesite strankin priimek:");
        JTextField surn = new JTextField(10);
        panel.add(labeluser);
        panel.add(text);
        panel.add(labelPass);
        panel.add(surn);
        String[] options = new String[]{"Potrdi", "Prekliči"};
        int option = JOptionPane.showOptionDialog(null, panel, "Strankini podatki",
                                 JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                 null, options, options[1]);
        if(option == 0){ // pressing OK button
        
            String username = new String(text.getText());
            String priimek = new String(surn.getText());
            
            if(username.trim().equals("") || priimek.trim().equals("")){
                JOptionPane .showMessageDialog(null, "Napačen format strankinega imena ali priimka- vsebovati morata vsaj po 1 znak. Popravitev podatke in poskusite ponovno","Napaka pri izbiri stranke",JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.println(username);
            System.out.println(priimek);
         
            
            for (String name: stranke_data.keySet()){

                String key =name;
           
                System.out.println(key);  


            } 

            
            
            
            ArrayList <String> strankiniPodatki = stranke_data.get(username+priimek);
            if(strankiniPodatki == null){
                JOptionPane .showMessageDialog(null, "Stranka ni vnešena v bazo","Napaka pri izbiri stranke - nevnešena stranka",JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            
            for(String str: strankiniPodatki){
                System.out.println(str);
            }
        
            String _izposojevalec_field_dolVozIzp = strankiniPodatki.get(1);
            String _izposojevalec_field_email  = strankiniPodatki.get(2);
            String _izposojevalec_field_ime  = strankiniPodatki.get(3);
            String _izposojevalec_field_kraj  = strankiniPodatki.get(4);
            String _izposojevalec_field_naslov = strankiniPodatki.get(5);
            String _izposojevalec_field_postnaSt  = strankiniPodatki.get(6);
            String _izposojevalec_field_priimek = strankiniPodatki.get(7);
            String _izposojevalec_field_telefon  = strankiniPodatki.get(8);
            izposojevalec_field_dolVozIzp.setText(_izposojevalec_field_dolVozIzp);
            izposojevalec_field_email.setText(_izposojevalec_field_email);
            izposojevalec_field_ime.setText(_izposojevalec_field_ime);
            izposojevalec_field_kraj.setText(_izposojevalec_field_kraj);
            izposojevalec_field_naslov.setText(_izposojevalec_field_naslov);
            izposojevalec_field_postnaSt.setText(_izposojevalec_field_postnaSt);
            izposojevalec_field_priimek.setText(_izposojevalec_field_priimek);
            izposojevalec_field_telefon.setText(_izposojevalec_field_telefon);

                    

            Boolean _izposoja_rad_menjalnik_avtomatski =  Boolean.parseBoolean(strankiniPodatki.get(9));
            Boolean _izposoja_rad_menjalnik_rocni = Boolean.parseBoolean(strankiniPodatki.get(10));
            izposoja_rad_menjalnik_avtomatski.setSelected(_izposoja_rad_menjalnik_avtomatski);
            izposoja_rad_menjalnik_rocni.setSelected(_izposoja_rad_menjalnik_rocni);
            
            
            String _izposoja_combo_naVoljo = strankiniPodatki.get(11);
            String _izposoja_combo_oddati = strankiniPodatki.get(12);
            String _izposoja_combo_prevzeti = strankiniPodatki.get(13);
            String _izposoja_combo_velikost = strankiniPodatki.get(14);
            String _izposoja_combo_vrstaGoriva = strankiniPodatki.get(15);
            String _placilo_combo_nacinPlacila = strankiniPodatki.get(16);
            izposoja_combo_naVoljo.setSelectedItem(_izposoja_combo_naVoljo);
            izposoja_combo_oddati.setSelectedItem(_izposoja_combo_oddati);
            izposoja_combo_prevzeti.setSelectedItem(_izposoja_combo_prevzeti);
            izposoja_combo_velikost.setSelectedItem(_izposoja_combo_velikost);
            izposoja_combo_vrstaGoriva.setSelectedItem(_izposoja_combo_vrstaGoriva);
            placilo_combo_nacinPlacila.setSelectedItem(_placilo_combo_nacinPlacila);
            
            
  
            Boolean _izposojevalec_check_dodatno = Boolean.parseBoolean(strankiniPodatki.get(17));
            izposojevalec_check_dodatno.setSelected(_izposojevalec_check_dodatno);
            
            /*String _izposojevalec_spinner_starost = strankiniPodatki.get(18);                           //TODO ŠE TE TAZADNE MORŠ!!!!!!!
            izposojevalec_spinner_starost.setValue(Integer.parseInt(_izposojevalec_spinner_starost));
            
            
            String _placilo_field_CSV= strankiniPodatki.get(19);
            String _placilo_field_stKartice = strankiniPodatki.get(20);
            placilo_field_CSV.setText(_placilo_field_CSV);
            placilo_field_stKartice.setText(_placilo_field_stKartice);
            */
                    
            statusnaVrstica.setText("Podatki o stranki uspešno naloženi!");
        }
    }
    
    
    
    
    private void datoteka_odpriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datoteka_odpriActionPerformed
        nalozi_podatke();
        
    }//GEN-LAST:event_datoteka_odpriActionPerformed

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
        
        //vozilo_combo_znamka.setModel(new DefaultComboBoxModel(comboBoxItems.toArray())); ----SET AFTER
    }  
        
    boolean checkValues(){
        Boolean ok = true;

        String _izposojevalec_field_dolVozIzp=  izposojevalec_field_dolVozIzp.getText();
        String _izposojevalec_field_email = izposojevalec_field_email.getText();
        String _izposojevalec_field_ime = izposojevalec_field_ime.getText();
        String _izposojevalec_field_kraj = izposojevalec_field_kraj.getText();
        String _izposojevalec_field_naslov= izposojevalec_field_naslov.getText();
        String _izposojevalec_field_postnaSt = izposojevalec_field_postnaSt.getText();
        String _izposojevalec_field_priimek=izposojevalec_field_priimek.getText();
        String _izposojevalec_field_telefon = izposojevalec_field_telefon.getText();

        int index = placilo_combo_nacinPlacila.getSelectedIndex();

        if(_izposojevalec_field_dolVozIzp.equals("")){
            izposojevalec_field_dolVozIzp.setBackground(Color.red);
            ok=  false;
        }
        if(_izposojevalec_field_email.equals("")){
            izposojevalec_field_email.setBackground(Color.red);
            ok=  false;
        }
        if(_izposojevalec_field_ime.equals("")){
            izposojevalec_field_ime.setBackground(Color.red);
            ok=  false;
        }
        if(_izposojevalec_field_kraj.equals("")){
            izposojevalec_field_kraj.setBackground(Color.red);
            ok=  false;
        }
        if(_izposojevalec_field_naslov.equals("")){
            izposojevalec_field_naslov.setBackground(Color.red);
            ok=  false;
        }
        if(_izposojevalec_field_postnaSt.equals("")){
            izposojevalec_field_postnaSt.setBackground(Color.red);
            ok=  false;
        }
        
        if(_izposojevalec_field_priimek.equals("")){
            izposojevalec_field_priimek.setBackground(Color.red);
            ok=  false;
        }
        
        
        if(_izposojevalec_field_telefon.equals("")){
            izposojevalec_field_telefon.setBackground(Color.red);
            ok=  false;
        }
        
        if(index == 1){ //mora vnesti še kartico
            String _placilo_field_CSV = placilo_field_CSV.getText();
            String _placilo_field_stKartice = placilo_field_stKartice.getText();
            
            if(_placilo_field_CSV.equals("")){
                placilo_field_CSV.setBackground(Color.red);
                ok=  false;
            }
            if(_placilo_field_stKartice.equals("")){
                placilo_field_stKartice.setBackground(Color.red);
                ok=  false;
            }
        }
        
        return ok;
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
        }else{ //CHECK ZA VSAJ IME, PRIIMEK - po tem se dela query
            String _izposojevalec_field_ime = izposojevalec_field_ime.getText();
            String _izposojevalec_field_priimek=izposojevalec_field_priimek.getText();
            boolean ok = true;
            
            if(_izposojevalec_field_ime.equals("")){
                izposojevalec_field_ime.setBackground(Color.red);
                ok=  false;
            }

            if(_izposojevalec_field_priimek.equals("")){
                izposojevalec_field_priimek.setBackground(Color.red);
                ok=  false;
            }
            
            if(ok == false){
                JOptionPane.showMessageDialog(null,"Za hranitev datoteke vnesite vsaj ime in priimek","Potrebovana ime in priimek",JOptionPane.ERROR_MESSAGE);
                return;
            }

            
        }

       String userDir = System.getProperty("user.dir");
       String filePath = userDir + File.separator + "src"+ File.separator + "sources"+ File.separator + "strankeData.txt";
       BufferedWriter writer;

        try {
            writer = new BufferedWriter(new FileWriter(new File(filePath)));

            //SHRANI ŠE VSE PREŠJME USERJE NAZAJ

            for(String key: stranke_data.keySet()){
                writer.write(key); //ključ iskanja je ime in priimek
                writer.write(";");
                ArrayList <String> dat = stranke_data.get(key);
                for(String val :dat){
                    writer.write(val); //ključ iskanja je ime in priimek
                    writer.write(";");
                }
                writer.write("\n");
            }





            //SHRANI SE TEGA USERJA

            String _izposojevalec_field_dolVozIzp=  izposojevalec_field_dolVozIzp.getText();
            String _izposojevalec_field_email = izposojevalec_field_email.getText();
            String _izposojevalec_field_ime = izposojevalec_field_ime.getText();
            String _izposojevalec_field_kraj = izposojevalec_field_kraj.getText();
            String _izposojevalec_field_naslov= izposojevalec_field_naslov.getText();
            String _izposojevalec_field_postnaSt = izposojevalec_field_postnaSt.getText();
            String _izposojevalec_field_priimek=izposojevalec_field_priimek.getText();
            String _izposojevalec_field_telefon = izposojevalec_field_telefon.getText();


            writer.write(_izposojevalec_field_ime + _izposojevalec_field_priimek); //ključ iskanja je ime in priimek
            writer.write(";");

            writer.write(_izposojevalec_field_dolVozIzp);
            writer.write(";");
            writer.write(_izposojevalec_field_email);
            writer.write(";");
            writer.write(_izposojevalec_field_ime);
            writer.write(";");
            writer.write(_izposojevalec_field_kraj);
            writer.write(";");
            writer.write(_izposojevalec_field_naslov);
            writer.write(";");
            writer.write(_izposojevalec_field_postnaSt);
            writer.write(";");
            writer.write(_izposojevalec_field_priimek);
            writer.write(";");
            writer.write(_izposojevalec_field_telefon);
            writer.write(";");


            Boolean _izposoja_rad_menjalnik_avtomatski = izposoja_rad_menjalnik_avtomatski.isSelected();
            Boolean _izposoja_rad_menjalnik_rocni = izposoja_rad_menjalnik_rocni.isSelected();
            writer.write(_izposoja_rad_menjalnik_avtomatski+"");
            writer.write(";");
            writer.write(_izposoja_rad_menjalnik_rocni+"");
            writer.write(";");


            String _izposoja_combo_naVoljo = izposoja_combo_naVoljo.getSelectedItem().toString();
            String _izposoja_combo_oddati = izposoja_combo_oddati.getSelectedItem().toString();
            String _izposoja_combo_prevzeti = izposoja_combo_prevzeti.getSelectedItem().toString();
            String _izposoja_combo_velikost = izposoja_combo_velikost.getSelectedItem().toString();
            String _izposoja_combo_vrstaGoriva = izposoja_combo_vrstaGoriva.getSelectedItem().toString();
            String _placilo_combo_nacinPlacila = placilo_combo_nacinPlacila.getSelectedItem().toString();
            writer.write(_izposoja_combo_naVoljo);
            writer.write(";");
            writer.write(_izposoja_combo_oddati);
            writer.write(";");
            writer.write(_izposoja_combo_prevzeti);
            writer.write(";");
            writer.write(_izposoja_combo_velikost);
            writer.write(";");
            writer.write(_izposoja_combo_vrstaGoriva);
            writer.write(";");
            writer.write(_placilo_combo_nacinPlacila);
            writer.write(";");


            Boolean _izposojevalec_check_dodatno = izposojevalec_check_dodatno.isSelected();
            writer.write(_izposojevalec_check_dodatno+"");
            writer.write(";");

            String _izposojevalec_spinner_starost = (Integer)izposojevalec_spinner_starost.getValue() + "";
            writer.write(_izposojevalec_spinner_starost);
            writer.write(";");


            String _placilo_field_CSV=placilo_field_CSV.getText();
            String _placilo_field_stKartice = placilo_field_stKartice.getText();
            writer.write(_placilo_field_CSV);
            writer.write(";");
            writer.write(_placilo_field_stKartice);
            writer.write(";");

            writer.write("\n");

            writer.close();
            JOptionPane.showMessageDialog(null, "Datoteka je shranjena","Uspešno shranjeno",JOptionPane.INFORMATION_MESSAGE);
            
            statusnaVrstica.setText("Podatki o stranki uspešno shranjeni!");
            readStranke();
            
        } catch (IOException e) {}

    }//GEN-LAST:event_datoteka_shraniActionPerformed

    
    void clear(){
        
        
        izposoja_combo_naVoljo.setSelectedIndex(0);
        izposoja_combo_oddati.setSelectedIndex(0);
        izposoja_combo_prevzeti.setSelectedIndex(0);
        izposoja_combo_velikost.setSelectedIndex(0);
        izposoja_combo_vrstaGoriva.setSelectedIndex(0);
        izposoja_rad_menjalnik_avtomatski.setSelected(false);
        izposoja_rad_menjalnik_rocni.setSelected(true);



        izposojevalec_check_dodatno.setSelected(false);
        izposojevalec_field_dolVozIzp.setText("");
        izposojevalec_field_email.setText("");
        izposojevalec_field_ime.setText("");
        izposojevalec_field_kraj.setText("");
        izposojevalec_field_naslov.setText("");
        izposojevalec_field_postnaSt.setText("");
        izposojevalec_field_priimek.setText("");
        izposojevalec_field_telefon.setText("");
        izposojevalec_spinner_starost.setValue(18);

        placilo_combo_nacinPlacila.setSelectedIndex(0);
        placilo_field_CSV.setText("");
        placilo_field_stKartice.setText("");

        showNullView();




        statusnaVrstica.setText("Polja pobrisana");
    
    
    }
    
    
    
    
    
    private void datoteka_izhodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datoteka_izhodActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Ali se res želite odjaviti?","Warning",dialogButton);
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

    private void tool_OdpriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_OdpriActionPerformed
        datoteka_odpriActionPerformed(evt);
    }//GEN-LAST:event_tool_OdpriActionPerformed

    private void tool_ShraniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_ShraniActionPerformed
        datoteka_shraniActionPerformed(evt);
    }//GEN-LAST:event_tool_ShraniActionPerformed

    private void button_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_clearActionPerformed
        clear();
    }//GEN-LAST:event_button_clearActionPerformed

    private void izposojevalec_field_postnaStActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izposojevalec_field_postnaStActionPerformed

    }//GEN-LAST:event_izposojevalec_field_postnaStActionPerformed

    private void izposojevalec_field_postnaStKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_izposojevalec_field_postnaStKeyTyped

    }//GEN-LAST:event_izposojevalec_field_postnaStKeyTyped

    private void izposojevalec_field_priimekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izposojevalec_field_priimekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_izposojevalec_field_priimekActionPerformed

    private void izposojevalec_field_naslovActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izposojevalec_field_naslovActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_izposojevalec_field_naslovActionPerformed

    private void izposojevalec_check_dodatnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izposojevalec_check_dodatnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_izposojevalec_check_dodatnoActionPerformed

    private void placilo_field_stKarticeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placilo_field_stKarticeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_placilo_field_stKarticeActionPerformed

    private void pomoc_pomocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pomoc_pomocActionPerformed

        JOptionPane.showMessageDialog(null, "Za vnos stranke ki je že obiskala Avtek, pritisnite gumb Naloži podatke in sistem bo naložil njene podatke");

    }//GEN-LAST:event_pomoc_pomocActionPerformed

    private void placilo_button_placiloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placilo_button_placiloActionPerformed
        set_View_Labels();
        
        
        
    }//GEN-LAST:event_placilo_button_placiloActionPerformed

    private void izposoja_combo_velikostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izposoja_combo_velikostActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_izposoja_combo_velikostActionPerformed

    private void datoteka_izhod1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datoteka_izhod1ActionPerformed
       int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Ali se res želite odjaviti?","Warning",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            dispose(); //disposes bank screen
            
            Login info = new Login(username);
            info.setVisible(true);
        }
    }//GEN-LAST:event_datoteka_izhod1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Avtek().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup_menjalnik;
    private javax.swing.JButton button_clear;
    private javax.swing.JMenuItem datoteka_izhod;
    private javax.swing.JMenuItem datoteka_izhod1;
    private javax.swing.JMenuItem datoteka_odpri;
    private javax.swing.JMenuItem datoteka_shrani;
    private javax.swing.JLabel dgs;
    private javax.swing.JComboBox<String> izposoja_combo_naVoljo;
    private javax.swing.JComboBox<String> izposoja_combo_oddati;
    private javax.swing.JComboBox<String> izposoja_combo_prevzeti;
    private javax.swing.JComboBox<String> izposoja_combo_velikost;
    private javax.swing.JComboBox<String> izposoja_combo_vrstaGoriva;
    private org.jdesktop.swingx.JXDatePicker izposoja_datePicker_do;
    private org.jdesktop.swingx.JXDatePicker izposoja_datePicker_od;
    private javax.swing.JLabel izposoja_label_izposojaAvtomobila;
    private javax.swing.JLabel izposoja_label_izposojaDo;
    private javax.swing.JLabel izposoja_label_izposojaOd;
    private javax.swing.JLabel izposoja_label_menjalnik;
    private javax.swing.JLabel izposoja_label_naVoljo;
    private javax.swing.JLabel izposoja_label_oddatiV;
    private javax.swing.JLabel izposoja_label_prevzetiV;
    private javax.swing.JLabel izposoja_label_velikost;
    private javax.swing.JLabel izposoja_label_vrstaGoriva;
    private javax.swing.JRadioButton izposoja_rad_menjalnik_avtomatski;
    private javax.swing.JRadioButton izposoja_rad_menjalnik_rocni;
    private javax.swing.JCheckBox izposojevalec_check_dodatno;
    private javax.swing.JTextField izposojevalec_field_dolVozIzp;
    private javax.swing.JTextField izposojevalec_field_email;
    private javax.swing.JTextField izposojevalec_field_ime;
    private javax.swing.JTextField izposojevalec_field_kraj;
    private javax.swing.JTextField izposojevalec_field_naslov;
    private javax.swing.JTextField izposojevalec_field_postnaSt;
    private javax.swing.JTextField izposojevalec_field_priimek;
    private javax.swing.JTextField izposojevalec_field_telefon;
    private javax.swing.JLabel izposojevalec_label_dodatkoZav;
    private javax.swing.JLabel izposojevalec_label_dolVozIzp;
    private javax.swing.JLabel izposojevalec_label_email;
    private javax.swing.JLabel izposojevalec_label_ime;
    private javax.swing.JLabel izposojevalec_label_izposojevalec;
    private javax.swing.JLabel izposojevalec_label_kraj;
    private javax.swing.JLabel izposojevalec_label_naslov;
    private javax.swing.JLabel izposojevalec_label_pSt;
    private javax.swing.JLabel izposojevalec_label_priimek;
    private javax.swing.JLabel izposojevalec_label_starost;
    private javax.swing.JSpinner izposojevalec_spinner_starost;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JLabel kartica_label;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menu_datoteka;
    private javax.swing.JMenu menu_orodja;
    private javax.swing.JMenu menu_pomoc;
    private javax.swing.JPanel panelView;
    private javax.swing.JPanel panel_izposojaAvtomobila;
    private javax.swing.JPanel panel_izposojevalec;
    private javax.swing.JPanel panel_placilo;
    private javax.swing.JButton placilo_button_placilo;
    private javax.swing.JComboBox<String> placilo_combo_nacinPlacila;
    private javax.swing.JTextField placilo_field_CSV;
    private javax.swing.JTextField placilo_field_stKartice;
    private javax.swing.JLabel placilo_label_CSV;
    private javax.swing.JLabel placilo_label_nacinPlacila;
    private javax.swing.JLabel placilo_label_placilo;
    private javax.swing.JLabel placilo_label_stevilkaKartice;
    private javax.swing.JPanel placilo_panel_kartica;
    private javax.swing.JMenuItem pomoc_Avtor;
    private javax.swing.JMenuItem pomoc_pomoc;
    private javax.swing.JMenuItem popravi_item1;
    private javax.swing.JScrollPane scrollPane_StatusnaVrstica;
    private javax.swing.JLabel statusnaVrstica;
    private javax.swing.JTabbedPane tabbedPane;
    private javax.swing.JLabel telefon;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JButton tool_Odpri;
    private javax.swing.JButton tool_Shrani;
    private javax.swing.JLabel view_avto_display;
    private javax.swing.JLabel view_cenaDan_display;
    private javax.swing.JLabel view_dodatno_display;
    private javax.swing.JLabel view_ime_display;
    private javax.swing.JLabel view_kartica_display;
    private javax.swing.JLabel view_label_ime;
    private javax.swing.JLabel view_label_povzetek;
    private javax.swing.JLabel view_label_priimek;
    private javax.swing.JLabel view_label_total;
    private javax.swing.JLabel view_oddatiV_display;
    private javax.swing.JLabel view_prevzetiV_display;
    private javax.swing.JLabel view_priimek_display;
    private javax.swing.JLabel view_stDni_display;
    private javax.swing.JLabel view_tipPlacila_display;
    // End of variables declaration//GEN-END:variables
}
