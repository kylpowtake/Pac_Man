package model;

import java.net.*;
import java.util.*;
import java.io.*;

public class MainServeur {
	
	
	static Vector<Socket> clients = new Vector<>();				//vecteur contenant tout les clients (socket)
	static ServeurRecepteur test = null;
	static PrintWriter out;
	static String chemin = "layouts/capsuleClassic.lay";		//chemin envoyé au client à sa connexion 
	
	
	
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
					
					//creation de nouveaux gestionnaires pour le client 
	                ServeurRecepteur serveurRecepteur = new ServeurRecepteur(clientSocket);           
	                
	                Thread ecoute = new Thread(serveurRecepteur);
	           
	                //ajout du client dans la liste
	                clients.add(clientSocket);
	  
	                //lancement des thread d'écoute
	                ecoute.start();    
	                
				}	
			}catch (Exception e){ 
	            e.printStackTrace();
			}
		}else{
			System.out.println("erreur dans la nombre d'arguments  ./Programme port");
		}
	}
	
	public static Thread setEmetteur(Socket so){
		ServeurEmetteur serveurEmetteur = new ServeurEmetteur(so);
        Thread envoi = new Thread(serveurEmetteur);
		return envoi;
		
	}
	
	public static void setGame(Socket so,int identifiant){
		try {
			Maze laby = new Maze(chemin);
			PacmanGame game = new PacmanGame(laby, chemin);
			game.setLabyrinthe(laby);
			ControleurGame controleur = new ControleurGame(game);
			
			game.setIdentifiant(identifiant);
			
			//demarrage du thread d'envoi 
			setEmetteur(so).start();
			
			//set du game et du controleur 
			ServeurEmetteur.game = game;
			ServeurRecepteur.controleur = controleur;
			
			//envoi du chemin au client 
			ServeurEmetteur.sendMessage("chemin:"+MainServeur.chemin); 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}	



	                

