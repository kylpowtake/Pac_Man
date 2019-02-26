package model;

import java.util.ArrayList;
import java.util.Random;


/**
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
	 * permet de créer des agent
	 */
	private AgentFabrique agentFabrique = new AgentFabrique();
	
	/**
	 * Permet de savoir si on a gagné ou perdu en fin du jeu 
	 */
	
	/**
	 * Constructeur de PacmanGame avec un labrinthe et un chemin vers le fichier contenant le labyrinthe.
	 */
	public PacmanGame(Maze labyrinthe ,String chemin){
		super(labyrinthe, chemin);
		
		
		fantomes = new ArrayList<Agent>();
		pacmans = new ArrayList<Agent>();
		
		
		this.labyrinthe = labyrinthe;
		this.RechargementAgents();
	}		
	
	/**
	 * @param fantomes : La nouvelle lif de fantômes.
	 */
	public void setFantomes(ArrayList<Agent> fantomes){
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

	
	public void RechargementAgents(){
		this.fantomes.clear();
		this.pacmans.clear();
		
		for(int i = 0; i < getLabyrinthe().getInitNumberOfGhosts(); i++){
			Agent fantome_temp  = this.agentFabrique.createAgent(false, this.getLabyrinthe().getGhosts_start().get(i),comportementFantome);
			this.fantomes.add(fantome_temp);
		}
		for(int i = 0; i < getLabyrinthe().getInitNumberOfPacmans(); i++){		
			Agent pacman_temp = this.agentFabrique.createAgent(true, this.getLabyrinthe().getPacman_start().get(i), comportementPacman);
			this.pacmans.add(pacman_temp);
		}
	}
	
	//Méthode appelé quand un tour est lancé
	public void takeTurn(){
		//set action des fantomes qui ne sont pas controlés 
		AgentAction action = new AgentAction(0);
		for(int i = 0; i < fantomes.size(); i++){
			fantomes.get(i).getComportement().comportement(fantomes.get(i),this);
		}

		//deplacement de tous les fantomes 
		for(int i = 0; i < fantomes.size(); i++){
			action.setDirection(fantomes.get(i).getNextAction());
			fantomes.get(i).getPosition().setDir(fantomes.get(i).getNextAction());
			this.moveAgent(fantomes.get(i), action);
		}
		
		mortAgent();
		
		//set action des pacmans qui ne sont pas controlés
		for(int i = 1 ; i < pacmans.size(); i++){
			pacmans.get(i).getComportement().comportement(pacmans.get(i),this);
		}
		//deplacement de tous les pacmans 
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
				this.setNbPoints(this.getNbPoints()+5);
				this.setIsInvincible(true);
				this.getLabyrinthe().estInvinsible = true;
				this.setTourInvincible(this.getNbTours() + 20);
				//renvoyer quelque chose si une capsule est mangee pour le son
			}
		}
		
		mortAgent();
		
		if(this.getTourInvincible() == this.getNbTours()){
			this.setIsInvincible(false);
			this.getLabyrinthe().estInvinsible = false;
		}
				
		gameOver();
		this.setNbTours(this.getNbTours()+1);
		ServeurEmetteur.sendMessage(this.toString());
		
		
	}

	
	/**
	 * Méthode appelée quand game doit être reinitialiser.
	 */
	public void initializeGame(){
		RechargementAgents();
	}
	
	
	/**
	 * Méthode prenant un chemin vers un layout et mettant à jour les valeurs des fantômes et des pacmans.
	 */
	public void actualiser(String chemin){
		this.setChemin(chemin);
		try {
			System.out.println("On est dans actualiser : ");
			this.setLabyrinthe(new Maze(chemin));
			this.RechargementAgents();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
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
    			System.out.println("On va au nord : " + action.getDirection());
    			agent.getPosition().setY(agent.getPosition().getY() - 1);
    			break;
    		case Maze.SOUTH:
    			System.out.println("On va au sud : " + action.getDirection());
    			agent.getPosition().setY(agent.getPosition().getY() + 1);
    			break;
    		case Maze.EAST:
    			System.out.println("On va a l'est : " + action.getDirection());
    			agent.getPosition().setX(agent.getPosition().getX() + 1);
    			break;
    		case Maze.WEST:
    			System.out.println("On va à l'ouest : " + action.getDirection());
    			agent.getPosition().setX(agent.getPosition().getX() - 1);
    			break;
    		default:
    			System.out.println("On défault : " + action.getDirection());
    			break;
        	}
    	} else {
    		System.out.println("On va dans un mur à : " + action.getDirection());
    	}
    }
    
    /**
     * on regarde pour chaque pacman si il est à la même position qu'un fantome 
     * si c'est le cas et qu'il à l'invincibilite alors le fantome meurt sinon le pacman meurt 
     */
    public void mortAgent(){
		for(int i = 0; i < pacmans.size(); i++){
			PositionAgent positionPacman = new PositionAgent(pacmans.get(i).getPosition());
			boolean isAlivePacman = true;
			for(int j=0; j< fantomes.size(); j++){
				PositionAgent positionFantome = new PositionAgent(fantomes.get(j).getPosition());	
				if(isAlivePacman == true && this.getIsInvincible() == false){				
					if(positionPacman.getX() == positionFantome.getX() && positionPacman.getY() == positionFantome.getY()){
						pacmans.remove(i);
						this.getLabyrinthe().getPacman_start().remove(i);
						i--;
						isAlivePacman = false;
						this.setNbies(this.getNbVies()-1);
						ServeurEmetteur.sendMessage("musique:sounds/pacman_death.wav;");
					}
				}
				if(isAlivePacman == true && this.getIsInvincible() == true){
					if(positionPacman.getX() == positionFantome.getX() && positionPacman.getY() == positionFantome.getY()){
						fantomes.remove(j);
						this.getLabyrinthe().getGhosts_start().remove(j);
						j--;
						this.setNbPoints(this.getNbPoints()+10);
						ServeurEmetteur.sendMessage("musique:sounds/ghost_death.wav;");
					}
					
				}
			}
		}
    }
    
    /**
     * Charge un niveau aléatoire parmis ceux disponibles
     * @return String (chemin du maze)
     */
    public String levelUp(){
		int rnd = new Random().nextInt(this.allMazes.size());
		String map = this.allMazes.get(rnd);
		this.actualiser(map);
		this.allMazes.remove(rnd);
		return map;
	}
    
    /**
     * Fin du jeu si
     * - Tous les pacmans sont morts 
     * - Toutes les pacgommes sont mangées 
     * @return true si fin endGame false sinon
     */
    public void gameOver(){
    	
    	//cas ou il n'y a plus de pacmans jouables 
    	//ou le joueur n'a plus de vies 
    	//(défaite)
    	if(getNbVies()<=0){
    		this.stop();																		//jeu en pause 
    		ServeurEmetteur.sendMessage("chemin"+"layouts/capsuleClassic.lay"+";etat:false");	//chargement d'un nouveau labyrinthe 
    		Bdd.sendScore(this.getIdentifiant(),this.getNbPoints());							//envoi du score 
    		this.setNbPoints(0);																//remise du score à 0;
    		this.setNbies(3);
    	}

    	
    	//cas ou une vie est perdue
    	//sauvegrde de l'etat actuel du labyrinthe 
    	if(this.getNbVies() < this.getNbVies()){
    		this.setNbiesTemp(this.getNbVies());
			boolean savedFood[][] = this.getLabyrinthe().getFood();
			try {
				labyrinthe = new Maze(this.getChemin());
				this.getLabyrinthe().food = savedFood;
				RechargementAgents();
			} catch (Exception e) {
				e.printStackTrace();
			}	
    	}
    	
    	//test pour savoir si il reste des capsule dans le labyrinthe 
    	boolean noCapsuleFound = true;
    	for(int i = 0; i< this.getLabyrinthe().getSizeX(); i++){
    		for(int j = 0; j < this.getLabyrinthe().getSizeY(); j++){
    			if(this.getLabyrinthe().food[i][j] == true){ noCapsuleFound = false; }
    		}
    	}
    	
    	//cas ou il n'y a plus de capsules dans le labyrinthe (victoire)
    	if(noCapsuleFound == true){
    		ServeurEmetteur.sendMessage("musique:sounds/next_level.wav");				//envoi du sound de victoire 
    		ServeurEmetteur.sendMessage("chemin:"+this.levelUp()+";etat:true");			//envoi d'un nouveau labyrinthe		
    		
    	}
    }
    
    /**
     * Cherche l'ennemi ou mur le plus proche dans la direction est de l'agent. 
     * @param TypeAgent : le type de l'agent.
     * @param agent : agent cherchant où son ses ennemis.
     * @return : la distance entre l'agent et son ennemi, si il n'y en a pas, le mur le plusproche.
     */
    public int ChercheAgentEst(boolean TypeAgent, Agent agent){
    	int valeur_distance = 0;
		PositionAgent position_agent = new PositionAgent(agent.getPosition().getX(), agent.getPosition().getY(), agent.getPosition().getDir());
    	if(TypeAgent){
			position_agent.setX(position_agent.getX()+1);
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isPacman(position_agent.getX(), position_agent.getY())){
    			position_agent.setX(position_agent.getX()+1);
    			valeur_distance++;
    		}
    		if(this.getLabyrinthe().isWall(position_agent.getX(),  position_agent.getY())){
    			if(!this.testFinMur(agent, 2)){
        				valeur_distance = -1;
    			} else {
    				valeur_distance += 10;
    			}
    		}
    	} else {
			position_agent.setX(position_agent.getX()+1);
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isFantome(position_agent.getX(), position_agent.getY())){
    			position_agent.setX(position_agent.getX()+1);
    			valeur_distance++;
    		}
    		if(this.getLabyrinthe().isWall(position_agent.getX(),  position_agent.getY())){
    			if(!this.testFinMur(agent, 2)){
    				valeur_distance = -1;
    			}else {
    				valeur_distance += 10;
    			}
    		}
    	}
    	return valeur_distance;
    }

    
    /**
     * Cherche l'ennemi ou mur le plus proche dans la direction ouest de l'agent. 
     * @param TypeAgent : le type de l'agent.
     * @param agent : agent cherchant où son ses ennemis.
     * @return : la distance entre l'agent et son ennemi, si il n'y en a pas, le mur le plusproche.
     */
    public int ChercheAgentOuest(boolean TypeAgent, Agent agent){
    	int valeur_distance = 0;
		PositionAgent position_agent = new PositionAgent(agent.getPosition().getX(), agent.getPosition().getY(), agent.getPosition().getDir());
    	if(TypeAgent){
			position_agent.setX(position_agent.getX()-1);
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isPacman(position_agent.getX(), position_agent.getY())){
    			position_agent.setX(position_agent.getX()-1);
    			valeur_distance++;
    		}
    		if(this.getLabyrinthe().isWall(position_agent.getX(),  position_agent.getY())){
    			if(!this.testFinMur(agent, 3)){
    				valeur_distance = -1;
    			} else {
    				valeur_distance += 10;
    			}
    		}
    	} else {
			position_agent.setX(position_agent.getX()-1);
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isFantome(position_agent.getX(), position_agent.getY())){
    			position_agent.setX(position_agent.getX()-1);
    			valeur_distance++;
    		}
    		if(this.getLabyrinthe().isWall(position_agent.getX(),  position_agent.getY())){
    			if(!this.testFinMur(agent, 3)){
    				valeur_distance = -1;
    			} else {
    				valeur_distance += 10;
    			}
    		}
    	}
    	return valeur_distance;
    }
    
    
    
    /**
     * Cherche l'ennemi ou mur le plus proche dans la direction sud de l'agent. 
     * @param TypeAgent : le type de l'agent.
     * @param agent : agent cherchant où son ses ennemis.
     * @return : la distance entre l'agent et son ennemi, si il n'y en a pas, le mur le plusproche.
     */
    public int ChercheAgentSud(boolean TypeAgent, Agent agent){
    	int valeur_distance = 0;
		PositionAgent position_agent = new PositionAgent(agent.getPosition().getX(), agent.getPosition().getY(), agent.getPosition().getDir());
    	if(TypeAgent){
			position_agent.setY(position_agent.getY()+1);
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isPacman(position_agent.getX(), position_agent.getY())){
    			position_agent.setY(position_agent.getY()+1);
    			valeur_distance++;
    		}
    		if(this.getLabyrinthe().isWall(position_agent.getX(),  position_agent.getY())){
    			if(!this.testFinMur(agent, 1)){
    				valeur_distance = -1;
    			} else {
    				valeur_distance += 10;
    			}
    		}
    	} else {
			position_agent.setY(position_agent.getY()+1);
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isFantome(position_agent.getX(), position_agent.getY())){
    			position_agent.setY(position_agent.getY()+1);
    			valeur_distance++;
    		}
    		if(this.getLabyrinthe().isWall(position_agent.getX(),  position_agent.getY())){
    			if(!this.testFinMur(agent, 1)){
    				valeur_distance = -1;
    			} else {
    				valeur_distance += 10;
    			}
    		}
    	}
    	return valeur_distance;
    }
    
    /**
     * Cherche l'ennemi ou mur le plus proche dans la direction ouest de l'agent. 
     * @param TypeAgent : le type de l'agent.
     * @param agent : agent cherchant où son ses ennemis.
     * @return : la distance entre l'agent et son ennemi, si il n'y en a pas, le mur le plusproche.
     */
    public int ChercheAgentNord(boolean TypeAgent, Agent agent){
    	int valeur_distance = 0;
		PositionAgent position_agent = new PositionAgent(agent.getPosition().getX(), agent.getPosition().getY(), agent.getPosition().getDir());
    	if(TypeAgent){
			position_agent.setY(position_agent.getY()-1);
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isPacman(position_agent.getX(), position_agent.getY())){
    			position_agent.setY(position_agent.getY()-1);
    			valeur_distance++;
    		}
    		if(this.getLabyrinthe().isWall(position_agent.getX(),  position_agent.getY())){
    			if(!this.testFinMur(agent, 0)){
    				valeur_distance = -1;
    			} else {
    				valeur_distance += 10;
    			}
    		}
    	} else {
			position_agent.setY(position_agent.getY()-1);
    		while(!this.getLabyrinthe().isWall(position_agent.getX(), position_agent.getY()) && !this.getLabyrinthe().isFantome(position_agent.getX(), position_agent.getY())){
    			position_agent.setY(position_agent.getY()-1);
    			valeur_distance++;
    		}
    		if(this.getLabyrinthe().isWall(position_agent.getX(),  position_agent.getY())){
    			if(!this.testFinMur(agent, 0)){
    				valeur_distance = -1;
    			} else {
    				valeur_distance += 10;
    			}
    		}
    	}
    	return valeur_distance;
    }

}

