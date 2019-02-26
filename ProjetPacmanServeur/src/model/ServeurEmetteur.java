package model;

import java.net.*;
import java.io.*;


public class ServeurEmetteur extends Thread {
	
	static Socket clientSocket;
	static PrintWriter sortie;
	static Game game;
	
	
	//constructeur 
	public ServeurEmetteur(Socket so){
		clientSocket = so;
		try {
		    sortie = new PrintWriter(clientSocket.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	public static void sendMessage(String chaine){
		sortie.println(chaine);
	}
	
	
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
