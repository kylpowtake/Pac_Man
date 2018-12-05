import java.util.Random;


public class ComportementFantome extends ComportementAgent {
	
	public void comportement(Agent agent, Game game){
		AgentAction action = new AgentAction(agent.getNextAction());
		
		int iteration = 0;
		System.out.println("test");
		
		while(!game.isLegalMove(agent, action) || iteration < 1){
			iteration++;
			Random rand = new Random(); 
			int nombreAleatoire = rand.nextInt(4523 - 3154) + 2483;
			
			action.setDirection(nombreAleatoire % 4);			
		}
			agent.setNextAction(action.getDirection());	
	}
}
