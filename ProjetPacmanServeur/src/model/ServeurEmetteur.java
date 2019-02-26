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
		//this.game = game;
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
		while(true){
			if(game.getIsRunning() == true){
				try {
					Thread.sleep(1000/game.getNbTours());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				game.takeTurn();
			}
			sortie.flush();
		}
	 } 
}
