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
	private boolean isInvincible;
	private int tourInvincible;
	private String chemin;
	protected int nbJoueursPacmans = 0;
	protected int nbJoueursFantome = 0;
	
	/**
	 * Constructeur de PacmanGame avec un labrinthe
	 */
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
	
	
	/**
	 * Méthode appelé quand une des conditions de fin de partie est vérifiée
	 */
	public void gameOver(){
		System.out.print("You Died");
		this.notifierObservateur(false, false);
	}
	
	//Méthode appelé quand un tour est lancé
	public void takeTurn(){
		
		AgentAction action = new AgentAction(0);
		for(int i = this.nbJoueursFantome; i < fantomes.size(); i++){
			ComportementFantome.comportement(fantomes.get(i), this);
			action.setDirection(fantomes.get(i).getNextAction());
			this.moveAgent(fantomes.get(i), action);
		}
		mortAgent();
		for(int i = this.nbJoueursPacmans; i < pacmans.size(); i++){
			ComportementPacman.comportement(pacmans.get(i), this);
			action.setDirection(pacmans.get(i).getNextAction());
			this.moveAgent(pacmans.get(i), action);
			PositionAgent position = new PositionAgent(pacmans.get(i).getPosition());
			if(this.getLabyrinthe().isFood(position.getX(), position.getY())){
				this.getLabyrinthe().setFood(position.getX(), position.getY(), false);
				this.NbPoints += 1; 
			}
			if(this.getLabyrinthe().isCapsule(position.getX(),position.getY())){
				this.getLabyrinthe().setCapsule(position.getX(), position.getY(), false);
				this.NbPoints += 10; //si un pacman mange une pacgomme il a 10 point 
				this.isInvincible = true;
				tourInvincible = this.NbTours + 10;
			}
		}
		if(tourInvincible == this.NbTours){
			this.isInvincible = true; // /!\ a changer rend les pacmans invincibles des le premier tour 
		}
		if(finJeu() == true){
			gameOver();
		}
		mortAgent();
		NbTours++;
		this.notifierObservateur(false, false);
	}
	
	//Méthode appelé quand le jeu est initialisé ou réinitialisé
	public void initializeGame(){
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
    			newaction.setDirection(action.getDirection() + 1);
    			agent.setNextAction(newaction.getDirection());
    			break;
    		case Maze.EAST:
    			newaction.setDirection(action.getDirection()  - 2);
    			agent.setNextAction(newaction.getDirection());
    			break;
    		case Maze.WEST:
    			newaction.setDirection(action.getDirection() - 2);
    			agent.setNextAction(newaction.getDirection());
    			break; 
    		}
    	}
    }
    
    /**
     * on regarde pour chaque pacman si il est sur un fantome 
     * si c'est le cas et qu'il a l'invincibilite alors le fantome meurt sinon pacman meurt 
     */
    public void mortAgent(){
		for(int i = 0; i < pacmans.size(); i++){
			PositionAgent positionPacman = new PositionAgent(pacmans.get(i).getPosition());
			boolean isAlivePacman = true;
			for(int j=0; j< fantomes.size(); j++){
				PositionAgent positionFantome = new PositionAgent(fantomes.get(j).getPosition());
				if(isAlivePacman == true && this.isInvincible == false){
					if(positionPacman.getX() == positionFantome.getX() && positionPacman.getY() == positionFantome.getY()){
						pacmans.remove(i);
						this.labyrinthe.getPacman_start().remove(i);
						isAlivePacman = false;
						System.out.println("Un Pacman est mort");
						this.notifierObservateur(false, false);
						this.NbVies -= 1;
					}
				}
				if(isAlivePacman = true && this.isInvincible == true){
					if(positionPacman.getX() == positionFantome.getX() && positionPacman.getY() == positionFantome.getY()){
						fantomes.remove(j);
						this.labyrinthe.getGhosts_start().remove(j);
						System.out.println("Un fantome est mort");
						this.notifierObservateur(false, false);
						this.NbPoints += 30; //si un pacman mange une pacgomme il a 30 point
					}
					
				}
			}
		}
    }
    
    /**
     * Fin du jeu si
     * - Tous les pacmans sont morts 
     * - Toutes les pacgommes sont mangées 
     * @return true si fin endGame false sinon
     */
    public boolean finJeu(){
    	if(pacmans.isEmpty()){
    		System.out.println("Tous les pacmans sont morts fin du jeu");
    		return true ;
    	}
    	boolean noCapsuleFound = true;
    	for(int i = 0; i< this.labyrinthe.getSizeX(); i++){
    		for(int j = 0; j < this.labyrinthe.getSizeY(); j++){
    			if(this.labyrinthe.food[i][j] == true){
    				noCapsuleFound = false;
    			}
    		}
    	}
    	if(noCapsuleFound == true){
    		System.out.println("Plus de capsules fin du Jeu");
    		return true;
    	}
    	return false;
    }
	
}

