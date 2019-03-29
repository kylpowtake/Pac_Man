import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Classe s"occupant du lancement de l'application. Prend les paramètres données par l'utilisateur et tente de communiquer avec le serveur.
 * @author etudiant
 */
public class MainClient {
	//Le port utilisé pour communiqué avec le serveur.
	static int port;
	//String contenant l'adresse IP du serveur.
	static String serveur;
	//Socket pour la communication entre ce client et le serveur.
	static Socket socket;
	//La vue utilisé pour l'affichage de l'état du jeu à l'utilisateur
	static View view;
	//Vue gérant la connexion de l'utilisateur avec son pseudo et son mot de passe.
	static ViewConnexion viewConnexion;
	
	/**
	 * Méthode chargée du commencement de la communcation avec le serveur.
	 * @param args : Le premier est le numéro de port à utiliser pour communiquer avec le serveur.
	 * 				 Le deuxièeme est l'adresse IP du serveur.
	 */
	public static void main(String[] args) {
		//On surveille qu'on ait bien les deux paramètre nécessaires pour la connexion au serveur, le port à utiliser et l'adresse IP du serveur.
		if(args.length == 2){
			//Le premier argument est le numéro du port.
			port = Integer.parseInt(args[0]);
			//Le deuxième argument est l'adresse IP du serveur.
			serveur = args[1];
		}

		try {
			//On essaye d'ouvrir une connexion entre le client et le serveur.
			socket = new Socket(serveur,port);
		} catch (UnknownHostException e) {
			System.out.println("Problème de host inconnue : " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Problème lors de la socketisation: " + e.getMessage());
		}
		
		//On initialise la partie du client chargée de l'envoie de messages au serveur.
		ClientRecepteur envoie = new ClientRecepteur(socket);
		//On initialise la partie du client chargée de la réception de messages venant du serveur.
		ClientEmetteur reception = new ClientEmetteur(socket);
		//On commence la partie du client chargée de l'envoie de messages au serveur.
		envoie.start();
		//On commmence la partie du client chargée de la réception de messages venant du serveur.
		reception.start();
	    
		//Il faut que l'utilisateur se connecte avec son pseudo et son mot de passe pour pouvoir jouer.
		//Cette vue permet à l'utilisateur soit de se connecter soit de créer un nouveau compte.
		viewConnexion = new ViewConnexion();
	}
	
	/**
	 * Méthode créant une vue à partir d'un String contenant le chemin vers un labyrinthe.
	 * @param chemin : Contient le chemin vers un labyrinthe.
	 */
	public static void setView(String chemin){
			//On crée la vue à partir du labyrinthe.
			view = new View(chemin);
	}
}
