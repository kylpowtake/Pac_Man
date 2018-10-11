

public class Main{

	public static void main(String [] args){
		
		SimpleGame Partie = new SimpleGame();
		InterfaceController controlleur = new ControleurGame(Partie);
		
		Partie.initializeGame();
		//Partie.launch();

	}


}