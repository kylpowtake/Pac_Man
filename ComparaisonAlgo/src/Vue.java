import javax.swing.GroupLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;





public class Vue extends javax.swing.JFrame{
	private JLabel labelParam;
	private JLabel label;
    private JMenuItem menuChoseFile;
    private javax.swing.JMenuBar jMenuChoseFile;
    private JPanel jPanelGlobal;
    private JTextField texteParam;

	/**
	 * Méthode pour construire la vue.
	 */
    public Vue() {
        InitComponents();
    }
    
    /**
     * Méthode initialisant tous les comosants de la vue.
     */
	private void InitComponents(){
		//Initialisation basique des composants.
		menuChoseFile = new javax.swing.JMenuItem();
		jMenuChoseFile = new javax.swing.JMenuBar();
        labelParam = new javax.swing.JLabel();
        jPanelGlobal = new javax.swing.JPanel();
        texteParam = new JTextField();
        
        
        
		//On donne l'affichage voulue.
		jMenuChoseFile.setBorder(null);
		jMenuChoseFile.add(menuChoseFile);
        setJMenuBar(jMenuChoseFile);
		
        //On ajoute le texte au label devant l'entrée des paramètres voulues.
        labelParam.setText("Paramètres à utiliser : ");

        
		//On ajoute le texte à afficher sur le bouton
		menuChoseFile.setText("Ouvrir un fichier");
		//On ajoute le lu=istener sur le bouton.
		menuChoseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent event) {
                menuchActionPerformed(event);
            }
        });
		
		
		//On gère le layout des composants de la fenêtre.
        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanelGlobal);
        jPanelGlobal.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
        		jPanelLayout.createSequentialGroup()
        		.addComponent(labelParam)
        		.addComponent(texteParam, javax.swing.GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
        		;
        jPanelLayout.setVerticalGroup(
        		jPanelLayout.createParallelGroup()
        		.addComponent(labelParam)
        		.addComponent(texteParam, javax.swing.GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE))
        		;
		
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addComponent(jPanelGlobal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
        		.addComponent(jPanelGlobal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));       
        //Rend la fenêtre visible et ajuste sa largeur et sa hauteur.
		pack();
	}
	
	
	
    /**
     * @param event
     * FileChooser, permet de sélectionner un fichier dans le système.
     */
    private void menuchActionPerformed(java.awt.event.ActionEvent event) {
        String fileName="";
        JFileChooser chooser = new JFileChooser();
        chooser.setApproveButtonText("choisir");
		if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			fileName = chooser.getSelectedFile().getPath();
			Main.readFile(fileName);
	    }	
    }
}
