

public class Main{

	public static void main(String [] args){
		
		SimpleGame Partie = new SimpleGame();
		InterfaceController controlleur = new ControleurGame(Partie);
		ViewCommande Vue_commande = new ViewCommande(Partie,controlleur);
		Partie.initializeGame();
		//Partie.launch();

	}


}