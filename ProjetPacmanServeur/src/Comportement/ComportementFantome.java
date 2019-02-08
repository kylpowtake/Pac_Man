package Comportement;
import model.*;

public abstract class ComportementFantome extends ComportementAgent {
	
	/**
	 * Fonction faisant le choix entre le comportement normal ou celui de fuite selon si pacman est vulnérable.
	 * @param agent : L'agent dont la prochaine action va être choisie.
	 * @param game : Contient le labyrinthe.
	 */
	public void ChoixComportement(Agent agent,Game game){
		if(game.getIsInvincible()){
			this.comportementFuite(agent, game);
		} else {
			this.comportementNormal(agent, game);
		}
	}
	
	
	public void comportement(Agent agent, Game game){
		
		PositionAgent posAgent = agent.getPosition();
		
		int nombre_mur = getNombreMurAutourPosition(posAgent, game);
		switch(nombre_mur){
		case 0 :
			//On applique Le comportement Normal ou Fuite
			this.ChoixComportement(agent, game);
			break;
		case 1 :
			//On applique Le comportement Normal ou Fuite
			this.ChoixComportement(agent, game);			
			break;
		case 2 :
			//Il continue s'il n'y a pas de mur et qu'il n'est pas à l'arrêt, sinon on lance le comportement une fois sur deux.
			if(this.TestPresenceMur(this.getPositionNextAction(agent, game), game) || posAgent.getDir() == 4 || game.getNbTours() % 2 == 1){
				this.ChoixComportement(agent, game);
			}
			break;
		case 3 :
			//Une seule direction possible à suivre.
			agent.setNextAction(this.getDirectionAvecTroisMurs(posAgent, game));
			break;
		default :
			//Pas de direction = direction pause
			agent.setNextAction(4);
			break;
		}
	}
	
	public abstract void comportementNormal(Agent agent, Game game);
	public abstract void comportementFuite(Agent agent, Game game);
}
