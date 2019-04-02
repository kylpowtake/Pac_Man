import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;


 
public class Main {
	
	public static String[] options;
	public static String[] optionsTemp;
	public static String chaineOption = "";		
	public static String fileNameA = "";		//nom du fichier avec chemin absolu
	public static String fileName = "";			//nom du fichier 
	public static Map<Integer, Double> tableau  = new HashMap<Integer, Double>();	//stocke les résultats obtenus après utilisation du classifieur
	

	public static void main(String[] args) {
		new Vue().setVisible(true);
	}
	
	public static void executer(){
		
		
		try {
			
			DataSource source = new DataSource(fileNameA);	//chargement des données du fichier 
			Instances data = source.getDataSet();			//creation du jeu de donnée 
			if(data.classIndex() == -1){
				data.setClassIndex(data.numAttributes() -1);
			}
			
			useClassifier(data);			//utilisation du classifier sur le jeu de données
			writeFile(tableau,fileName);	//enregistrement des résultats dans un fichier csv
			readFile(fileName);				//lecture du fichier er recupération du resultat le plus performant 
			
			//affichage du graphe 
	    	LineChart ex = new LineChart(tableau);
	    	Vue.graphPanel.removeAll();				
	    	Vue.graphPanel.add(ex);
	    	Vue.graphPanel.updateUI();
	    	
	    	//remise a 0 de la chaine d'option et du tableau de données 
			chaineOption = "";
			tableau.clear();
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	    	Path currentRelativePath = Paths.get("");
	    	String file = currentRelativePath.toAbsolutePath().toString()+"/"+filename+"_methodJ48.csv";
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
            
          //affichage du meilleur résultat obtenu 
	 	    printResults(tableau);
            
            Vue.resultLabel.append("\n\n------------------------------------\n\nMeilleur résultat\n" +
            		"\nParamètres utilisés :\n" + paramètresOpti +
            		"\nTaux d'erreur :\n" + value + "%" +
 				    "\nNombre d'éléments :\n" + nombreFeuilles);
            
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
	 * problème noté si il y a moins de 20 instances dans le jeu de données n'entre pas dans le for 
	 */
	protected static void useClassifier(Instances data) throws Exception {
	    J48 base = new J48();
	    options[options.length - 2] = "-M";
	    optionsTemp = new String[options.length];
	    
	    int nombreInstance = data.numInstances();
	    int increment = nombreInstance/90;
	    //System.out.println("nombre d'instances : " + nombreInstance);

	    for(int i=2; i<nombreInstance*0.1; i+=increment){
	    	options[options.length -1] = "" + i;
	    
	    	//recopie du tableau pour ne pas perdre les données 
	    	//une fois celles-ci consommées
	    	for (int j =0;j<options.length;j++){
	    		optionsTemp[j] = options[j];
	    	}
	    	
	    	//definit les options a utiliser sur notre classifieur 
	    	//attention consomme les données 
	    	base.setOptions(optionsTemp);
	    	
	 	    Evaluation evaluation = new Evaluation(data);
	 	    evaluation.crossValidateModel(base, data, 5, new Random(1));
	 	    
	 	    //sauvegarde des résultat dans une map 
	 	    tableau.put(i, evaluation.errorRate()*100);
	    }
	  }
	
	
	/**
	 * @param tableau
	 * la méthode affiche le taux d'erreur le plus faible obtenu ainsi que 
	 * le nombre d'element minimum par feuille qui y est associé
	 * une fois la classification effectuée 
	 */
	public static void printResults(Map<Integer, Double> tableau){
		double tauxErreur = 100;
		int nbFeuilles = 1000;
		Set<Entry<Integer, Double>> setHm = tableau.entrySet();
	    Iterator<Entry<Integer, Double>> it = setHm.iterator();
	    while(it.hasNext()){
	    	Entry<Integer, Double> e = it.next();
	        if(e.getValue()<tauxErreur){
	        	tauxErreur = e.getValue();
	        	nbFeuilles = e.getKey();
	        }
	     }
	    Vue.resultLabel.setText("\nNombre d'éléments  : \n" + nbFeuilles +
				   				"\n\nTaux d'erreur : \n" + tauxErreur +"%");
	}
}
