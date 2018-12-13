
public class AgentFabrique {
	public Agent createAgent(boolean typeAgent,PositionAgent p,EnumComportement c){
		
		switch (c){
			case PACMAN_INITIAL :
				return new Agent(typeAgent,p,new ComportementPacman());
			case FANTOME_INITIAL :
				return new Agent(typeAgent,p,new ComportementPacman());
			case PACMAN_NORMAL :
				return new Agent(typeAgent,p,new ComportementPacman());
			case PACMAN_YOLO :
				return new Agent(typeAgent,p,new ComportementPacman());
			case PACMAN_NORMAL_ALGO :
				return new Agent(typeAgent,p,new ComportementPacman());
			case PACMAN_YOLO_ALGO :
				return new Agent(typeAgent,p,new ComportementPacman());
			case FANTOME_NORMAL :
				return new Agent(typeAgent,p,new ComportementPacman());
			case FANTOME_EFFRAYE :
				return new Agent(typeAgent,p,new ComportementPacman());
			case FANTOME_NORMAL_ALGO :
				return new Agent(typeAgent,p,new ComportementPacman());
			case FANTOME_EFFRAYE_ALGO :
				return new Agent(typeAgent,p,new ComportementPacman());
				
		}
		return null;
	}
}
