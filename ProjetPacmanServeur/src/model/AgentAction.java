package model;


/**
 * @author Tarzan
 *
 * Classe permettant de définir les actions d'un agent
 * 
 * direction est un int allant de 0 à 4 et représentant la direction de l'agent
 * (nord, sud, ouest, est ou pause)
 *
 */
public class AgentAction {
	
	//attributs 
	private int direction;
	
	//constructeur 
	public AgentAction(){}
	
	public AgentAction(int direction){
		if(direction > -1 && direction < 5){
			this.direction = direction;
		}
	}
	
	//méthodes 
	public void setDirection(int direction){
		if(direction > -1 && direction < 5){
		this.direction = direction;
		}
	}
	
	public int getDirection(){
		return this.direction;
	}
	
}
