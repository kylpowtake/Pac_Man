package model;

import java.net.*;
import java.io.*;


public class ServeurEmetteur extends Thread {
	
	Socket clientSocket;
	PrintWriter sortie;
	Game game;
	
	
	//constructeur 
	public ServeurEmetteur(Socket so){
		clientSocket = so;
		try {
		    sortie = new PrintWriter(clientSocket.getOutputStream(),true);
		    System.out.println(clientSocket.getPort());
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	
	public void sendMessage(String chaine){
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
