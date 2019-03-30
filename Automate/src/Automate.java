import java.io.*;
import java.util.*;


public class Automate{
	
    String 	nomfich;		//nom du fichier
    String 	alphabet[];		//alphabet
    String 	etat[];			//etats
    String 	qi;				//etat initial
    String 	f[];			//etats finaux
    static Vector<String> mots = new Vector<String>();	//vector contenant les mots 
    static public void readFile(String fileName){
    	String line = null;
    	try {
            FileReader fileReader =  new FileReader(fileName);
            BufferedReader bufferedReader =  new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                mots.add(line);
            }   
            
            generation();
            bufferedReader.close();  
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
        	ex.printStackTrace();
        }
    }
    
    
    static public void generation(){
		System.out.println("On commence.");
    	Vue.e = new Etat();
    	Vue.e.InitialiserAccepteurMots(mots);
    	Vue.e.Affichage();
    	//Fusionne les états finaux.
    	Vue.e.GeneralfusionEtatsFinaux();
    	Vue.e.Affichage();
    	Vue.e.FusionEtatsNonFinaux();
      	Vue.e.Affichage();
		System.out.println("On finit");
    }
}