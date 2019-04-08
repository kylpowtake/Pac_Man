import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Classe s'occupant d'envoyer les informations au serveur.
 * @author etudiant
 *
 */
public class ClientEmetteur extends Thread{
	//La socket pour communiquer (ici, envoyer des messages)  avec le serveur.
	Socket socket;

	/*
	 * Le String contenant le message a envoyé au serveur.
	 * Son changement active son envoie.
	 */
	private static String message = "";
	
	//Le String utilisé pour sauvegarder le message précédent pour vérifier un changement de message.
	private static String messageAnterieur = "";
	
	/**
	 * Constructeur de ClientEmetteur initialisant la socket.
	 * @param socket : Socket à utlisé pour la communication avec le serveur.
	 */
	public ClientEmetteur(Socket socket){
		this.socket = socket;
	}
	
	/**
	 * Méthode s'occupant de tester si un message doit être envoyé au serveur et si oui l'envoie.
	 */
	public void run(){
		try {
			//Le printWriter pour la communication avec le serveur.
			PrintWriter sortie;
			//On l'initialise avec la socket.
			sortie = new PrintWriter(socket.getOutputStream(), true);	
			//On n'arrête jamais, si on a plus besoin d'envoyer de messages, on ne joue plus.
			while(true){
				//Si message n'est pas nul et n'est pas égal au message précédent, il doit être envoyé.
				if(message!="null" && message!="" && !message.isEmpty() && messageAnterieur != message){
					//On envoie le message au serveur.
					sortie.println(message);
					//On change le message précédent avec le nouveau message précédent.
					messageAnterieur = message;
					//On affiche le message envoyé au serveur.
					System.out.println("Message envoyé : " + message);
				}
				//On flush la sortie.
				sortie.flush();
			}
		} catch(NullPointerException npe){
			System.out.println("Problème : " + npe.getMessage());
		} catch (IOException e) {
			System.out.println("Problème : " + e.getMessage());
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				System.out.println("Problème apparu lors de la fermeture de socket : " + e.getMessage());
			}
		}
	}
	
	/**
	 * Renvoie le dernier message envoyer ou à envoyer au serveur..
	 * @return : dernier message.
	 */
	public static String getMessage(){
		//Renvoie le dernier message envoyer ou à envoyer au serveur..
		return message;
	}
	
	/**
	 * Change le message à envoyer.
	 * @param messageNew : Nouveau messsage à envoyer.
	 */
	public static void setMessage(String messageNew){
		message = messageNew;
	}
}
