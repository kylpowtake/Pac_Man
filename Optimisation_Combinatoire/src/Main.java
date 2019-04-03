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
				Double positionTemp = new Double(i,j);
				int indiceBatiment = structureUltime.ChercheBatimentPlacablePosition(positionTemp);
				if(indiceBatiment != -1 && structureUltime.PlacageBatimentPosition(structureUltime.getBatiments().get(indiceBatiment), positionTemp, indiceBatiment)){
					listPositionsBatiments.add(positionTemp);
				} else {
					//System.out.println(indiceBatiment);
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
    	
    	structureUltime.OrganisedRandom();
    	System.out.println("taille des batiments");
    	for(int i = 0; i < structureUltime.getBatiments().size();i++){
    		System.out.println(structureUltime.ReturnSurface(structureUltime.getBatiments().get(i)));
    	}
    		
	}
	
	
	
	public static Double[] Puissances1000(){
		Double[] tableauFinal = new Double[structureUltime.getBatiments().size()];
		Double[] tableauTemp = new Double[structureUltime.getBatiments().size()];
		ArrayList<Double> teee = new ArrayList<Double>();
		int surface = 0;
		structureUltime.ReinitUltime();
		structureUltime.OrganisedRandom();
		AlgorithmeGlouton();
		surface = structureUltime.ReturnSurfaceGrille();
		tableauTemp = structureUltime.getPositionsBatiments();
		tableauFinal = structureUltime.getPositionsBatiments();
		structureUltime.ReinitUltime();		
		for(int i = 0; i < 1000; i++){
			structureUltime.OrganisedRandom();
			teee = AlgorithmeGlouton();
			tableauTemp = structureUltime.getPositionsBatiments();
			if(structureUltime.ReturnSurfaceGrille() > surface){
				System.out.println("On passe dans le if, surface : " + surface);
				tableauFinal = tableauTemp;
				surface = structureUltime.ReturnSurfaceGrille();
			}
			structureUltime.ReinitUltime();
		}
		for(int i = 0; i < tableauFinal.length; i++){
			System.out.println("Final Batiment numéro : " + i + "   de position : " + tableauFinal[i].getPremier() + "   " + tableauFinal[i].getSecond());
			System.out.println("temp  Batiment numéro : " + i + "   de position : " + tableauTemp[i].getPremier() + "   " + tableauTemp[i].getSecond());
		}
		return tableauFinal;
	}
	
	public static void main(String[] args) {
		FileDialog fd = new FileDialog(new JFrame());
		fd.setVisible(true);
		Parser(fd.getFiles()[0].getAbsolutePath());

		Double [] temp = Puissances1000();
		
		/*
		ArrayList<Double> teee = AlgorithmeGlouton();
		
		for(int i = 0; i < teee.size(); i++){
			System.out.println("temp  Batiment numéro : " + i + "   de position : " + teee.get(i).getPremier() + "   " + teee.get(i).getSecond());			
		}

		structureUltime.OrganisedRandom();
		teee = AlgorithmeGlouton();
		structureUltime.ReinitUltime();
		
		
		teee = AlgorithmeGlouton();
		
		
		for(int i = 0; i < teee.size(); i++){
			System.out.println("temp  Batiment numéro : " + i + "   de position : " + teee.get(i).getPremier() + "   " + teee.get(i).getSecond());			
		}
		*/
		
		
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