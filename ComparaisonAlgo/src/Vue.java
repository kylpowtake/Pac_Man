import java.awt.TextArea;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;



@SuppressWarnings("serial")
public class Vue extends JFrame{
	
	private 		JButton 	Executer;
	@SuppressWarnings("rawtypes")
	private		 	JComboBox	classifieur;
	private 		JLabel 		jLabel1;
    private 		JLabel 		jLabel2;
    private 		JMenuBar 	jMenuBar1;
    private 		JMenuItem 	menuch;
    private 		JTextField 	textParams;
    public static	JPanel 		jPanel1;
    public static 	JPanel 		graphPanel;
    public static 	TextArea 	resultLabel;

    
	/**
	 * Méthode pour construire la vue.
	 */
    public Vue() {
        InitComponents();
    }
    
    /**
     * Méthode initialisant tous les comosants de la vue.
     */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void InitComponents() {
    	
    	String[] ClassifieurString = {"J48"};
    	
        jPanel1 	= new JPanel();
        Executer 	= new JButton();
        graphPanel 	= new JPanel();
        resultLabel = new TextArea();
        textParams 	= new JTextField();
        jLabel1		= new JLabel();
        jLabel2 	= new JLabel();
        jMenuBar1 	= new JMenuBar();
        menuch		= new JMenuItem();
        classifieur = new JComboBox(ClassifieurString);

       //-----------------------------------------------------------------------------------------------------
        

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 8, true));
        
        graphPanel.setBackground(new java.awt.Color(204, 204, 204));
        graphPanel.setOpaque(true);
        
        resultLabel.setBackground(new java.awt.Color(204, 204, 204));
        resultLabel.setFont(new java.awt.Font("Tahoma", 3, 17));
        resultLabel.setText("\n\n\n\n\n\n        En attente \n               de \n         traitement");
        resultLabel.setEditable(false);

        
        jLabel1.setText("Paramètres :");
        jLabel2.setText("Classifieur :");
        Executer.setEnabled(false);
        
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
        
        Executer.setText("Executer");
        Executer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	resultLabel.setText("\n\n\n\n          Traitement \n                 en \n           cours");
                ExecuterActionPerformed(evt);
            }
        });
        
       
      //-----------------------------------------------------------------------------------------------------
        
        //JFrame paramètres
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 540));
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
                    			.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    			.addComponent(classifieur, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            	.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textParams, javax.swing.GroupLayout.PREFERRED_SIZE, 400,javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Executer, javax.swing.GroupLayout.PREFERRED_SIZE, 100,javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(graphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)));
        
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                	.addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                	.addComponent(classifieur, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textParams, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Executer,javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                		.addComponent(graphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                		.addComponent(resultLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
     * @param event
     * FileChooser, permet de sélectionner un fichier dans le système.
     */
    private void menuchActionPerformed(java.awt.event.ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.setApproveButtonText("choisir");
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			Main.fileNameA = chooser.getSelectedFile().getAbsolutePath();
			Main.fileName = chooser.getSelectedFile().getName();
			Executer.setEnabled(true);
			
	    }	
    }
    
    
    /**
     * @param evt
     * Onclick sur "Executer" 
     * défini les paramètres et lance le classifieur  
     */
    private void ExecuterActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	
    	//récupération et enregistrement des options dans la variable prévue a cet effet
    	String[] Options = textParams.getText().split(" ");
    	Main.options = new String[Options.length + 2];
    	for(int i=0; i< Options.length; i++){
    		Main.options[i] = Options[i];
    	}
    	
    	
    	for(int i=0;i<Main.options.length;i++){
			if(Main.options[i]!=null)
				Main.chaineOption += Main.options[i]+" ";
	    }
    	
    	//lance le classifier et génère le graphe
    	resultLabel.setText("\n\n\n\n\n\n        Traitement \n               en \n             cours");
    	Main.executer();

    }
}