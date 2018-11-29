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
	
	public Game(Maze labyrinthe, String chemin){
		this.NbTours = 0;
		this.chemin = chemin;
	}
	
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
			Thread.sleep(1000/this.nombre_de_tours_par_secondes);
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
    
	public String getChemin(){
		return this.chemin;
	}
	
	public void setChemin(String chemin){
		this.chemin = chemin;
	}
	
	public void setLabyrinthe(Maze labyrinthe){
		System.out.println("Dans le set de labyrinthe : ");
		this.labyrinthe = labyrinthe;
	}
	
	public Maze getLabyrinthe(){
		return this.labyrinthe;
	}
	
    //méthodes abstraites
    abstract public void setActionParTouche();
	abstract public void gameOver();
	abstract public void takeTurn();
	abstract public void initializeGame();
	abstract public void actualiser(String chemin);
    abstract public boolean isLegalMove(Agent agent, AgentAction action);
    abstract public boolean isLegalMoveInt(Agent agent, int action);
    

	
    //observateur     
	public void enregistrerObservateur(Observateur observateur){observateurs.add(observateur);}
	public void supprimerObservateur(Observateur observateur){observateurs.remove(observateur);}
	public void notifierObservateur(boolean testrestart, boolean testtransformation, boolean GameOver) {
		for(int i = 0; i< observateurs.size(); i++) {
			observateurs.get(i).actualiser(testrestart, testtransformation, GameOver);
		}
	}
}
