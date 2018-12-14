
public class AgentFabrique {
	public Agent createAgent(boolean typeAgent,PositionAgent p,ComportementAgent c){
		
		//agent pacman 
		if(typeAgent == true){
			ComportementFantomeFacile compo = new ComportementFantomeFacile();
			return new Agent(typeAgent,p, compo);
			
			/*
			switch(c){
			case c:
				return new Agent(typeAgent,p,c);
				break;
			case c:
				return new Agent(typeAgent,p,c);
				break;
			default :
				return new Agent(typeAgent,p,c);
				break;	
			 */
		}
		//agent fantome 
		else{
			return new Agent(typeAgent,p,c);
			
			/*
			switch(c){
			case c:
				return new Agent(typeAgent,p,c);
				break;
			case c:
				return new Agent(typeAgent,p,c);
				break;
			default :
				return new Agent(typeAgent,p,c);
				break;
			*/	
				
		}

		/*
		System.out.println("L'agent ne peut pas etre crée");
		return null;
		*/
	}
}
