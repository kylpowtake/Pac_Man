package model;

import java.net.*;
import java.io.*;

public class ServeurRecepteur extends Thread {
	Socket clientSocket;
	
	//constructeur 
	public ServeurRecepteur(Socket so){
		this.clientSocket = so;
	}
	
	public void run(){
		try {
			String chaine;
			BufferedReader entree = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter sortie = new PrintWriter(clientSocket.getOutputStream(),true); 
			
			
			while(true){
				chaine = entree.readLine();

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
