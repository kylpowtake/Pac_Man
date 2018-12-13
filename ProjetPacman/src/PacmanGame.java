import java.util.ArrayList;


/**
 * @author Pizza-Man
 * 
 * Classe héritant de Game et composé des méthodes pour gérer une partie
 * 
 * getLabyrinthe() de type Maze, contient différentes méthodes liées à l'orientation, déplacement, direction et initialisation d'un getLabyrinthe()
 * 
 * fantomes de type ArrayList<Agent> contient tout les fantomes de a partie
 * 
 * pacmans de type ArrayList<Agent> contient tout les pacmans de la partie
 *
 */

public class PacmanGame extends Game{

	/**
	 * Liste des fantômes.
	 */
	private ArrayList<Agent> fantomes;
	/**
	 * Liste des pacmans.
	 */
	private ArrayList<Agent> pacmans;
	/**
	 * Si les pacmans sont invincibles ou pas.
	 */
	private boolean isInvincible;
	/**
	 * Nombre de tours restant d'invincibilité pour les pacmans.
	 */
	private int tourInvincible;
	
	/**
	 * Constructeur de PacmanGame avec un labrinthe et un chemin vers le fichier contenant le labyrinthe.
	 */
	public PacmanGame(Maze labyrinthe ,String chemin){
		super(labyrinthe, chemin);
		
		
		fantomes = new ArrayList<Agent>();
		pacmans = new ArrayList<Agent>();
		
		this.setLabyrinthe(labyrinthe);
		for(int i = 0; i < getLabyrinthe().getInitNumberOfGhosts(); i++){
			Agent fantome_temp = new Agent(false, labyrinthe.getGhosts_start().get(i),new ComportementFantome());
			this.fantomes.add(fantome_temp);
		}
		for(int i = 0; i < getLabyrinthe().getInitNumberOfPacmans(); i++){
			
			Agent pacman_temp = new Agent(true, labyrinthe.getPacman_start().get(i),new ComportementPacman());
			this.pacmans.add(pacman_temp);
		}
	}		
	
	/**
	 * @param fantomes : La nouvelle liste de fantômes.
	 */
	public void setFantomes(ArrayList<Agent> fantomes){
		System.out.println("Dans le set de fantomes : ");
		this.fantomes = fantomes;
	}
	
	/**
	 * @return la liste des fantômes.
	 */
	public ArrayList<Agent> getFantomes(){
		return this.fantomes;
	}
	
	/**
	 * @param pacmans : Nouvelle liste de pacmans.
	 */
	public void setPacmans(ArrayList<Agent> pacmans){
		this.pacmans = pacmans;
	}
	
	/**
	 * @return la liste des pacmans.
	 */
	public ArrayList<Agent> getPacmans(){
		return this.pacmans;
	}
	
	
	/**
	 * Méthode appelé quand une des conditions de fin de partie est vérifiée
	 */
	public void gameOver(){
		System.out.print("You Died");
		this.notifierObservateur(false, false, true);
	}
	
	//Méthode appelé quand un tour est lancé
	public void takeTurn(){
		
		setActionParTouche();
		//set action des fantomes qui ne sont pas controlés 
		AgentAction action = new AgentAction(0);
		for(int i = this.getNbJoueursFantome(); i < fantomes.size(); i++){
			fantomes.get(i).getComportement().comportement(fantomes.get(i),this);
			//ComportementFantome.comportement(fantomes.get(i), this);
		}
		//deplacement des de tous les fantomes 
		for(int i = 0; i < fantomes.size(); i++){
			action.setDirection(fantomes.get(i).getNextAction());
			fantomes.get(i).getPosition().setDir(fantomes.get(i).getNextAction());
			this.moveAgent(fantomes.get(i), action);
		}
		
		mortAgent();
		
		System.out.println(this.getNbJoueursPacman());
		//set action des pacmans qui ne sont pas controlés
		for(int i = this.getNbJoueursPacman(); i < pacmans.size(); i++){
			pacmans.get(i).getComportement().comportement(pacmans.get(i),this);
			//ComportementPacman.comportement(pacmans.get(i), this);
		}
		//deplacement e tous les pacmans 
		for(int i = 0; i < pacmans.size(); i++){
			action.setDirection(pacmans.get(i).getNextAction());
			pacmans.get(i).getPosition().setDir(pacmans.get(i).getNextAction());
			this.moveAgent(pacmans.get(i), action);
			PositionAgent position = new PositionAgent(pacmans.get(i).getPosition());
			if(this.getLabyrinthe().isFood(position.getX(), position.getY())){
				this.getLabyrinthe().setFood(position.getX(), position.getY(), false);
				this.setNbPoints(this.getNbPoints()+1);
			}
			if(this.getLabyrinthe().isCapsule(position.getX(),position.getY())){
				this.getLabyrinthe().setCapsule(position.getX(), position.getY(), false);
				this.setNbPoints(this.getNbPoints()+10);
				this.isInvincible = true;
				this.getLabyrinthe().estInvinsible = true;
				tourInvincible = this.getNbTours() + 20;
			}
		}
		if(tourInvincible == this.getNbTours()){
			this.isInvincible = false; // /!\ a changer rend les pacmans invincibles des le premier tour 
			this.getLabyrinthe().estInvinsible = false;
		}
		if(finJeu() == true){
			gameOver();
		}
		mortAgent();
		this.setNbTours(this.getNbTours()+1);
		this.notifierObservateur(false, false, false);
	}
	

