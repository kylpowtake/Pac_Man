package model;

import java.net.*;
import java.io.*;
import java.net.InetAddress;
import java.util.Enumeration;

/**
 * La classe permet de recrvoir et de traiter 
 * les méssages envoyés par le client 
 */
public class ServeurRecepteur extends Thread {
	
	ServeurEmetteur serveurEmetteur;
	Socket clientSocket;
	ControleurGame controleur;
	static String chemin = "layouts/tinyMaze.lay";		//chemin du labyrinthe envoyé au client à sa connexion 

	
	//constructeur 
	public ServeurRecepteur(Socket so){
		clientSocket = so;
	}
	
	/**
	 * @param chaine
	 * la méthode traite la chaine envoyée 
	 * par le client(direction,demande de connexion,commande)
	 */
	public void traiter(String chaine){
		String[] parts = chaine.split(":");
		System.out.println(chaine);
		
		//cas d'une direction 
		if(parts[0].equals("direction")){	
			switch(parts[1]){
			case "0" :
				controleur.getGame().pacmans.get(0).setNextAction(0);
				break;
			case "1" :
				controleur.getGame().pacmans.get(0).setNextAction(1);
				break;
			case "2" :
				controleur.getGame().pacmans.get(0).setNextAction(2);
				break;
			case "3" :
				controleur.getGame().pacmans.get(0).setNextAction(3);
				break;
			default :
				controleur.getGame().pacmans.get(0).setNextAction(4);
				break;
			}
		}
		//cas d'une demande de connexion
		else if(parts[0].equals("pseudo")){		
			 String[] parts2 = parts[1].split(";");
			 String pseudo = parts2[0];
			 String mdp = parts[2];
			 int identifiant = Bdd.connect(pseudo, mdp);
			 //si le client dispose d'un compte valide on crée le jeu 
			 if(identifiant!=-1){
				 setGame(clientSocket,identifiant);				
			 }
			 //sinon on le redirige vers la page d'inscription 
			 else{
				 setEmetteur(clientSocket);
				try {
					String ip = null;
					NetworkInterface nif = NetworkInterface.getByName("wlo1");
					Enumeration <InetAddress> inetAdrress = nif.getInetAddresses();
					while(inetAdrress.hasMoreElements()){
						ip = inetAdrress.nextElement().getHostName();
					}
					serveurEmetteur.sendMessage("connexion:http:/"+ip+":8080/pro/inscription");
				} catch (SocketException e) {
					e.printStackTrace();
				}
			 }
		}//cas du changement de vitesse du jeu 
		else if(parts[0].equals("slider")){
			controleur.slider(Integer.parseInt(parts[1]));
		}	
		//cas d'une commande (play/pause/changement de labyrinthe)
		else{								
			switch(parts[1]){
			case "init":
				controleur.init();
				break;
			case "play" :
				controleur.start();
				break;
			case "pause" :
				controleur.pause();
				break;
			case "chemin" :
				MainServeur.SendMessageClient(clientSocket, "chemin:"+parts[2]+";etat:true");
				controleur.changement(parts[2]);
			default :
				System.out.println("cas non pris en charge");
				break;
			}
		}
	}
	
	/**
	 * la méthode se met en attente d'un message a traiter tant que 
	 * le client est connecté 
	 */
	public void run(){
		try {
			String chaine;
			BufferedReader entree = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			while(true){
				chaine = entree.readLine();
				traiter(chaine);
			 }	 
		} catch(NullPointerException npe){
			System.out.println("Pointeur null  : " + npe.getMessage());
			controleur.finJeu();
		} catch(SocketException se){
			System.out.println("deconnexion d'un client " + se.getMessage());
			controleur.finJeu();
		} catch(IOException e){
			System.out.println("Problème lors du run : " + e.getMessage());
			controleur.finJeu();
		}
	 } 
	
	
	public Thread setEmetteur(Socket so){
		serveurEmetteur = new ServeurEmetteur(so);
        Thread envoi = new Thread(serveurEmetteur);
		return envoi;
	}
	
	/**
	 * @param so
	 * @param identifiant
	 * la méthode crée le jeu une fois que 
	 * le client s'est connecté 
	 */
	public void setGame(Socket so,int identifiant){
		try {
			Maze laby = new Maze(chemin);
			PacmanGame game = new PacmanGame(laby, chemin, clientSocket);
			game.setLabyrinthe(laby);
			controleur = new ControleurGame(game);
			game.setIdentifiant(identifiant);
			
			//demarrage du thread d'envoi 
			setEmetteur(so).start();
			
			//set du game et du controleur 
			serveurEmetteur.game = game;
			
			//envoi du chemin du labyrinthe au client 
			serveurEmetteur.sendMessage("chemin:"+MainServeur.chemin); 
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
