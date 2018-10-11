
import java.util.ArrayList;
import java.util.List;





public abstract class  Game implements Runnable, Subject  {
	
	protected int compteur_Nombre_Tours;
	protected int nombre_Tours_Maximum;
	protected Thread threadJeu;
	public boolean isRunning;
	
	private List<Observer> observers = new ArrayList<>();

	
	public void launch(){
		threadJeu = new Thread(this);
		threadJeu.start();
		isRunning = true;
	}
	
	
	public void init(){
		compteur_Nombre_Tours = 0;
		initializeGame();
	}
	
	
	public void step(){
		if(compteur_Nombre_Tours < nombre_Tours_Maximum){
			takeTurn();
			try {
				Thread.sleep((long)500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			gameOver();
		}
	}
	
	
	public void run(){
		isRunning = true;
		while(compteur_Nombre_Tours < nombre_Tours_Maximum && isRunning){
			step();
			notifyObserver();
		}
		
		gameOver();
	}
	
	
	public void pause(){
		isRunning = false;
	}
	
	public abstract void gameOver();
	
	public abstract void  initializeGame();
	
	public abstract void takeTurn();
	
	public void addObserver(Observer observer){
		observers.add(observer);
	}
	
	public void removeObserver(Observer observer){
		observers.remove(observer);
	}
	
	public void notifyObserver(){
		for(int i = 0; i<observers.size(); i++){
			observers.get(i).update();
		}
	}
	
}