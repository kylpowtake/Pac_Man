import java.util.ArrayList;


public class GestionReceptionMessage {

	public static void GestionMessageGlobal(String message){
		System.out.println(message);
		if(message.startsWith("chemin:")){
			GestionMessageChemin(message);
		} else if(message.startsWith("update;")) {
			GestionMessageUpdate(message);
		} else {
			System.out.println("Message reçu non traitable");
		}
	}
	
	public static void GestionMessageChemin(String message){
		if(MainClient.viewConnexion.TestPresent()){
			MainClient.viewConnexion.Close();
		}
		if(message.contains(";")){
			String[] partieMessageMajeurs = message.split(";");
			String[] partieChemin = partieMessageMajeurs[0].split(":");
			MainClient.view.setChemin(partieChemin[1]);
			String[] partieEtat = partieMessageMajeurs[1].split(":");
			MainClient.view.setEtat(partieEtat[1]);
			MainClient.view.actualiserChemin();
		}
		
		
		int emplacement = message.indexOf(":");
		String chemin = message.substring(emplacement+1);
		System.out.println(message + "      " + chemin);
		MainClient.setView(chemin);
	}
	
	

	public static void GestionMessageUpdate(String message){
		//Contient les parties de message de type 		nominfo : infos
		String partiesMessageMajeurs[] = null;
		
		//On s'assure que le message contient un ;
		if(message.contains(";")){
			//On sépare le string avec  ;  comme séparateur et qu'on met dans partiesMessageMajeurs[].
			partiesMessageMajeurs = message.split(";");
		} else {
			System.out.println("Il n'y a pas de séparateur dans message update :  ;    On est mal là");
			System.exit(1);
		}
		//On s'assure qu'il y a assez de partie dans le message.
		if(partiesMessageMajeurs.length == 10){
			//On s'occupe des différentes parties une par une.
			//L'ordre est : food, invincible, agent, score, vie, nbtours, nomterrain, musique, etat.
			//On s'occupe de food.
			GestionPartieMessageFood(partiesMessageMajeurs[1]);
			//On s'occupe des capsules.
			GestionPartieMessageCapsule(partiesMessageMajeurs[2]);
			//On s'occupe d'invincible
			GestionPartieMessageInvincible(partiesMessageMajeurs[3]);
			GestionPartieMessageAgents(partiesMessageMajeurs[4]);

			GestionPartieMessageScore(partiesMessageMajeurs[5]);

			GestionPartieMessageVie(partiesMessageMajeurs[6]);

			GestionPartieMessageTours(partiesMessageMajeurs[7]);

			GestionPartieMessageChemin(partiesMessageMajeurs[8]);

			GestionPartieMessageMusique(partiesMessageMajeurs[9]);
			
			MainClient.view.actualiser();
			
		} else {
			System.out.println("Il n'y a pas assez de parties dans le message, il en faut dix en tout : on est mal là : " + partiesMessageMajeurs.length);
			System.exit(1);
		}
		
	}
	
	//Gère food
	public static void GestionPartieMessageFood(String food_string){
		ArrayList<int[]> food = new ArrayList<>();
		//Contient le string food et le string avec les emplacements de food.
		String messagePartieLegereSeparation[] = null;
		//Contient les strings placements de food.
		String messagePartielSeparationVirgule[] = null;
		//Contient le x et y d'un food.
		String messageXYFood[] = null;
		//On s'assure que le message contient un :
		if(food_string.contains(":")){
			//On sépare le string avec  :  comme séparateur et qu'on met dans partiesMessageMajeurs[].
			messagePartieLegereSeparation = food_string.split(":");
		} else {
			System.out.println("Il n'y a pas de séparateur dans message food :  :    On est mal là" + food_string);
			System.exit(1);
		}
		//On s'assure qu'il y a assez de partie dans le message.
		if(messagePartieLegereSeparation.length == 2){
			//On s'occupe des différentes parties une par une.
			//L'ordre est : x1 y1,x2 y2
			//On sépare le string avec  ,  comme séparateur et qu'on met dans partiesMessageMajeurs[].
			messagePartielSeparationVirgule = messagePartieLegereSeparation[1].split(",");
			//Pour chaque string contenantun emplacement de food : 
			for(int i = 0; i < messagePartielSeparationVirgule.length; i++){
				//On sépare le x du y.
				messageXYFood = messagePartielSeparationVirgule[i].split(" ");
				//On l'ajoute à food.
				int minFood[] = {Integer.parseInt(messageXYFood[0]),Integer.parseInt(messageXYFood[1])};
				food.add(minFood);
			}
			//Gestion de la miseà jour de food dans View.
			GestionMAJFood(food);
		} else {
			System.out.println("Il n'y a pas assez de parties dans le message, il en faut deux en tout : on est mal là");
			System.exit(1);
		}
	}
	
