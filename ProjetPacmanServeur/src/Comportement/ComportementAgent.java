package Comportement;

import java.util.ArrayList;
import model.*;



public abstract class ComportementAgent {
	
	
	public abstract void comportement(Agent agent, Game game);
	
	/**
	 * Comportement appliqer si l'agent est en fuite.
	 * @param agent : l'agent auquel est appliqé le comportement
	 * @param game : utilisé pour gérer le comportement
	 */
	public abstract void comportementNormal(Agent agent, Game game);

	/**
	 * Comportement appliqer si l'agent est en fonctionnnement normal.
	 * @param agent : l'agent auquel est appliqé le comportement
	 * @param game : utilisé pour gérer le comportement
	 */
	public abstract void comportementFuite(Agent agent, Game game);
	
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
	
	
	
	/**
	 * Fonction retournant tous les emplacements possibles dans le jeu(qui ne sont pas des murs).
	 * @param game : contient le labyrinthe 
	 * @return Les emplacements possibles en ArrayList de PositionAgent.
	 */
	public ArrayList<PositionAgent> TousEmplacementMaze(Game game){
		ArrayList<PositionAgent> CasesPossibles = new ArrayList<>();
		PositionAgent CaseAjout = new PositionAgent(0,0,0);
		for(int i = 0; i < game.getLabyrinthe().getSizeX(); i++){
			for(int j = 0; j < game.getLabyrinthe().getSizeY(); j++){
				if(!game.getLabyrinthe().isWall(i, j)){
					CaseAjout.setX(i);
					CaseAjout.setY(j);
					CasesPossibles.add(CaseAjout);
				}
			}
		}
		return CasesPossibles;
	}
	
	
	/**
	 * Fonctin Retournant vrai si la position est dans l'arrrayList sinon retourne faux
	 * @param Cases : L'arraList de position
	 * @param pos : la position a testé
	 * @return vrai ou faux selon la présence de la position dans Cases.
	 */
	public boolean DansArrayList(ArrayList<PositionAgent> Cases, PositionAgent pos){
		for(int i = 0; i < Cases.size(); i++){
			if(Cases.get(i).getX() == pos.getX() && Cases.get(i).getY() == pos.getY()){
				return true;
			}
		}
		return false;
	}
	
	

	/**
	 * Fonction retournant l'arraylist de position autour de la position donnée qui ne sont pas des murs.
	 * @param pos
	 * @param game
	 * @return
	 */
	public ArrayList<PositionAgent> CaseLibreAutourCase(PositionAgent pos, Game game){	
		ArrayList<PositionAgent> Voisins = new ArrayList<>();
		PositionAgent nouvPos = new PositionAgent(pos);
		nouvPos.setDir(0);
		if(!game.getLabyrinthe().isWall(pos.getX()+1, pos.getY())){
			nouvPos = new PositionAgent(pos);
			nouvPos.setX(pos.getX()+1);
			Voisins.add(nouvPos);
		}
		if(!game.getLabyrinthe().isWall(pos.getX()-1, pos.getY())){
			nouvPos = new PositionAgent(pos);
			nouvPos.setX(pos.getX()-1);
			Voisins.add(nouvPos);
		}
		if(!game.getLabyrinthe().isWall(pos.getX(), pos.getY()+1)){
			nouvPos = new PositionAgent(pos);
			nouvPos.setY(pos.getY()+1);
			Voisins.add(nouvPos);
		}
		if(!game.getLabyrinthe().isWall(pos.getX(), pos.getY()-1)){
			nouvPos = new PositionAgent(pos);
			nouvPos.setY(pos.getY()-1);
			Voisins.add(nouvPos);
		}
		return Voisins;
	}
	
	
	
	
	/**
	 * Fonction ajoutant des cases si elles ne sont pas présentes dans la liste de cases.
	 * @param Cases : Liste dans laquelle ajouter les nouvelles cases
	 * @param CasesAjouts : Liste de cases à ajouter.
	 */
	public void AjoutSiNonPresentPositions(ArrayList<PositionAgent> Cases, ArrayList<PositionAgent> CasesAjouts){
		for(int i = 0; i < CasesAjouts.size(); i++){
			if(!DansArrayList(Cases, CasesAjouts.get(i))){
				Cases.add(CasesAjouts.get(i));
			}
		}
	}
	
	
	
