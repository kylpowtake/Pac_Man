import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;




public class GetAction extends AbstractAction {
	private ViewConnexion fenetre;
 
	public GetAction(ViewConnexion fenetre, String texte){
		super(texte);
 
		this.fenetre = fenetre;
	}
 
	public void actionPerformed(ActionEvent e) { 
		String textePseudo = fenetre.getTextePseudo().getText();
		String texteMDP = fenetre.getTexteMDP().getText();
		if(textePseudo.equals("") || textePseudo.equals("null")){
			fenetre.setLabelResultat("Il y a besoin d'un pseudo");
		} else 	if(texteMDP.equals("") || texteMDP.equals("null")){
			fenetre.setLabelResultat("Il y a besoin d'un mot de passe");
		} else {
			String combinaison = "pseudo:" + textePseudo +  ";MDP:" + texteMDP;
			System.out.println("On arrive à la combinaison");
			ClientEmetteur.setMessage(combinaison);
		}
	} 
}