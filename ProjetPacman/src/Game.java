import java.util.ArrayList;
import java.util.List;

public abstract class Game implements Runnable,Sujet{

	//attributs 
	public Touches panelTouches = new Touches();
	private Thread thread; 
	private boolean isRunning;
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
	
	
	//contructeur
	public Game(Maze labyrinthe, String chemin){
		this.NbTours = 0;
		this.chemin = chemin;
	}
	
	
	//getteurs/setteurs 
	public boolean getIsRunning(){return this.isRunning;}
	public void setIsRunnin(boolean etat){this.isRunning = etat;}
	
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
	
	
	
	//méthodes concrètes 
	public void init(){
		NbTours = 0;
		initializeGame();
	}
	
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
	
	public void run(){
		while(isRunning == true && NbTours < NbToursMax){
			step();
		}
		if(NbTours >= NbToursMax){
			System.out.println("fin du jeu");
		}
	}
	
	public void stop(){
		isRunning = false;
	}
	
    public void launch(){ 
        thread = new Thread(this);    
        thread.start();    //lance la methode run avec l'implementation de Runnable
        isRunning = true;
    }
    
    public void changement(String chemin){
    	actualiser(chemin);
    }
    
	
    //méthodes abstraites
    abstract public void setActionParTouche();
	abstract public void gameOver();
	abstract public void takeTurn();
	abstract public void initializeGame();
	abstract public void actualiser(String chemin);
    abstract public boolean isLegalMove(Agent agent, AgentAction action);
    abstract public boolean isLegalMoveInt(Agent agent, int action);
    

	
    //méthodes observateur     
	public void enregistrerObservateur(Observateur observateur){observateurs.add(observateur);}
	public void supprimerObservateur(Observateur observateur){observateurs.remove(observateur);}
	public void notifierObservateur(boolean testrestart, boolean testtransformation, boolean GameOver) {
		for(int i = 0; i< observateurs.size(); i++) {
			observateurs.get(i).actualiser(testrestart, testtransformation, GameOver);
		}
	}
}
