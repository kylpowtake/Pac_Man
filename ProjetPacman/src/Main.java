import java.io.File;



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

		try {
			Maze laby = new Maze("layouts/testClassic.lay");
			PacmanGame game = new PacmanGame(laby);
			game.setLabyrinthe(laby);
			ControleurGame controleur = new ControleurGame(game);
		} catch (Exception e) {
			e.printStackTrace();
		}

	/*	
		game.initializeGame();
		game.launch();	
	*/
		
	}
}

