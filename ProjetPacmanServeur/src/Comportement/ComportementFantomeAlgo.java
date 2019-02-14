package Comportement;
import model.*;

public class ComportementFantomeAlgo extends ComportementFantome{

	@Override
	public void comportementNormal(Agent agent, Game game) {
		PositionAgent ennemie = new PositionAgent(game.pacmans.get(0).getPosition());
		PositionAgent temp = new PositionAgent(this.CheminAgentEnnemie(agent.getPosition(), ennemie, game));
		if(temp != null){
			if(agent.getPosition().getX() == temp.getX() - 1){
				agent.setNextAction(2);
			} else if(agent.getPosition().getX() == temp.getX() + 1){
				agent.setNextAction(3);
			} else if(agent.getPosition().getY() == temp.getY() - 1){
				agent.setNextAction(1);
			} else {
				agent.setNextAction(0);
			}
		}
	}

	@Override
	public void comportementFuite(Agent agent, Game game) {
		PositionAgent ennemie = new PositionAgent(game.pacmans.get(0).getPosition());
		PositionAgent temp = this.CheminAgentEnnemie(agent.getPosition(), ennemie, game);
		if(temp != null){
			if(agent.getPosition().getX() == temp.getX() + 1){
				agent.setNextAction(2);
			} else if(agent.getPosition().getX() == temp.getX() - 1){
				agent.setNextAction(3);
			} else if(agent.getPosition().getY() == temp.getY() + 1){
				agent.setNextAction(1);
			} else {
				agent.setNextAction(0);
			}
		}
	}
}
