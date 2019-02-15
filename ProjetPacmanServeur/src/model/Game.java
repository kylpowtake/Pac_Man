package model;

import java.io.File;
import java.util.ArrayList;

import Comportement.EnumComportement;

public abstract class Game{

	public ArrayList<String> allMazes = new ArrayList<>();
	public Thread thread; 
	public boolean isRunning;
	public boolean isInvincible;
	public int NbToursSecondes = 2;		
	public int NbTours;						//tour actuel 
	public int NbToursMax = 100000000;		//nombre de tours max  
	public int NbPoints = 0;
	public int NbVies = 3;
	public String chemin;
	public Maze labyrinthe;
	public int etatJeu = 0; 				// renvoi l'etat du jeu (0 quand le jeu n'est pas fini, 1 quand on gagne 2 perd une vie 3 quand on perd le jeu)
	public int tourInvincible; 				//Nombre de tours restant d'invincibilité pour les pacmans.

	//Liste des agents 
	public ArrayList<Agent> fantomes;
	public ArrayList<Agent> pacmans;
	
	//Comportement par default pour les agents 
	public EnumComportement comportementPacman = EnumComportement.PACMAN_RANDOM;
	public EnumComportement comportementFantome = EnumComportement.FANTOME_RANDOM;
	
	
	//getteurs/setteurs 
	public boolean getIsRunning(){return this.isRunning;}
	public void setIsRunnin(boolean etat){this.isRunning = etat;}
	
	public boolean getIsInvincible(){return this.isInvincible;}
	public void setIsInvincible(boolean etat){this.isInvincible = etat;}
	
	public int getNbToursSecondes(){return this.NbToursSecondes;}
	public void setNbToursSecondes(int nb){this.NbToursSecondes = nb;}
	
	public int getNbTours(){return this.NbTours;}
	public void setNbTours(int nb){this.NbTours = nb;}
	
	public int getNbToursMax(){return this.NbToursMax;}
	public void setNbToursMax(int nb){this.NbToursMax = nb;}
	
	public int getNbPoints(){return this.NbPoints;}
	public void setNbPoints(int nb){this.NbPoints = nb;}
	
	public int getNbVies(){return this.NbVies;}
	public void setNbies(int nb){this.NbVies = nb;}
	
	public String getChemin(){return this.chemin;}
	public void setChemin(String chemin){this.chemin = chemin;}
	
	public Maze getLabyrinthe(){return this.labyrinthe;}
	public void setLabyrinthe(Maze labyrinthe){
		System.out.println("Dans le set de labyrinthe : ");
		this.labyrinthe = labyrinthe;
	}
	public int getTourInvincible(){return this.tourInvincible;}
	public void setTourInvincible(int tourInvincible){this.tourInvincible = tourInvincible;}
	
	/**
	 * Le constructeur du jeu.
	 * @param labyrinthe : Contient le labyrinthe.
	 * @param chemin : Contient le lien vers un fichier contenant le labyrinthe original.
	 */
	public Game(Maze labyrinthe, String chemin){
		this.NbTours = 0;
		this.chemin = chemin;
	}
	
	
	
	/**
	 * Methode pour formater les informations à envoyer au client
	 *  
	 */
	public String toString(){
		String chaine = "update;food:";
		for(int i = 0; i< this.getLabyrinthe().getSizeX(); i++){
    		for(int j = 0; j < this.getLabyrinthe().getSizeY(); j++){
    			if(this.getLabyrinthe().food[i][j] == true){
    				chaine += i + " " + j + ","; 
    			}
    		}
    	}
		if(chaine.endsWith(","))
		{
		  chaine = chaine.substring(0,chaine.length() - 1);
		  chaine += ";";
		}
		chaine += "agent:";
		for(int i = 0; i < fantomes.size(); i++){
			chaine += fantomes.get(i).getTypeAgent() + " " + fantomes.get(i).getPosition() + ",";
		}
		for(int i = 0; i < pacmans.size(); i++){
			chaine += pacmans.get(i).getTypeAgent() + " " + pacmans.get(i).getPosition() + ",";
		}
		chaine += "invincible:" + this.isInvincible +";";
		chaine += "score:" + this.getNbPoints() + ";";
		chaine += "vie:" + this.getNbTours() + ";";
		chaine += "etat:" + this.etatJeu + ";";
		
		return chaine;	
	}
	
	
	/**
	 * Initialisation du jeu.
	 * chargement de tous les labyrinthes dans un tableau
	 */
	public void init(){
		NbTours = 0;
		File directory = new File("layouts");	
		File[] files = directory.listFiles();
		for ( File f : files) {
			this.allMazes.add(f.getAbsolutePath());
		}
		initializeGame();
	}
	