	/**
	 * Fonction retournant le cout pour aller de casDebut à caseFin
	 * En effet la direction d'une case est l'indice dans la liste de la case précéante
	 * A part pour la première case qui a un indice de -1
	 * @param Cases : Liste des cases
	 * @param caseDebut : Case de départ.
	 * @param caseFin : Case de fin.
	 * @return cout du chemin
	 */
	public int CoutChemin(ArrayList<PositionAgent> Cases, PositionAgent caseDebut, PositionAgent caseFin){
		int indice = caseFin.getDir();
		int compteur_cout = 0;
		while(indice != -1){
			indice = Cases.get(indice).getDir();
			compteur_cout++;
		}
		return compteur_cout;
	}

	
	/**
	 * Fonction Ajoutant la position dans la liste si elle n'y est déjà pas.
	 * @param Cases : La liste de cases
	 * @param Case : La nouvelle case
	 */
	public void AjoutSiNonPresent(ArrayList<PositionAgent> Cases,PositionAgent Case){
			if(!DansArrayList(Cases, Case)){
				Cases.add(Case);
			}
	}

	
	/**
	 * Fonction Ajoutant la position dans la liste si elle n'y est déjà pas ou si le cout est inférieure.
	 * @param Cases1 : La liste de cases déjà visités
	 * @param Cases2 : La liste de cases à visiter
	 * @param Case : La nouvelle case
	 */
	public void AjoutSiNonPresentInferieur(ArrayList<PositionAgent> Cases1, ArrayList<PositionAgent> Cases2,PositionAgent Case){
		if(!DansArrayList(Cases1, Case)){
			if(!DansArrayList(Cases2, Case)){
				Cases2.add(Case);
			} else {
				for(int i = 0; i < Cases2.size(); i++){
					if(Case.getX() == Cases2.get(i).getX() && Case.getY() == Cases2.get(i).getY()){
						if(this.CoutChemin(Cases1, Cases1.get(0), Cases1.get(Case.getDir())) < this.CoutChemin(Cases1, Cases1.get(0), Cases1.get(Cases2.get(i).getDir()))){
							Cases2.remove(i);
							Cases2.add(Case);
						}
					}
				}
			}
		} else {
		}
	}	
	
	
	/**
	 * Fonction retournant le cout moyen entre cased et casef.
	 * @param cased : case de depart
	 * @param casef : case de fin
	 * @return cout moyen
	 */
	public int coutMoyen(PositionAgent cased, PositionAgent casef){
		int retour = 0;
		int clc = cased.getX() - casef.getX();
		if(clc < 0){
			retour = -clc;
		} else {
			retour = clc;
		}
		clc = cased.getY() - casef.getY();		
		if(clc < 0){
			retour += -clc;
		} else {
			retour += clc;
		}
		return retour;
	}
	
