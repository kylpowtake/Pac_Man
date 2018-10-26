public class Agent {
	private boolean typeAgent;
	private PositionAgent position;
	
	
	public Agent(boolean typeAgent,PositionAgent p){
		if(p.getDir()<5 && p.getDir()>-1){
			this.typeAgent = typeAgent;
			this.position = p;
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
}