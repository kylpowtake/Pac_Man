
 
public class SimpleGame extends Game{
	
	void gameOver(){
		System.out.print("You Died");
		this.notifierObservateur();
	}
	
	void takeTurn(){
		System.out.println("New turn");
		NbTours++;
		notifierObservateur();
	}
	
	void initializeGame(){
		System.out.println("Init " + NbToursMax + " tours");
		
		
	}
}
