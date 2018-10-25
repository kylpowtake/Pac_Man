import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class View implements Observateur{
	
	
	//attributs 
	Game game;
	InterfaceController controller;

	//panel de jeu et de commande 
	JFrame Commandes;
	JFrame Jeu;
	
	//label d'affichage du tour courant
	JLabel Label_2;
	JLabel Label_3;
	
	public JButton Step;
	public JButton Restart;
	public JButton Run;
	public JButton Pause;
	
	public JPanel jPanelMaze;
	public Maze maze;
	
    //méthodes
	public View(InterfaceController controller,Game game) {	
		this.game = game;
		this.controller = controller;
		game.enregistrerObservateur(this);
		this.createUserFrame();
	}	

	
	public void actualiser() {
		this.Label_2.setText("Turn : " + this.game.NbTours);
		this.Label_3.setText("Turn : " + this.game.NbTours);
		if(this.game.NbTours >= this.game.NbToursMax){
			this.Restart.setEnabled(true);
			this.Pause.setEnabled(false);
			this.Step.setEnabled(false);
			this.Run.setEnabled(false);
		}
	}
	
	
	
	public void createUserFrame() {
		
		try {
			maze = new Maze("layouts/bigCorners.lay");
		} catch (Exception e) {
			e.printStackTrace();
		}
		jPanelMaze = new PanelPacmanGame(maze);
		
		
		
		Commandes = new JFrame("Commandes");
		Commandes.setSize(new Dimension(1200, 200));
		Commandes.setLocation(400,780);
		
		Jeu = new JFrame("Game");
		Jeu.setSize(new Dimension(1200,700));
		Jeu.setLocation(400,50);
		
		
		JPanel controlPanelCommande = new JPanel(new GridLayout(2, 4));
		JPanel controlPanelHaut = new JPanel(new GridLayout(1, 4));
		JPanel controlPanelBas = new JPanel(new GridLayout(1, 2));
		JPanel controlPanelSlide = new JPanel(new GridLayout(2, 1));
		JPanel controlPanelTurn = new JPanel(new GridLayout());
		
		JPanel jeuPanel = new JPanel(new BorderLayout(2,1));
		
		
		Icon icon_restart = new ImageIcon("img/icon_restart.png");
		Restart = new JButton(icon_restart);
		Icon icon_run = new ImageIcon("img/icon_run.png");
		Run = new JButton(icon_run);
		Icon icon_step = new ImageIcon("img/icon_step.png");
		Step = new JButton(icon_step);
		Icon icon_pause = new ImageIcon("img/icon_pause.png");
		Pause = new JButton(icon_pause);
		
		
		Run.setEnabled(false);
		Pause.setEnabled(false);
		Step.setEnabled(false);
		
		
		
		
		//restart------------------------------------------
		
		Restart.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evenement) {
		    	controller.restart();
		    	Restart.setEnabled(false);
		    	Run.setEnabled(true);
		    	Step.setEnabled(true);
			}
		});
		
		//run------------------------------------------
		
		Run.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evenement) {
		    	controller.start();
		    	Run.setEnabled(false);
		    	Restart.setEnabled(false);
		    	Pause.setEnabled(true);
		    	Step.setEnabled(false);
			}
		});
		
		//step------------------------------------------

		
		Step.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evenement) {
		    	controller.step();
		    	Restart.setEnabled(true);
		    	Pause.setEnabled(false);
			}
		});
		
		//pause------------------------------------------

		
		Pause.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evenement) {
		    	controller.pause();
		    	Restart.setEnabled(true);
		    	Step.setEnabled(true);
		    	Pause.setEnabled(false);
			}
		});
		
		
		
		
		
		
		
		JLabel Label_1 = new JLabel("Number of turns per second");
		Label_1.setHorizontalAlignment(JLabel.CENTER);
		
		
		JSlider slide = new JSlider();
		
		slide.setMaximum(10);
	    slide.setMinimum(1);
	    slide.setValue(2);
	    slide.setPaintTicks(true);
	    slide.setPaintLabels(true);
	    slide.setMinorTickSpacing(1);
	    slide.setMajorTickSpacing(1);
	    
		slide.addChangeListener(new ChangeListener(){
			
			@Override
			public void stateChanged(ChangeEvent event) {
				JSlider source = (JSlider) event.getSource();
				game.nombre_de_tours_par_secondes = source.getValue();
			}
			
		});	  
		
		
	    
	    Label_2 = new JLabel("Turn : 8");
	    Label_2.setHorizontalAlignment(JLabel.CENTER);
	    
	    Label_3 = new JLabel("Current turn");
	    Label_3.setHorizontalAlignment(JLabel.CENTER);
	    Label_3.setVerticalAlignment(JLabel.TOP);
	    
	    
	    
	    
		
		
		controlPanelHaut.add(Restart);
		controlPanelHaut.add(Run);
		controlPanelHaut.add(Step);
		controlPanelHaut.add(Pause);
		
		controlPanelSlide.add(Label_1);
		controlPanelSlide.add(slide);
		
		controlPanelTurn.add(Label_2);
		
		controlPanelBas.add(controlPanelSlide);
		controlPanelBas.add(controlPanelTurn);
		
		controlPanelCommande.add(controlPanelHaut);
		controlPanelCommande.add(controlPanelBas);
		
		Commandes.add(controlPanelCommande);
		Commandes.setVisible(true);
		
		
		jeuPanel.add(Label_3,BorderLayout.PAGE_START);
		jeuPanel.add(jPanelMaze,BorderLayout.CENTER);
		
		Jeu.add(jeuPanel);
		Jeu.setVisible(true);

	}

}
