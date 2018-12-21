
public class ComportementPacmanFacile extends ComportementPacman {
	
	
	
	public void comportementNormal(Agent agent, Game game){
		
	}
	
	
	public void comportementFuite(Agent agent, Game game){
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Pas utilisé normalement
	public void comportement(Agent agent, Game game){
		
		/*
		if(!game.getIsInvincible()){
		Agent agentTest = new Agent(false, agent.getPosition(), agent.getComportement());
		agentTest.setNextAction(agent.getNextAction());		
		int[] TableauValeur = new int[4];
		TableauValeur[0] = game.NombreAgentNordEst(false, agentTest);
		TableauValeur[1] = game.NombreAgentNordOuest(false, agentTest);
		TableauValeur[2] = game.NombreAgentSudEst(false, agentTest);
		TableauValeur[3] = game.NombreAgentSudOuest(false, agentTest);
		boolean changement_direction = true;
		int[] ordre = this.getTableauOrdre(TableauValeur);
		int distanceNord = game.ChercheAgentNord(false, agentTest);
		int distanceSud = game.ChercheAgentSud(false, agentTest);
		int distanceEst = game.ChercheAgentEst(false, agentTest);
		int distanceOuest = game.ChercheAgentOuest(false, agentTest);
*/
		
		
		/*
		 * Selon la valeur correspondant au NombreAgentZone le plus élevé,
		 * On regarde si il y a un pacman plus près dans la ligne 1 ou dans la ligne 2
		 */
		/*
		switch(ordre[0]){
		case 0 :
			System.out.println("distance nord : " + distanceNord + " et distance est : " + distanceEst);
			if((distanceNord <= distanceEst && distanceNord > -1) || (distanceNord > distanceEst && distanceEst == -1) ){
					System.out.println("On est dans le compo facile, switch 1, case 0, vers nord");
					agent.setNextAction(0);
					changement_direction = true;
			} else if(distanceEst > -1){
				System.out.println("On est dans le compo facile, switch 1, case 0, vers est");
					agent.setNextAction(2);
					changement_direction = true;
			} else {
				System.out.println("On est dans le compo facile, switch 1, case 0, vers rien");
			}
			
			break;
		case 1 :
			System.out.println("distance nord : " + distanceNord + " et distance ouest : " + distanceOuest);
			if((distanceNord <= distanceOuest && distanceNord > -1)|| (distanceNord > distanceOuest && distanceOuest == -1)){
				System.out.println("On est dans le compo facile, switch 1, case 1, vers nord");
				agent.setNextAction(0);
				changement_direction = true;
			} else if(distanceOuest > -1){
				System.out.println("On est dans le compo facile, switch 1, case 1, vers ouest");
				agent.setNextAction(3);
				changement_direction = true;
			} else {
				System.out.println("On est dans le compo facile, switch 1, case 1, vers rien");
			}
			break;
		case 2 :
			System.out.println("distance sud : " + distanceSud + " et distance est : " + distanceEst);
			if((distanceSud <= distanceEst && distanceSud > -1) || (distanceSud > distanceEst && distanceEst == -1) ){
				System.out.println("On est dans le compo facile, switch 1, case 2, vers sud");
				agent.setNextAction(1);
				changement_direction = true;
		} else if(distanceEst > -1){
				System.out.println("On est dans le compo facile, switch 1, case 2, vers est");
				agent.setNextAction(2);
				changement_direction = true;
		} else {
			System.out.println("On est dans le compo facile, switch 1, case 2, vers rien");
		}
			break;
		case 3 :
			System.out.println("distance sud : " + distanceSud + " et distance Ouest : " + distanceOuest);
			if((distanceSud <= distanceOuest && distanceSud > -1) || (distanceSud > distanceOuest && distanceOuest == -1)){
				System.out.println("On est dans le compo facile, switch 1, case 3, vers sud");
				agent.setNextAction(1);
				changement_direction = true;
		} else if(distanceOuest > -1){
			System.out.println("On est dans le compo facile, switch 1, case 3, vers ouest");
				agent.setNextAction(3);
				changement_direction = true;
		} else {
			System.out.println("On est dans le compo facile, switch 1, case 3, vers rien");	
		}
			break;
		}
		/*
		 * Si la valeur de direction n'a pas encore été changé
		 */
		/*
		if(!changement_direction){
			/*
			 * Selon la valeur correspondant au NombreAgentZone le plus élevé,
			 * On regarde si il y a un pacman plus près dans la ligne 1 ou dans la ligne 2
			 */
		/*
			switch(ordre[1]){
			case 0 :
				System.out.println("distance nord : " + distanceNord + " et distance est : " + distanceEst);
				if((distanceNord >= distanceEst && distanceNord > -1) || (distanceNord > distanceEst && distanceEst == -1)){
					System.out.println("On est dans le compo facile, switch 2, case 0, vers nord");
						agent.setNextAction(0);
						changement_direction = true;
				} else if(distanceEst > -1){
					System.out.println("On est dans le compo facile, switch 2, case 0, vers est");
						agent.setNextAction(2);
						changement_direction = true;
				} else {
					System.out.println("On est dans le compo facile, switch 2, case 0, vers rien");
				}
				break;
			case 1 :
				System.out.println("distance nord : " + distanceNord + " et distance ouest : " + distanceOuest);
				if((distanceNord >= distanceOuest && distanceNord > -1) || (distanceNord > distanceOuest && distanceOuest == -1)){
					System.out.println("On est dans le compo facile, switch 2, case 1, vers nord");
					agent.setNextAction(0);
					changement_direction = true;
			} else if(distanceOuest > -1){
				System.out.println("On est dans le compo facile, switch 2, case 1, vers ouest");
					agent.setNextAction(3);
					changement_direction = true;
			} else {
				System.out.println("On est dans le compo facile, switch 2, case 1, vers rien");
			}
				break;
			case 2 :
				System.out.println("distance sud : " + distanceSud + " et distance est : " + distanceEst);
				if((distanceSud >= distanceEst && distanceSud > -1) || (distanceSud > distanceEst && distanceEst == -1)){
					System.out.println("On est dans le compo facile, switch 2, case 2, vers sud");
					agent.setNextAction(1);
					changement_direction = true;
			} else if(distanceEst > -1){
				System.out.println("On est dans le compo facile, switch 2, case 2, vers est");
					agent.setNextAction(2);
					changement_direction = true;
			} else {
				System.out.println("On est dans le compo facile, switch 2, case 2, vers rien");
			}
				break;
			case 3 :
				System.out.println("distance sud : " + distanceSud + " et distance ouest : " + distanceOuest);
				if((distanceSud >= distanceOuest && distanceSud > -1) || (distanceSud > distanceOuest && distanceOuest == -1)){
					System.out.println("On est dans le compo facile, switch 2, case 3, vers sud");
					agent.setNextAction(1);
					changement_direction = true;
			} else if(distanceOuest > -1){
				System.out.println("On est dans le compo facile, switch 2, case 3, vers ouest");
					agent.setNextAction(3);
					changement_direction = true;
			} else {
				System.out.println("On est dans le compo facile, switch 2, case 3, vers rien");
			}
				break;
			}
		}
		/*
		 * Si la valeur de direction n'a toujours pas changé
		 */
		/*
		if(!changement_direction){
			/*
			 * On applique le choix par hasard
			 */
		/*
			this.ComportementFinal(agent, game);
		}
		} else {
			/*
			 * Pacman est invincible
			 */
		/*
			Agent agentTest = new Agent(false, agent.getPosition(), agent.getComportement());
			agentTest.setNextAction(agent.getNextAction());		
			int[] TableauValeur = new int[4];
			TableauValeur[0] = game.NombreAgentNordEst(false, agentTest);
			TableauValeur[1] = game.NombreAgentNordOuest(false, agentTest);
			TableauValeur[2] = game.NombreAgentSudEst(false, agentTest);
			TableauValeur[3] = game.NombreAgentSudOuest(false, agentTest);
			boolean changement_direction = true;
			int[] ordre = this.getTableauOrdre(TableauValeur);
			int distanceNord = game.ChercheAgentNord(false, agentTest);
			int distanceSud = game.ChercheAgentSud(false, agentTest);
			int distanceEst = game.ChercheAgentEst(false, agentTest);
			int distanceOuest = game.ChercheAgentOuest(false, agentTest);
			/*
			 * Selon la valeur correspondant au NombreAgentZone le plus élevé,
			 * On regarde si il y a un pacman plus près dans la ligne 1 ou dans la ligne 2
			 */
		/*
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
		/*
			if(!changement_direction){
				/*
				 * Selon la valeur correspondant au NombreAgentZone le plus élevé,
				 * On regarde si il y a un pacman plus près dans la ligne 1 ou dans la ligne 2
				 */
		/*
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
		/*
			if(!changement_direction){
				/*
				 * On applique le choix par hasard
				 */
		/*
				this.ComportementFinal(agent, game);
			}
		} */
	}	
}
