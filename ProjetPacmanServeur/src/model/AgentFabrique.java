package model;
import Comportement.*;


public class AgentFabrique {
	public Agent createAgent(boolean typeAgent,PositionAgent p,EnumComportement c){		
			switch (c){
			case PACMAN_RANDOM :
				return new Agent(typeAgent,p,new ComportementPacmanRandom());
			case FANTOME_RANDOM :
				return new Agent(typeAgent,p,new ComportementFantomeRandom());
			case PACMAN_FACILE :
				return new Agent(typeAgent,p,new ComportementPacmanFacile());
			case FANTOME_FACILE :
				return new Agent(typeAgent,p,new ComportementFantomeFacile());	
			case PACMAN_ALGO :
				return new Agent(typeAgent,p,new ComportementPacmanAlgo());
			case FANTOME_ALGO:
				return new Agent(typeAgent,p,new ComportementFantomeAlgo());	
		}
		return null;
	}
}
