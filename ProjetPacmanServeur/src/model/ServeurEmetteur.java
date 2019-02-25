package model;

import java.net.*;
import java.io.*;


public class ServeurEmetteur extends Thread {
	
	static Socket clientSocket;
	static PrintWriter sortie;
	Game game;
	
	
	//constructeur 
	public ServeurEmetteur(Socket so,Game game){
		clientSocket = so;
		this.game = game;
		try {
		    sortie = new PrintWriter(clientSocket.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void test(String chaine){
		sortie.println(chaine);
	}
	
	
	public void run(){
		while(true){
			
			//System.out.print("a retirer probleme de flush\n");
			if(game.isRunning == true && game.NbTours < game.NbToursMax){
				try {
					Thread.sleep(1000/game.NbToursSecondes);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				game.takeTurn();
			}
			if(game.NbTours >= game.NbToursMax){
				System.out.println("fin du jeu");
			}
			sortie.flush();
		}
	 } 
}
