import java.util.Map;


public class ComportementFantomeAlgo extends ComportementFantome{
	
	public void comportementSOS(Agent agent, Game game){
		
		int [] distanceAgent = null;
		
		//si les pacmans ne sont pas invinsibles 
		if(!game.getIsInvincible()){
			
			agent.getPosition().getX();
			//pour tous les pacmans 
			for(int i =0; i< game.getLabyrinthe().getInitNumberOfPacmans();i++){
				PositionAgent positionPacman = new PositionAgent(game.pacmans.get(i).getPosition());
				int pos = Math.abs(positionPacman.getX() - agent.getPosition().getX()) + Math.abs(positionPacman.getY() - agent.getPosition().getY()) ;
				distanceAgent[i] = pos;
			}
			
			//recupérer les 3 pacmans les plus proches  
			int indice1 = 99,min1 = 99;
			int indice2 = 99,min2 = 99;
			int indice3 = 99,min3 = 99;
			
			for(int j=0; j< distanceAgent.length;j++){
				if(distanceAgent[j] < min1){
					min2 = min1;
					indice2 = indice1;
					min1 = distanceAgent[j];
					indice1 = j;
				}else if(distanceAgent[j] < min2){
					min3 = min2;
					indice3 = indice2;
					min2 = distanceAgent[j];
					indice1 = j;
				}else if(distanceAgent[j] < min3){
					min3 = distanceAgent[j];
					indice1 = j;
				}
			}
			
			//faire le A*
			
			
	
		}
		
		//si les pacmans sont invinsibles 
		else{
			
		}
		
	}
}
