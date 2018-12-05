import java.util.ArrayList;
import java.util.List;

public abstract class Game implements Runnable,Sujet{

//attributs 
	public Touches panelTouches = new Touches();
	protected int NbTours;
	protected int NbToursMax = 525;
	protected int NbPoints = 0;
	protected int NbVies = 3;
	protected int nbJoueursPacmans = 0;
	protected int nbJoueursFantome = 0;
	private String chemin;
	private Maze labyrinthe;

    Thread thread; 
	boolean isRunning;
	long nombre_de_tours_par_secondes = 2;
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
			Thread.sleep(1000/this.nombre_de_tours_par_secondes);
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
    
    /**
     * @return le chemin.
     */
	public String getChemin(){
		return this.chemin;
	}
	
	/**
	 * @param chemin : nouveau chemin.
	 */
	public void setChemin(String chemin){
		this.chemin = chemin;
	}
	
	/**
	 * @param labyrinthe : le nouveau labyrinthe.
	 */
	public void setLabyrinthe(Maze labyrinthe){
		System.out.println("Dans le set de labyrinthe : ");
		this.labyrinthe = labyrinthe;
	}
	
	/**
	 * @return le labyrinthe.
	 */
	public Maze getLabyrinthe(){
		return this.labyrinthe;
	}
	
    //méthodes abstraites
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
