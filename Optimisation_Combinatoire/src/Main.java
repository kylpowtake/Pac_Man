import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main {
	
    //Structure devant être utilisé pour le reste du programme.
	public static StructureUltime structureUltime;
	
	
	public static ArrayList<Double> AlgorithmeGlouton(){
		ArrayList<Double> listPositionsBatiments = new ArrayList<Double>();
		for(int i = 0; i < structureUltime.getTailleGrille().getPremier(); i++){
			for(int j = 0; j < structureUltime.getTailleGrille().getSecond(); j++){
				System.out.println("Tour : " + i + "  " + j);
				Double positionTemp = new Double(i,j);
				int indiceBatiment = structureUltime.ChercheBatimentPlacablePosition(positionTemp);
				if(indiceBatiment != -1 && structureUltime.PlacageBatimentPosition(structureUltime.getBatiments().get(indiceBatiment), positionTemp, indiceBatiment)){
					listPositionsBatiments.add(positionTemp);
				} else {
					System.out.println(indiceBatiment);
				}
			}
		}
		return listPositionsBatiments;
	}
	
	
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
    	
    	structureUltime.OrganizedBySurface();
    	System.out.println("taille des batiments");
    	for(int i = 0; i < structureUltime.getBatiments().size();i++){
    		System.out.println(structureUltime.ReturnSurface(structureUltime.getBatiments().get(i)));
    	}
    		
	}
	
	public static void main(String[] args) {
		FileDialog fd = new FileDialog(new JFrame());
		fd.setVisible(true);
		Parser(fd.getFiles()[0].getAbsolutePath());

		AlgorithmeGlouton();
		
		structureUltime.Affichage();
		
		System.out.println("Début.");
		/*
		 * Trois structures de données rasssemblés en une.
		 * 1 : Structure contenant les batiments et leurs tailles.
		 * 2 : Structure contenant leurs posituion (x et y)
		 * 3 : Structure contenant la grille en elle même (tab de tab de bool)
		 */
		
		System.out.println("Fini.");
	}
	


}