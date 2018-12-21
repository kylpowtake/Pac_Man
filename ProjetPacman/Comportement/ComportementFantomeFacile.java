public class ComportementFantomeFacile extends ComportementFantome {
	
	
	public void comportementFuite(Agent agent, Game game){
		System.out.println("comportement fantome Facile");
		/*
		 * Pacman est invincible
		 */
		Agent agentTest = new Agent(true, agent.getPosition(), agent.getComportement());
		agentTest.setNextAction(agent.getNextAction());		
		int[] TableauValeur = new int[4];
		TableauValeur[0] = game.NombreAgentNordEst(true, agentTest);
		TableauValeur[1] = game.NombreAgentNordOuest(true, agentTest);
		TableauValeur[2] = game.NombreAgentSudEst(true, agentTest);
		TableauValeur[3] = game.NombreAgentSudOuest(true, agentTest);
		boolean changement_direction = false;
		int[] ordre = this.getTableauOrdre(TableauValeur);
		int distanceNord = game.ChercheAgentNord(true, agentTest);
		int distanceSud = game.ChercheAgentSud(true, agentTest);
		int distanceEst = game.ChercheAgentEst(true, agentTest);
		int distanceOuest = game.ChercheAgentOuest(true, agentTest);
		/*
		 * Selon la valeur correspondant au NombreAgentZone le plus élevé,
		 * On regarde si il y a un pacman plus près dans la ligne 1 ou dans la ligne 2
		 */
		switch(ordre[3]){
		case 0 :
			if(distanceNord >= distanceEst && distanceNord > -1){
					agent.setNextAction(0);
					changement_direction = true;
			} else if(distanceEst > -1){
					agent.setNextAction(2);
					changement_direction = true;
			}
			break;
		case 1 :
			if(distanceNord >= distanceOuest && distanceNord > -1){
				agent.setNextAction(0);
				changement_direction = true;
		} else if(distanceOuest > -1){
				agent.setNextAction(3);
				changement_direction = true;
		}			
			break;
		case 2 :
			if(distanceSud >= distanceEst && distanceSud > -1){
				agent.setNextAction(1);
				changement_direction = true;
		} else if(distanceEst > -1){
				agent.setNextAction(2);
				changement_direction = true;
		}
			break;
		case 3 :
			if(distanceSud >= distanceOuest && distanceSud > -1){
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
			/*
			 * Selon la valeur correspondant au NombreAgentZone le plus élevé,
			 * On regarde si il y a un pacman plus près dans la ligne 1 ou dans la ligne 2
			 */
			switch(ordre[1]){
			case 0 :
				if(distanceNord >= distanceEst && distanceNord > -1){
						agent.setNextAction(0);
						changement_direction = true;
				} else if(distanceEst > -1){
						agent.setNextAction(2);
						changement_direction = true;
				}
				break;
			case 1 :
				if(distanceNord >= distanceOuest && distanceNord > -1){
					agent.setNextAction(0);
					changement_direction = true;
			} else if(distanceOuest > -1){
					agent.setNextAction(3);
					changement_direction = true;
			}			
				break;
			case 2 :
				if(distanceSud >= distanceEst && distanceSud > -1){
					agent.setNextAction(1);
					changement_direction = true;
			} else if(distanceEst > -1){
					agent.setNextAction(2);
					changement_direction = true;
			}
				break;
			case 3 :
				if(distanceSud >= distanceOuest && distanceSud > -1){
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
			this.ComportementFinal(agent, game);
		}
	}
	
	
	
	
	
	
	
	
	
	
	public void comportementNormal(Agent agent, Game game){
		System.out.println("comportement fantome Facile ");
		Agent agentTest = new Agent(true, agent.getPosition(), agent.getComportement());
		agentTest.setNextAction(agent.getNextAction());		
		int[] TableauValeur = new int[4];
		TableauValeur[0] = game.NombreAgentNordEst(true, agentTest);
		TableauValeur[1] = game.NombreAgentNordOuest(true, agentTest);
		TableauValeur[2] = game.NombreAgentSudEst(true, agentTest);
		TableauValeur[3] = game.NombreAgentSudOuest(true, agentTest);
		boolean changement_direction = false;
		int[] ordre = this.getTableauOrdre(TableauValeur);
		int distanceNord = game.ChercheAgentNord(true, agentTest);
		int distanceSud = game.ChercheAgentSud(true, agentTest);
		int distanceEst = game.ChercheAgentEst(true, agentTest);
		int distanceOuest = game.ChercheAgentOuest(true, agentTest);

		/*
		 * Selon la valeur correspondant au NombreAgentZone le plus élevé,
		 * On regarde si il y a un pacman plus près dans la ligne 1 ou dans la ligne 2
		 */
		switch(ordre[0]){
		case 0 :
			if((distanceNord <= distanceEst && distanceNord > -1) || (distanceNord > distanceEst && distanceEst == -1) ){
					agent.setNextAction(0);
					changement_direction = true;
			} else if(distanceEst > -1){
					agent.setNextAction(2);
					changement_direction = true;
			} else {
			}
			
			break;
		case 1 :
			if((distanceNord <= distanceOuest && distanceNord > -1)|| (distanceNord > distanceOuest && distanceOuest == -1)){
				agent.setNextAction(0);
				changement_direction = true;
			} else if(distanceOuest > -1){
				agent.setNextAction(3);
				changement_direction = true;
			} else {
			}
			break;
		case 2 :
			if((distanceSud <= distanceEst && distanceSud > -1) || (distanceSud > distanceEst && distanceEst == -1) ){
				agent.setNextAction(1);
				changement_direction = true;
		} else if(distanceEst > -1){
				agent.setNextAction(2);
				changement_direction = true;
		} else {
		}
			break;
		case 3 :
			if((distanceSud <= distanceOuest && distanceSud > -1) || (distanceSud > distanceOuest && distanceOuest == -1)){
				agent.setNextAction(1);
				changement_direction = true;
		} else if(distanceOuest > -1){
				agent.setNextAction(3);
				changement_direction = true;
		} else {
		}
			break;
		}
		/*
		 * Si la valeur de direction n'a pas encore été changé
		 */
		if(!changement_direction){
			/*
			 * Selon la valeur correspondant au NombreAgentZone le plus élevé,
			 * On regarde si il y a un pacman plus près dans la ligne 1 ou dans la ligne 2
			 */
			switch(ordre[1]){
			case 0 :
				if((distanceNord >= distanceEst && distanceNord > -1) || (distanceNord > distanceEst && distanceEst == -1)){
						agent.setNextAction(0);
						changement_direction = true;
				} else if(distanceEst > -1){
						agent.setNextAction(2);
						changement_direction = true;
				} else {
				}
				break;
			case 1 :
				if((distanceNord >= distanceOuest && distanceNord > -1) || (distanceNord > distanceOuest && distanceOuest == -1)){
					agent.setNextAction(0);
					changement_direction = true;
			} else if(distanceOuest > -1){
					agent.setNextAction(3);
					changement_direction = true;
			} else {
			}
				break;
			case 2 :
				if((distanceSud >= distanceEst && distanceSud > -1) || (distanceSud > distanceEst && distanceEst == -1)){
					agent.setNextAction(1);
					changement_direction = true;
			} else if(distanceEst > -1){
					agent.setNextAction(2);
					changement_direction = true;
			} else {
			}
				break;
			case 3 :
				if((distanceSud >= distanceOuest && distanceSud > -1) || (distanceSud > distanceOuest && distanceOuest == -1)){
					agent.setNextAction(1);
					changement_direction = true;
			} else if(distanceOuest > -1){
					agent.setNextAction(3);
					changement_direction = true;
			} else {
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
			this.ComportementFinal(agent, game);
		}
	}
}
