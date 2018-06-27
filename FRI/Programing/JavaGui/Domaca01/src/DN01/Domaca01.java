package DN01;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;

public class Domaca01 extends javax.swing.JFrame {

    public Domaca01() {
        initComponents();
        spinnerEventListener();
        
        getRootPane().setDefaultButton(button_Execute); //DEFAULT BUTTON
    }

    void spinnerEventListener(){
        spinner.addChangeListener((ChangeEvent e) -> {
            int index = (int)spinner.getValue();
            if(index + 1 <= comboBox.getItemCount()){
                label_Buttons.setText(label_Buttons.getText() + comboBox.getItemAt(index));
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        panel_main = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();
        tool_Izb1_J = new javax.swing.JButton();
        tool_Izb1_E = new javax.swing.JButton();
        tool_Izb1_R = new javax.swing.JButton();
        tool_Izb1_N = new javax.swing.JButton();
        tool_Izb1_E1 = new javax.swing.JButton();
        tool_Izb1_J1 = new javax.swing.JButton();
        tool_seperator = new javax.swing.JToolBar.Separator();
        tool_Izb2_H = new javax.swing.JButton();
        tool_Izb2_A = new javax.swing.JButton();
        tool_Izb2_B = new javax.swing.JButton();
        tool_Izb2_J = new javax.swing.JButton();
        tool_Izb2_A1 = new javax.swing.JButton();
        tool_Izb2_N = new javax.swing.JButton();
        panel_main_center = new javax.swing.JPanel();
        panel_main_center_nested = new javax.swing.JPanel();
        rad_Add = new javax.swing.JRadioButton();
        rad_remFirst = new javax.swing.JRadioButton();
        rad_remSelected = new javax.swing.JRadioButton();
        scrollPane_textFiedl = new javax.swing.JScrollPane();
        textField = new javax.swing.JTextField();
        button_Execute = new javax.swing.JButton();
        comboBox = new javax.swing.JComboBox<>();
        check_NoDuplicates = new javax.swing.JCheckBox();
        spinner = new javax.swing.JSpinner();
        panel_footer = new javax.swing.JPanel();
        scrollPane_label_Action = new javax.swing.JScrollPane();
        label_Action = new javax.swing.JLabel();
        scrollPane_label_Buttons = new javax.swing.JScrollPane();
        label_Buttons = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menu_datoteka = new javax.swing.JMenu();
        datoteka_odpri = new javax.swing.JMenuItem();
        datoteka_pobrisi = new javax.swing.JMenuItem();
        datoteka_izhod = new javax.swing.JMenuItem();
        menu_Izbire1 = new javax.swing.JMenu();
        izb1_J = new javax.swing.JMenuItem();
        izb1_E = new javax.swing.JMenuItem();
        izb1_R = new javax.swing.JMenuItem();
        izb1_N = new javax.swing.JMenuItem();
        izb1_E1 = new javax.swing.JMenuItem();
        izb1_J1 = new javax.swing.JMenuItem();
        menu_izbire2 = new javax.swing.JMenu();
        izb2_H = new javax.swing.JMenuItem();
        izb2_A = new javax.swing.JMenuItem();
        izb2_B = new javax.swing.JMenuItem();
        izb2_J = new javax.swing.JMenuItem();
        izb2_A1 = new javax.swing.JMenuItem();
        izb2_N = new javax.swing.JMenuItem();
        menu_pomoc = new javax.swing.JMenu();
        pomoc_Avtor = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        toolBar.setRollover(true);
        toolBar.setToolTipText("");

        tool_Izb1_J.setMnemonic('J');
        tool_Izb1_J.setText("Izb1 J");
        tool_Izb1_J.setFocusable(false);
        tool_Izb1_J.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Izb1_J.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Izb1_J.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_Izb1_JActionPerformed(evt);
            }
        });
        toolBar.add(tool_Izb1_J);

        tool_Izb1_E.setMnemonic('E');
        tool_Izb1_E.setText("Izb1 E");
        tool_Izb1_E.setFocusable(false);
        tool_Izb1_E.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Izb1_E.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Izb1_E.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_Izb1_EActionPerformed(evt);
            }
        });
        toolBar.add(tool_Izb1_E);

        tool_Izb1_R.setMnemonic('R');
        tool_Izb1_R.setText("Izb1 R");
        tool_Izb1_R.setFocusable(false);
        tool_Izb1_R.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Izb1_R.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Izb1_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_Izb1_RActionPerformed(evt);
            }
        });
        toolBar.add(tool_Izb1_R);

        tool_Izb1_N.setMnemonic('N');
        tool_Izb1_N.setText("Izb1 N");
        tool_Izb1_N.setFocusable(false);
        tool_Izb1_N.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Izb1_N.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Izb1_N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_Izb1_NActionPerformed(evt);
            }
        });
        toolBar.add(tool_Izb1_N);

        tool_Izb1_E1.setMnemonic('E');
        tool_Izb1_E1.setText("Izb1 E");
        tool_Izb1_E1.setFocusable(false);
        tool_Izb1_E1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Izb1_E1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Izb1_E1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_Izb1_E1ActionPerformed(evt);
            }
        });
        toolBar.add(tool_Izb1_E1);

        tool_Izb1_J1.setMnemonic('J');
        tool_Izb1_J1.setText("Izb1 J");
        tool_Izb1_J1.setFocusable(false);
        tool_Izb1_J1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Izb1_J1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Izb1_J1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_Izb1_J1ActionPerformed(evt);
            }
        });
        toolBar.add(tool_Izb1_J1);
        toolBar.add(tool_seperator);

        tool_Izb2_H.setMnemonic('H');
        tool_Izb2_H.setText("Izb2 H");
        tool_Izb2_H.setFocusable(false);
        tool_Izb2_H.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Izb2_H.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Izb2_H.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_Izb2_HActionPerformed(evt);
            }
        });
        toolBar.add(tool_Izb2_H);

        tool_Izb2_A.setMnemonic('A');
        tool_Izb2_A.setText("Izb2 A");
        tool_Izb2_A.setFocusable(false);
        tool_Izb2_A.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Izb2_A.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Izb2_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_Izb2_AActionPerformed(evt);
            }
        });
        toolBar.add(tool_Izb2_A);

        tool_Izb2_B.setMnemonic('B');
        tool_Izb2_B.setText("Izb2 B");
        tool_Izb2_B.setDisplayedMnemonicIndex(5);
        tool_Izb2_B.setFocusable(false);
        tool_Izb2_B.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Izb2_B.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Izb2_B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_Izb2_BActionPerformed(evt);
            }
        });
        toolBar.add(tool_Izb2_B);

        tool_Izb2_J.setMnemonic('J');
        tool_Izb2_J.setText("Izb2 J");
        tool_Izb2_J.setFocusable(false);
        tool_Izb2_J.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Izb2_J.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Izb2_J.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_Izb2_JActionPerformed(evt);
            }
        });
        toolBar.add(tool_Izb2_J);

        tool_Izb2_A1.setMnemonic('A');
        tool_Izb2_A1.setText("Izb2 A");
        tool_Izb2_A1.setFocusable(false);
        tool_Izb2_A1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Izb2_A1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Izb2_A1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_Izb2_A1ActionPerformed(evt);
            }
        });
        toolBar.add(tool_Izb2_A1);

        tool_Izb2_N.setMnemonic('N');
        tool_Izb2_N.setText("Izb2 N");
        tool_Izb2_N.setFocusable(false);
        tool_Izb2_N.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tool_Izb2_N.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tool_Izb2_N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tool_Izb2_NActionPerformed(evt);
            }
        });
        toolBar.add(tool_Izb2_N);

        buttonGroup.add(rad_Add);
        rad_Add.setSelected(true);
        rad_Add.setText("Dodaj");
        rad_Add.setFocusable(false);
        rad_Add.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rad_Add.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        buttonGroup.add(rad_remFirst);
        rad_remFirst.setText("Odstrani prvega");
        rad_remFirst.setFocusable(false);
        rad_remFirst.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rad_remFirst.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        buttonGroup.add(rad_remSelected);
        rad_remSelected.setText("Odstrani izbranega");
        rad_remSelected.setFocusable(false);
        rad_remSelected.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        rad_remSelected.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        scrollPane_textFiedl.setViewportView(textField);

        button_Execute.setText("Izvedi izbrano akcijo");
        button_Execute.setFocusable(false);
        button_Execute.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        button_Execute.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        button_Execute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ExecuteActionPerformed(evt);
            }
        });

        comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Prešernova ulica 42", "Glinška ulica 12", "Ulica upora 6", "Na Logu 16" }));
        comboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxActionPerformed(evt);
            }
        });

        check_NoDuplicates.setText("Prepovej dvojnike");
        check_NoDuplicates.setFocusable(false);
        check_NoDuplicates.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        check_NoDuplicates.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        spinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 20, 1));

        javax.swing.GroupLayout panel_main_center_nestedLayout = new javax.swing.GroupLayout(panel_main_center_nested);
        panel_main_center_nested.setLayout(panel_main_center_nestedLayout);
        panel_main_center_nestedLayout.setHorizontalGroup(
            panel_main_center_nestedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_main_center_nestedLayout.createSequentialGroup()
                .addGroup(panel_main_center_nestedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_main_center_nestedLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rad_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(rad_remFirst)
                        .addGap(83, 83, 83)
                        .addComponent(rad_remSelected))
                    .addGroup(panel_main_center_nestedLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addGroup(panel_main_center_nestedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(button_Execute, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_main_center_nestedLayout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(check_NoDuplicates))))
                    .addGroup(panel_main_center_nestedLayout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(panel_main_center_nestedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel_main_center_nestedLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane_textFiedl, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        panel_main_center_nestedLayout.setVerticalGroup(
            panel_main_center_nestedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_main_center_nestedLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(panel_main_center_nestedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rad_remSelected)
                    .addComponent(rad_Add)
                    .addComponent(rad_remFirst))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(comboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(spinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(check_NoDuplicates)
                .addGap(18, 18, 18)
                .addComponent(button_Execute, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addGroup(panel_main_center_nestedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel_main_center_nestedLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane_textFiedl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(326, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout panel_main_centerLayout = new javax.swing.GroupLayout(panel_main_center);
        panel_main_center.setLayout(panel_main_centerLayout);
        panel_main_centerLayout.setHorizontalGroup(
            panel_main_centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_main_centerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel_main_center_nested, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_main_centerLayout.setVerticalGroup(
            panel_main_centerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_main_centerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_main_center_nested, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panel_mainLayout = new javax.swing.GroupLayout(panel_main);
        panel_main.setLayout(panel_mainLayout);
        panel_mainLayout.setHorizontalGroup(
            panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_mainLayout.createSequentialGroup()
                .addGroup(panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                    .addComponent(panel_main_center, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        panel_mainLayout.setVerticalGroup(
            panel_mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_mainLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panel_main_center, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        getContentPane().add(panel_main, java.awt.BorderLayout.PAGE_START);

        scrollPane_label_Action.setBorder(null);

        label_Action.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        scrollPane_label_Action.setViewportView(label_Action);

        scrollPane_label_Buttons.setBorder(null);

        label_Buttons.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        scrollPane_label_Buttons.setViewportView(label_Buttons);

        javax.swing.GroupLayout panel_footerLayout = new javax.swing.GroupLayout(panel_footer);
        panel_footer.setLayout(panel_footerLayout);
        panel_footerLayout.setHorizontalGroup(
            panel_footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane_label_Buttons, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
            .addComponent(scrollPane_label_Action)
        );
        panel_footerLayout.setVerticalGroup(
            panel_footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_footerLayout.createSequentialGroup()
                .addComponent(scrollPane_label_Action, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(scrollPane_label_Buttons, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        getContentPane().add(panel_footer, java.awt.BorderLayout.PAGE_END);

        menu_datoteka.setMnemonic('D');
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

        datoteka_pobrisi.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        datoteka_pobrisi.setMnemonic('P');
        datoteka_pobrisi.setText("Pobriši");
        datoteka_pobrisi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datoteka_pobrisiActionPerformed(evt);
            }
        });
        menu_datoteka.add(datoteka_pobrisi);

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

        menu_Izbire1.setText("Izbire1");

        izb1_J.setMnemonic('J');
        izb1_J.setText("Izbira1 J");
        izb1_J.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izb1_JActionPerformed(evt);
            }
        });
        menu_Izbire1.add(izb1_J);

        izb1_E.setMnemonic('E');
        izb1_E.setText("Izbira1 E");
        izb1_E.setToolTipText("");
        izb1_E.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izb1_EActionPerformed(evt);
            }
        });
        menu_Izbire1.add(izb1_E);

        izb1_R.setMnemonic('R');
        izb1_R.setText("Izbira1 R");
        izb1_R.setDisplayedMnemonicIndex(8);
        izb1_R.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izb1_RActionPerformed(evt);
            }
        });
        menu_Izbire1.add(izb1_R);

        izb1_N.setMnemonic('N');
        izb1_N.setText("Izbira1 N");
        izb1_N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izb1_NActionPerformed(evt);
            }
        });
        menu_Izbire1.add(izb1_N);

        izb1_E1.setMnemonic('E');
        izb1_E1.setText("Izbira1 E");
        izb1_E1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izb1_E1ActionPerformed(evt);
            }
        });
        menu_Izbire1.add(izb1_E1);

        izb1_J1.setMnemonic('J');
        izb1_J1.setText("Izbira1 J");
        izb1_J1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izb1_J1ActionPerformed(evt);
            }
        });
        menu_Izbire1.add(izb1_J1);

        menuBar.add(menu_Izbire1);

        menu_izbire2.setText("Izbire2");

        izb2_H.setMnemonic('H');
        izb2_H.setText("Izbira2 H");
        izb2_H.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izb2_HActionPerformed(evt);
            }
        });
        menu_izbire2.add(izb2_H);

        izb2_A.setMnemonic('A');
        izb2_A.setText("Izbira2 A");
        izb2_A.setDisplayedMnemonicIndex(8);
        izb2_A.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izb2_AActionPerformed(evt);
            }
        });
        menu_izbire2.add(izb2_A);

        izb2_B.setMnemonic('B');
        izb2_B.setText("Izbira2 B");
        izb2_B.setDisplayedMnemonicIndex(8);
        izb2_B.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izb2_BActionPerformed(evt);
            }
        });
        menu_izbire2.add(izb2_B);

        izb2_J.setMnemonic('J');
        izb2_J.setText("Izbira2 J");
        izb2_J.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izb2_JActionPerformed(evt);
            }
        });
        menu_izbire2.add(izb2_J);

        izb2_A1.setMnemonic('A');
        izb2_A1.setText("Izbira2 A");
        izb2_A1.setDisplayedMnemonicIndex(8);
        izb2_A1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izb2_A1ActionPerformed(evt);
            }
        });
        menu_izbire2.add(izb2_A1);

        izb2_N.setMnemonic('N');
        izb2_N.setText("Izbira2 N");
        izb2_N.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izb2_NActionPerformed(evt);
            }
        });
        menu_izbire2.add(izb2_N);

        menuBar.add(menu_izbire2);

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

        menuBar.add(menu_pomoc);

        setJMenuBar(menuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tool_Izb2_NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_Izb2_NActionPerformed
        izb2_NActionPerformed(evt);
    }//GEN-LAST:event_tool_Izb2_NActionPerformed

    private void tool_Izb2_A1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_Izb2_A1ActionPerformed
        izb2_A1ActionPerformed(evt);
    }//GEN-LAST:event_tool_Izb2_A1ActionPerformed

    private void tool_Izb2_JActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_Izb2_JActionPerformed
        izb2_JActionPerformed(evt);
    }//GEN-LAST:event_tool_Izb2_JActionPerformed

    private void tool_Izb2_BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_Izb2_BActionPerformed
        izb2_BActionPerformed(evt);
    }//GEN-LAST:event_tool_Izb2_BActionPerformed

    private void tool_Izb2_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_Izb2_AActionPerformed
        izb2_AActionPerformed(evt);
    }//GEN-LAST:event_tool_Izb2_AActionPerformed

    private void tool_Izb2_HActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_Izb2_HActionPerformed
        izb2_HActionPerformed(evt);
    }//GEN-LAST:event_tool_Izb2_HActionPerformed

    private void tool_Izb1_J1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_Izb1_J1ActionPerformed
        izb1_J1ActionPerformed(evt);
    }//GEN-LAST:event_tool_Izb1_J1ActionPerformed

    private void tool_Izb1_E1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_Izb1_E1ActionPerformed
        izb1_E1ActionPerformed(evt);
    }//GEN-LAST:event_tool_Izb1_E1ActionPerformed

    private void tool_Izb1_NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_Izb1_NActionPerformed
        izb1_NActionPerformed(evt);
    }//GEN-LAST:event_tool_Izb1_NActionPerformed

    private void tool_Izb1_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_Izb1_RActionPerformed
        izb1_RActionPerformed(evt);
    }//GEN-LAST:event_tool_Izb1_RActionPerformed

    private void tool_Izb1_EActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_Izb1_EActionPerformed
        izb1_EActionPerformed(evt);
    }//GEN-LAST:event_tool_Izb1_EActionPerformed

    private void tool_Izb1_JActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tool_Izb1_JActionPerformed
        izb1_JActionPerformed(evt);
    }//GEN-LAST:event_tool_Izb1_JActionPerformed

    private void comboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxActionPerformed

    }//GEN-LAST:event_comboBoxActionPerformed

    private void button_ExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ExecuteActionPerformed

        if(rad_Add.isSelected()){ //button ADD
            String vsebina = textField.getText();
            if(!vsebina.equals("")){
                if(!checkExists(vsebina) || !check_NoDuplicates.isSelected()){
                    textField.setText(null);
                    comboBox.addItem(vsebina);
                    label_Action.setText("Dodajam");
                }else
                    label_Action.setText("Dvojniki so prepovedani");  
            }else
                label_Action.setText("Vnesite element v vnosno vrstico");

        }else if(rad_remFirst.isSelected()){ //button REMOVE FIRST

            if(comboBox.getItemCount() > 0){
                comboBox.removeItemAt(0);
                label_Action.setText("Odstranujem prvega");
            }

        }else if(rad_remSelected.isSelected()){ //button REMOVE SELECTED

            if(comboBox.getItemCount() > 0){
                int index = comboBox.getSelectedIndex();
                comboBox.removeItemAt(index);
                label_Action.setText("Odstranjujem izbranega");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Izberite eno možnost");
        }
        
    }//GEN-LAST:event_button_ExecuteActionPerformed

    private void pomoc_AvtorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pomoc_AvtorActionPerformed
        label_Action.setText("Jernej Habjan\n63150106");
        JOptionPane.showMessageDialog(null, "Jernej Habjan\n63150106");
    }//GEN-LAST:event_pomoc_AvtorActionPerformed

    private void izb2_NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izb2_NActionPerformed
        label_Buttons.setText(label_Buttons.getText() + (char)izb2_N.getMnemonic());
    }//GEN-LAST:event_izb2_NActionPerformed

    private void izb2_A1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izb2_A1ActionPerformed
        label_Buttons.setText(label_Buttons.getText() + (char)izb2_A1.getMnemonic());
    }//GEN-LAST:event_izb2_A1ActionPerformed

    private void izb2_JActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izb2_JActionPerformed
        label_Buttons.setText(label_Buttons.getText() + (char)izb2_J.getMnemonic());
    }//GEN-LAST:event_izb2_JActionPerformed

    private void izb2_BActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izb2_BActionPerformed
        label_Buttons.setText(label_Buttons.getText() + (char)izb2_B.getMnemonic());
    }//GEN-LAST:event_izb2_BActionPerformed

    private void izb2_AActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izb2_AActionPerformed
        label_Buttons.setText(label_Buttons.getText() + (char)izb2_A.getMnemonic());
    }//GEN-LAST:event_izb2_AActionPerformed

    private void izb2_HActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izb2_HActionPerformed
        label_Buttons.setText(label_Buttons.getText() + (char)izb2_H.getMnemonic());
    }//GEN-LAST:event_izb2_HActionPerformed

    //another dropdown button
    private void izb1_J1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izb1_J1ActionPerformed
        label_Buttons.setText(label_Buttons.getText() + (char)izb1_J1.getMnemonic());
    }//GEN-LAST:event_izb1_J1ActionPerformed

    private void izb1_E1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izb1_E1ActionPerformed
        label_Buttons.setText(label_Buttons.getText() + (char)izb1_E1.getMnemonic());
    }//GEN-LAST:event_izb1_E1ActionPerformed

    private void izb1_NActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izb1_NActionPerformed
        label_Buttons.setText(label_Buttons.getText() + (char)izb1_N.getMnemonic());
    }//GEN-LAST:event_izb1_NActionPerformed

    private void izb1_RActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izb1_RActionPerformed
        label_Buttons.setText(label_Buttons.getText() + (char)izb1_R.getMnemonic());
    }//GEN-LAST:event_izb1_RActionPerformed

    private void izb1_EActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izb1_EActionPerformed
        label_Buttons.setText(label_Buttons.getText() + (char)izb1_E.getMnemonic());
    }//GEN-LAST:event_izb1_EActionPerformed

    private void izb1_JActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izb1_JActionPerformed
        label_Buttons.setText(label_Buttons.getText() + (char)izb1_J.getMnemonic());
    }//GEN-LAST:event_izb1_JActionPerformed

    private void datoteka_izhodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datoteka_izhodActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Ali res želite zapustiti program?","Opozorilo",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_datoteka_izhodActionPerformed

    private void datoteka_pobrisiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datoteka_pobrisiActionPerformed
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog (null, "Ali res želite pobrisati vsa polja?","Opozorilo",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            textField.setText(null);
            check_NoDuplicates.setSelected(false);
            rad_Add.setSelected(true);
            label_Action.setText(null);
            label_Buttons.setText(null);
            spinner.setValue(0);
        }
    }//GEN-LAST:event_datoteka_pobrisiActionPerformed

    private void datoteka_odpriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datoteka_odpriActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        if(f !=null){
            String fileName = f.getAbsolutePath();
            label_Action.setText(fileName); 
        }

    }//GEN-LAST:event_datoteka_odpriActionPerformed

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Domaca01().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JButton button_Execute;
    private javax.swing.JCheckBox check_NoDuplicates;
    private javax.swing.JComboBox<String> comboBox;
    private javax.swing.JMenuItem datoteka_izhod;
    private javax.swing.JMenuItem datoteka_odpri;
    private javax.swing.JMenuItem datoteka_pobrisi;
    private javax.swing.JMenuItem izb1_E;
    private javax.swing.JMenuItem izb1_E1;
    private javax.swing.JMenuItem izb1_J;
    private javax.swing.JMenuItem izb1_J1;
    private javax.swing.JMenuItem izb1_N;
    private javax.swing.JMenuItem izb1_R;
    private javax.swing.JMenuItem izb2_A;
    private javax.swing.JMenuItem izb2_A1;
    private javax.swing.JMenuItem izb2_B;
    private javax.swing.JMenuItem izb2_H;
    private javax.swing.JMenuItem izb2_J;
    private javax.swing.JMenuItem izb2_N;
    private javax.swing.JLabel label_Action;
    private javax.swing.JLabel label_Buttons;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menu_Izbire1;
    private javax.swing.JMenu menu_datoteka;
    private javax.swing.JMenu menu_izbire2;
    private javax.swing.JMenu menu_pomoc;
    private javax.swing.JPanel panel_footer;
    private javax.swing.JPanel panel_main;
    private javax.swing.JPanel panel_main_center;
    private javax.swing.JPanel panel_main_center_nested;
    private javax.swing.JMenuItem pomoc_Avtor;
    private javax.swing.JRadioButton rad_Add;
    private javax.swing.JRadioButton rad_remFirst;
    private javax.swing.JRadioButton rad_remSelected;
    private javax.swing.JScrollPane scrollPane_label_Action;
    private javax.swing.JScrollPane scrollPane_label_Buttons;
    private javax.swing.JScrollPane scrollPane_textFiedl;
    private javax.swing.JSpinner spinner;
    private javax.swing.JTextField textField;
    private javax.swing.JToolBar toolBar;
    private javax.swing.JButton tool_Izb1_E;
    private javax.swing.JButton tool_Izb1_E1;
    private javax.swing.JButton tool_Izb1_J;
    private javax.swing.JButton tool_Izb1_J1;
    private javax.swing.JButton tool_Izb1_N;
    private javax.swing.JButton tool_Izb1_R;
    private javax.swing.JButton tool_Izb2_A;
    private javax.swing.JButton tool_Izb2_A1;
    private javax.swing.JButton tool_Izb2_B;
    private javax.swing.JButton tool_Izb2_H;
    private javax.swing.JButton tool_Izb2_J;
    private javax.swing.JButton tool_Izb2_N;
    private javax.swing.JToolBar.Separator tool_seperator;
    // End of variables declaration//GEN-END:variables

    boolean checkExists(String value){
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            String item = comboBox.getItemAt(i);
            if(value.equals(item)) return true;
        }
        return false;
    }
}