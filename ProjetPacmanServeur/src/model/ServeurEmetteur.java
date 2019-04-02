package model;

import java.net.*;
import java.io.*;

/**
 * la classe permet d'emettre les informations 
 * sur l'etat du jeu à un client défini 
 */
public class ServeurEmetteur extends Thread {
	
	Socket clientSocket;
	PrintWriter sortie;
	Game game;			
	
	
	//constructeur 
	public ServeurEmetteur(Socket so){
		clientSocket = so;
		try {
		    sortie = new PrintWriter(clientSocket.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * methode émettant un message au client 
	 */
	public void sendMessage(String chaine){
		sortie.println(chaine);
	}
	
	/**
	 * méthode qui effectue un tour des que le jeu est lancé
	 * et tant qu'il n'est pas mis en pause ou terminé
	 */
	public void run(){
		while(game.finThread != true){
			if(game.getIsRunning() == true){
				game.takeTurn();
				try {
					Thread.sleep(1000/game.getNbToursSecondes());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}			
			}
			sortie.flush();
		}
	 } 
}