	//Met à jour food
	public static void GestionMAJFood(ArrayList<int[]> food){
		boolean[][] foodtemp = 	new boolean[MainClient.view.maze.getSizeX()][MainClient.view.maze.getSizeY()];
		for(int i = 0; i <foodtemp.length; i++){
			for(int j = 0; j < foodtemp[i].length; j++){
				foodtemp[i][j] = false;
			}
		}
		for(int i = 0; i < food.size(); i++){
			foodtemp[food.get(i)[0]][food.get(i)[1]] = true;
		}
		MainClient.view.setFood(foodtemp);
	}
	
	
	
	
	//Gère food
	public static void GestionPartieMessageCapsule(String capsule_string){
		ArrayList<int[]> capsule = new ArrayList<>();
		//Contient le string capsule et le string avec les emplacements de capsule.
		String messagePartieLegereSeparation[] = null;
		//Contient les strings placements de capsule.
		String messagePartielSeparationVirgule[] = null;
		//Contient le x et y d'une capsule.
		String messageXYCapsule[] = null;
		//On s'assure que le message contient un :
		if(capsule_string.contains(":")){
			//On sépare le string avec  :  comme séparateur et qu'on met dans partiesMessageMajeurs[].
			messagePartieLegereSeparation = capsule_string.split(":");
		} else {
			System.out.println("Il n'y a pas de séparateur dans message food :  :    On est mal là" + capsule_string);
			System.exit(1);
		}
		//On s'assure qu'il y a assez de partie dans le message.
		if(messagePartieLegereSeparation.length == 2){
			//On s'occupe des différentes parties une par une.
			//L'ordre est : x1 y1,x2 y2
			//On sépare le string avec  ,  comme séparateur et qu'on met dans partiesMessageMajeurs[].
			messagePartielSeparationVirgule = messagePartieLegereSeparation[1].split(",");
			//Pour chaque string contenantun emplacement de food : 
			for(int i = 0; i < messagePartielSeparationVirgule.length; i++){
				//On sépare le x du y.
				messageXYCapsule = messagePartielSeparationVirgule[i].split(" ");
				//On l'ajoute à capsule.
				int minCapsule[] = {Integer.parseInt(messageXYCapsule[0]),Integer.parseInt(messageXYCapsule[1])};
				capsule.add(minCapsule);
			}
			//Gestion de la miseà jour de capsule dans View.
			GestionMAJCapsule(capsule);
		} else {
			System.out.println("Il n'y a pas assez de parties dans le message, il en faut deux en tout : on est mal là");
			System.exit(1);
		}
	}
	
	//Met à jour capsule
	public static void GestionMAJCapsule(ArrayList<int[]> capsule){
		boolean[][] capsuletemp = 	new boolean[MainClient.view.maze.getSizeX()][MainClient.view.maze.getSizeY()];
		for(int i = 0; i <capsuletemp.length; i++){
			for(int j = 0; j < capsuletemp[i].length; j++){
				capsuletemp[i][j] = false;
			}
		}
		for(int i = 0; i < capsule.size(); i++){
			capsuletemp[capsule.get(i)[0]][capsule.get(i)[1]] = true;
		}
		MainClient.view.setCapsule(capsuletemp);
	}
	
	
	
	//Gère invincible
	public static void GestionPartieMessageInvincible(String invincible_string){
		//Contient le string invincible et le string avec sa valeur.
		String messagePartieLegereSeparation[] = null;
		//On s'assure que le message contient un :
		if(invincible_string.contains(":")){
			//On sépare le string avec  :  comme séparateur et qu'on met dans partiesMessageMajeurs[].
			messagePartieLegereSeparation = invincible_string.split(":");
		} else {
			System.out.println("Il n'y a pas de séparateur dans message invincible :  :    On est mal là.");
			System.exit(1);
		}
		//On s'assure qu'il y a assez de partie dans le message.
		if(messagePartieLegereSeparation.length == 2){
			//Il n'y a qu'une valeur pour invincible
			GestionMAJInvincible(messagePartieLegereSeparation[1]);
			//Gestion de la miseà jour de invincible dans View.
		} else {
			System.out.println("Il n'y a pas assez de parties dans le message, il en faut deux en tout : on est mal là.");
			System.exit(1);
		}
	}

