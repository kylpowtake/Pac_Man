import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


 
public class Main {
	
	public static String[] options;
	public static String[] options2;
	public static String chaineOption = "";
	public static Map<Integer, Double> tableau  = new HashMap<Integer, Double>();
	

	public static void main(String[] args) {
		
		GestionParametres(args);
		new Vue().setVisible(true);
		/*
		FileDialog fd = new FileDialog(new JFrame());
		fd.setVisible(true);
		
		try {
			DataSource source = new DataSource(fd.getFiles()[0].getAbsolutePath());
			Instances data = source.getDataSet();
			if(data.classIndex() == -1){
				data.setClassIndex(data.numAttributes() -1);
			}
			useClassifier(data);							//utilisation du classifier sur le jeu de données
			writeFile(tableau,fd.getFiles()[0].getName());	//enregistrement des résultats dans un fichier csv
			readFile(fd.getFiles()[0].getName());			//lecture du fichier er recupération du resultat le plus performant 
			
			//afichage du graphe 
			JFrame p = new JFrame();
	    	LineChart ex = new LineChart(tableau);
	    	p.add(ex);
	    	p.setSize(700, 450);
	    	p.setLocation(300,200);
	        p.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	
	
	
	/**
	 * @param tableau(nombre minimum d'elements pas feuille, taux d'instances mal classés) 
	 * @param filename (chemin absolu vers le fichier)
	 * Crée un fichier si il n'existe pas 
	 * Enregistre les résultats de la méthode utilisée  
	 */
	public static void writeFile(Map<Integer, Double> tableau, String filename){
		Set<Entry<Integer, Double>> setHm = tableau.entrySet();
	    Iterator<Entry<Integer, Double>> it = setHm.iterator();
	    try{
	        String file = "/home/etudiant/workspace/ComparaisonAlgo/" + filename + "_methodJ48.csv";
	        FileWriter fw = new FileWriter(file,true);
	        fw.write("-M"+","+chaineOption+"\n");
	        while(it.hasNext()){
		         Entry<Integer, Double> e = it.next();
		         fw.write(+e.getKey()+","+e.getValue()+"\n");
		     }
	        fw.close();
	    }catch(IOException ioe){
	        System.err.println("IOException: " + ioe.getMessage());
	    }
	}
	
	
	/**
	 * @param filename (chemin absolu vers le fichier)
	 * Lit le fichier et renvoi la méthode la plus performante ainsi que la valeur 
	 */
	public static void readFile(String filename){
		String csvFile = filename+"_methodJ48.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        double value = 100;
        String nombreFeuilles = "";
        String paramètres = "";
        String paramètresOpti = "";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(cvsSplitBy);
                if(!values[0].equals("-M")){
                	if(Double.parseDouble(values[1])<value){ 	
                    	nombreFeuilles = values[0];
                    	value = Double.parseDouble(values[1]);
                    	paramètresOpti = paramètres;
                    }
                }else{
                	paramètres = values[1];
                }
            }
            System.out.println("Valeur optimale : " + 
            	   "\nparamètres utilisés : " + paramètresOpti +
 				   "\nombre minimum d'elements pas feuille: -M " + nombreFeuilles + 
 				   "\ntaux d'erreur : " + value);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	/**
	 * @param data (jeu de données utilisé)
	 * @throws Exception
	 * utilisation du la méthode de classification J48 sur les données passées en paramètres 
	 */
	protected static void useClassifier(Instances data) throws Exception {
	    J48 base = new J48();
	    options[options.length - 2] = "-M";
	    options2 = new String[options.length];
	    
	    int nombreInstance = data.numInstances();
	    int increment = nombreInstance/90;
	    //System.out.println(nombreInstance);
	    
	    for(int i=2; i<nombreInstance*0.1; i+=increment){
	    	options[options.length -1] = "" + i;
	    	
	    	//recopie du tableau pour ne pas perdre les données 
	    	//une fois celles-ci consommées
	    	for (int j =0;j<options.length;j++){
	    		options2[j] = options[j];
	    	}
	    	
	    	//definit les options a utiliser sur notre classifieur 
	    	//attention consomme les données 
	    	base.setOptions(options2);
	 	    Evaluation evaluation = new Evaluation(data);
	 	    evaluation.crossValidateModel(base, data, 5, new Random(1));
	 	    
	 	    //sauvegarde des résultat dans une map 
	 	    tableau.put(i, evaluation.errorRate()*100);
	    }
	  }
	
	
	/**
	 * Méthode gérant les paramètres données par l'utilisateur pour le test actuel.
	 * @param args
	 */
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
		options = new String[args.length + 2];
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
		for(int i = 0; i < args.length; i++){
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
				System.out.println("on y passe");
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
				System.out.println("on y passe");
				break;
			default :
				System.out.println("on y passe" + args[i]);

				//Seulement deux possibiltés autorisés,
				//Le string est ce lui d'un des deux chemin possibles vers le fichier contenant l'ensemble de données(celui d'entraînement et celui d'évaluation)
				break;
			}
		}
		//On test la présence des paramètres obligatoires et de ceux contradictoires.
		if((testU == false && testC == false && testQ == false) 
				|| ((testQ != testR) || (testR != testN)) 
				|| (testU == true && testC == true) 
				|| (testU == true && testQ == true)
				|| (testC == true && testQ == true)
				|| (testU == true && testS == true)){
			System.out.println("Erreur de paramètrage." + testM + testU);
		} else {
			System.out.println("Paramétrage réussi.");
		}	
		for(int i=0;i<options.length;i++){
			if(options[i]!=null)
				chaineOption += options[i]+" ";
	    }
	}
	
}
