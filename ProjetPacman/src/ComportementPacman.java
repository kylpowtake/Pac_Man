import java.util.Random;


public abstract class ComportementPacman {
	
	public static void comportement(Agent agent, Game game){
		
		AgentAction action = new AgentAction(agent.getNextAction());
		
		int iteration = 0;
		boolean changement = false;
		
		while(!game.isLegalMove(agent, action) && iteration < 5){
			if(iteration > 12){
				
			}
			if(iteration > 6 && changement){
				iteration++;
				action.setDirection(action.getDirection() + 1);
			} else if(iteration > 6){
				iteration++;
				action.setDirection(0);
				changement = true;
			}
			iteration++;
			Random rand = new Random(); 
			int nombreAleatoire = rand.nextInt(4523 - 3154) + 2483;
			
			action.setDirection(nombreAleatoire % 4);			
		}
			agent.setNextAction(action.getDirection());
	}
}