	//Met à jour invincible
	public static void GestionMAJInvincible(String invincible){
		if(invincible.matches("true")){
			MainClient.view.setInvincible(true);
		} else if (invincible.matches("false")) {
			MainClient.view.setInvincible(false);			
		} else {
			System.out.println("La valeur d'invincible n'est pas autorisé. : " + invincible);
			System.exit(1);				
		}
	}
	
	
	//Gère agent
	public static void GestionPartieMessageAgents(String agents_string){
		ArrayList<int[]> agents = new ArrayList<>();
		//Contient le string agents et le string avec les données des agents.
		String messagePartieLegereSeparation[] = null;
		//Contient les strings placements de food.
		String messagePartielSeparationVirgule[] = null;
		//Contient le type, x, y et direction d'un agent.
		String messageTXYDAgents[] = null;
		//On s'assure que le message contient un :
		if(agents_string.contains(":")){
			//On sépare le string avec  :  comme séparateur et qu'on met dans partiesMessageMajeurs[].
			messagePartieLegereSeparation = agents_string.split(":");
		} else {
			System.out.println("Il n'y a pas de séparateur :  :    On est mal là");
			System.exit(1);
		}
		//On s'assure qu'il y a assez de partie dans le message.
		if(messagePartieLegereSeparation.length == 2){
			//On s'occupe des différentes parties une par une.
			//L'ordre est : t1 x1 y1 d1, t2 x2 y2 d2 
			//On sépare le string avec  ,  comme séparateur et qu'on met dans partiesMessageMajeurs[].
			messagePartielSeparationVirgule = messagePartieLegereSeparation[1].split(",");
			//Pour chaque string contenant les données de agent : 
			for(int i = 0; i < messagePartielSeparationVirgule.length; i++){
				//On sépare les données une par une.
				messageTXYDAgents = messagePartielSeparationVirgule[i].split(" ");
				//On les ajoutent à agent.
				int minAgent[] = {Integer.parseInt(messageTXYDAgents[0]),Integer.parseInt(messageTXYDAgents[1]),Integer.parseInt(messageTXYDAgents[2]), Integer.parseInt(messageTXYDAgents[3])};
				agents.add(minAgent);
			}
			//Gestion de la mise à jour des agents dans View.
			GestionMAJAgents(agents);
		} else {
			System.out.println("Il n'y a pas assez de parties dans le message, il en faut deux en tout : on est mal là");
			System.exit(1);
		}
	}
	
	//Met à jour agent
	public static void GestionMAJAgents(ArrayList<int[]> agents){
		ArrayList<PositionAgent> fantomes = new ArrayList<>();
		ArrayList<PositionAgent> pacmans = new ArrayList<>();
		
		for(int i = 0; i < agents.size(); i++){
			PositionAgent posAgent = new PositionAgent(agents.get(i)[1], agents.get(i)[2], agents.get(i)[3]);
			if(agents.get(i)[0] == 0){
				fantomes.add(posAgent);
			} else {
				pacmans.add(posAgent);				
			}
		}
		MainClient.view.setPacmans(pacmans);
		MainClient.view.setFantomes(fantomes);
	}

	
	//Gère score
	public static void GestionPartieMessageScore(String score_string){
		//Contient le string score et le string avec sa valeur.
		String messagePartieLegereSeparation[] = null;
		//On s'assure que le message contient un :
		if(score_string.contains(":")){
			//On sépare le string avec  :  comme séparateur et qu'on met dans partiesMessageMajeurs[].
			messagePartieLegereSeparation = score_string.split(":");
		} else {
			System.out.println("Il n'y a pas de séparateur :  :    On est mal là.");
			System.exit(1);
		}
		//On s'assure qu'il y a assez de partie dans le message.
		if(messagePartieLegereSeparation.length == 2){
			//Il n'y a qu'une valeur pour score
			GestionMAJScore(Integer.parseInt(messagePartieLegereSeparation[1]));
			//Gestion de la mise à jour de score dans View.
		} else {
			System.out.println("Il n'y a pas assez de parties dans le message, il en faut deux en tout : on est mal là.");
			System.exit(1);
		}
	}

	//Met à jour score
	public static void GestionMAJScore(int score){
		MainClient.view.setScore(score);
	}

	//Gère vie
	public static void GestionPartieMessageVie(String vie_string){
		//Contient le string vie et le string avec sa valeur.
		String messagePartieLegereSeparation[] = null;
		//On s'assure que le message contient un :
		if(vie_string.contains(":")){
			//On sépare le string avec  :  comme séparateur et qu'on met dans partiesMessageMajeurs[].
			messagePartieLegereSeparation = vie_string.split(":");
		} else {
			System.out.println("Il n'y a pas de séparateur :  :    On est mal là.");
			System.exit(1);
		}
		//On s'assure qu'il y a assez de partie dans le message.
		if(messagePartieLegereSeparation.length == 2){
			//Il n'y a qu'une valeur pour vie
			GestionMAJVie(Integer.parseInt(messagePartieLegereSeparation[1]));
			//Gestion de la mise à jour de vie dans View.
		} else {
			System.out.println("Il n'y a pas assez de parties dans le message, il en faut deux en tout : on est mal là.");
			System.exit(1);
		}
	}

