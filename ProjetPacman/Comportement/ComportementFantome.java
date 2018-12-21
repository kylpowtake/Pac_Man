import java.util.Random;


public abstract class ComportementFantome extends ComportementAgent {
	
	
	
	/* 
	 * - Si deux murs : Utiliser le comportement tous les n tours avec un nb tours mod n
	 * 
	 * Comportement : 
	 * Si en fuite : 
	 *  - Si 0 ou 1 mur : Regarde pour chaque case sans mur autour de lui pour X nombre de cases d'affilées si il y a chemin sans danger ou le moins.
	 *  
	 *  Si en normal :
	 *  - Si o ou 1 mur : Regarde en croix et calcul jusqu'au premier mur/pacman,
	 *  				  Puis Si aucune trouvaille de pacman, calcul les culs de sacs en n cases,
	 *  				  Puis si aucune unique solution, Cherche parmi n pacmans plus proche. 
	 * 
	 * 
	 */
	
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
			System.out.println("Pas de mur");
			//On applique Le comportement Normal ou Fuite
			this.ChoixComportement(agent, game);
			break;
		case 1 :
			System.out.println("Un mur");
			//On applique Le comportement Normal ou Fuite
			this.ChoixComportement(agent, game);			
			break;
		case 2 :
			//Il continue s'il n'y a pas de mur et qu'il n'est pas à l'arrêt, sinon on lance le comportement.
			System.out.println("Deux murs");
			if(this.TestPresenceMur(this.getPositionNextAction(agent, game), game) || posAgent.getDir() == 4 || game.getNbTours() % 2 == 1){
				this.ChoixComportement(agent, game);
			}
			break;
		case 3 :
			//Une seule direction possible à suivre.
			System.out.println("Trois Murs");
			agent.setNextAction(this.getDirectionAvecTroisMurs(posAgent, game));
			break;
		default :
			//Pas de direction = direction pause
			System.out.println("Quatre murs ou autre");
			agent.setNextAction(4);
			break;
		}
		System.out.println("Fin compo");
	}
	
	public abstract void comportementNormal(Agent agent, Game game);
	public abstract void comportementFuite(Agent agent, Game game);
}
