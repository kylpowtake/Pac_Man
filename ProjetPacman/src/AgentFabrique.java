
public class AgentFabrique {
	public Agent createAgent(boolean typeAgent,PositionAgent p,ComportementAgent c){
		
		//agent pacman 
		if(typeAgent == true){
			switch(c){
			case :
				return new Agent(typeAgent,p,c);
				break;
			case :
				return new Agent(typeAgent,p,c);
				break;
			default :
				return new Agent(typeAgent,p,c);
				break;	
		}
		//agent fantome 
		else{
			switch(c){
			case :
				return new Agent(typeAgent,p,c);
				break;
			case :
				return new Agent(typeAgent,p,c);
				break;
			default :
				return new Agent(typeAgent,p,c);
				break;
				
				
		}
		
		System.out.println("L'agent ne peut pas etre crée");
		return null;
		
	}
}
