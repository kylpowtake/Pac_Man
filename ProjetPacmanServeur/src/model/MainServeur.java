package model;

import java.net.*;
import java.util.*;
import java.io.*;

public class MainServeur {
	
	static ArrayList<ServeurRecepteur> clientRecepteur = new ArrayList<>(); 	//vecteur contenant tout les clients
	static String chemin = "layouts/tinyMaze.lay";								//chemin envoyé au client à sa connexion 
	static PrintWriter out;
	
	
	
	public static void main(String[] args)throws IOException{
		
		ServerSocket serverSocket;	//socket du serveur 
		Socket clientSocket;		//socket du client 
		int port;					//numero du port sur le quel le serveur demarre (> 2500)	

		if(args.length == 1){
	
			try{
				port = Integer.parseInt(args[0]);
				serverSocket = new ServerSocket(port);
				System.out.println("serveur lancé");
				
				//boucle infini en attente de connexion d'un nouveau client 
				while(true){
					
					//connexion d'un nouveau client 
	                clientSocket = serverSocket.accept(); 
	                System.out.println("nouveau client connecté : " + clientSocket); 
					
					//création d'un nouveau gestionnaire pour le client 
	                ServeurRecepteur serveurRecepteur = new ServeurRecepteur(clientSocket);           
	                clientRecepteur.add(serveurRecepteur);
	                
	                //démarrage du thread de réception des messages 
	                serveurRecepteur.start();
				}	
			}catch (Exception e){ 
	            e.printStackTrace();
			}
		}else{
			System.out.println("erreur dans la nombre d'arguments : ./nomProgramme numeroPort");
		}
	}

	/**
	 * @param socket 
	 * @param message 
	 * permet d'envoyer un message un client identifié par son socket s
	 */
	public static void SendMessageClient(Socket socket, String message){
		for(int i = 0; i < clientRecepteur.size(); i++){
			if(clientRecepteur.get(i).clientSocket.equals(socket)){
				clientRecepteur.get(i).serveurEmetteur.sendMessage(message);
			}
		}
	}
}	



	                

