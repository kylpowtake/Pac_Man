import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;


public class Main {
	
    //Structure devant être utilisé pour le reste du programme.
	public static StructureUltime structureUltime;
	
	//Tableau pour les permutations.
	public static Double[] TableauPermut;
	
	//Resultat optimal pour les permutations.
	public static int resultat;
	
	//Position  optimal pour les permutations.
	public static Double[] PositionOpti;
	
	//Ordre optimal pour les permutations.
	public static Double[] OrdreOpti;
	
	/**
	 * L'algorithme glouton, prend la liste de batiments et place le premier pouvant être placer jusqu'à ce qu'il ne puisse plus en placer.
	 * De plus il affiche la taille des bâtiments avant de commencer et affiche la grille/le batiment placer/ la position choisie à chaque bâtiment placer.
	 * @return : Une ArrayList de Double représentant la position où sont placés les batiments.
	 */
	public static Double[] AlgorithmeGloutonAvecAffichage(){
    	System.out.println("taille des batiments");
    	int somme = 0;
    	for(int i = 0; i < structureUltime.getBatiments().size();i++){
    		int temp = structureUltime.ReturnSurface(structureUltime.getBatiments().get(i));
    		//System.out.println(temp);
    		somme += temp;
    	}
    	System.out.println("Some des surfaces : " + somme);
		Double[] listPositionsBatiments = new Double[structureUltime.getBatiments().size()];
		for(int i = 0; i < structureUltime.getTailleGrille().getPremier(); i++){
			for(int j = 0; j < structureUltime.getTailleGrille().getSecond(); j++){
				Double positionTemp = new Double(i,j);
				int indiceBatiment = structureUltime.ChercheBatimentPlacablePosition(positionTemp);
				if(indiceBatiment != -1 && structureUltime.PlacageBatimentPosition(structureUltime.getBatiments().get(indiceBatiment), positionTemp, indiceBatiment)){
					Double temp = new Double(structureUltime.getPositionsBatiments()[indiceBatiment].getPremier(), structureUltime.getPositionsBatiments()[indiceBatiment].getSecond());
					listPositionsBatiments[indiceBatiment] = temp;
					System.out.println("numéro: " + indiceBatiment + " de position : " + positionTemp.getPremier() + "   " + positionTemp.getSecond());			
					System.out.println("numéro: " + indiceBatiment + " de taille : " + structureUltime.getBatiments().get(indiceBatiment).getPremier() + "   " + structureUltime.getBatiments().get(indiceBatiment).getSecond());
					//structureUltime.AffichageGrille();
				}
			}
		}
		return listPositionsBatiments;
	}
	
	
	
