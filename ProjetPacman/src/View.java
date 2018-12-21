import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class View implements Observateur{
	
	/**
	 * On mange
	 * On meurt
	 * Invincible
	 */
	
	//attributs 
	Game game;
	InterfaceController controller;
	private Maze labyrinthe;
	public String chemin = "";
	public JPanel jPanelMaze;
	public Maze maze;
	public ArrayList<String> allMazes = new ArrayList<>();
	public ImageIcon iconLife;
	public ImageIcon iconInvincible;
	
	//les fenêtres 
	JFrame Configuration;	//fenêtre de configuration du nombre de joueurs
	JFrame Commandes;		//fenêtre de commandes du jeu
	JFrame Jeu;				//fenêtre du jeu 
	
	//les label 
	JLabel Label_1; 		//label pour afficher du texte 
	JLabel Label_2;			//label pour afficher le nombre de tours
	JLabel Label_3; 		//label pour afficher le nombre de poins 
	JLabel Label_4; 		//label pour afficher le nombre de vies 
	JLabel Label_5; 		//label pour afficher l'invincibilité du pacman
	
	//les boutons 
	JButton Step;
	JButton Restart;
	JButton Run;
	JButton Pause;
	JButton changeMaze;
	
	
	
    //contructeur
	public View(InterfaceController controller,PacmanGame game) {
		this.game = game;
		this.labyrinthe = game.getLabyrinthe();
		this.controller = controller;
		game.enregistrerObservateur(this);
		this.createUserFrame(labyrinthe);
		
		//récuperation de la liste des labyrinthes 
		File directory = new File("layouts");	
		File[] files = directory.listFiles();
		for ( File f : files) {
			this.allMazes.add(f.getAbsolutePath());
		}
		
	}	

	
	//méthodes 
	/**
	 * (non-Javadoc)
	 * @see Observateur#actualiser(boolean, boolean, boolean)
	 * la méthode actualise la fenêtre du jeu a chaque tour 
	 */
	public void actualiser(boolean booleanRestart, boolean testtransformation, boolean GameOver) {
		
		//update nombre de tours et nombre de points 
		this.Label_2.setText("Turn : " + this.game.getNbTours());		
		this.Label_3.setText("Number of points : " + this.game.getNbPoints());
		
		
		//update nombre de vies 
		switch(this.game.getNbVies()){
			case 1:
				iconLife = new ImageIcon("img/pacman1life.png");
				this.Label_4.setIcon(iconLife);
				break;
			case 2:
				iconLife = new ImageIcon("img/pacman2lifes.png");
				this.Label_4.setIcon(iconLife);
				break;
			case 3:
				iconLife = new ImageIcon("img/pacman3lifes.png");
				this.Label_4.setIcon(iconLife);
				break;
		}
		
		//update invinsibilité 
		if(game.getIsInvincible()){
			iconLife = new ImageIcon("img/pacmanInvincible.png");
			this.Label_5.setIcon(iconLife);
		}
		else{
			iconLife = new ImageIcon("img/pacmanNormal.png");
			this.Label_5.setIcon(iconLife);
		}
		
		//si le jeu est terminé 
			//si on a gagné 
				//alors on charge un autre labyrinthe aléatoirement 
			//sinon
				//si le nombre de vies est supérieur à 0
					//on sauvegarde l'état du labyrinthe et on peut recommencer le jeu acet état
				//sinon 
					//on doit recommencer avec l'état initial du labyrinthe le nombre de points à 0 et le nombre de vies à 3
		if(GameOver){
			if(this.game.getFinJeu()){	
				game.playSound("sounds/next_level.wav");
				int rnd = new Random().nextInt(this.allMazes.size());
				try {
					this.labyrinthe = new Maze(this.allMazes.get(rnd));
					game.actualiser(this.allMazes.get(rnd));
					this.allMazes.remove(rnd);
					controller.SetView(this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				if(this.game.getNbVies()>0){
					boolean savedFood[][] = this.game.getLabyrinthe().getFood();
					try {
						this.labyrinthe = new Maze(this.game.getChemin());
						game.actualiser(this.game.getChemin());
						this.game.getLabyrinthe().food = savedFood;
						controller.SetView(this);
					} catch (Exception e) {
						e.printStackTrace();
					}
					this.Restart.setEnabled(false);
					this.Pause.setEnabled(false);
					this.Step.setEnabled(true);
					this.Run.setEnabled(true);
					this.changeMaze.setEnabled(true);
					game.setIsRunnin(false);
				}
				else{
					game.playSound("sounds/you_died.wav");
					ImageIcon icon2 = new ImageIcon("sounds/you_died.gif");
					JFrame f = new JFrame();
					JLabel l = new JLabel();
					l.setIcon(icon2);
					f.setSize(640,360);
					f.setLocation(710,250);
					f.add(l);
					f.setVisible(true);
					try {
						Thread.sleep(8000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					f.setVisible(false);
					
					this.game.setNbies(3);
					this.game.setNbPoints(0);
					this.Restart.setEnabled(true);
					this.Pause.setEnabled(false);
					this.Step.setEnabled(false);
					this.Run.setEnabled(false);
					this.changeMaze.setEnabled(true);
					game.setIsRunnin(false);
				}
			}
			
		}
		
	
		//on supprime le composant qui contient l'état du labyrinthe  
		Jeu.getContentPane().getComponent(0);
		Jeu.validate();

		//on le met à jour en fonction du chemin (nom du labyrinthe) reçu 
		if(booleanRestart){
			try {
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
		
		//et on remet ce nouvel état dans le composant 
		jPanelMaze = new PanelPacmanGame(this.game.getLabyrinthe());
		Jeu.add(jPanelMaze,BorderLayout.CENTER);
		Jeu.validate();
	}
	
	
	/**
	 * méthode permettant de configurer un labyrinthe 
	 */
	void setLabyrinthe(Maze Labyrinthe){
		this.labyrinthe = Labyrinthe;
		game = new PacmanGame(Labyrinthe,this.game.getChemin());
	}

	
	/**
	 * méthode permettant de créer toutes les fenêtres 
	 */
	public void createUserFrame(Maze labyrinthe) {
				
		try {
			maze = game.getLabyrinthe();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		//police d'écriture 
		Font font = new Font("Serif",Font.BOLD,15);
		
		
		//les 3 fenêtres 
		Configuration = new JFrame("Configuration");
		Configuration.setSize(new Dimension(500,150));
		Configuration.setLocation(700,300);
		
		Commandes = new JFrame("Commandes");
		Commandes.setSize(new Dimension(1200, 200));
		Commandes.setLocation(400,780);
		
		Jeu = new JFrame("Game");
		Jeu.setSize(new Dimension(1200,700));
		Jeu.setLocation(400,50);
		
	
		
		
		//les composants de la fenêtre de configuration 
		final String[] nbJoueurs = {"0","1","2","3","4"};
		final String[] strategie = {"Random","Facile","A*"};
		final JComboBox nbJoueursPacman = new JComboBox(nbJoueurs);
		final JComboBox nbJoueursFantome = new JComboBox(nbJoueurs);
		final JComboBox choixStrategiePacman = new JComboBox(strategie);
		final JComboBox choixStrategieFantome = new JComboBox(strategie);
		
		final JPanel configurationPanel = new JPanel(new GridLayout(7, 2));
		configurationPanel.add(new JLabel("Nombre de joueurs maximum : 4 "));
		configurationPanel.add(new JLabel(""));
		configurationPanel.add(new JLabel("Nombre de joueurs pacman"));
		configurationPanel.add(nbJoueursPacman);
		configurationPanel.add(new JLabel("Nombre de joueurs fantome"));
		configurationPanel.add(nbJoueursFantome);
		configurationPanel.add(new JLabel(""));
		configurationPanel.add(new JLabel(""));
		configurationPanel.add(new JLabel("Stratégies adoptées"));
		configurationPanel.add(new JLabel(""));
		configurationPanel.add(new JLabel("Stratégie Pacman"));
		configurationPanel.add(choixStrategiePacman);
		configurationPanel.add(new JLabel("Stratégie Fantome"));
		configurationPanel.add(choixStrategieFantome);
	
		Configuration.add(configurationPanel);
		
		
		
		
		
		//les composants de la fenêtre de commande 
		JPanel controlPanelCommande = new JPanel(new GridLayout(2, 1));
		JPanel controlPanelHaut = new JPanel(new GridLayout(1, 4));
		JPanel controlPanelBas = new JPanel(new GridLayout(1, 2));
		JPanel controlPanelSlide = new JPanel(new GridLayout(2, 1));
		JPanel controlPanelTurn = new JPanel(new GridLayout(2, 1));	
		JPanel controlPanelPictures = new JPanel(new GridLayout(1,2));
		
		Icon icon_restart = new ImageIcon("img/icon_restart.png");
		Restart = new JButton(icon_restart);
		Icon icon_run = new ImageIcon("img/icon_run.png");
		Run = new JButton(icon_run);
		Icon icon_step = new ImageIcon("img/icon_step.png");
		Step = new JButton(icon_step);
		Icon icon_pause = new ImageIcon("img/icon_pause.png");
		Pause = new JButton(icon_pause);

		changeMaze = new JButton("Change Maze");
		changeMaze.setPreferredSize(new Dimension(40, 40));
		
		//les etats par default des boutons de la fenêtre de commande 
		Run.setEnabled(false);
		Pause.setEnabled(false);
		Step.setEnabled(false);
		
		
		//le slide de la fenêtre de commande 
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
				game.setNbToursSecondes(source.getValue());
			}
			
		});	  
		
		
		//les labels de la fenêtre de commande 
		JLabel Label_1 = new JLabel("Number of turns per second");
		Label_1.setHorizontalAlignment(JLabel.CENTER);
		Label_1.setFont(font);
		 
	    Label_2 = new JLabel("Turn : 0");
	    Label_2.setHorizontalAlignment(JLabel.CENTER);
	    Label_2.setFont(font);
	    
	    Label_3 = new JLabel("Number of points : " + this.game.getNbPoints());
	    Label_3.setHorizontalAlignment(JLabel.CENTER);
	    Label_3.setFont(font);
	    
	    iconLife = new ImageIcon("img/pacman3lifes.png");
	    Label_4 = new JLabel();
	    this.Label_4.setIcon(iconLife);
	    
	    iconInvincible = new ImageIcon("img/pacmanNormal.png");
	    Label_5 = new JLabel();
	    this.Label_5.setIcon(iconInvincible);
	    Label_5.setHorizontalAlignment(JLabel.RIGHT);
	    
	    Label_1.setHorizontalAlignment(JLabel.CENTER);
	    
	    
	    controlPanelPictures.add(Label_4);
	    controlPanelPictures.add(Label_5);
		controlPanelHaut.add(Restart);
		controlPanelHaut.add(Run);
		controlPanelHaut.add(Step);
		controlPanelHaut.add(Pause);
		controlPanelSlide.add(Label_1);
		controlPanelSlide.add(slide);
		controlPanelTurn.add(Label_3);
		controlPanelTurn.add(controlPanelPictures);
		controlPanelTurn.add(Label_2);
		controlPanelTurn.add(changeMaze);
		controlPanelBas.add(controlPanelSlide);
		controlPanelBas.add(controlPanelTurn);
		controlPanelCommande.add(controlPanelHaut);
		controlPanelCommande.add(controlPanelBas);

		Commandes.add(controlPanelCommande);
		Commandes.setVisible(true);
		
		
		
		
		
		//les composants de la fenêtre du jeu 
		jPanelMaze = new PanelPacmanGame(maze);
		game.panelTouches.addKeyListener(game.panelTouches);
		game.panelTouches.setFocusable(true);
		
		Jeu.add(game.panelTouches,BorderLayout.CENTER);
		Jeu.add(jPanelMaze,BorderLayout.CENTER);		
		Jeu.setVisible(true);
		
		
		
		//les action listener sur les composants 
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
		    	
		    	//request focus from panel touches 
		    	game.panelTouches.requestFocus();
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
		    	
		    	//request focus from panel touches 
		    	game.panelTouches.requestFocus();
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
				chooser.setCurrentDirectory(new File("layouts")); 
				chooser.showOpenDialog(null);
				
				String chemin = chooser.getSelectedFile().getAbsolutePath();
				
				//changer de labyrinthe est equivalent à déclarer forfait le nombre de 
				//points est donc remis à 0 et le nombre de vies à 3
				game.setNbies(3);
				game.setNbPoints(0);
				
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
					game.setNbJoueursPacman(0); 
					break;
				case 1:
					game.setNbJoueursPacman(1);
					break;
				case 2:
					game.setNbJoueursPacman(2); 
					break;
				case 3:
					game.setNbJoueursPacman(3); 
					break;
				case 4:
					game.setNbJoueursPacman(4); 
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
					game.setNbJoueursFantome(0);
					break;
				case 1:
					game.setNbJoueursFantome(1);
					break;
				case 2:
					game.setNbJoueursFantome(2);
					break;
				case 3:
					game.setNbJoueursFantome(3);
					break;
				case 4:
					game.setNbJoueursFantome(4);
					break;
				}
				
			}
		});
		
		//choixStrategiePacman------------------------------------------
		
		choixStrategiePacman.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				Integer choixStrategie = choixStrategiePacman.getSelectedIndex();
				switch(choixStrategie){
				case 0:
					game.comportementPacman = EnumComportement.PACMAN_RANDOM;
					break;
				case 1:
					game.comportementPacman = EnumComportement.PACMAN_FACILE;
					break;
				case 2:
					game.comportementPacman = EnumComportement.PACMAN_ALGO;
					break;
				}
				controller.restart();
			}	
		});
		
		
		//choixStrategieFantome------------------------------------------
		
			choixStrategieFantome.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					Integer choixStrategie = choixStrategieFantome.getSelectedIndex();
					switch(choixStrategie){
					case 0:
						game.comportementFantome = EnumComportement.FANTOME_RANDOM;
						break;
					case 1:
						game.comportementFantome = EnumComportement.FANTOME_FACILE;
						break;
					case 2:
						game.comportementFantome = EnumComportement.FANTOME_ALGO;
						break;
					}
					controller.restart();
				}	
			});
		
	}
}
