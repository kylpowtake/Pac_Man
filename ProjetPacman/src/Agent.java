/**
 * Classe permettant de définir un agent 
 *
 */


public class Agent {
	
	//attributs 
	private boolean typeAgent;
	private PositionAgent position;
	private int nextAction;
	private ComportementAgent Comportementagent;
	
	/**
	 * Constructeur d'agent 
	 * @param typeAgent
	 * @param p
	 */
	public Agent(boolean typeAgent,PositionAgent p,ComportementAgent c){
		if(p.getDir()<5 && p.getDir()>-1){
			this.typeAgent = typeAgent;
			this.position = p;
			this.nextAction = p.getDir();
			this.Comportementagent = c;
		}
	}
	
	/**
	 * @return : le type de l'agent.
	 */
	boolean getTypeAgent(){
		return this.typeAgent;
	}
	
	/**
	 * @return la position de l'agent.
	 */
	PositionAgent getPosition(){
		return this.position;
	}
	
	/**
	 * Change la position si sa direction va du nord au stop.
	 * @param p : La nouvelle positionde l'agent.
	 */
	void setPosition(PositionAgent p){
		if(p.getDir()<5 && p.getDir()>-1){
			this.position = p;
		}
	}
	
	/**
	 * Change l'action si elle est entre -1 et 5 : correspond au direction du nord au sud.
	 * @param nextAction : La direction du future déplacement.
	 */
	public void setNextAction(int nextAction){
		if(nextAction > -1 && nextAction < 5){
			this.nextAction = nextAction;
		}
	}
	
	/**
	 * @return la nextAction.
	 */
	public int getNextAction(){
		return this.nextAction;
	}
	
	public ComportementAgent getComportement(){
		return this.Comportementagent;
	}
	
}