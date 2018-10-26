import java.io.File;



public class Main {
	
	public static void main(String[] args) {

		try {
			Maze laby = new Maze("layouts/testClassic.lay");
			PacmanGame game = new PacmanGame(laby);
			ControleurGame controleur = new ControleurGame(game);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