	/**
	 * L'algorithme glouton, prend la liste de batiments et place le premier pouvant être placer jusqu'à ce qu'il ne puisse plus en placer.
	 * @return : Une ArrayList de Double représentant la position où sont placés les batiments.
	 */
	public static Double[] AlgorithmeGlouton(){
		//tableau des positions à retourner.
		//Réinitialise le tableau.
		structureUltime.ReinitUltime();
		Double[] listPositionsBatiments = new Double[structureUltime.getBatiments().size()];
		for(int i = 0; i< listPositionsBatiments.length; i++){
			listPositionsBatiments[i] = new Double(-1,-1);
		}
		for(int i = 0; i < structureUltime.getGrille().size(); i++){
			for(int j = 0; j < structureUltime.getGrille().get(i).size(); j++){
				//position actuelle.
				Double positionTemp = new Double(i,j);
				//L'indice du batiment pouvant être placé.
				int indiceBatiment = structureUltime.ChercheBatimentPlacablePosition(positionTemp);
				//Pose le batiment.
				if(indiceBatiment != -1){
					if( structureUltime.PlacageBatimentPosition(structureUltime.getBatiments().get(indiceBatiment), positionTemp, indiceBatiment)){
					Double temp = new Double(structureUltime.getPositionsBatiments()[indiceBatiment].getPremier(), structureUltime.getPositionsBatiments()[indiceBatiment].getSecond());
					listPositionsBatiments[indiceBatiment] = temp;
					}
				}
			}
		}
		return listPositionsBatiments;
	}
	
	
	/**
	 * Parser.
	 * @param fileName : nom du fichier à parser.
	 */
	public static void Parser(String fileName){
		String line = null;
    	try {
            FileReader fileReader =  new FileReader(fileName);
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
            int indiceLigne = 0;
            Double taille = new Double();
            ArrayList<Double> batiments = new ArrayList<Double>();
            while((line = bufferedReader.readLine()) != null) {
            	String[] tableau = line.split(",");
            	switch(indiceLigne){
	            	case 0:
	            		taille = new Double(Integer.parseInt(tableau[0]),Integer.parseInt(tableau[1]));
	            		System.out.println("taille de la structure : " + tableau[0] + " " + tableau[1]);
	            		break;
	            	case 1:
	            		System.out.println("nombre de bâtiments : " + tableau[0]);
	            		break;
	            	default:
	            		System.out.println("taille du bâtiment : " + tableau[0] + " " + tableau[1]);
	            		Double batiment = new Double(Integer.parseInt(tableau[0]),Integer.parseInt(tableau[1]));
	            		batiments.add(batiment);
	            		break;
            	}
            	indiceLigne++;
            }   
            bufferedReader.close();  
    		structureUltime = new StructureUltime(batiments, taille);

        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
        	ex.printStackTrace();
        }
    	
    	structureUltime.OrganisedRandom();
    	System.out.println("taille des batiments");
    	for(int i = 0; i < structureUltime.getBatiments().size();i++){
    		System.out.println(structureUltime.ReturnSurface(structureUltime.getBatiments().get(i)));
    	}
    		
	}
	
	
	/**
	 * Méthode renvoyant un tableau de Double identique à celui donnné en paramètre.
	 * @param tableau : Tableau de Double
	 * @return : Tableau de double.
	 */
	public static Double[] CopiageTableau(Double[] tableau){
		Double[] result = new Double[tableau.length];
		for(int i = 0; i < tableau.length; i++){
			result[i] = new Double(-1,-1);
		}
		for(int i = 0; i < tableau.length; i++){
			result[i] = tableau[i];
		}
		return result;
	}
	
	
	/**
	 * Méthode testant l'algorithme glouton 1000 fois avec des ordres de batiments aléatoires.
	 * @return : Les positions de batiments donnant une surface utilisée maximale.
	 */
	public static Double[] Puissances1000(){
		//Tableau contenant les position optimales trouvées.
		Double[] tableauFinal = new Double[structureUltime.getBatiments().size()];
		ArrayList<Double> teee = new ArrayList<Double>();
		//La surface optimale obtenue.
		int surface = 0;
		for(int i = 0; i < 1000; i++){
			//On utilise le randomiseur de batiments.
			structureUltime.OrganisedRandom();
			//On applique l'algo glouton.
			AlgorithmeGlouton();
			if(structureUltime.ReturnSurfaceGrille() >= surface){
				tableauFinal = CopiageTableau(structureUltime.getPositionsBatiments());
				teee = new ArrayList<Double> (structureUltime.getBatiments());
				surface = structureUltime.ReturnSurfaceGrille();
			}
			//On réinitialise la grille.
			structureUltime.ReinitUltime();
		}
		//On met les batiments dans l'ordre permettant le résultat optimal.
		structureUltime.setBatiments(teee);
		//On retourne les positions optimales trouvées.
		return tableauFinal;
	}
	
	
	
	/**
	 * Méthode testant l'algorithme sur toutes les permutations.
	 * @return : Les positions de batiments donnant une surface utilisée maximale.
	 */
	public static void PermutationTotale(){

		TableauPermut = new Double[structureUltime.getBatiments().size()];
		for(int i = 0; i < TableauPermut.length; i++){
			TableauPermut[i] = new Double(structureUltime.getBatiments().get(i));
		}
		OrdreOpti = new Double[structureUltime.getBatiments().size()];
		PositionOpti = new Double[structureUltime.getBatiments().size()];
		resultat = 0;
		PermutAll(structureUltime.getBatiments().size());
	}
	
	
	public static void PermutAll(int n) {
		if(n == 1) {
			Appli();
		} else {
			for(int i = 0; i < n-1; i++) {
				PermutAll(n - 1);
			    if(n % 2 == 0) {
			    	swap(i, n-1);
			    } else {
			    	swap(0, n-1);
			    }
			}
			PermutAll(n - 1);
		}
	}
				
