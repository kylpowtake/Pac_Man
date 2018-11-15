import java.util.ArrayList;
import java.util.List;

public abstract class Game implements Runnable,Sujet{

//attributs 
	protected int NbTours;
	protected int NbToursMax = 525;
    Thread thread; 
	boolean isRunning;
	long nombre_de_tours_par_secondes = 2;
	private List<Observateur> observateurs = new ArrayList<>();
	
	
//methodes concrètes
	
	public Game(){
		this.NbTours = 0;
	}
	
	public void init(){
		NbTours = 0;
		initializeGame();
	}
	
	public void step(){
		if(NbTours < NbToursMax){
			takeTurn();
		}
		else{gameOver();}
		try {
			Thread.sleep(1000/this.nombre_de_tours_par_secondes);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(this.NbTours == this.NbToursMax){
			gameOver();
		}
	}
	
	public void run(){
		while(isRunning == true && NbTours < NbToursMax){
			step();
		}
		stop();
		if(NbTours >= NbToursMax){
			System.out.println("fin du jeu");
		}
	}
	
	public void stop(){isRunning = false;}
	
    public void launch(){ 
        thread = new Thread(this);    
        thread.start();    //lance la methode run avec l'implementation de Runnable
        isRunning = true;
    }
    
    public void changement(String chemin){
    	actualiser(chemin);
    }
	
    
 //méthodes abstraites
	abstract void gameOver();
	abstract void takeTurn();
	abstract void initializeGame();
	abstract void actualiser(String chemin);

//observateur     
	public void enregistrerObservateur(Observateur observateur){observateurs.add(observateur);}
	public void supprimerObservateur(Observateur observateur){observateurs.remove(observateur);}
	public void notifierObservateur(boolean testrestart, boolean testtransformation) {
		for(int i = 0; i< observateurs.size(); i++) {
			observateurs.get(i).actualiser(testrestart, testtransformation);
		}
	}
}
