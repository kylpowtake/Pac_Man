import java.util.Random;


public class ComportementFantomeRandom extends ComportementFantome{
	
	public void comportementNormal(Agent agent, Game game){
		System.out.println("comportement fantome Random");
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
	
	public void comportementFuite(Agent agent, Game game){
		System.out.println("comportement fantome Random");
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