	/**
	 * Méthode appelée quand game doit être reinitialiser.
	 */
	public void initializeGame(){
		this.fantomes.clear();
		this.pacmans.clear();
		
		for(int i = 0; i < getLabyrinthe().getInitNumberOfGhosts(); i++){
			Agent fantome_temp = new Agent(false, this.getLabyrinthe().getGhosts_start().get(i),new ComportementFantome());
			this.fantomes.add(fantome_temp);
		}
		for(int i = 0; i < getLabyrinthe().getInitNumberOfPacmans(); i++){
			
			Agent pacman_temp = new Agent(true, this.getLabyrinthe().getPacman_start().get(i),new ComportementPacman());
			this.pacmans.add(pacman_temp);
		}
		this.notifierObservateur(true, false, false);
	}
	
	
	/**
	 * Méthode prenant un chemin vers un layout et mettant à jour les valeurs des fantômes et des pacmans.
	 */
	public void actualiser(String chemin){
		this.setChemin(chemin);
		try {
			System.out.println("On est dans actualiser : ");
			this.setLabyrinthe(new Maze(chemin));
			this.fantomes.clear();
			this.pacmans.clear();			
			for(int i = 0; i < getLabyrinthe().getInitNumberOfGhosts(); i++){
				Agent fantome_temp = new Agent(false, this.getLabyrinthe().getGhosts_start().get(i),new ComportementFantome());
				this.fantomes.add(fantome_temp);
			}
			for(int i = 0; i < getLabyrinthe().getInitNumberOfPacmans(); i++){
				Agent pacman_temp = new Agent(true, this.getLabyrinthe().getPacman_start().get(i),new ComportementPacman());
				this.pacmans.add(pacman_temp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.notifierObservateur(false, true, false);
		
	}
	
	
    /**
     * Retourne vrai si l'agent peut faire l'action, sinon retourne faux.
     * Il ne peut pas le faire si il y a un mur dans cette direction.
     */
	public boolean isLegalMove(Agent agent, AgentAction action){
    	PositionAgent agentPosition = agent.getPosition();
    	int XPosition = agentPosition.getX();
    	int YPosition = agentPosition.getY();
    	
    	switch (action.getDirection()){
    		case Maze.NORTH:
    			YPosition--;
    			break;
    		case Maze.SOUTH:
    			YPosition++;
    			break;
    		case Maze.EAST:
    			XPosition++;
    			break;
    		case Maze.WEST:
    			XPosition--;
    			break;
    		default:
    			break;
    	}	
    	if(!this.getLabyrinthe().isWall(XPosition,YPosition)){
    		return true;
    	} else {
    		return false;
    	}
    }
    
    
    
    /**
     * Retourne vrai si l'agent peut faire l'action, sinon retourne faux.
     * Il ne peut pas le faire si il y a un mur dans cette direction.
     */
    public boolean isLegalMoveInt(Agent agent, int action){
    	PositionAgent agentPosition = agent.getPosition();
    	int XPosition = agentPosition.getX();
    	int YPosition = agentPosition.getY();	
    	switch (action){
        	case Maze.NORTH:
        		YPosition--;
       			break;
        	case Maze.SOUTH:
        		YPosition++;
        		break;
        	case Maze.EAST:
        		XPosition++;
        		break;
        	case Maze.WEST:
        		XPosition--;
        		break;
        	case Maze.STOP:
        		break;
    	}    	
 		if(!this.getLabyrinthe().isWall(XPosition,YPosition)){
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
    			agent.getPosition().setY(agent.getPosition().getY() - 1);
    			break;
    		case Maze.SOUTH:
    			agent.getPosition().setY(agent.getPosition().getY() + 1);
    			break;
    		case Maze.EAST:
    			agent.getPosition().setX(agent.getPosition().getX() + 1);
    			break;
    		case Maze.WEST:
    			agent.getPosition().setX(agent.getPosition().getX() - 1);
    			break;
    		default:
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
						this.getLabyrinthe().getPacman_start().remove(i);
						isAlivePacman = false;
						System.out.println("Un Pacman est mort");
						this.setNbies(this.getNbVies()-1);
					}
				}
				if(isAlivePacman = true && this.isInvincible == true){
					if(positionPacman.getX() == positionFantome.getX() && positionPacman.getY() == positionFantome.getY()){
						fantomes.remove(j);
						this.getLabyrinthe().getGhosts_start().remove(j);
						System.out.println("Un fantome est mort");
						this.setNbPoints(this.getNbPoints()+30);
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
    		System.out.println("Tous les pacmans sont morts, fin du jeu.");
    		return true ;
    	}
    	boolean noCapsuleFound = true;
    	for(int i = 0; i< this.getLabyrinthe().getSizeX(); i++){
    		for(int j = 0; j < this.getLabyrinthe().getSizeY(); j++){
    			if(this.getLabyrinthe().food[i][j] == true){
    				noCapsuleFound = false;
    			}
    		}
    	}
    	if(noCapsuleFound == true){
    		System.out.println("Plus de capsules, fin du Jeu.");
    		return true;
    	}
    	return false;
    }
    
    /**
     * @author etudiant_pas_moi.
     * Cherche l'ennemi ou mur le plus proche dans la direction est de l'agent. 
     * @param TypeAgent : le type de l'agent.
     * @param agent : agent cherchant où son ses ennemis.
     * @return : la distance entre l'agent et son ennemi, si il n'y en a pas, le mur le plusproche.
     */
    public int ChercheAgentEst(boolean TypeAgent, Agent agent){
    	int valeur_distance = 0;
		PositionAgent position_agent = agent.getPosition();
    	if(TypeAgent){
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isPacman(position_agent.getX(), position_agent.getY())){
    			position_agent.setX(position_agent.getX()+1);
    			valeur_distance++;
    		}
    	} else {
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isFantome(position_agent.getX(), position_agent.getY())){
    			position_agent.setX(position_agent.getX()+1);
    			valeur_distance++;
    		}
    	}
    	return valeur_distance;
    }

    
    /**
     * @author etudiant_pas_moi.
     * Cherche l'ennemi ou mur le plus proche dans la direction ouest de l'agent. 
     * @param TypeAgent : le type de l'agent.
     * @param agent : agent cherchant où son ses ennemis.
     * @return : la distance entre l'agent et son ennemi, si il n'y en a pas, le mur le plusproche.
     */
    public int ChercheAgentOuest(boolean TypeAgent, Agent agent){
    	int valeur_distance = 0;
		PositionAgent position_agent = agent.getPosition();
    	if(TypeAgent){
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isPacman(position_agent.getX(), position_agent.getY())){
    			position_agent.setX(position_agent.getX()-1);
    			valeur_distance++;
    		}
    	} else {
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isFantome(position_agent.getX(), position_agent.getY())){
    			position_agent.setX(position_agent.getX()-1);
    			valeur_distance++;
    		}
    	}
    	return valeur_distance;
    }
    
    /**
     * @author etudiant_pas_moi.
     * Cherche l'ennemi ou mur le plus proche dans la direction sud de l'agent. 
     * @param TypeAgent : le type de l'agent.
     * @param agent : agent cherchant où son ses ennemis.
     * @return : la distance entre l'agent et son ennemi, si il n'y en a pas, le mur le plusproche.
     */
    public int ChercheAgentSud(boolean TypeAgent, Agent agent){
    	int valeur_distance = 0;
		PositionAgent position_agent = agent.getPosition();
    	if(TypeAgent){
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isPacman(position_agent.getX(), position_agent.getY())){
    			position_agent.setY(position_agent.getY()+1);
    			valeur_distance++;
    		}
    	} else {
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isFantome(position_agent.getX(), position_agent.getY())){
    			position_agent.setY(position_agent.getY()+1);
    			valeur_distance++;
    		}
    	}
    	return valeur_distance;
    }
    
    /**
     * @author etudiant_pas_moi.
     * Cherche l'ennemi ou mur le plus proche dans la direction ouest de l'agent. 
     * @param TypeAgent : le type de l'agent.
     * @param agent : agent cherchant où son ses ennemis.
     * @return : la distance entre l'agent et son ennemi, si il n'y en a pas, le mur le plusproche.
     */
    public int ChercheAgentNord(boolean TypeAgent, Agent agent){
    	int valeur_distance = 0;
		PositionAgent position_agent = agent.getPosition();
    	if(TypeAgent){
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isPacman(position_agent.getX(), position_agent.getY())){
    			position_agent.setY(position_agent.getY()-1);
    			valeur_distance++;
    		}
    	} else {
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isFantome(position_agent.getX(), position_agent.getY())){
    			position_agent.setY(position_agent.getY()-1);
    			valeur_distance++;
    		}
    	}
    	return valeur_distance;
    }
    
    
    
    
    

	
	public void setActionParTouche() {
		int j = 0;
		for(int i=0;i<this.getNbJoueursFantome();i++){
			this.fantomes.get(i).setNextAction(this.panelTouches.touchesCliques[j]);
			j++;
		}
		for(int i=0;i<this.getNbJoueursPacman();i++){
			this.pacmans.get(i).setNextAction(this.panelTouches.touchesCliques[j]);
			j++;
		}
		
		/*
		if(this.getNbJoueursFantome() > 0){
		    
			this.fantomes.get(0).setNextAction(this.panelTouches.touchesCliques[0]);
		}
		if(this.getNbJoueursPacman() > 0){
			this.pacmans.get(0).setNextAction(this.panelTouches.touchesCliques[0]);
		}*/
	}
	
}

