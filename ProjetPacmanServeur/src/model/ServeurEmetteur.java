package model;

import java.net.*;
import java.io.*;


public class ServeurEmetteur extends Thread {
	
	static Socket clientSocket;
	Game game;
	
	//constructeur 
	public ServeurEmetteur(Socket so,Game game){
		clientSocket = so;
		this.game = game;
	}
	
	public static void test(String chaine){
		try {
			PrintWriter sortie = new PrintWriter(clientSocket.getOutputStream(),true); 
			sortie.println(chaine);
		} catch(NullPointerException npe){
			System.out.println("Pointeur null  : " + npe.getMessage());
		} catch(SocketException se){
			System.out.println("deconnexion d'un client " + se.getMessage());
		} catch(IOException e){
			System.out.println("Problème lors du run : " + e.getMessage());
		}
	}
	
	
	public void run(){
		
		while(game.isRunning == true && game.NbTours < game.NbToursMax){
			try {
				Thread.sleep(1000/game.NbToursSecondes);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			game.step();
		}
		if(game.NbTours >= game.NbToursMax){
			System.out.println("fin du jeu");
		}
	 } 
}
