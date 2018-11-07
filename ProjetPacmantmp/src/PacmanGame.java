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

	private Maze labyrinthe;
	private ArrayList<Agent> fantomes;
	private ArrayList<Agent> pacmans;

	//Constructeur de PacmanGame avec un labrinthe
	public PacmanGame(Maze labyrinthe){
		super();
		
		fantomes = new ArrayList<Agent>();
		pacmans = new ArrayList<Agent>();
		
		this.labyrinthe = labyrinthe;
		for(int i = 0; i < labyrinthe.getInitNumberOfGhosts(); i++){
			Agent fantome_temp = new Agent(false,new PositionAgent(1,2,3));//labyrinthe.getGhosts_start().get(i));
			this.fantomes.add(fantome_temp);
		}
		for(int i = 0; i < labyrinthe.getInitNumberOfPacmans(); i++){
			Agent pacman_temp = new Agent(true,labyrinthe.getPacman_start().get(i));
			this.pacmans.add(pacman_temp);
		}
	}		
	
	public void setLabyrinthe(Maze labyrinthe){
		this.labyrinthe = labyrinthe;
	}
	
	public Maze getLabyrinthe(){
		return this.labyrinthe;
	}
	
	public void setFantomes(ArrayList<Agent> fantomes){
		this.fantomes = fantomes;
	}
	
	public ArrayList<Agent> getFantomes(){
		return this.fantomes;
	}
	
	public void setPacmans(ArrayList<Agent> pacmans){
		this.pacmans = pacmans;
	}
	
	public ArrayList<Agent> getPacmans(){
		return this.pacmans;
	}
	
	
	
	//Méthode appelé quand une des conditions de fin de partie est vérifié
	void gameOver(){
		System.out.print("You Died");
		this.notifierObservateur(false);
	}
	
	//Méthode appelé quand un tour est lancé
	void takeTurn(){
	
		for(int i = 0; i < fantomes.size(); i++){
		}
		
		NbTours++;
		this.notifierObservateur(false);
	}
	
	//Méthode appelé quand le jeu est initialisé ou réinitialisé
	void initializeGame(){
		
		for(int i = 0; i < labyrinthe.getInitNumberOfGhosts(); i++){
			this.fantomes.get(i).setPosition(labyrinthe.getGhosts_start().get(i));
		}
		for(int i = 0; i < labyrinthe.getInitNumberOfPacmans(); i++){
			this.pacmans.get(i).setPosition(labyrinthe.getPacman_start().get(i));
		}
		this.notifierObservateur(true);
	}
	
	//Retourne vrai si l'agent peut faire l'action, retourne faux sinon
    public boolean isLegalMove(Agent agent, AgentAction action){
    	PositionAgent agentPosition = agent.getPosition();
    	int XPosition = agentPosition.getX();
    	int YPosition = agentPosition.getY();
    	
    	switch (action.getDirection()){
    		case Maze.NORTH:
    			XPosition++;
    			break;
    		case Maze.SOUTH:
    			XPosition--;
    			break;
    		case Maze.EAST:
    			YPosition++;
    			break;
    		case Maze.WEST:
    			YPosition--;
    			break;
    		case Maze.STOP:
    			break;
    	}
    	
    	
    	if(!this.labyrinthe.isWall(XPosition,YPosition)){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    public void moveAgent(Agent agent ,AgentAction action){
    	PositionAgent position = new PositionAgent(action.getVx(),action.getVy(),action.getDirection());
    	if(isLegalMove(agent,action)){
    		agent.setPosition(position);
    	}
    }
	
	
}
