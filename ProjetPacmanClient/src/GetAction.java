import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;



/**
 * Classe gérant la conformité des pseudo et mot de passe de l'utilisateur voulant se connecter.
 * @author etudiant
 */
public class GetAction extends AbstractAction {
	//La fenêtre de connexion de l'utilisateur à son compte.
	private ViewConnexion fenetre;
 
	/**
	 * Initialise le GetAction avec la fenêtre de connexion et le 
	 * @param fenetre
	 * @param texte
	 */
	public GetAction(ViewConnexion fenetre, String texte){
		super(texte);
		this.fenetre = fenetre;
	}
	
	/**
	 * Méthode invoqué quand l'utilisateur essaie de se conecter à son compte.
	 * Test si son pseudo et son mot de passe ne sont pas nulls.
	 * Si ils ne le sont pas, utilise la méthode de ClientEmetteur pour envoyer la tentative de connexion au serveur.
	 */
	public void actionPerformed(ActionEvent e) {
		//Cherche le pseudo de l'utilisateur.
		String textePseudo = fenetre.getTextePseudo().getText();
		//cherchele mot de passe de l'utilisateur.
		String texteMDP = fenetre.getTexteMDP().getText();
		//Test si le pseudo est null.
		if(textePseudo.equals("") || textePseudo.equals("null")){
			fenetre.setLabelResultat("Il y a besoin d'un pseudo");
		}
		//Test si le mot de passe est null.
		else if(texteMDP.equals("") || texteMDP.equals("null")){
			fenetre.setLabelResultat("Il y a besoin d'un mot de passe");
		} else {
			//Formatte le pseudo et le mot de passe en un String adapté pour l'envoie au serveur.
			String combinaison = "pseudo:" + textePseudo +  ";MDP:" + texteMDP;
			//Envoie au serveur le pseudo et el mot de passe.
			ClientEmetteur.setMessage(combinaison);
		}
	} 
}