import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ViewConnexion {

	private JFrame fenetre;
	
	private JLabel labelPseudo;
	
	private JLabel labelMDP;
	
	private JTextField textePseudo;
	
	private JTextField texteMDP;
	
	private JButton buttonEnvoie;
	
	private JLabel labelResultat;
	
	public ViewConnexion(){
		CreateUserFrame();
	}
	
	
	public void CreateUserFrame(){
		
		Font font = new Font("Serif",Font.BOLD,15);

		fenetre = new JFrame("fenetre"); 
		fenetre.setSize(new Dimension(800, 400));
		fenetre.setLocation(300,300);
		
		// Tout les JPanel
		JPanel controlePanelFenetre = new JPanel(new GridLayout(3,1));
		JPanel controlePanelHaut = new JPanel(new GridLayout(1,2));
		JPanel controlePanelBas = new JPanel(new GridLayout(1,2));
		JPanel controlePanelEnvoie = new JPanel(new GridLayout(2,1));
		
		//Instanciations du JLabel pseudo
		labelPseudo = new JLabel("Pseudo");
	    labelPseudo.setHorizontalAlignment(JLabel.CENTER);
	    labelPseudo.setFont(font);
		controlePanelHaut.add(labelPseudo);
		
		//Instanciations du JTextField pseudo
		textePseudo = new JTextField();
		textePseudo.setColumns(15);
		controlePanelHaut.add(textePseudo);

		//Instanciations du JLabel MDP
		labelMDP = new JLabel("Mot de passe");
	    labelMDP.setHorizontalAlignment(JLabel.CENTER);
	    labelMDP.setFont(font);
		controlePanelBas.add(labelMDP);

		//Instanciations du JTextField MDP
		texteMDP = new JTextField();
		texteMDP.setColumns(30);
		controlePanelBas.add(texteMDP);
		
		//Instanciation du JButton buttonEnvoie
		buttonEnvoie = new JButton(new GetAction(this, "Connexion"));
	    buttonEnvoie.setHorizontalAlignment(JLabel.CENTER);
	    buttonEnvoie.setFont(font);
	    controlePanelEnvoie.add(buttonEnvoie);

		//Instanciations du JLabel resultat
		labelResultat = new JLabel("");
		labelResultat.setHorizontalAlignment(JLabel.CENTER);
		labelResultat.setFont(font);
		controlePanelEnvoie.add(labelResultat);

		
		//Ajouts des JPanels dans le JPanel général
	    controlePanelFenetre.add(controlePanelHaut);
	    controlePanelFenetre.add(controlePanelBas);
	    controlePanelFenetre.add(controlePanelEnvoie);
	    	    
	    //Ajout du JPanel gnéral dans le JFrame et visibilation du JFrame.
	    fenetre.add(controlePanelFenetre);
	    fenetre.setVisible(true);

	}
	
	//Getter sur le JTextField texteMDP
	public JTextField getTexteMDP(){
		return this.texteMDP;
	}
	
	//Getter sur le JTextField textePseudo
	public JTextField getTextePseudo(){
		return this.textePseudo;
	}
	
	public void setLabelResultat(String resultat){
		this.labelResultat.setText(resultat);
	}
	
	public void Close(){
		fenetre.dispose();
	}
	
	public boolean TestPresent(){
		if(this.getTexteMDP() != null){
			return true;
		} else {
			return false;
		}
	}
}
