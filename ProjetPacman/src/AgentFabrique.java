
public class AgentFabrique {
	public Agent createAgent(boolean typeAgent,PositionAgent p,EnumComportement c){		
			switch (c){
			case PACMAN_RANDOM :
				return new Agent(typeAgent,p,new ComportementPacman());
			case FANTOME_RANDOM :
				return new Agent(typeAgent,p,new ComportementPacman());
			case PACMAN_FACILE :
				return new Agent(typeAgent,p,new ComportementPacmanFacile());
			case FANTOME_FACILE :
				return new Agent(typeAgent,p,new ComportementFantomeFacile());	
			
				/* à implémenter 
			case PACMAN_ALGO :
				return new Agent(typeAgent,p,new ComportementPacman());
			case FANTOME_ALGO:
				return new Agent(typeAgent,p,new ComportementPacman());	
				*/
		}
		return null;
	}
}
