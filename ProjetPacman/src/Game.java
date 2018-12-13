import java.util.ArrayList;
import java.util.List;

public abstract class Game implements Runnable,Sujet{

	//attributs 
	public Touches panelTouches = new Touches();
	private Thread thread; 
	private boolean isRunning;
	private boolean isInvincible;
	private boolean finJeu;
	private int NbToursSecondes = 2;
	private int NbTours;
	private int NbToursMax = 100000000;
	private int NbPoints = 0;
	private int NbVies = 3;
	private int NbJoueursPacman = 0;
	private int NbJoueursFantome = 0;
	private String chemin;
	private Maze labyrinthe;
	private List<Observateur> observateurs = new ArrayList<>();
	
	
//methodes concrètes
	
	/**
	 * Le constructeur du jeu.
	 * @param labyrinthe : Contient le labyrinthe.
	 * @param chemin : Contient le lien vers un fichier contenant le labyrinthe original.
	 */
	public Game(Maze labyrinthe, String chemin){
		this.NbTours = 0;
		this.chemin = chemin;
	}
	
	
	//getteurs/setteurs 
	public boolean getIsRunning(){return this.isRunning;}
	public void setIsRunnin(boolean etat){this.isRunning = etat;}
	
	public boolean getIsInvincible(){return this.isInvincible;}
	public void setIsInvincible(boolean etat){this.isInvincible = etat;}
	
	public boolean getFinJeu(){return this.finJeu;}
	public void setFinJeu(boolean etat){this.finJeu = etat;}
	
	public int getNbToursSecondes(){return this.NbToursSecondes;}
	public void setNbToursSecondes(int nb){this.NbToursSecondes = nb;}
	
	public int getNbTours(){return this.NbTours;}
	public void setNbTours(int nb){this.NbTours = nb;}
	
	public int getNbToursMax(){return this.NbToursMax;}
	public void setNbToursMax(int nb){this.NbToursMax = nb;}
	
	public int getNbPoints(){return this.NbPoints;}
	public void setNbPoints(int nb){this.NbPoints = nb;}
	
	public int getNbVies(){return this.NbVies;}
	public void setNbies(int nb){this.NbVies = nb;}
	
	public int getNbJoueursPacman(){return this.NbJoueursPacman;}
	public void setNbJoueursPacman(int nb){this.NbJoueursPacman = nb;}
	
	public int getNbJoueursFantome(){return this.NbJoueursFantome;}
	public void setNbJoueursFantome(int nb){this.NbJoueursFantome = nb;}
	
	public String getChemin(){return this.chemin;}
	public void setChemin(String chemin){this.chemin = chemin;}
	
	public Maze getLabyrinthe(){return this.labyrinthe;}
	public void setLabyrinthe(Maze labyrinthe){
		System.out.println("Dans le set de labyrinthe : ");
		this.labyrinthe = labyrinthe;
	}

	/**
	 * On initialise le jeu.
	 */
	public void init(){
		NbTours = 0;
		initializeGame();
	}
	
	/**
	 * Si le nombre de tours est inférieur au nombre de tours max on implémente un tour, sinon on finit le jeu.
	 * Après chaque tour on endort le jeu pour un certain temps. 
	 */
	public void step(){
		if(NbTours <= NbToursMax){
			takeTurn();
		}
		else{
			gameOver();
		}
		try {
			Thread.sleep(1000/this.NbToursSecondes);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Tant que le jeu n'est pas finit on fait des steps. 
	 */
	public void run(){
		while(isRunning == true && NbTours < NbToursMax){
			step();
		}
		if(NbTours >= NbToursMax){
			System.out.println("fin du jeu");
		}
	}
	
	/**
	 * Méthode arrêtant la boucle du run.
	 */
	public void stop(){
		isRunning = false;
	}
	
	/**
	 * Lance le run.
	 */
    public void launch(){ 
        thread = new Thread(this);    
        thread.start();    //lance la methode run avec l'implementation de Runnable
        isRunning = true;
    }
    
    /**
     * Actualise l'affichage et le game à partir d'un fichier contenant le 
     * @param chemin : le chemin vers le fichier contenant le labyrinthe.
     */
    public void changement(String chemin){
    	actualiser(chemin);
    }
    
	
    //méthodes abstraites
    abstract public void setActionParTouches();
	abstract public void gameOver();
	abstract public void takeTurn();
	abstract public void initializeGame();
	abstract public void actualiser(String chemin);
    abstract public boolean isLegalMove(Agent agent, AgentAction action);
    abstract public boolean isLegalMoveInt(Agent agent, int action);

	
    //observateur     
    /**
     * Ajoute l'observateur donnée à la liste d'observateurs.
     */
	public void enregistrerObservateur(Observateur observateur){
		observateurs.add(observateur);
	}
	
	/**
	 * enlève l'observateur donné de laliste d'observateur.
	 */
	public void supprimerObservateur(Observateur observateur){
		observateurs.remove(observateur);
	}
	
	/**
	 * Notifie tous les observateurs pour qu'ils puisse appliquer les méthodesnécessaires.
	 * @Param booleanRestart : Indique si il y a besoin d'afficher le labyrinthe de départ.
	 * @Param booleanTransformation : Indique si il y a besoin d'afficher le changement de labyrinthe.
	 * @Param GameOver : Indique si il y a besoin d'afficher le game over.
	 */
	public void notifierObservateur(boolean booleanRestart, boolean booleanTransformation, boolean GameOver) {
		for(int i = 0; i< observateurs.size(); i++) {
			observateurs.get(i).actualiser(booleanRestart, booleanTransformation, GameOver);
		}
	}
}
