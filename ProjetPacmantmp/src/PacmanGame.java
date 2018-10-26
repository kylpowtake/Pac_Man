import java.util.ArrayList;


/**
 * @author Pizza-Man
 * 
 * Classe héritant de Game et composé des méthodes pour gérer une partie
 * 
 * labyrinthe de type Maze, contient différentes méthodes liées à l'orientation, déplacement, direction et initialisation d'un labyrinthe
 * 
 * fantomes de type ArrayList<Agent> contient tout les fantomes de a partie
 * 
 * pacmans de type ArrayList<Agent> contient tout les pacmans de la partie
 *
 */

public class PacmanGame extends Game{

	Maze labyrinthe;
	ArrayList<Agent> fantomes;
	ArrayList<Agent> pacmans;

	//Constructeur de PacmanGame avec un labrinthe
	public PacmanGame(Maze labyrinthe){
		super();
		this.labyrinthe = labyrinthe;
		for(int i = 0; i < labyrinthe.getInitNumberOfGhosts(); i++){
			Agent fantome_temp = new Agent(false,labyrinthe.getGhosts_start().get(i));
			this.fantomes.add(fantome_temp);
		}
		for(int i = 0; i < labyrinthe.getInitNumberOfPacmans(); i++){
			Agent pacman_temp = new Agent(true,labyrinthe.getPacman_start().get(i));
			this.pacmans.add(pacman_temp);
		}
	}		
	
	//Méthode appelé quand une des conditions de fin de partie est vérifié
	void gameOver(){
		System.out.print("You Died");
		this.notifierObservateur();
	}
	
	//Méthode appelé quand un tour est lancé
	void takeTurn(){
		System.out.println("New turn");
		NbTours++;
		notifierObservateur();
	}
	
	//Méthode appelé quand le jeu est initialisé ou réinitialisé
	void initializeGame(){
		System.out.println("Init " + NbToursMax + " tours");
		
	}
	
}
