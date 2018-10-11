import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;


public class ViewCommande implements Observer{
	
	Game Game_actuelle;
	InterfaceController controlleur;
	JFrame frame_commandes;
	
	JFrame frame_vue;
	
	JPanel control_Panel_Jeu;
	JLabel label_2_bis;
	
	JPanel control_Panel_General;
	JPanel control_Panel_Haut;
	JPanel control_Panel_Bas;
	JPanel control_Panel_Gauche;
	JPanel control_Panel_Droit;
	JLabel label_2;
	
	public ViewCommande(Game game_actuelle, InterfaceController controlleur){
		this.Game_actuelle = game_actuelle;
		this.controlleur = controlleur;
		this.Game_actuelle.addObserver(this);
		this.createUserFrame();
	}
	
	public void update() {
		System.out.print("Une update de fait de ViewCommande\n");
		this.label_2.setText("Turn : " + this.Game_actuelle.compteur_Nombre_Tours);
		this.label_2_bis.setText("Turn : " + this.Game_actuelle.compteur_Nombre_Tours);
	}
	
	public void createUserFrame(){
		frame_commandes = new JFrame("Commandes");
		frame_commandes.setSize(800, 300);
		frame_commandes.setLocation(450, 550);
		
		frame_vue = new JFrame("Jeu");
		frame_vue.setSize(800, 500);
		frame_vue.setLocation(450, 50);
		
		
		control_Panel_Jeu = new JPanel(new GridLayout(1,1));

		
		control_Panel_General = new JPanel(new GridLayout(2,2));
		
		control_Panel_Haut = new JPanel(new GridLayout(1,4));
		control_Panel_Bas = new JPanel(new GridLayout(1,2));
		
		control_Panel_Gauche = new JPanel(new GridLayout(2,1));
		control_Panel_Droit = new JPanel(new GridLayout(1,1));
		
		
		control_Panel_General.add(control_Panel_Haut);
		control_Panel_General.add(control_Panel_Bas);
		
		control_Panel_Bas.add(control_Panel_Gauche);
		control_Panel_Bas.add(control_Panel_Droit);
		
		
		Icon icon_restart = new ImageIcon("/home/etudiant/workspace/Pacman/Icones-20180927/icon_restart.png");
		JButton restart = new JButton(icon_restart);
		
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				controlleur.restart();
			}
		});
		
		
		
		Icon icon_step = new ImageIcon("/home/etudiant/workspace/Pacman/Icones-20180927/icon_step.png");
		JButton step = new JButton(icon_step);
		
		step.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				controlleur.step();
			}
		});
		
		
		
		Icon icon_pause = new ImageIcon("/home/etudiant/workspace/Pacman/Icones-20180927/icon_pause.png");
		JButton pause = new JButton(icon_pause);
		
		pause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				controlleur.pause();
			}
		});
		
		
		
		
		Icon icon_run = new ImageIcon("/home/etudiant/workspace/Pacman/Icones-20180927/icon_run.png");
		JButton run = new JButton(icon_run);
		
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				controlleur.start();
			}
		});
		
		JSlider slider = new JSlider();
		slider.setMaximum(10);
		slider.setMinimum(0);
		slider.setPaintLabels(true);
		slider.setPaintTicks(true);
		slider.setMinorTickSpacing(1);
		slider.setMajorTickSpacing(1);
		slider.setValue(2);
		
		JLabel label_1 = new JLabel("Number of turn per second");
		label_1.setHorizontalAlignment(JLabel.CENTER);
		
		label_2 = new JLabel("Turn : " + this.Game_actuelle.compteur_Nombre_Tours); 
		label_2.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		label_2_bis = new JLabel("Turn : " + this.Game_actuelle.compteur_Nombre_Tours); 
		label_2_bis.setHorizontalAlignment(JLabel.CENTER);
		label_2_bis.setVerticalAlignment(JLabel.TOP);
		
		
		control_Panel_Haut.add(step);
		control_Panel_Haut.add(pause);
		control_Panel_Haut.add(restart);
		control_Panel_Haut.add(run);

		control_Panel_Gauche.add(label_1);
		control_Panel_Gauche.add(slider);
		control_Panel_Droit.add(label_2);
		
		control_Panel_Jeu.add(label_2_bis);
		
		frame_vue.add(control_Panel_Jeu);
		frame_vue.setVisible(true);
		
		frame_commandes.add(control_Panel_General);
		frame_commandes.setVisible(true);
	}
	
}
		
		
		
		