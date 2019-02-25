import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
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
import java.util.ArrayList;


public class View{

	//attributs 
	private Maze labyrinthe;
	public JPanel jPanelMaze;
	public Maze maze;
	public ArrayList<String> allMazes = new ArrayList<>();
	public ImageIcon iconLife;
	public ImageIcon iconInvincible;
	public Touches panelTouches = new Touches();
	private boolean food[][];
	private boolean capsule[][];
	private boolean isInvincible;
	private ArrayList<PositionAgent> fantomes;
	private ArrayList<PositionAgent> pacmans;
	private int NbPoints = 0;
	private int NbVies = 3;
	private int NbTours = 0;
	private String chemin = "";
	private String musique;
	private String etat;
	
	
	
	//les fenêtres 
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
	public View(String chemin) {
		this.chemin = chemin;
		try {
			System.out.println("apaapap      " + chemin);
			this.maze = new Maze(chemin);
			this.labyrinthe = maze;
			this.createUserFrame(maze);		
		} catch (Exception e) {
			System.out.println("icicicici nous passons pasooons");

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}	
	
	
    /**
     * méthode pour rajouter de la musique au jeu 
     */
    public void playSound(String path){
    	try {
		    File yourFile = new File(path);
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;

		    stream = AudioSystem.getAudioInputStream(yourFile);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.start();
		}
		catch (Exception e) {
		    //whatevers
		}
    }

	
	//méthodes 
	/**
	 * (non-Javadoc)
	 * @see Observateur#actualiser(boolean, boolean, boolean)
	 * la méthode actualise la fenêtre du jeu a chaque tour 
	 */
	public void actualiser() {
		
		//update nombre de tours et nombre de points 
		//Valeurs à ajouter de reçu en JSON
		this.Label_2.setText("Turn : " + NbTours);		
		this.Label_3.setText("Number of points : " + NbPoints);
		
		
		//update nombre de vies 
		//Valeur à ajouter de reçu en JSON
		switch(NbVies){
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
		//Valeur à ajouter de reçu en JSON
		if(isInvincible){
			iconLife = new ImageIcon("img/pacmanInvincible.png");
			this.Label_5.setIcon(iconLife);
		}
		else{
			iconLife = new ImageIcon("img/pacmanNormal.png");
			this.Label_5.setIcon(iconLife);
		}
		
		//playSound("sounds/next_level.wav");		
	
		//on supprime le composant qui contient l'état du labyrinthe  
		Jeu.getContentPane().getComponent(0);
		Jeu.validate();
		
		//et on remet ce nouvel état dans le composant 
		jPanelMaze = new PanelPacmanGame(labyrinthe);
		Jeu.add(jPanelMaze,BorderLayout.CENTER);
		Jeu.validate();
	}
	
	
	
	
	public void actualiserChemin(){
		//On a gagné et on doit afficher le nouveau labyrinthe 
		if(etat == "true"){
			
		} 
		//On a perdu toutes nos vies et on doit rafficher le chemin de base	mais avant cela un peu de dark soul
		else {
			playSound("sounds/you_died.wav");
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
				e.printStackTrace();
			}
			f.setVisible(false);
		}
		
		
		this.Restart.setEnabled(true);
		this.Pause.setEnabled(false);
		this.Step.setEnabled(false);
		this.Run.setEnabled(false);
		this.changeMaze.setEnabled(true);
		
		try {
			labyrinthe = new Maze(chemin);
			
			//on supprime le composant qui contient l'état du labyrinthe  
			Jeu.getContentPane().getComponent(0);
			Jeu.validate();
			
			//et on remet ce nouvel état dans le composant 
			jPanelMaze = new PanelPacmanGame(labyrinthe);
			Jeu.add(jPanelMaze,BorderLayout.CENTER);
			Jeu.validate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * méthode permettant de configurer un labyrinthe 
	 */
	void setLabyrinthe(Maze Labyrinthe){
		this.labyrinthe = Labyrinthe;
	}

	
	/**
	 * méthode permettant de créer toutes les fenêtres 
	 */
	public void createUserFrame(Maze labyrinthe) {
				
		try {
			maze = this.labyrinthe;

		}catch (Exception e) {
			e.printStackTrace();
		}
		

		
		
		//police d'écriture 
		Font font = new Font("Serif",Font.BOLD,15);
		
				
		Commandes = new JFrame("Commandes");
		Commandes.setSize(new Dimension(1200, 200));
		Commandes.setLocation(400,780);
		
		Jeu = new JFrame("Game");
		Jeu.setSize(new Dimension(1200,700));
		Jeu.setLocation(400,50);
		
		
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
				//Envoyer valeur à serveur
			}
			
		});	  
		
		
		//les labels de la fenêtre de commande 
		JLabel Label_1 = new JLabel("Number of turns per second");
		Label_1.setHorizontalAlignment(JLabel.CENTER);
		Label_1.setFont(font);
		 
	    Label_2 = new JLabel("Turn : 0");
	    Label_2.setHorizontalAlignment(JLabel.CENTER);
	    Label_2.setFont(font);
	    
	    //Donnée reçu en JSON
	    Label_3 = new JLabel("Number of points : " + this.NbPoints);
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
		panelTouches.addKeyListener(panelTouches);
		panelTouches.setFocusable(true);
		
		Jeu.add(panelTouches,BorderLayout.CENTER);
		Jeu.add(jPanelMaze,BorderLayout.CENTER);		
		Jeu.setVisible(true);
		
		
		
		//les action listener sur les composants 
		//restart------------------------------------------
		
		Restart.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evenement) {
		    	Restart.setEnabled(false);
		    	Run.setEnabled(true);
		    	Step.setEnabled(true);
		    	changeMaze.setEnabled(true);
		    	ClientEmetteur.setMessage("commande:init");
			}
		});
		
		//run------------------------------------------
		
		Run.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evenement) {
		    	
		    	Run.setEnabled(false);
		    	Restart.setEnabled(false);
		    	Pause.setEnabled(true);
		    	Step.setEnabled(false);
		    	changeMaze.setEnabled(false);
		    	ClientEmetteur.setMessage("commande:play");
		    	
		    	//request focus from panel touches 
		    	panelTouches.requestFocus();
			}
		});
		
		//step------------------------------------------

		
		Step.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evenement) {
		    	Restart.setEnabled(true);
		    	Pause.setEnabled(false);
		    	changeMaze.setEnabled(true);
		    	ClientEmetteur.setMessage("commande:step");

		    	//request focus from panel touches 
		    	panelTouches.requestFocus();
			}
		});
		
		//pause------------------------------------------

		
		Pause.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent evenement) {
		    	Restart.setEnabled(true);
		    	Run.setEnabled(true);
		    	Step.setEnabled(true);
		    	Run.setEnabled(true);
		    	Pause.setEnabled(false);
		    	changeMaze.setEnabled(true);
		    	ClientEmetteur.setMessage("commande:pause");

			}
		});
		
		//changeMaze------------------------------------------
		//Pas sur de le laisser
		changeMaze.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				JFileChooser chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new File("layouts")); 
				chooser.showOpenDialog(null);
				
				String chemin = chooser.getSelectedFile().getAbsolutePath();
				
		    	Restart.setEnabled(true);
		    	Run.setEnabled(false);
		    	Step.setEnabled(false);
		    	ClientEmetteur.setMessage("commande:" + chemin);

			}
		});

	}
	
	
	/*
	 * Tous les getters et setters.
	 */
	public  void setFood(boolean foodt[][]){
		food = foodt;
		this.labyrinthe.food = foodt;
	}
	public boolean[][] getFood(){
		return food;
	}
	public  void setCapsule(boolean capsulet[][]){
		capsule = capsulet;
		this.labyrinthe.setCapsuleFull(capsule);
	}
	public boolean[][] getCapsule(){
		return capsule;
	}
	public void setInvincible(boolean isInvincible){
		this.isInvincible = isInvincible ;
	}
	
	public boolean getInvincible(){
		return this.isInvincible;
	}
	public void setFantomes(ArrayList<PositionAgent> fantomes){
		this.fantomes = fantomes;
		this.labyrinthe.setGhosts_start(fantomes);
	}
	public ArrayList<PositionAgent> getFantomes(){
		return this.fantomes;
	}
	public void setPacmans(ArrayList<PositionAgent> pacmans){
		this.pacmans = pacmans;
		this.labyrinthe.setPacman_start(pacmans);
	}
	public ArrayList<PositionAgent> getPacmans(){
		return this.pacmans;
	}
	public void setScore(int score){
		this.NbPoints = score ;
	}
	
	public int getScore(){
		return this.NbPoints;
	}
	public void setVie(int vie){
		this.NbVies = vie ;
	}
	
	public int getVie(){
		return this.NbVies;
	}
	public void setTours(int NbTours){
		this.NbTours = NbTours ;
	}
	
	public int getTours(){
		return this.NbTours;
	}
	public void setChemin(String chemin){
		this.chemin = chemin ;
	}
	
	public String getChemin(){
		return this.chemin;
	}
	public void setMusique(String musique){
		this.musique = musique ;
	}
	
	public String getMusique(){
		return this.musique;
	}
	public void setEtat(String etat){
		this.etat = etat ;
	}
	
	public String getEtat(){
		return this.etat;
	}
}
