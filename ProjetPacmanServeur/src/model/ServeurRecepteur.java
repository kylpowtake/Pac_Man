package model;

import java.net.*;
import java.io.*;

public class ServeurRecepteur extends Thread {
	static Socket clientSocket;
	static ControleurGame controleur;
	
	
	//constructeur 
	public ServeurRecepteur(Socket so){
		clientSocket = so;
	}
	
	/**
	 * @param chaine
	 * traite la chaine envoyée par le client 
	 * en fonction de si c'est une direction, une commande ou une demande de connexion  
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
		}else if(parts[0].equals("pseudo")){		
			 String[] parts2 = parts[1].split(";");
			 String pseudo = parts2[0];
			 String mdp = parts[2];
			 int identifiant = Bdd.connect(pseudo, mdp);
			 if(identifiant!=-1){
				 MainServeur.setGame(clientSocket,identifiant);
			 }else{
				 MainServeur.setEmetteur(clientSocket);
				 ServeurEmetteur.sendMessage("connexion:http://localhost:8080/pro/connexion");
			 }
 
		}
		//la commande que le joueur envoi(play,pause,slider,changement)
		else{								
			switch(parts[1]){
			case "play" :
				controleur.start();
				break;
			case "pause" :
				controleur.pause();
				break;
			case "slider":
				controleur.slider(Integer.parseInt(parts[1]));
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
			controleur.finJeu();
		} catch(IOException e){
			System.out.println("Problème lors du run : " + e.getMessage());
			controleur.finJeu();
		}
	 } 
}
