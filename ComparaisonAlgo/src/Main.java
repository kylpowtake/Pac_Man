import java.util.Random;

import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.UnassignedClassException;
import weka.core.converters.ConverterUtils.DataSource;


 
public class Main {
	
	protected static void useClassifier(Instances data) throws Exception {
	    AttributeSelectedClassifier classifier = new AttributeSelectedClassifier();
	    CfsSubsetEval eval = new CfsSubsetEval();
	    GreedyStepwise search = new GreedyStepwise();
	    search.setSearchBackwards(true);
	    J48 base = new J48();
	    classifier.setClassifier(base);
	    classifier.setEvaluator(eval);
	    classifier.setSearch(search);
	    Evaluation evaluation = new Evaluation(data);
	    evaluation.crossValidateModel(classifier, data, 5, new Random(1));
	    System.out.println(evaluation.toSummaryString());
	  }
	
	
	public static void main(String[] args) {	
		
		GestionParametres(args);
		
		try {
			//On charge le fichier à l'emplacement indiqué.
			DataSource source = new DataSource("/home/etudiant/workspace/ComparaisonAlgo/weather.numeric.arff");
			//On charge les instances du fichier contenant les données.
			Instances data = source.getDataSet();

			//On charge le fichier à l'emplacement indiqué.(Fichier contenant les données pour s'entraîner)
			DataSource sourceTrain = new DataSource("/home/etudiant/workspace/ComparaisonAlgo/mushroom_train.arff");
			//On charge le fichier à l'emplacement indiqué. (Fichier contenant les données pour évaluer)
			DataSource sourceTest = new DataSource("/home/etudiant/workspace/ComparaisonAlgo/mushroom_valid.arff");
			//On charge les instances du fichier contenant les données. (Pour s'entraîner)
			Instances train = sourceTrain.getDataSet();
			//On charge les instances du fichier contenant les données. (Pour évaluer)
			Instances test = sourceTest.getDataSet();
			//Permet de s'occuper d'attributs ayant des valeurs négatives
			if(data.classIndex() == -1){
				data.setClassIndex(data.numAttributes() -1);
			}			
			//Permet de s'occuper d'attributs ayant des valeurs négatives
			if(train.classIndex() == -1){
				train.setClassIndex(train.numAttributes() -1);
			}			
			//Permet de s'occuper d'attributs ayant des valeurs négatives
			if(test.classIndex() == -1){
				test.setClassIndex(test.numAttributes() -1);
			}
			
			//Si il y a un jeu d'entrainement et un jeu d'évaluation.
			if(true){
			//Créer un classifier (ici, J48)
			J48 jPerso = new J48();
			//Créer un tableau de string allant contenir les options et on les ajoutes à ce tableau
			String[] options = new String[5];
			options[0] = "-C";
			options[1] = "0.25";
			options[2] = "-M";
			options[3] = "2";
			options[4] = "";
			//On ajoute les options aux classifiers
			jPerso.setOptions(options);
			//On créer un truc
			AttributeSelectedClassifier cls = new AttributeSelectedClassifier();
			//On créer un truc pour l'eval
		    CfsSubsetEval eval = new CfsSubsetEval();
		    //On créer un truc pour la recherche
		    GreedyStepwise search = new GreedyStepwise();
		    //On applique le classifier au truc
			cls.setClassifier(jPerso);
			//On applique l'eval au classifier
			cls.setEvaluator(eval);
			//On applique la recherche au classifier
			cls.setSearch(search);

			try{
			cls.buildClassifier(train);
			} catch(UnassignedClassException uce){
				System.out.println("Erreur : cls : " + uce.getMessage());
			}
			//On utilise les données pour s'entraîner pour préparer la méthode d'évaluation
			Evaluation evaltest = new Evaluation(train);
			//On évalue le odèle obtenue.
			evaltest.evaluateModel(cls, test);
			//On affiche le résultat de l'évaluation
			System.out.println(evaltest.toSummaryString("\nResults\n======\n", false));
			}
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void GestionParametres(String[] args){
		//Le premier argument est le nom du fichier
		//Le deuxième argument est le nom de la méthode à utiliser
		//Les arguments suivants sont les paramètres de la méthode
		//Il y a des paramètres contradictoires, ils ne peuvent être ensemble, un bolean est utilisé pour vérifié leur présence.
		//Les derniers arguments sont les chemins vers les fichiers ou leurs noms.
		//Le nombre de paramètres est  :  nombreArguments - 1(Nom fichier) - 1(Nom méthode) - 1~2(chemin, peut être deux chemins)
		//Il y a donc un maximum de paramètres de NombreArguments - 3
		//Et un minimum de paramètres de NombreArguments - 4
		
		//les options allant être utilisés pour ajoutés les paramètres à la méthode.
		String[] options = new String[args.length - 2 - 1];
		//Le compteur pour savoir où placer l'argument actuel dans le tableau.
		int compteur = 0;
		
		//Les options n'allant pas ensemble :
		//  U et C
		//  S et U
		//  R/N/Q et U
		//  R/N/Q et C
		//  Si il y a R, N ou Q ==> il faut les deux autres, ils sont liés.
		//  Il faut donc des booleans pour gérer ces situations
		//  Si ils sont faux, ils ne sont pas présent, sinon ils sont vrai == présents
		//  Il en faut au moins un de vrai (C , U , R/N/Q).
		//  Le paramètre M est toujours présent, il est obligatoire.
		boolean testU = false;
		boolean testC = false;
		boolean testR = false;
		boolean testN = false;
		boolean testQ = false;
		boolean testS = false;
		boolean testM = false;
		//Il y a deux chemin vers des fichiers possibles,
		// celui contenant l'ensemble de de données de l'entraînement.
		// celui contenant l'ensemble de de données d'évaluation.
		String CheminEnt = null;
		String CheminEval = null;
		for(int i = 2; i < args.length; i++){
			switch(args[i]){
			case "-L" :
				options[compteur] = args[i];
				compteur++;
				break;
			case "-O" :
				options[compteur] = args[i];
				compteur++;
				break;
			case "-S" :
				options[compteur] = args[i];
				compteur++;
				testS = true;
				break;
			case "-C" :
				options[compteur] = args[i];
				compteur++;
				i++;
				options[compteur] = args[i];
				compteur++;
				testC = true;
				break;
			case "-B" :
				options[compteur] = args[i];
				compteur++;
				break;
			case "-M" :
				options[compteur] = args[i];
				compteur++;
				i++;
				options[compteur] = args[i];
				compteur++;
				testM = true;
				break;
			case "-A" :
				options[compteur] = args[i];
				compteur++;
				break;
			case "-J" :
				options[compteur] = args[i];
				compteur++;
				break;
			case "-doNotMakeSplitPointActualValue" :
				options[compteur] = args[i];
				compteur++;
				break;
			case "-output-debug-info" :
				options[compteur] = args[i];
				compteur++;
				break;
			case "-do-not-check-capabilities" :
				options[compteur] = args[i];
				compteur++;
				break;
			case "-num-decimal-places" :
				options[compteur] = args[i];
				compteur++;
				i++;
				options[compteur] = args[i];
				compteur++;
				break;
			case "-batch-size" :
				options[compteur] = args[i];
				compteur++;
				i++;
				options[compteur] = args[i];
				compteur++;
				break;
			case "-N" :
				options[compteur] = args[i];
				compteur++;
				i++;
				options[compteur] = args[i];
				compteur++;
				testN = true;
				break;
			case "-Q" :
				options[compteur] = args[i];
				compteur++;
				i++;
				options[compteur] = args[i];
				compteur++;
				testQ = true;
				break;
			case "-R" :
				options[compteur] = args[i];
				compteur++;
				testR = true;
				break;
			case "-U" :
				options[compteur] = args[i];
				compteur++;
				testU = true;
				break;
			default :
				//Seulement deux possibiltés autorisés,
				//Le string est ce lui d'un des deux chemin possibles vers le fichier contenant l'ensemble de données(celui d'entraînement et celui d'évaluation)
				break;
			}
		}
		//On test la présence des paramètres obligatoires et de ceux contradictoires.
		if((testM == false) 
				|| (testU == false && testC == false && testQ == false) 
				|| ((testQ != testR) || (testR != testN)) 
				|| (testU == true && testC == true) 
				|| (testU == true && testQ == true)
				|| (testC == true && testQ == true)
				|| (testU == true && testS == true)){
			System.out.println("Erreur de paramètrage.");
		} else {
			System.out.println("Paramétrage réussi.");
		}
	}
	
}
