import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
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
import javax.swing.JFileChooser;
import java.io.File;


public class View implements Observateur{
	
	
	//attributs 
	PacmanGame game;
	InterfaceController controller;
	private Maze labyrinthe;

	//panel de jeu et de commande 
	JFrame Commandes;
	JFrame Jeu;
	
	//label d'affichage du tour courant
	JLabel Label_2;
	
	public JButton Step;
	public JButton Restart;
	public JButton Run;
	public JButton Pause;
	public JButton changeMaze;

	public JPanel jPanelMaze;
	public Maze maze;
	
    //méthodes
	public View(InterfaceController controller,PacmanGame game) {	
		this.game = game;
		this.labyrinthe = game.getLabyrinthe();
		this.controller = controller;
		game.enregistrerObservateur(this);
		this.createUserFrame(labyrinthe);
		}	

	
	public void actualiser(boolean testBool) {
		this.Label_2.setText("Turn : " + this.game.NbTours);
		if(this.game.NbTours >= this.game.NbToursMax){
			this.Restart.setEnabled(true);
			this.Pause.setEnabled(false);
			this.Step.setEnabled(false);
			this.Run.setEnabled(false);
		}

		Jeu.getContentPane().removeAll();
		Jeu.validate();
		//Jeu.repaint();
		
		if(testBool){
		jPanelMaze = new PanelPacmanGame(labyrinthe);
		}
		
		Jeu.add(jPanelMaze,BorderLayout.CENTER);
		Jeu.validate();
		//Jeu.repaint();
	}
	
	
	void setLabyrinthe(Maze Labyrinthe){
		this.labyrinthe = Labyrinthe;
	}
	
	void transformation(String laby){
		try {
			Maze maze = new Maze(laby);
			this.setLabyrinthe(maze);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void createUserFrame(Maze labyrinthe) {
				
		try {
			maze = game.getLabyrinthe();
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
		JPanel controlPanelTurn = new JPanel(new GridLayout(2, 1));				
		
		Icon icon_restart = new ImageIcon("img/icon_restart.png");
		Restart = new JButton(icon_restart);
		Icon icon_run = new ImageIcon("img/icon_run.png");
		Run = new JButton(icon_run);
		Icon icon_step = new ImageIcon("img/icon_step.png");
		Step = new JButton(icon_step);
		Icon icon_pause = new ImageIcon("img/icon_pause.png");
		Pause = new JButton(icon_pause);


		changeMaze = new JButton("change Maze");
		changeMaze.setPreferredSize(new Dimension(40, 40));
		
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
		    	changeMaze.setEnabled(true);
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
		    	changeMaze.setEnabled(false);
			}
		});
		
		//step------------------------------------------

		
		Step.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evenement) {
		    	controller.step();
		    	Restart.setEnabled(true);
		    	Pause.setEnabled(false);
		    	changeMaze.setEnabled(true);
			}
		});
		
		//pause------------------------------------------

		
		Pause.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evenement) {
		    	controller.pause();
		    	Restart.setEnabled(true);
		    	Step.setEnabled(true);
		    	Pause.setEnabled(false);
		    	changeMaze.setEnabled(true);
			}
		});
		
		//changeMaze------------------------------------------
		
		changeMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				JFileChooser chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new File("/home/etudiant/workspace/ProjetPacman/layouts")); 
				chooser.showOpenDialog(null);
				transformation(chooser.getSelectedFile().getAbsolutePath());
				
		    	Restart.setEnabled(true);
		    	Run.setEnabled(false);
		    	Step.setEnabled(false);
				controller.restart();
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
	    
		
		
		controlPanelHaut.add(Restart);
		controlPanelHaut.add(Run);
		controlPanelHaut.add(Step);
		controlPanelHaut.add(Pause);
		
		controlPanelSlide.add(Label_1);
		controlPanelSlide.add(slide);
		
		controlPanelTurn.add(Label_2);
		controlPanelTurn.add(changeMaze);

		
		controlPanelBas.add(controlPanelSlide);
		controlPanelBas.add(controlPanelTurn);
		
		controlPanelCommande.add(controlPanelHaut);
		controlPanelCommande.add(controlPanelBas);
		
		Commandes.add(controlPanelCommande);
		Commandes.setVisible(true);
		
		
		
		Jeu.add(jPanelMaze,BorderLayout.CENTER);
		Jeu.setVisible(true);

	}

}
