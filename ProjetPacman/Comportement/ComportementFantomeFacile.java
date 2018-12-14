
public class ComportementFantomeFacile extends ComportementFantome {
	
	public void comportement(Agent agent, Game game){
		System.out.println("Réussi");
		int[] TableauValeur = new int[4];
		TableauValeur[0] = game.NombreAgentNordEst(true, agent);
		TableauValeur[1] = game.NombreAgentNordOuest(true, agent);
		TableauValeur[2] = game.NombreAgentSudEst(true, agent);
		TableauValeur[3] = game.NombreAgentSudOuest(true, agent);
		boolean changement_direction = false;
		int[] ordre = this.getTableauOrdre(TableauValeur);
		int distanceNord = game.ChercheAgentNord(true, agent);
		int distanceSud = game.ChercheAgentSud(true, agent);
		int distanceEst = game.ChercheAgentEst(true, agent);
		int distanceOuest = game.ChercheAgentOuest(true, agent);
		System.out.println("test dans compo : " + TableauValeur[0] + " et " + TableauValeur[1] + " et " + TableauValeur[2] + " et " + TableauValeur[3]);
		System.out.println("test pour ordre : " + ordre[0] + " et " + ordre[1] + " et " + ordre[2] + " et " + ordre[3]);

		/*
		 * Selon la valeur correspondant au NombreAgentZone le plus élevé,
		 * On regarde si il y a un pacman plus près dans la ligne 1 ou dans la ligne 2
		 */
		switch(ordre[0]){
		case 0 :
			if(distanceNord >= distanceEst){
					agent.setNextAction(0);
					changement_direction = true;
			} else if(distanceEst > -1){
					agent.setNextAction(2);
					changement_direction = true;
			}
			break;
		case 1 :
			if(distanceNord >= distanceOuest){
				agent.setNextAction(0);
				changement_direction = true;
		} else if(distanceOuest > -1){
				agent.setNextAction(3);
				changement_direction = true;
		}			
			break;
		case 2 :
			if(distanceSud >= distanceEst){
				agent.setNextAction(1);
				changement_direction = true;
		} else if(distanceEst > -1){
				agent.setNextAction(2);
				changement_direction = true;
		}
			break;
		case 3 :
			if(distanceSud >= distanceOuest){
				System.out.println("Dans le case 3");
				agent.setNextAction(1);
				changement_direction = true;
		} else if(distanceEst > -1){
				agent.setNextAction(3);
				changement_direction = true;
		}
			break;
		}
		/*
		 * Si la valeur de direction n'a pas encore été changé
		 */
		if(!changement_direction){
			System.out.println("Dans le deuxième switch");
			/*
			 * Selon la valeur correspondant au NombreAgentZone le plus élevé,
			 * On regarde si il y a un pacman plus près dans la ligne 1 ou dans la ligne 2
			 */
			switch(ordre[1]){
			case 0 :
				if(distanceNord >= distanceEst){
						agent.setNextAction(0);
						changement_direction = true;
				} else if(distanceEst > -1){
						agent.setNextAction(2);
						changement_direction = true;
				}
				break;
			case 1 :
				if(distanceNord >= distanceOuest){
					agent.setNextAction(0);
					changement_direction = true;
			} else if(distanceOuest > -1){
					agent.setNextAction(3);
					changement_direction = true;
			}			
				break;
			case 2 :
				if(distanceSud >= distanceEst){
					agent.setNextAction(1);
					changement_direction = true;
			} else if(distanceEst > -1){
					agent.setNextAction(2);
					changement_direction = true;
			}
				break;
			case 3 :
				if(distanceSud >= distanceOuest){
					agent.setNextAction(1);
					changement_direction = true;
			} else if(distanceEst > -1){
					agent.setNextAction(3);
					changement_direction = true;
			}
				break;
			}
		}
		/*
		 * Si la valeur de direction n'a toujours pas changé
		 */
		if(!changement_direction){
			/*
			 * On applique le choix par hasard
			 */
			System.out.println("ou pas");
			this.comportementSOS(agent, game);
		}
	}
	
}
