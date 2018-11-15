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
	private String chemin;
	
	//Constructeur de PacmanGame avec un labrinthe
	public PacmanGame(Maze labyrinthe, String chemin){
		super();
		
		this.chemin = chemin;
		
		fantomes = new ArrayList<Agent>();
		pacmans = new ArrayList<Agent>();
		
		this.labyrinthe = labyrinthe;
		for(int i = 0; i < labyrinthe.getInitNumberOfGhosts(); i++){
			//Agent fantome_temp = new Agent(false,new PositionAgent(labyrinthe.getGhosts_start().get(i).getX(), labyrinthe.getGhosts_start().get(i).getY(), labyrinthe.getGhosts_start().get(i).getDir()));//labyrinthe.getGhosts_start().get(i));
			Agent fantome_temp = new Agent(false, labyrinthe.getGhosts_start().get(i));
			this.fantomes.add(fantome_temp);
		}
		for(int i = 0; i < labyrinthe.getInitNumberOfPacmans(); i++){
			
			//Agent pacman_temp = new Agent(false,new PositionAgent(labyrinthe.getPacman_start().get(i).getX(), labyrinthe.getPacman_start().get(i).getY(), labyrinthe.getPacman_start().get(i).getDir()));//labyrinthe.getGhosts_start().get(i));
			Agent pacman_temp = new Agent(true, labyrinthe.getPacman_start().get(i));
			this.pacmans.add(pacman_temp);
		}
	}		
	
	public String getChemin(){
		return this.chemin;
	}
	
	public void setChemin(String chemin){
		this.chemin = chemin;
	}
	
	public void setLabyrinthe(Maze labyrinthe){
		System.out.println("Dans le set de labyrinthe : ");
		this.labyrinthe = labyrinthe;
	}
	
	public Maze getLabyrinthe(){
		return this.labyrinthe;
	}
	
	public void setFantomes(ArrayList<Agent> fantomes){
		System.out.println("Dans le set de fantomes : ");
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
		this.notifierObservateur(false, false);
	}
	
	//Méthode appelé quand un tour est lancé
	void takeTurn(){
		
		AgentAction action = new AgentAction(0);
		for(int i = 0; i < fantomes.size(); i++){
			System.out.println("on est dans le mouvement : " + fantomes.get(i).getPosition().getX() + fantomes.get(i).getPosition().getY() + 
					" et dans initial : " + this.labyrinthe.getGhosts_start().get(i).getX() + this.labyrinthe.getGhosts_start().get(i).getY() + ".\n\n");
			action.setDirection(fantomes.get(i).getNextAction());
			this.moveAgent(fantomes.get(i), action);
		}
		
		for(int i = 0; i < pacmans.size(); i++){
			action.setDirection(pacmans.get(i).getNextAction());
			this.moveAgent(pacmans.get(i), action);
			PositionAgent position = new PositionAgent(pacmans.get(i).getPosition());
			if(this.getLabyrinthe().isFood(position.getX(), position.getY())){
				this.getLabyrinthe().setFood(position.getX(), position.getY(), false);
			}
		}
		
		NbTours++;
		this.notifierObservateur(false, false);
	}
	
	//Méthode appelé quand le jeu est initialisé ou réinitialisé
	void initializeGame(){
		for(int i = 0; i < labyrinthe.getInitNumberOfGhosts(); i++){
			/*
			PositionAgent p = new PositionAgent(labyrinthe.getGhosts_start().get(i).getX(), labyrinthe.getGhosts_start().get(i).getY(), labyrinthe.getGhosts_start().get(i).getDir());
			this.fantomes.get(i).setPosition(p);
			*/
			this.fantomes.get(i).setPosition(labyrinthe.getGhosts_start().get(i));
		}
		for(int i = 0; i < labyrinthe.getInitNumberOfPacmans(); i++){
			/*
			PositionAgent p = new PositionAgent(labyrinthe.getPacman_start().get(i).getX(), labyrinthe.getPacman_start().get(i).getY(), labyrinthe.getPacman_start().get(i).getDir());
			this.fantomes.get(i).setPosition(p);
			*/
			this.pacmans.get(i).setPosition(labyrinthe.getPacman_start().get(i));
			}
		this.notifierObservateur(true, false);
	}
	
	
	//Méthode prenant en paramètre un chemin vers un layout et actualisant les valeurs
	public void actualiser(String chemin){
		this.chemin = chemin;
		try {
			System.out.println("On est dans actualiser : ");
			this.labyrinthe = new Maze(chemin);
			
			this.fantomes.clear();
			this.pacmans.clear();
			
			for(int i = 0; i < labyrinthe.getInitNumberOfGhosts(); i++){
				//Agent fantome_temp = new Agent(false,new PositionAgent(labyrinthe.getGhosts_start().get(i).getX(), labyrinthe.getGhosts_start().get(i).getY(), labyrinthe.getGhosts_start().get(i).getDir()));//labyrinthe.getGhosts_start().get(i));
				Agent fantome_temp = new Agent(false, labyrinthe.getGhosts_start().get(i));
				this.fantomes.add(fantome_temp);
			}
			for(int i = 0; i < labyrinthe.getInitNumberOfPacmans(); i++){
				
				//Agent pacman_temp = new Agent(false,new PositionAgent(labyrinthe.getPacman_start().get(i).getX(), labyrinthe.getPacman_start().get(i).getY(), labyrinthe.getPacman_start().get(i).getDir()));//labyrinthe.getGhosts_start().get(i));
				Agent pacman_temp = new Agent(true, labyrinthe.getPacman_start().get(i));
				this.pacmans.add(pacman_temp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.notifierObservateur(false, true);
		
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
    
    /**
     * Met à jour la position de l'agent donné en fonction du déplacement donné,
     * 		si l'action n'est pas légale, alors ça position est pour l'instant changé de façon manuel.  
     * @param agent : L'agent dont la position doit être changé
     * @param action : L'action faites par l'agent
     */
    public void moveAgent(Agent agent, AgentAction action){
    	
    	if(isLegalMove(agent, action)){
        	switch (action.getDirection()){
    		case Maze.NORTH:
    			agent.getPosition().setX(agent.getPosition().getX() + 1);
    			break;
    		case Maze.SOUTH:
    			agent.getPosition().setX(agent.getPosition().getX() - 1);
    			break;
    		case Maze.EAST:
    			agent.getPosition().setY(agent.getPosition().getY() + 1);
    			break;
    		case Maze.WEST:
    			agent.getPosition().setY(agent.getPosition().getY() - 1);
    			break;
    		case Maze.STOP:
    			break;
    		default:
    			break;
        	}
    	} else {
    		AgentAction newaction = new AgentAction(0);
    		switch(action.getDirection()){
    		case Maze.NORTH:
    			newaction.setDirection(action.getDirection() + 3);
    			agent.setNextAction(newaction.getDirection());
    			break;
    		case Maze.SOUTH:
    			newaction.setDirection(action.getDirection() - 1);
    			agent.setNextAction(newaction.getDirection());
    			break;
    		case Maze.EAST:
    			newaction.setDirection(action.getDirection() - 1);
    			agent.setNextAction(newaction.getDirection());
    			break;
    		case Maze.WEST:
    			newaction.setDirection(action.getDirection() - 1);
    			agent.setNextAction(newaction.getDirection());
    			break; 
    		}
    	}
    }
	
}