	private static void swap(int a, int b) {
		Double tmp = TableauPermut[a];
		TableauPermut[a] = TableauPermut[b];
		TableauPermut[b] = tmp;
	}

	private static void Appli() {
		
		//On réinitialise la grille.
		structureUltime.ReinitUltime();
		
		
		ArrayList<Double> arrTemp = new ArrayList<Double>();
		for(int i  =0; i < Main.TableauPermut.length; i++){
			arrTemp.add(TableauPermut[i]);
		}
		structureUltime.setBatiments(arrTemp);
		//On applique l'algo glouton.
		AlgorithmeGlouton();
		//On test si c'est plus optimal.
		if(structureUltime.ReturnSurfaceGrille() >= Main.resultat){
			Main.PositionOpti = CopiageTableau(structureUltime.getPositionsBatiments());
			Double[] temp = new Double[structureUltime.getBatiments().size()];
			for(int i = 0; i < temp.length; i++){
				temp[i] = new Double(structureUltime.getBatiments().get(i));
			}
			Main.OrdreOpti = temp;
			Main.resultat = structureUltime.ReturnSurfaceGrille();
		}
	}
	

	
	public static void GeneratorProblem(int L){
		Double tailleGrille = new Double(L, L);
		ArrayList<Double> batiments = new ArrayList<Double>();
		while(batiments.size() < L){
	        int rand1 = (int) (Math.random()*1000%(2*Math.sqrt(L)));
	        int rand2 = (int) (Math.random()*1000%(2*Math.sqrt(L)));
	        if(rand1 >= 1 && rand2 >= 1 && rand1 <= (2*Math.sqrt(L)) && rand2 <= (2*Math.sqrt(L))){
		        Double tailleBatiment = new Double(rand1, rand2);
		        batiments.add(tailleBatiment);
	        }
		}
		structureUltime = new StructureUltime(batiments, tailleGrille);
	}
	
	
	/**
	 * Méthode lançant la sélection d'un fichier/problèmeet sa résolution.
	 */
	public static void LancementGestionFichier(){
		FileDialog fd = new FileDialog(new JFrame());
		fd.setVisible(true);
		Parser(fd.getFiles()[0].getAbsolutePath());
		
		AlgorithmeGlouton();
		
		Vue vue = new Vue(structureUltime);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode lançant la résolution d'un problème avec 1000 ordres de bâtiments aléatoires.
	 */
	public static void lancementPuissances1000(){
		//GeneratorProblem(10);
		
		Double temp[] = Puissances1000();
		
		structureUltime.UtilisationSolution(temp);
		
		structureUltime.AffichageGrille();
		
		Vue vue = new Vue(structureUltime);
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode lançant la résolution d'un problème avec toutes les permutations possibles.
	 * Prend environ 30 secondes avec 10 batiments.
	 */
	public static void lancementPermutationTotale(){

		//GeneratorProblem(10);
		
		Main.PermutationTotale();
		
		Double[] temp = new Double[structureUltime.getBatiments().size()];
		temp = Main.PositionOpti;
		ArrayList<Double> arrTemp = new ArrayList<Double>();
		for(int i  =0; i < Main.OrdreOpti.length; i++){
			arrTemp.add(OrdreOpti[i]);
		}
		structureUltime.setBatiments(arrTemp);
		structureUltime.UtilisationSolution(temp);
		
		structureUltime.AffichageGrille();
		
		Vue vue = new Vue(structureUltime);
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Le main.
	 */
	public static void main(String[] args) {
		
		System.out.println("Début du programme.");

		//Lance la sélection d'un fichier/problème et sa résolution.
		LancementGestionFichier();
		
		//Lance la résolution avec 1000 ordres de batiment aléatoires.
		lancementPuissances1000();
		
		//Lance la résolution avec toutes les permutations possibles de l'ordre de batiment.
		//Prend environ 30 secondes avec 10 batiments.
		lancementPermutationTotale();
				
		System.out.println("Fin du programme");
	}
	


}