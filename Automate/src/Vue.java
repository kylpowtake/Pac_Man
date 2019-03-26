import javax.swing.*;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


@SuppressWarnings("serial")
public class Vue extends javax.swing.JFrame {
	
	private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel l;
    private javax.swing.JMenuItem menuch;
    private javax.swing.JTextField t1;
    private javax.swing.JTextField t2;
    
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vue().setVisible(true);
            }
        });
    }
	
    public Vue() {
        initComponents();
    }

    
    /**
     * Création des composants de la vue 
     */
    private void initComponents() {
    	
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        l = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        t2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuch = new javax.swing.JMenuItem();

       //-----------------------------------------------------------------------------------------------------
        

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 8, true));
        
        l.setBackground(new java.awt.Color(204, 204, 204));
        l.setFont(new java.awt.Font("Tahoma", 3, 18));
        l.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        l.setText("AFD");
        l.setOpaque(true);

        jLabel2.setText("Nom du fichier :");
        jLabel3.setText("mots à verifier :");
        
        jMenuBar1.setBorder(null);
        jMenuBar1.add(menuch);
        setJMenuBar(jMenuBar1);
        
      //-----------------------------------------------------------------------------------------------------
        
        //ActionListeners
        menuch.setText("Ouvrir un fichier");
        menuch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuchActionPerformed(evt);
            }
        });
        
        jButton1.setText("verifier");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        
        jButton2.setText("reset");
        jButton2.setEnabled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
       
      //-----------------------------------------------------------------------------------------------------
        
        //JFrame paramètres
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(528, 409));
        setLocation(700,200);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
			private void formWindowOpened(WindowEvent evt) {}
        });
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
			private void formComponentHidden(ComponentEvent evt){}
        });
        
        //-----------------------------------------------------------------------------------------------------
        
        //JPanel paramètres
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 439, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(t2, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE))
                            .addComponent(t1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(l, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(t2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addComponent(l, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(22, 22, 22))))
        );

      	//-----------------------------------------------------------------------------------------------------
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    
    /**
     * @param evt
     * FileChooser, permet de sélectionner un fichier 
     */
    private void menuchActionPerformed(java.awt.event.ActionEvent evt) {
        String fileName="";
        JFileChooser chooser = new JFileChooser();
        chooser.setApproveButtonText("choisir");
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			fileName = chooser.getSelectedFile().getPath();
			Automate.readFile(fileName);
	    }	
        t2.setText(fileName);
        t2.setEnabled(false);
    }

    /**
     * @param evt
     * Onclick sur "Vérifier" execute l'automate et génère le résultat 
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
	   try{
	       Automate a;
	       a=new Automate(t2.getText());
	       boolean b=false;
	       b=a.verif(a.qi,t1.getText());
	       if(b){
	           l.setText("Mot accepté");
	           l.setForeground(Color.GREEN);
	       }	
	       else{
	           l.setText("Mot refusé");
	           l.setForeground(Color.RED);
	       }
	    }catch (IOException e){
	         System.out.println(e);
	    }
        jButton2.setEnabled(true);
        jButton1.setEnabled(false);   
    }
    
    /**
     * @param evt
     * Onclick sur "Reset" supprime le mot à vérifier 
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
	    t1.setText(null);
	    l.setText("AFD");
	    l.setForeground(Color.BLACK);
	    jButton2.setEnabled(false);
	    jButton1.setEnabled(true);
    }
}
