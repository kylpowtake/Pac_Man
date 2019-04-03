import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

public class Main {
	
    //Structure devant être utilisé pour le reste du programme.
	public static StructureUltime structureUltime = new StructureUltime();
	
	public static void Parser(String fileName){
		String line = null;
    	try {
            FileReader fileReader =  new FileReader(fileName);
            BufferedReader bufferedReader =  new BufferedReader(fileReader);
            int indiceLigne = 0;
            while((line = bufferedReader.readLine()) != null) {
            	String[] tableau = line.split(",");
            	switch(indiceLigne){
	            	case 0:
	            		structureUltime.setTailleGrille(new Double(Integer.parseInt(tableau[0]),Integer.parseInt(tableau[1])));
	            		System.out.println("taille de la structure : " + tableau[0] + " " + tableau[1]);
	            		break;
	            	case 1:
	            		System.out.println("nombre de bâtiments : " + tableau[0]);
	            		break;
	            	default:
	            		System.out.println("taille du bâtiment : " + tableau[0] + " " + tableau[1]);
	            		Double batiment = new Double(Integer.parseInt(tableau[0]),Integer.parseInt(tableau[1]));
	            		structureUltime.getBatiments().add(batiment);
	            		break;
            	}
            	indiceLigne++;
            }   
            bufferedReader.close();  
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

		System.out.println("Avant le premier.");
		structureUltime.PlacageBatimentPosition(new Double(2,2), new Double(0,0));
		structureUltime.AffichageGrille();
		System.out.println("Avantle deuxième ");
		structureUltime.PlacageBatimentPosition(new Double(2,2), new Double(2,0));
		structureUltime.AffichageGrille();
		System.out.println("Avant le troisième ");
		structureUltime.PlacageBatimentPosition(new Double(2,2), new Double(0,2));
		structureUltime.AffichageGrille();
		System.out.println("Avant le dernier ");
		structureUltime.PlacageBatimentPosition(new Double(2,2), new Double(2,2));
		structureUltime.AffichageGrille();
		structureUltime.PlacageBatimentPosition(new Double(2,2), new Double(2,2));
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