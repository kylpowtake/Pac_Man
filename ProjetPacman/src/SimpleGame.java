



public class SimpleGame extends Game{

	public void initializeGame() {
		System.out.print("Jeu initialisé \n");
		this.nombre_Tours_Maximum = 10; 
	}

	public void takeTurn() {
		System.out.print("Nouveau tour lancé \n");
		compteur_Nombre_Tours++;
		notifyObserver();
	}
	
	public void gameOver() {
		System.out.print("Jeu fini \n");
	}

}