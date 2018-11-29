import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private Touches panelTouches = new Touches();

	String chemin = "";
	
	//panel de jeu et de commande 
	JFrame Configuration;
	JFrame Commandes;
	JFrame Jeu;
	
	//les label 
	JLabel Label_2; //label pour afficher le nombre de tours
	JLabel Label_3; //label pour afficher le nombre de poins 
	JLabel Label_4; //label pour afficher le nombre de vies 
	
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
		System.out.println("test");
		this.labyrinthe = game.getLabyrinthe();
		this.controller = controller;
		game.enregistrerObservateur(this);
		this.createUserFrame(labyrinthe);
		}	

	
	public void actualiser(boolean testrestart, boolean testtransformation) {
	
		this.Label_2.setText("Turn : " + this.game.NbTours);
		this.Label_3.setText("Nombres de points : " + this.game.NbPoints);
		this.Label_4.setText("Nombres de vies : " + this.game.NbVies);
		
		if(this.game.NbTours >= this.game.NbToursMax){
			this.Restart.setEnabled(true);
			this.Pause.setEnabled(false);
			this.Step.setEnabled(false);
			this.Run.setEnabled(false);
		}
		Jeu.getContentPane().getComponent(0);
		Jeu.validate();
		//Jeu.repaint();
		
		if(testrestart){
			try {
				System.out.println("Dans le test de testBool : ");
				this.labyrinthe = new Maze(this.game.getChemin());
				game.actualiser(this.game.getChemin());
				controller.SetView(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(testtransformation){
			if(this.chemin == ""){
				this.chemin = this.game.getChemin();
			}
			try {
				this.labyrinthe = new Maze(this.chemin);
				controller.SetView(this);
			} catch (Exception e) {
				e.printStackTrace();
				}
			}
		
		jPanelMaze = new PanelPacmanGame(this.game.getLabyrinthe());
		
		Jeu.add(jPanelMaze,BorderLayout.CENTER);
		Jeu.validate();
		//Jeu.repaint();
	}
	
	
	void setLabyrinthe(Maze Labyrinthe){
		this.labyrinthe = Labyrinthe;
		game = new PacmanGame(Labyrinthe,this.game.getChemin());
	}

	
	public void createUserFrame(Maze labyrinthe) {
				
		try {
			System.out.println("test");
			maze = game.getLabyrinthe();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("test");

		jPanelMaze = new PanelPacmanGame(maze);
		
		
		
		Commandes = new JFrame("Commandes");
		Commandes.setSize(new Dimension(1200, 200));
		Commandes.setLocation(400,780);
		
		Jeu = new JFrame("Game");
		Jeu.setSize(new Dimension(1200,700));
		Jeu.setLocation(400,50);
		
		Configuration = new JFrame("Configuration");
		Configuration.setSize(new Dimension(500,100));
		Configuration.setLocation(700,300);
		
		final String[] nbJoueurs = {"0","1","2","3","4"};
		final JComboBox nbJoueursPacman = new JComboBox(nbJoueurs);
		final JComboBox nbJoueursFantome = new JComboBox(nbJoueurs);
		
		final JPanel configurationPanel = new JPanel(new GridLayout(3, 2));
		configurationPanel.add(new JLabel("Nombre de joueurs maximum : 4 "));
		configurationPanel.add(new JLabel(""));
		configurationPanel.add(new JLabel("Nombre de joueurs pacmans"));
		configurationPanel.add(nbJoueursPacman);
		configurationPanel.add(new JLabel("Nombre de joueurs fantome"));
		configurationPanel.add(nbJoueursFantome);
		
	
		Configuration.add(configurationPanel);
		
		
		JPanel controlPanelCommande = new JPanel(new GridLayout(2, 1));
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
		    	Configuration.setVisible(true);
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
		    	Configuration.setVisible(false);
			}
		});
		
		//step------------------------------------------

		
		Step.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evenement) {
		    	controller.step();
		    	Restart.setEnabled(true);
		    	Pause.setEnabled(false);
		    	changeMaze.setEnabled(true);
		    	Configuration.setVisible(false);
			}
		});
		
		//pause------------------------------------------

		
		Pause.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evenement) {
		    	controller.pause();
		    	Restart.setEnabled(true);
		    	Run.setEnabled(true);
		    	Step.setEnabled(true);
		    	Run.setEnabled(true);
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
				
				String chemin = chooser.getSelectedFile().getAbsolutePath();
				
		    	Restart.setEnabled(true);
		    	Run.setEnabled(false);
		    	Step.setEnabled(false);
				controller.changement(chemin);
			}
		});
		
		//nbJoueursPacman------------------------------------------
		nbJoueursPacman.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Integer nbPacmans = nbJoueursPacman.getSelectedIndex();
				switch(nbPacmans){
				case 0:
					game.nbJoueursPacmans = 0; 
					break;
				case 1:
					game.nbJoueursPacmans = 1;
					break;
				case 2:
					game.nbJoueursPacmans = 2; 
					break;
				case 3:
					game.nbJoueursPacmans = 3; 
					break;
				case 4:
					game.nbJoueursPacmans = 4; 
					break;
				}
			}
		});
		
		
		//nbJoueursFantomes------------------------------------------
		nbJoueursFantome.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Integer nbFantomes = nbJoueursFantome.getSelectedIndex();
				switch(nbFantomes){
				case 0:
					game.nbJoueursFantome = 0; 
					break;
				case 1:
					game.nbJoueursFantome = 1;
					break;
				case 2:
					game.nbJoueursFantome = 2; 
					break;
				case 3:
					game.nbJoueursFantome = 3; 
					break;
				case 4:
					game.nbJoueursFantome = 4; 
					break;
				}
				
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
	    
	    Label_3 = new JLabel("Nombres de points : " + this.game.NbPoints);
	    Label_3.setHorizontalAlignment(JLabel.CENTER);
	    
	    Label_4 = new JLabel("Nombres de vies : " + this.game.NbVies);
	    Label_1.setHorizontalAlignment(JLabel.CENTER);
		
		
		controlPanelHaut.add(Restart);
		controlPanelHaut.add(Run);
		controlPanelHaut.add(Step);
		controlPanelHaut.add(Pause);
		
		controlPanelSlide.add(Label_1);
		controlPanelSlide.add(slide);
		
		controlPanelTurn.add(Label_3);
		controlPanelTurn.add(Label_4);
		controlPanelTurn.add(Label_2);
		controlPanelTurn.add(changeMaze);

		
		controlPanelBas.add(controlPanelSlide);
		controlPanelBas.add(controlPanelTurn);
		
		controlPanelCommande.add(controlPanelHaut);
		controlPanelCommande.add(controlPanelBas);
		
		Commandes.add(controlPanelCommande);
		Commandes.setVisible(true);
		
		panelTouches.addKeyListener(panelTouches);
		panelTouches.setFocusable(true);
		
		Jeu.add(panelTouches,BorderLayout.CENTER);
		Jeu.add(jPanelMaze,BorderLayout.CENTER);
		Jeu.setVisible(true);

	}

}
