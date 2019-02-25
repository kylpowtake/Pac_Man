import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class MainClient {

	static int port;
	
	static String serveur;
	
	static Socket socket;
	
	static View view;
	
	static ViewConnexion viewConnexion;
	
	public static void main(String[] args) {
		if(args.length == 2){
			port = Integer.parseInt(args[0]);
			serveur = args[1];
		}

		try {
			socket = new Socket(serveur,port);
		} catch (UnknownHostException e) {
			System.out.println("Problème de host inconnue : " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Problème lors de la socketisation: " + e.getMessage());
		}
		
		
		ClientRecepteur envoie = new ClientRecepteur(socket);
		ClientEmetteur reception = new ClientEmetteur(socket);
		envoie.start();
		reception.start();
	    
		viewConnexion = new ViewConnexion();
	}
	
	public static void setView(String chemin){
			view = new View(chemin);
	}
}