	//Met à jour vie
	public static void GestionMAJVie(int vie){
		if(vie > 0 && vie <= 3){
		MainClient.view.setVie(vie);
		} else {
			System.out.println("Les vies ne peuvent être hors de l'intervalle 0-3");
		}
	}

	
	//Gère nbtours
	public static void GestionPartieMessageTours(String tours_string){
		//Contient le string tours et le string avec sa valeur.
		String messagePartieLegereSeparation[] = null;
		//On s'assure que le message contient un :
		if(tours_string.contains(":")){
			//On sépare le string avec  :  comme séparateur et qu'on met dans partiesMessageMajeurs[].
			messagePartieLegereSeparation = tours_string.split(":");
		} else {
			System.out.println("Il n'y a pas de séparateur :  :    On est mal là.");
			System.exit(1);
		}
		//On s'assure qu'il y a assez de partie dans le message.
		if(messagePartieLegereSeparation.length == 2){
			//Il n'y a qu'une valeur pour tours
			GestionMAJTours(Integer.parseInt(messagePartieLegereSeparation[1]));
			//Gestion de la mise à jour de tours dans View.
		} else {
			System.out.println("Il n'y a pas assez de parties dans le message, il en faut deux en tout : on est mal là.");
			System.exit(1);
		}
	}

	//Met à jour tours
	public static void GestionMAJTours(int tours){
		MainClient.view.setTours(tours);
	}
	
	

	//Gère le chemin
	public static void GestionPartieMessageChemin(String chemin_string){
		//Contient le string chemin et le string avec sa valeur.
		String messagePartieLegereSeparation[] = null;
		//On s'assure que le message contient un :
		if(chemin_string.contains(":")){
			//On sépare le string avec  :  comme séparateur et qu'on met dans partiesMessageMajeurs[].
			messagePartieLegereSeparation = chemin_string.split(":");
		} else {
			System.out.println("Il n'y a pas de séparateur :  :    On est mal là.");
			System.exit(1);
		}
		//On s'assure qu'il y a assez de partie dans le message.
		if(messagePartieLegereSeparation.length == 2){
			//Il n'y a qu'une valeur pour chemin
			GestionMAJChemin(messagePartieLegereSeparation[1]);
			//Gestion de la mise à jour de chemin dans View.
		} else {
			System.out.println("Il n'y a pas assez de parties dans le message, il en faut deux en tout : on est mal là.");
			System.exit(1);
		}
	}

	//Met à jour le chemin
	public static void GestionMAJChemin(String chemin){
		MainClient.view.setChemin(chemin);
	}
	
	
	//Gère la musique
	public static void GestionPartieMessageMusique(String musique_string){
		//Contient le string musique et le string avec sa valeur.
		String messagePartieLegereSeparation[] = null;
		//On s'assure que le message contient un :
		if(musique_string.contains(":")){
			//On sépare le string avec  :  comme séparateur et qu'on met dans partiesMessageMajeurs[].
			messagePartieLegereSeparation = musique_string.split(":");
		} else {
			System.out.println("Il n'y a pas de séparateur :  :    On est mal là.");
			System.exit(1);
		}
		//On s'assure qu'il y a assez de partie dans le message.
		if(messagePartieLegereSeparation.length == 2){
			//Il n'y a qu'une valeur pour musique
			GestionMAJMusique(messagePartieLegereSeparation[1]);
			//Gestion de la mise à jour de musique dans View.
		} else {
			System.out.println("Il n'y a pas assez de parties dans le message, il en faut deux en tout : on est mal là.");
			System.exit(1);
		}
	}

	//Met à jour la musique
	public static void GestionMAJMusique(String musique){
		MainClient.view.setMusique(musique);
	}
	
	
	//Gère etat
	public static void GestionPartieMessageEtat(String etat_string){
		//Contient le string etat et le string avec sa valeur.
		String messagePartieLegereSeparation[] = null;
		//On s'assure que le message contient un :
		if(etat_string.contains(":")){
			//On sépare le string avec  :  comme séparateur et qu'on met dans partiesMessageMajeurs[].
			messagePartieLegereSeparation = etat_string.split(":");
		} else {
			System.out.println("Il n'y a pas de séparateur :  :    On est mal là.");
			System.exit(1);
		}
		//On s'assure qu'il y a assez de partie dans le message.
		if(messagePartieLegereSeparation.length == 2){
			//Il n'y a qu'une valeur pour etat
			GestionMAJEtat(messagePartieLegereSeparation[1]);
			//Gestion de la mise à jour de etat dans View.
		} else {
			System.out.println("Il n'y a pas assez de parties dans le message, il en faut deux en tout : on est mal là.");
			System.exit(1);
		}
	}

	//Met à jour etat
	public static void GestionMAJEtat(String etat){
		MainClient.view.setEtat(etat);	
	}
}
