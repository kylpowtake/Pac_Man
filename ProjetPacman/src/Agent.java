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
	
	//constructeur 
	public Agent(boolean typeAgent,PositionAgent p,ComportementAgent c){
		if(p.getDir()<5 && p.getDir()>-1){
			this.typeAgent = typeAgent;
			this.position = p;
			this.nextAction = p.getDir();
			this.Comportementagent = c;
		}
	}
	
	//méthodes 
	boolean getTypeAgent(){
		return this.typeAgent;
	}
	
	PositionAgent getPosition(){
		return this.position;
	}
	
	void setPosition(PositionAgent p){
		if(p.getDir()<5 && p.getDir()>-1){
			this.position = p;
		}
	}
	
	public void setNextAction(int nextAction){
		if(nextAction > -1 && nextAction < 5){
			this.nextAction = nextAction;
		}
	}
	
	public int getNextAction(){
		return this.nextAction;
	}
	
	public ComportementAgent getComportement(){
		return this.Comportementagent;
	}
	
}