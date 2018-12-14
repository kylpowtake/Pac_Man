


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
			String chemin = "layouts/capsuleClassic.lay";
			Maze laby = new Maze(chemin);
			PacmanGame game = new PacmanGame(laby, chemin);
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