	/**
	 * Renvoie l'indice de la deuxième liste donnant celui avec le plus petit cout possible final.
	 * @param Cases1 : Les cases visités contenant le chemin.
	 * @param Cases2 : Liste contenant la case à choisir.
	 * @param caseDebut : la case de départ.
	 * @param case_fin : la case de fin.
	 * @param game : le jeu en lui même
	 * @return l'indice de la case à ajouter
	 */
	public int MinimumList(ArrayList<PositionAgent> Cases1, ArrayList<PositionAgent> Cases2, PositionAgent caseDebut, PositionAgent case_fin, Game game){
		int cout_minimum = 80;
		int cout_temp = 0;
		int indice = 0;
		for(int i = 0; i < Cases2.size(); i++){
			cout_temp = this.CoutChemin(Cases1, caseDebut, Cases2.get(i));
			cout_temp += this.coutMoyen(Cases2.get(i),case_fin);
			if(cout_temp < cout_minimum){
				cout_minimum = cout_temp;
				indice = i;
			}
		}
		return indice;
	}
	
	
	public PositionAgent PremiereCase(ArrayList<PositionAgent> cases, PositionAgent caseFin){
		int indice = caseFin.getDir();
		int cout = 1;
		int temp = 0;
		if(indice == -1){
			return caseFin;
		}
		while(indice != 0){
			cout++;
			temp = indice;
			indice = cases.get(indice).getDir();
		}
		PositionAgent pos = new PositionAgent(cases.get(temp));
		pos.setDir(cout);
		return pos;
	}
	
	
	/**
	 * Affiche les éléments de la liste
	 * @param list : La liste à afficher
	 */
	public void AffichageList(ArrayList<PositionAgent> list){
		System.out.println("\n");
		for(int i = 0; i < list.size(); i++ ){
			System.out.println("Element x : " + list.get(i).getX() + ", y : " + list.get(i).getY() + " et dir : " + list.get(i).getDir());
		}
		System.out.println("\n");
	}
	
	/**
	 * Fonction appliquant l'A* entre agent et ennemie.
	 * @param agent : La position de départ
	 * @param ennemie : La position d'arrivée
	 * @param game : le jeu avec des variables et méthodes
	 * @return la case menant utilisant le cheminle plus rapide
	 */
	public PositionAgent CheminAgentEnnemie(PositionAgent agent, PositionAgent ennemie, Game game){
		//Contient toutes les cases visiées, direction montre la case précédante.
		ArrayList<PositionAgent> casesVisite = new ArrayList<>();
		//Contient les casesvoisines de celles déjà visiter.
		ArrayList<PositionAgent> casesAVisiter = new ArrayList<>();
		//Compteur de case
		int compteur_case = 0;
		int indice = 0;
		
		//Position de l'agent
		PositionAgent posAgentDebut = new PositionAgent(agent);
		posAgentDebut.setDir(-1);
		casesVisite.add(posAgentDebut);
		//Position de l'ennemie
		PositionAgent posEnnemie = new PositionAgent(ennemie);
		//Position temporelle.
		PositionAgent posTemp = new PositionAgent(posAgentDebut);		
		
		//On prend les cases voisines sans murs à côté de la case de début
		ArrayList<PositionAgent> casesvoisines = this.CaseLibreAutourCase(posAgentDebut, game);
		//On les ajoutes à la liste en mettant leur directionà la valeur de la case initial.
		for(int i = 0; i < casesvoisines.size(); i++){
			casesvoisines.get(i).setDir(compteur_case);
			this.AjoutSiNonPresent(casesAVisiter,casesvoisines.get(i)); 
		}
		casesvoisines.clear();
		//Le while,on n'arrête quand il n'y a plus de cases à ajouter ou quand on a trouvé la case finale.
		while(!casesAVisiter.isEmpty() && !this.DansArrayList(casesVisite, posEnnemie)){
			indice = this.MinimumList(casesVisite, casesAVisiter, posAgentDebut, posEnnemie, game);
			posTemp = casesAVisiter.get(indice);
			casesVisite.add(posTemp);
			casesAVisiter.remove(indice);
			
			//On prend les cases voisines sans murs à côté de la case ajouter
			casesvoisines = this.CaseLibreAutourCase(posTemp, game);
			for(int i = 0; i < casesvoisines.size(); i++){
				casesvoisines.get(i).setDir(casesVisite.size()-1);
			}
			//On les ajoutes à la liste en mettant leur direction à la valeur de la case initial.
			for(int i = 0; i < casesvoisines.size(); i++){
				this.AjoutSiNonPresentInferieur(casesVisite, casesAVisiter,casesvoisines.get(i));
			}
		}
		if(casesAVisiter.isEmpty()){
			return null;
		} else {
			posTemp = casesVisite.get(casesVisite.size()-1);
			posTemp = new PositionAgent(this.PremiereCase(casesVisite, posTemp));
		}
		return posTemp;
	}
}




