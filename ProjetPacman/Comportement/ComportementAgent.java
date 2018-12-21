
public abstract class ComportementAgent {
	
	public abstract void comportement(Agent agent, Game game);
	
	public abstract void comportementSOSNormal(Agent agent, Game game);

	public abstract void comportementSOSFuite(Agent agent, Game game);
	
	public void ComportementFinal(Agent agent, Game game){
		agent.setNextAction(1);
	}
	
	public int[] getTableauOrdre(int[] tableau){
		int min = 20;
		int indice = 0;
		for(int i = 0; i < tableau.length; i++){
			if(min >= tableau[i]){
				min = tableau[i];
				indice = i;
			}
		}
		int[] ordre = {indice, indice, indice, indice};
		for(int i = 0; i < 4; i++){
			if(tableau[i] >= tableau[ordre[0]]){
				ordre[3] = ordre[2];
				ordre[2] = ordre[1];
				ordre[1] = ordre[0];
				ordre[0] = i;
			} else if (tableau[i] >= tableau[ordre[1]]){
				ordre[3] = ordre[2];
				ordre[2] = ordre[1];
				ordre[1] = i;
			} else if (tableau[i] >= tableau[ordre[2]]){
				ordre[3] = ordre[2];
				ordre[2] = i;
			} else {
				ordre[3] = i;
			}
		}
		return ordre;
	}
	
	/**
	 * Fonction donnant le nombre de mur autour de la position.
	 * @param pos : la position dans le labyrinthe.
	 * @param game : Le jeu contenant le labyrinthe.
	 * @return int : le nombre de murs autour de la position, allant de 0 à 4.
	 */
	public int getNombreMurAutourPosition(PositionAgent pos, Game game){
		int mur = 0;
		if(game.getLabyrinthe().isWall(pos.getX()+1, pos.getY())){
			mur++;
		}
		if(game.getLabyrinthe().isWall(pos.getX()-1, pos.getY())){
			mur++;
		}
		if(game.getLabyrinthe().isWall(pos.getX(), pos.getY()+1)){
			mur++;
		}
		if(game.getLabyrinthe().isWall(pos.getX(), pos.getY()-1)){
			mur++;
		}
		return mur;
	}
	
	
	/**
	 * Fonction retournant la position que devrait avoir l'agent au prochain tour.
	 * @param agent : agent dont on test la prochaine position.
	 * @param game : Contient des valeurs et fonctions utiles.
	 * @return la prochaine position de l'agent.
	 */
	public PositionAgent getPositionNextAction(Agent agent, Game game){

		//Position allant être retournée.
		PositionAgent Pos = new PositionAgent(agent.getPosition());
		
		//Selon la valeur de la prochaine action de l'agent.
		switch(agent.getNextAction()){
		case 0 :
			//On va au nord, donc on baisse son Y de 1.
			Pos.setY(Pos.getY()-1);
			break;
		case 1 :
			//On va au sud, donc on augmente son Y de 1.
			Pos.setY(Pos.getY()+1);
			break;
		case 2 :
			//On va à l'est donc on augmente son X de 1.
			Pos.setX(Pos.getX()+1);
			break;
		case 3 :
			//On va à l'ouest donc on baisse son Y de 1.
			Pos.setX(Pos.getX()-1);
			break;
		default :
			//On a une autre valeur/direction, on ne fait donc rien.
			break;
		}
		//On retourne la position obtenue.
		return Pos;
	}
	
	/**
	 * Fonction retournant la direction vers laquelle il n'y a pas de mur,
	 * si il n'y a ques des murs, renvoie pause.
	 * @param PosAgent : La position au centre des quatres cases testé.
	 * @param game : Contient le labyrinthe
	 * @return la direction où il n'y a pas de mur sinon returne pause.
	 */
	public int getDirectionAvecTroisMurs(PositionAgent PosAgent, Game game){
		PositionAgent posAgent = new PositionAgent(PosAgent);
		if(!game.getLabyrinthe().isWall(posAgent.getX()+1, posAgent.getY())){
			return 2;
		}
		if(!game.getLabyrinthe().isWall(posAgent.getX()-1, posAgent.getY())){
			return 3;
		}
		if(!game.getLabyrinthe().isWall(posAgent.getX(), posAgent.getY()+1)){
			return 1;
		}
		if(!game.getLabyrinthe().isWall(posAgent.getX(), posAgent.getY()-1)){
			return 0;
		}
		return 4;
	}
	
	/**
	 * Fonction testant si il y a un mur à la position donnée.
	 * Utilisée car plus rapide à utiliser.
	 * @param posAgent : La position à vérifier
	 * @param game : contient le labyrinthe
	 * @return si il y a un mur à la position
	 */
	public boolean TestPresenceMur(PositionAgent posAgent, Game game){
		if(game.getLabyrinthe().isWall(posAgent.getX(), posAgent.getY())){
			return true;
		} else {
			return false;
		}
	}
}




