package model;

import java.net.*;
import java.io.*;

public class ServeurRecepteur extends Thread {
	Socket clientSocket;
	static ControleurGame controleur;
	
	//constructeur 
	public ServeurRecepteur(Socket so,ControleurGame controleur){
		this.clientSocket = so;
		ServeurRecepteur.controleur = controleur;
	}
	
	/**
	 * @param chaine
	 * traite la chaine envoyée par le client 
	 * en fonction de si c'est une direction ou une commande 
	 */
	static public void traiter(String chaine){
		String[] parts = chaine.split(":");
		//la direction que le joueur envoi(haut,bas,gauche,droite)
		if(parts[0].equals("direction")){	
			switch(parts[1]){
			case "0" :
				controleur.getGame().pacmans.get(0).setNextAction(0);
				break;
			case "1" :
				controleur.getGame().pacmans.get(0).setNextAction(1);
				break;
			case "2" :
				controleur.getGame().pacmans.get(0).setNextAction(2);
				break;
			case "3" :
				controleur.getGame().pacmans.get(0).setNextAction(3);
				break;
			default :
				controleur.getGame().pacmans.get(0).setNextAction(4);
				break;
			}
		}
		//la commande que le joueur envoi(init,play,step,pause,changement)
		else{								
			switch(parts[1]){
			case "init" :
				controleur.restart();
				break;
			case "play" :
				controleur.start();
				break;
			case "step" :
				controleur.step();
				break;
			case "pause" :
				controleur.pause();
				break;
			default :
				controleur.changement(parts[1]);
				break;
			}
		}
	}
	
	public void run(){
		try {
			String chaine;
			BufferedReader entree = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			while(true){
				chaine = entree.readLine();
				traiter(chaine);
			 }	 
		} catch(NullPointerException npe){
			System.out.println("Pointeur null  : " + npe.getMessage());
		} catch(SocketException se){
			System.out.println("deconnexion d'un client " + se.getMessage());
		} catch(IOException e){
			System.out.println("Problème lors du run : " + e.getMessage());
		}
	 } 
}
