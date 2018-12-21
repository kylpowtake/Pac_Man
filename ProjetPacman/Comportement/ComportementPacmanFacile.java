import java.util.ArrayList;

public class ComportementPacmanFacile extends ComportementPacman {
	
	ArrayList<PositionAgent> distanceCapsule = new ArrayList<PositionAgent>();
	
	@Override
	public void comportementFuite(Agent agent, Game game) {
		System.out.println("comportement pacman facile");
		AgentAction action = new AgentAction(agent.getNextAction());
		
		int x = 0;
		int y = 0;
		int min = 5000;
		
		for(int i = 0; i< game.getLabyrinthe().getSizeX(); i++){
    		for(int j = 0; j < game.getLabyrinthe().getSizeY(); j++){
    			if(game.getLabyrinthe().food[i][j] == true){
    				x = Math.abs(agent.getPosition().getX() - i);
    				y = Math.abs(agent.getPosition().getY() - j);
    				int distance = x + y;				
    				distanceCapsule.add(new PositionAgent(i,j,distance));
    			}
    		}
    	}
		
		for(int i =0; i< distanceCapsule.size();i++){
			if(distanceCapsule.get(i).getDir() < min){
				min = this.distanceCapsule.get(i).getDir();
				x = this.distanceCapsule.get(i).getX();
				y = this.distanceCapsule.get(i).getY();
			}
		}
		
		
		if(agent.getPosition().getX() < x){
			action.setDirection(2);	
		}else if(agent.getPosition().getX() > x){
			action.setDirection(3);
		}else{
			if(agent.getPosition().getY() < y){
				action.setDirection(1);
			}else{
				action.setDirection(0);
			}
		}
		
		if(!game.isLegalMove(agent, action)){
			
			if(agent.getPosition().getY() < y){
				action.setDirection(1);
			}else{
				action.setDirection(0);
			}if(agent.getPosition().getX() < x){
				action.setDirection(2);	
			}else{
				action.setDirection(3);
			}
		}
		
		
		
		agent.setNextAction(action.getDirection());
	}	


	@Override
	public void comportementNormal(Agent agent, Game game) {
		System.out.println("comportement pacman facile");
		AgentAction action = new AgentAction(agent.getNextAction());
		
		int x = 0;
		int y = 0;
		int min = 5000;
		boolean hautBas = true;
		boolean gaucheDroite = true;
		
		for(int i = 0; i< game.getLabyrinthe().getSizeX(); i++){
    		for(int j = 0; j < game.getLabyrinthe().getSizeY(); j++){
    			if(game.getLabyrinthe().food[i][j] == true){
    				x = Math.abs(agent.getPosition().getX() - i);
    				y = Math.abs(agent.getPosition().getY() - j);
    				int distance = x + y;				
    				distanceCapsule.add(new PositionAgent(x,y,distance));
    			}
    		}
    	}
		
		for(int i =0; i< distanceCapsule.size();i++){
			if(distanceCapsule.get(i).getDir() < min){
				min = this.distanceCapsule.get(i).getDir();
				x = this.distanceCapsule.get(i).getX();
				y = this.distanceCapsule.get(i).getY();
			}
		}
		
		if(agent.getPosition().getX() < x){
			hautBas = true;
			
		}else if(agent.getPosition().getX() > x){
			hautBas = false;
		}else{
			if(agent.getPosition().getY() < y){
				gaucheDroite = true;
			}else{
				gaucheDroite = false;
			}
		}
		
		while(!game.isLegalMove(agent, action)){
			
			if(hautBas == true){
				action.setDirection(0);			
			}else{
				action.setDirection(1);
			}
			
			if(gaucheDroite == true){
				action.setDirection(2);
			}else{
				action.setDirection(3);
			}
		}
		
		agent.setNextAction(action.getDirection());
	}	
}
