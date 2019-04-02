import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Classe s'occupant de recevoir les informations venant du serveur.
 * @author etudiant
 *
 */
public class ClientRecepteur extends Thread{
	//La socket pour communiquer (ici, recevoir des messages)  avec le serveur. 
	Socket socket;
	
	/**
	 * Constucteur de ClientRecepteur initialisant la socket permettant de communiquer avec le serveur.
	 * @param socket : Socket à utlisé pour la communication avec le serveur.
	 */
	public ClientRecepteur(Socket socket){
		this.socket = socket;
	}

	/**
	 * Méthode s'occupant d'attendre qu'un message arrive du serveur et le redirigeant vers la méthode gérant les messages.
	 */
	public void run(){
		try{
			//Initialisation du BufferedReader avec la socket liée au serveur.
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//Le String allant contenir les messages du serveur.
			String message;
			while(true){
				//On attend qu'un message venant du serveur arrive et le lit.
				message = reader.readLine();
				//On appelle la fonction chargée de s'occuper des messages reçus du serveur.
				Gestion(message);
			}
		} catch(IOException ioe){
			System.out.println("Problème survenu : " + ioe.getMessage());
		}
	}
	
	
	
	/**
	 * Méthode appellant la vrai méthode se chargant de s'occuper du message reçu du serveur.	
	 * @param message : message reçu du serveur.
	 */
	public void Gestion(String message){
		//Appel de la méthode se chargant des messages reçus du serveur.
		GestionReceptionMessage.GestionMessageGlobal(message);
	}
}
