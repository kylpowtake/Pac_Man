
public class Main {
	
	public static void main(String[] args) {
		try {
			String chemin = "layouts/capsuleClassic.lay";
			Maze laby = new Maze(chemin);
			PacmanGame game = new PacmanGame(laby, chemin);
			game.setLabyrinthe(laby);
			ControleurGame controleur = new ControleurGame(game);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}

