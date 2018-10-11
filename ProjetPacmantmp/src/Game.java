import java.util.ArrayList;
import java.util.List;

public abstract class Game implements Runnable,Sujet{

//attributs 
	protected int NbTours;
	protected int NbToursMax = 7;
    Thread thread; 
	boolean isRunning;
	private List<Observateur> observateurs = new ArrayList<>();
	
	
//methodes concrètes
	public void init(){
		NbTours = 0;
		initializeGame();
		notifierObservateur();
	}
	
	public void step(){
		if(NbTours < NbToursMax){takeTurn();notifierObservateur();}
		else{gameOver();notifierObservateur();}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		while(isRunning == true && NbTours < NbToursMax){
			step();
			notifierObservateur();
		}
		stop();
		if(NbTours >= NbToursMax){
			
			System.out.println("fin du jeu");
		}
		notifierObservateur();
	}
	
	public void stop(){isRunning = false;}
	
    public void launch(){ 
        thread = new Thread(this);    
        thread.start();    //lance la methode run avec l'implementation de Runnable
        isRunning = true;
    }
	
    
 //méthodes abstraites
	abstract void gameOver();
	abstract void takeTurn();
	abstract void initializeGame();
	

//observateur     
	public void enregistrerObservateur(Observateur observateur){observateurs.add(observateur);}
	public void supprimerObservateur(Observateur observateur){observateurs.remove(observateur);}
	public void notifierObservateur() {
		for(int i = 0; i< observateurs.size(); i++) {
			observateurs.get(i).actualiser();
		}
	}
}
