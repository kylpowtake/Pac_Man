
public class Main {
	
	public static void main(String[] args) {
/*
	  SimpleGame game = new SimpleGame();
	  game.NbToursMax = 2;
	  
	  game.initializeGame();
	  
	  game.step();
	  game.step();
	  
	  int i = game.getNbTours();
	  System.out.println(i);
	}
	*/
		
		SimpleGame game = new SimpleGame();
		ControleurGame controleur = new ControleurGame(game);
	/*	
		game.initializeGame();
		game.launch();	
	*/
		
	}
}

