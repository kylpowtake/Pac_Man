package model;

import java.net.*;
import java.util.*;
import java.io.*;

public class MainServeur {
	
	static ArrayList<ServeurRecepteur> clientRecepteur = new ArrayList<>();
	static Vector<Socket> clients = new Vector<>();				//vecteur contenant tout les clients (socket)
	static ServeurRecepteur test = null;
	static PrintWriter out;
	static String chemin = "layouts/tinyMaze.lay";		//chemin envoyé au client à sa connexion 
	static int id = 0;
	
	
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
	                serveurRecepteur.id = id;
	                id++;
	                clientRecepteur.add(serveurRecepteur);
	                
	                //Thread ecoute = new Thread(serveurRecepteur);
	           
	                //ajout du client dans la liste
	                clients.add(clientSocket);
	  
	                serveurRecepteur.start();
	                for(int  i = 0 ;i < clientRecepteur.size(); i++){
	                	ServeurRecepteur temp = clientRecepteur.get(i);
	                	System.out.println("Recepteur de client : num : " + temp.id + " et la socket : " + temp.clientSocket.toString());
	                }
	                //lancement des thread d'écoute
	                //ecoute.start();    
	                
				}	
			}catch (Exception e){ 
	            e.printStackTrace();
			}
		}else{
			System.out.println("erreur dans la nombre d'arguments  ./Programme port");
		}
	}

	public static void Patate(Socket socket, String message){
		for(int i = 0; i < clientRecepteur.size(); i++){
			if(clientRecepteur.get(i).clientSocket.equals(socket)){
				clientRecepteur.get(i).serveurEmetteur.sendMessage(message);
			}
		}
	}
}	



	                