	/**
	 * Si le nombre de tours est inférieur au nombre de tours max on implémente un tour, sinon on finit le jeu.
	 * Après chaque tour on endort le jeu pour un certain temps. 
	 */
	public void step(){
		if(NbTours <= NbToursMax){
			takeTurn();
		}
		else{
			//rien pour l'instant il y avait un gameOver();
		}
		try {
			Thread.sleep(1000/this.NbToursSecondes);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode arrêtant la boucle du run (ServeurEmetteur).
	 */
	public void stop(){
		isRunning = false;
	}
	
	/**
	 * Méthode relançant la boucle du run (ServeurEmetteur)
	 */
    public void play(){ 
        isRunning = true;
    }
    
    /**
     * Actualise l'affichage et le game à partir d'un fichier contenant le 
     * @param chemin : le chemin vers le fichier contenant le labyrinthe.
     */
    public void changement(String chemin){
    	actualiser(chemin);
    }
    
    
 
    //Cherche le nombre d'agents adverses qu'il y a dans la partie Nord Est du labyrinthe.
    public int NombreAgentNordEst(boolean typeAgent, Agent agent){
    	//Si l'agent est un fantôme
    	int valeurRetour = 0;
    	/*
    	 * On cherche le nombre de pacmans
    	 */
    	if(typeAgent){
    		for(int i=agent.getPosition().getX(); i < this.labyrinthe.getSizeX(); i++){
    			for(int j=agent.getPosition().getY(); j > 0; j--){
    				if(this.labyrinthe.isPacman(i,  j)){
    					valeurRetour++;
    				}
    			}
    		}
    		return valeurRetour;
    	} else {
    		//Sinon c'est un pacman
    		/*
    		 * On cherche le nombre de fantomes
    		 */
        	for(int i=agent.getPosition().getX(); i > 0; i++){
        		for(int j=agent.getPosition().getY(); j<this.labyrinthe.getSizeY(); j++){
        			if(this.labyrinthe.isFantome(i,  j)){
        				valeurRetour++;
        			}
        		}
        	}
        	return valeurRetour;
    	}
    }


    /*
     * Cherche le nombre d'agents adverses qu'il y a dans la partie Nord Ouest du labyrinthe.
     */
    public int NombreAgentNordOuest(boolean typeAgent, Agent agent){
    	//Si l'agent est un fantôme
    	int valeurRetour = 0;
    	/*
    	 * On cherche le nombre de pacmans
    	 */
    	if(typeAgent){
    		for(int i=agent.getPosition().getX(); i > 0; i--){
    			for(int j=agent.getPosition().getY(); j > 0; j--){
    				if(this.labyrinthe.isPacman(i,  j)){
    					valeurRetour++;
    				}
    			}
    		}
    		return valeurRetour;
    	} else {
    		//Sinon c'est un pacman
    		/*
    		 * On cherche le nombre de fantomes
    		 */
        	for(int i=agent.getPosition().getX(); i > 0; i--){
        		for(int j=agent.getPosition().getY(); j > 0; j--){
        			if(this.labyrinthe.isFantome(i,  j)){
        				valeurRetour++;
        			}
        		}
        	}
        	return valeurRetour;
    	}
    }
   
    /*
     * Cherche le nombre d'agents adverses qu'il y a dans la partie Sud Ouest du labyrinthe.
     */
    public int NombreAgentSudOuest(boolean typeAgent, Agent agent){
    	//Si l'agent est un fantôme
    	int valeurRetour = 0;
    	/*
    	 * On cherche le nombre de pacmans
    	 */
    	if(typeAgent){
    		for(int i=agent.getPosition().getX(); i > 0; i--){
    			for(int j=agent.getPosition().getY(); j<this.labyrinthe.getSizeY(); j++){
    				if(this.labyrinthe.isPacman(i,  j)){
    					valeurRetour++;
    				}
    			}
    		}
    		return valeurRetour;
    	} else {
    		//Sinon c'est un pacman
    		/*
    		 * On cherche le nombre de fantomes
    		 */
        	for(int i=agent.getPosition().getX(); i > 0; i--){
        		for(int j=agent.getPosition().getY(); j<this.labyrinthe.getSizeY(); j++){
        			if(this.labyrinthe.isFantome(i,  j)){
        				valeurRetour++;
        			}
        		}
        	}
        	return valeurRetour;
    	}
    }
    
    /*
     * Cherche le nombre d'agents adverses qu'il y a dans la partie Sud Est du labyrinthe.
     */
    public int NombreAgentSudEst(boolean typeAgent, Agent agent){
    	//Si l'agent est un fantôme
    	int valeurRetour = 0;
    	if(typeAgent){
    		/*
    		 * On cherche le nombre de pacmans
    		 */
    		for(int i=agent.getPosition().getX(); i < this.labyrinthe.getSizeX(); i++){
    			for(int j=agent.getPosition().getY(); j<this.labyrinthe.getSizeY(); j++){
    				if(this.labyrinthe.isPacman(i,  j)){
    					valeurRetour++;
    				}
    			}
    		}
    		return valeurRetour;
    	} else {
    		//Sinon c'est un pacman
    		/*
    		 * On cherche le nombre de fantomes
    		 */
        	for(int i=agent.getPosition().getX(); i<this.labyrinthe.getSizeX(); i++){
        		for(int j=agent.getPosition().getY(); j<this.labyrinthe.getSizeY(); j++){
        			if(this.labyrinthe.isFantome(i,  j)){
        				valeurRetour++;
        			}
        		}
        	}
        	return valeurRetour;
    	}
    }
    
   /*
    * Test si il y a un passage avant le mur dans la direction donnée
    */
    public boolean testFinMur(Agent agent, int dir){
    	PositionAgent pos = new PositionAgent(agent.getPosition().getX(), agent.getPosition().getY(), agent.getPosition().getDir());
    	switch(dir){
    	case 0 :
			pos.setY(pos.getY()-1);
    		while(!this.labyrinthe.isWall(pos.getX(), pos.getY())){
    			if(!this.labyrinthe.isWall(pos.getX()+1, pos.getY())){
    				return true;
    			}
    			if(!this.labyrinthe.isWall(pos.getX()-1, pos.getY())){
    				return true;
        		}
    			pos.setY(pos.getY()-1);
    		}
    		break;
    	case 1 :
			pos.setY(pos.getY()+1);
    		while(!this.labyrinthe.isWall(pos.getX(), pos.getY())){
    			if(!this.labyrinthe.isWall(pos.getX()-1, pos.getY())){
    				return true;
    			}
    			if(!this.labyrinthe.isWall(pos.getX()+1, pos.getY())){
    				return true;
        		}
    			pos.setY(pos.getY()+1);
    		}
    		break;
    	case 2 :
			pos.setX(pos.getX()+1);
    		while(!this.labyrinthe.isWall(pos.getX(), pos.getY())){
    			if(!this.labyrinthe.isWall(pos.getX(), pos.getY()-1)){
    				return true;
    			}
    			if(!this.labyrinthe.isWall(pos.getX(), pos.getY()+1)){
    				return true;
        		}
    			pos.setX(pos.getX()+1);
    		}
    		break;
    	case 3 :
    		pos.setX(pos.getX()-1);
    		while(!this.labyrinthe.isWall(pos.getX(), pos.getY())){
    			if(!this.labyrinthe.isWall(pos.getX(), pos.getY()-1)){
    				return true;
    			}
    			if(!this.labyrinthe.isWall(pos.getX(), pos.getY()+1)){
    				return true;
        		}
    			pos.setX(pos.getX()-1);
    		}
    		break;
    	default :
    		return false;
    	}
    	return false;
    }
    
	
    //méthodes abstraites
	abstract public void takeTurn();
	abstract public void initializeGame();
	abstract public void actualiser(String chemin);
    abstract public boolean isLegalMove(Agent agent, AgentAction action);
    abstract public boolean isLegalMoveInt(Agent agent, int action);
    abstract public int ChercheAgentOuest(boolean typeAgent, Agent agent);
    abstract public int ChercheAgentEst(boolean typeAgent, Agent agent);
    abstract public int ChercheAgentNord(boolean typeAgent, Agent agent);
    abstract public int ChercheAgentSud(boolean typeAgent, Agent agent);

}
