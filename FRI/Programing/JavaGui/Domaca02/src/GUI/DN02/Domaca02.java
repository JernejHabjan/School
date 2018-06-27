package GUI.DN02;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Domaca02 extends javax.swing.JFrame {

    Color textColor = Color.BLACK;
    Color textBgColor = Color.YELLOW;
    
    String searchWord = "";
    
    public Domaca02() {
        initComponents();

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator5 = new javax.swing.JSeparator();
        panel_ToolBar = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        tool_Odpri = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        tool_Shrani = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        tool_NajdiVse = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        tool_ZamenjajVse = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        tool_Papajscina = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        button_clear = new javax.swing.JButton();
        scrollPane_StatusnaVrstica = new javax.swing.JScrollPane();
        statusnaVrstica = new javax.swing.JLabel();
        panel_main = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        leftLabel = new javax.swing.JLabel();
        rightLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        leftTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        rightTextField = new javax.swing.JTextField();
        MenuVrstica = new javax.swing.JMenuBar();
        menu_datoteka = new javax.swing.JMenu();
        datoteka_odpri = new javax.swing.JMenuItem();
        datoteka_shrani = new javax.swing.JMenuItem();
        datoteka_izhod = new javax.swing.JMenuItem();
        menu_iskanje = new javax.swing.JMenu();
        iskanje_najdiVse = new javax.swing.JMenuItem();
        iskanje_zamenjajVse = new javax.swing.JMenuItem();
        iskanje_papajscina = new javax.swing.JMenuItem();
        menu_barve = new javax.swing.JMenu();
        barve_besedilo = new javax.swing.JMenuItem();
        barve_podlaga = new javax.swing.JMenuItem();
        menu_pomoc = new javax.swing.JMenu();
        pomoc_Avtor = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel_ToolBar.setLayout(new java.awt.BorderLayout());

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

        tool_NajdiVse.setMnemonic('N');
        tool_NajdiVse.setText("Najdi vse");
        tool_NajdiVse.setFocusable(false);
        tool_NajdiVse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_NajdiVse.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_NajdiVse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_NajdiVseActionPerformed(evt);
            }
        });
        toolBar.add(tool_NajdiVse);
        toolBar.add(jSeparator3);

        tool_ZamenjajVse.setMnemonic('Z');
        tool_ZamenjajVse.setText("Zamenjaj vse");
        tool_ZamenjajVse.setFocusable(false);
        tool_ZamenjajVse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_ZamenjajVse.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_ZamenjajVse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_ZamenjajVseActionPerformed(evt);
            }
        });
        toolBar.add(tool_ZamenjajVse);
        toolBar.add(jSeparator7);

        tool_Papajscina.setMnemonic('P');
        tool_Papajscina.setText("Papajščina");
        tool_Papajscina.setFocusable(false);
        tool_Papajscina.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Papajscina.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Papajscina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_PapajscinaActionPerformed(evt);
            }
        });
        toolBar.add(tool_Papajscina);
        toolBar.add(jSeparator4);
        toolBar.add(jSeparator6);

        button_clear.setMnemonic('P');
        button_clear.setText("Počisti polja");
        button_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_clearActionPerformed(evt);
            }
        });
        toolBar.add(button_clear);

        panel_ToolBar.add(toolBar, java.awt.BorderLayout.NORTH);

        getContentPane().add(panel_ToolBar, java.awt.BorderLayout.NORTH);

        statusnaVrstica.setText(" ");
        scrollPane_StatusnaVrstica.setViewportView(statusnaVrstica);

        getContentPane().add(scrollPane_StatusnaVrstica, java.awt.BorderLayout.PAGE_END);

        textPane.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textPaneMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(textPane);

        leftLabel.setText("Najdi");

        rightLabel.setText("Zamenjaj");

        leftTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leftTextFieldActionPerformed(evt);
            }
        });
        jScrollPane2.setViewportView(leftTextField);

        jScrollPane1.setViewportView(rightTextField);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(leftLabel)
                .addGap(73, 73, 73)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(rightLabel)
                .addGap(50, 50, 50)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rightLabel)
                    .addComponent(leftLabel))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout panel_mainLayout = new javax.swing.GroupLayout(panel_main);
        panel_main.setLayout(panel_mainLayout);
        panel_mainLayout.setHorizontalGroup(
            panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panel_mainLayout.setVerticalGroup(
            panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_mainLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
        );

        getContentPane().add(panel_main, java.awt.BorderLayout.CENTER);

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

        MenuVrstica.add(menu_datoteka);

        menu_iskanje.setText("Iskanje");

        iskanje_najdiVse.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        iskanje_najdiVse.setMnemonic('N');
        iskanje_najdiVse.setText("Najdi vse");
        iskanje_najdiVse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iskanje_najdiVseActionPerformed(evt);
            }
        });
        menu_iskanje.add(iskanje_najdiVse);

        iskanje_zamenjajVse.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        iskanje_zamenjajVse.setMnemonic('Z');
        iskanje_zamenjajVse.setText("Zamenjaj vse");
        iskanje_zamenjajVse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iskanje_zamenjajVseActionPerformed(evt);
            }
        });
        menu_iskanje.add(iskanje_zamenjajVse);

        iskanje_papajscina.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        iskanje_papajscina.setMnemonic('P');
        iskanje_papajscina.setText("Papajščina");
        iskanje_papajscina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iskanje_papajscinaActionPerformed(evt);
            }
        });
        menu_iskanje.add(iskanje_papajscina);

        MenuVrstica.add(menu_iskanje);

        menu_barve.setText("Barve");

        barve_besedilo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        barve_besedilo.setMnemonic('B');
        barve_besedilo.setText("Besedilo");
        barve_besedilo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barve_besediloActionPerformed(evt);
            }
        });
        menu_barve.add(barve_besedilo);

        barve_podlaga.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        barve_podlaga.setMnemonic('P');
        barve_podlaga.setText("Podlaga");
        barve_podlaga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barve_podlagaActionPerformed(evt);
            }
        });
        menu_barve.add(barve_podlaga);

        MenuVrstica.add(menu_barve);

        menu_pomoc.setText("Pomoč");
        menu_pomoc.setToolTipText("");

        pomoc_Avtor.setMnemonic('A');
        pomoc_Avtor.setText("Avtor");
        pomoc_Avtor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pomoc_AvtorActionPerformed(evt);
            }
        });
        menu_pomoc.add(pomoc_Avtor);

        MenuVrstica.add(menu_pomoc);

        setJMenuBar(MenuVrstica);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    File f;
    
    private void chooseFile(){
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        f = chooser.getSelectedFile();
        if(f !=null && f.getName().endsWith(".txt")){
            statusnaVrstica.setText("Ime datoteke: " +f.getName() +", Dolžina datoteke: "+ f.length() + " znakov.");
            try {
                textPane.setText(null);
                try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));) {
                    String line;
                    while ((line = br.readLine()) != null){
                        textPane.setText(textPane.getText()+ line + "\n");
                       
                    }
                    textPane.requestFocus();
                }
            }catch (IOException ex) {
                Logger.getLogger(Domaca02.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void datoteka_odpriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datoteka_odpriActionPerformed
        chooseFile();


    }//GEN-LAST:event_datoteka_odpriActionPerformed

    private void datoteka_shraniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datoteka_shraniActionPerformed
        String filename = JOptionPane.showInputDialog("Poimenujte datoteko");

        if(filename != null){
            JFileChooser savefile = new JFileChooser();
            savefile.setSelectedFile(new File(filename));
            BufferedWriter writer;
            int sf = savefile.showSaveDialog(null);
            if(sf == JFileChooser.APPROVE_OPTION){
                try {
                    writer = new BufferedWriter(new FileWriter(savefile.getSelectedFile()));
                    textPane.write(writer);
                    writer.close();
                    //JOptionPane.showMessageDialog(null, "File has been saved","File Saved",JOptionPane.INFORMATION_MESSAGE);
                    // true for rewrite, false for override
                    statusnaVrstica.setText("Datoteka - " + filename + " - je shranjena.");

                } catch (IOException e) {}
            }else if(sf == JFileChooser.CANCEL_OPTION){
                //JOptionPane.showMessageDialog(null, "File save has been canceled");

                statusnaVrstica.setText("Shranjevanje datoteke - "+ f.getName() +" - je bilo prekinjeno.");
            }

            textPane.requestFocus();
        }else{
            statusnaVrstica.setText("Za shranjevanje morate določiti ime datoteke.");
        }
        
    }//GEN-LAST:event_datoteka_shraniActionPerformed

    private void datoteka_izhodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datoteka_izhodActionPerformed
       
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Ali res želite zapustiti program?","Pozor!",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_datoteka_izhodActionPerformed

    private void higlightStuff(){
        String text = textPane.getText().toLowerCase();
        int highlighted = 0;
        Boolean first = true;
        int i = 0;
        if ((searchWord != null) && (searchWord.length() > 0)) {
            Highlighter.HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(textBgColor);
            
            //first remove all highlights:
            textPane.getHighlighter().removeAllHighlights();
            //then add new ones
            int offset = text.indexOf(searchWord);
            int length = searchWord.length();
            
            
            while ( offset != -1){
                try{
                     if(first) {
                        i = offset;
                        first = false;
                    }
                    
                    
                    textPane.getHighlighter().addHighlight(offset, offset + length, painter);
                    offset = text.indexOf(searchWord, offset + 1);
                    
                   
                    
                    highlighted++;
                }catch(BadLocationException ble) {
                    System.out.println(ble); 
                }
            }
            
        }
        
        textPane.grabFocus();
        textPane.moveCaretPosition(i);
        
        //JOptionPane.showMessageDialog(null,"Dokument vsebuje: " + highlighted + " besed.","Iskanje besed",JOptionPane.INFORMATION_MESSAGE);
        statusnaVrstica.setText("Dokument vsebuje: " + highlighted + " besed.");
        
    
    }
    
    private void iskanje_najdiVseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iskanje_najdiVseActionPerformed
        
        searchWord = leftTextField.getText();

        higlightStuff();
    }//GEN-LAST:event_iskanje_najdiVseActionPerformed

    void replace(){
        
        searchWord = leftTextField.getText().toLowerCase();
        String text = textPane.getText().toLowerCase();
        String replaceWord = rightTextField.getText().toLowerCase();


        int numChanged = 0;
        
        if ((searchWord != null) && (searchWord.length() > 0)    &&       (replaceWord != null) && (replaceWord.length() > 0)) {
            String replaceString = "";

            int offset = text.indexOf(searchWord);
            while ( offset != -1){
                replaceString = text.replace(searchWord,replaceWord);
                offset = text.indexOf(searchWord, offset + 1);
                ++numChanged;
            }
            textPane.setText(replaceString);
        }

        //JOptionPane.showMessageDialog(null,"Zamenjali ste: " + numChanged + " besed.","Zamenjava besed",JOptionPane.INFORMATION_MESSAGE);
        statusnaVrstica.setText("Zamenjali ste: " + numChanged + " besed.");
        
        
    }
    
    private void iskanje_zamenjajVseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iskanje_zamenjajVseActionPerformed
        replace();
        
        
    }//GEN-LAST:event_iskanje_zamenjajVseActionPerformed
    
    private void barve_besediloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barve_besediloActionPerformed
        
        Color c = JColorChooser.showDialog(null, "Zamenjajte barvo izbranega besedila", textColor);
        if(c != null) textColor = c;
        
        StyledDocument d = textPane.getStyledDocument();
        Style s = d.addStyle("BarvaOzadja", null);
        StyleConstants.setForeground(s,textColor);
        
        d.setCharacterAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), d.getStyle("BarvaOzadja"), false);
        statusnaVrstica.setText("Zamenjali ste barvo besedila na: " + textColor.toString());
    }//GEN-LAST:event_barve_besediloActionPerformed

    private void barve_podlagaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barve_podlagaActionPerformed
      
        Color c = JColorChooser.showDialog(null, "Zamenjajte barvo ozadja izbranega besedila",textBgColor);
        if(c != null) textBgColor = c;

        StyledDocument d = textPane.getStyledDocument();
        Style s = d.addStyle("BarvaOzadja", null);
        StyleConstants.setBackground(s,textBgColor);
        
        d.setCharacterAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), d.getStyle("BarvaOzadja"), false);
        statusnaVrstica.setText("Zamenjali ste barvo ozadja besedila na: " + textBgColor.toString());
    }//GEN-LAST:event_barve_podlagaActionPerformed

    private void pomoc_AvtorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pomoc_AvtorActionPerformed
        statusnaVrstica.setText("Jernej Habjan\n63150106");
        
        String userDir = System.getProperty("user.dir");
        String filePath = userDir + "\\src\\sources\\Jernej.jpg";
        
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
            Logger.getLogger(Domaca02.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_pomoc_AvtorActionPerformed

    private void button_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_clearActionPerformed
        leftTextField.setText(null);
        rightTextField.setText(null);
        textPane.setText(null);
    }//GEN-LAST:event_button_clearActionPerformed

    private void tool_NajdiVseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_NajdiVseActionPerformed
        iskanje_najdiVseActionPerformed(evt);
    }//GEN-LAST:event_tool_NajdiVseActionPerformed

    private void tool_ShraniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_ShraniActionPerformed
        datoteka_shraniActionPerformed(evt);
    }//GEN-LAST:event_tool_ShraniActionPerformed

    private void tool_OdpriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_OdpriActionPerformed
        datoteka_odpriActionPerformed(evt);
    }//GEN-LAST:event_tool_OdpriActionPerformed

    private void textPaneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textPaneMouseClicked
        
        if(evt.getClickCount()==2){
            chooseFile();
        }
    }//GEN-LAST:event_textPaneMouseClicked

    void papajscina(){
        String text = textPane.getText();
       
        String lastSamoglasnik = "a";
        
        StringBuilder sb = new StringBuilder(text);
        
        for (int i = 0; i < sb.length(); i++){
            
            String c = sb.charAt(i)+"";    
            switch (c){
                case "a":
                    sb.insert(i+1, "p"+ lastSamoglasnik);
                    lastSamoglasnik = "a";
                    i+=2;
                    break;
                case "e":
                    sb.insert(i+1, "p"+ lastSamoglasnik);
                    lastSamoglasnik = "e";
                    i+=2;
                    break;
                case "i":
                    sb.insert(i+1, "p"+ lastSamoglasnik);
                    lastSamoglasnik = "i";
                    i+=2;
                    break;
                case "o":
                    sb.insert(i+1, "p"+ lastSamoglasnik);
                    lastSamoglasnik = "o";
                    i+=2;
                    break;
                case "u":
                    sb.insert(i+1, "p"+ lastSamoglasnik);
                    lastSamoglasnik = "u";
                    i+=2;
                    break;
                case "A":
                    sb.insert(i+1, "p"+ lastSamoglasnik);
                    lastSamoglasnik = "A";
                    i+=2;
                    break;
                case "E":
                    sb.insert(i+1, "p"+ lastSamoglasnik);
                    lastSamoglasnik = "E";
                    i+=2;
                    break;
                case "I":
                    sb.insert(i+1, "p"+ lastSamoglasnik);
                    lastSamoglasnik = "I";
                    i+=2;
                    break;
                case "O":
                    sb.insert(i+1, "p"+ lastSamoglasnik);
                    lastSamoglasnik = "O";
                    i+=2;
                    break;
                case "U":
                    sb.insert(i+1, "p"+ lastSamoglasnik);
                    lastSamoglasnik = "U";
                    i+=2;
                    break;
            }
            textPane.setText(sb.toString());
        }
        if(!text.equals(""))
            statusnaVrstica.setText("Text je spremenjen v papajščino.");
        else
            statusnaVrstica.setText("Vpišite tekst v vnosno polje.");
    }
    
    private void tool_PapajscinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_PapajscinaActionPerformed
 
        papajscina();
    }//GEN-LAST:event_tool_PapajscinaActionPerformed

    private void tool_ZamenjajVseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_ZamenjajVseActionPerformed
        iskanje_zamenjajVseActionPerformed(evt);
    }//GEN-LAST:event_tool_ZamenjajVseActionPerformed

    private void iskanje_papajscinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iskanje_papajscinaActionPerformed
        papajscina();
    }//GEN-LAST:event_iskanje_papajscinaActionPerformed

    private void leftTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leftTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_leftTextFieldActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new Domaca02().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar MenuVrstica;
    private javax.swing.JMenuItem barve_besedilo;
    private javax.swing.JMenuItem barve_podlaga;
    private javax.swing.JButton button_clear;
    private javax.swing.JMenuItem datoteka_izhod;
    private javax.swing.JMenuItem datoteka_odpri;
    private javax.swing.JMenuItem datoteka_shrani;
    private javax.swing.JMenuItem iskanje_najdiVse;
    private javax.swing.JMenuItem iskanje_papajscina;
    private javax.swing.JMenuItem iskanje_zamenjajVse;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JLabel leftLabel;
    private javax.swing.JTextField leftTextField;
    private javax.swing.JMenu menu_barve;
    private javax.swing.JMenu menu_datoteka;
    private javax.swing.JMenu menu_iskanje;
    private javax.swing.JMenu menu_pomoc;
    private javax.swing.JPanel panel_ToolBar;
    private javax.swing.JPanel panel_main;
    private javax.swing.JMenuItem pomoc_Avtor;
    private javax.swing.JLabel rightLabel;
    private javax.swing.JTextField rightTextField;
    private javax.swing.JScrollPane scrollPane_StatusnaVrstica;
    private javax.swing.JLabel statusnaVrstica;
    private javax.swing.JTextPane textPane;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JButton tool_NajdiVse;
    private javax.swing.JButton tool_Odpri;
    private javax.swing.JButton tool_Papajscina;
    private javax.swing.JButton tool_Shrani;
    private javax.swing.JButton tool_ZamenjajVse;
    // End of variables declaration//GEN-END:variables
}
