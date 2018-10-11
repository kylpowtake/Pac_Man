
public class SimpleGame extends Game{
	
	void gameOver(){
		System.out.print("You Died");
	}
	
	void takeTurn(){
		System.out.println("New turn");
		NbTours++;
	}
	
	void initializeGame(){
		System.out.println("Init " + NbToursMax + " tours");
		
		
	}
}
