import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Classe s'occupant d'envoyer les informations au serveur.
 * @author Kylian
 *
 */
public class ClientEmetteur extends Thread{
	Socket socket;
	
	private static String message = "";
	
	private static String messageAnterieur = "";
	
	public ClientEmetteur(Socket socket){
		this.socket = socket;
	}
	
	
	public void run(){
		try {
			PrintWriter sortie;
			sortie = new PrintWriter(socket.getOutputStream(), true);
			boolean fin = false;
			
			while(!fin){

				if(message!="null" && message!="" && !message.isEmpty() && messageAnterieur != message){
					sortie.println(message);
					messageAnterieur = message;
					System.out.println("Message envoyé : " + message);
				}
				sortie.flush();
			}
			socket.close();
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
	
	
	public static String getMessage(){
		return message;
	}
	
	public static void setMessage(String messageNew){
		message = messageNew;
	}
}
