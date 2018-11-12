public class Agent {
	private boolean typeAgent;
	private PositionAgent position;
	private int nextAction;
	
	public Agent(boolean typeAgent,PositionAgent p){
		if(p.getDir()<5 && p.getDir()>-1){
			this.typeAgent = typeAgent;
			this.position = p;
			this.nextAction = p.getDir();
		}
	}
	
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

}