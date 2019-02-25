import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Classe s'occupant de recevoir les informations venant du serveur.
 * @author Kylian
 *
 */
public class ClientRecepteur extends Thread{
	Socket socket;
	
	public ClientRecepteur(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		try{
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message;
			while(true){
				message = reader.readLine();
				Gestion(message);
			}
		} catch(IOException ioe){
			System.out.println("Problème survenu : " + ioe.getMessage());
		}
	}
	
	
	
	/*
	 * Cherche la méthode approprié pour le message donné.
	 */
	public void Gestion(String message){
		GestionReceptionMessage.GestionMessageGlobal(message);
	}
}
