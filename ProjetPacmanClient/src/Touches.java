import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 * Classe permettant de savoir quand l'utilisateur appuie sur une des touches liées aux déplacements du Pacman.
 * @author etudiant
 */
public class Touches extends JPanel implements KeyListener {
	//Int contenant la dernière touche prise en compte utilisée.
	public int touchesCliques;

	
	@Override
    public void keyPressed(KeyEvent e) {
		//Cherche quelle touche a été pressée.
		switch (e.getKeyCode())
	   	  {
			//Si c'est la touche "Haut"
	   	  	case KeyEvent.VK_UP :
	   	  		this.touchesCliques = 0;
	   	  		ClientEmetteur.setMessage("direction:" + touchesCliques);
	   	  		break;
	   	  	//Si c'est la touche "Bas"
	   	  	case KeyEvent.VK_DOWN:
	   	  		this.touchesCliques = 1;
	   	  		ClientEmetteur.setMessage("direction:" + touchesCliques);
	   	  		break;
	   	  	//Si c'est la touche "Gauche"
	   	  	case KeyEvent.VK_LEFT:
	   	  		this.touchesCliques = 3;
	   	  		ClientEmetteur.setMessage("direction:" + touchesCliques);
	   	  		break;
	   	  	//Si c'est la touche "Droite"
	   	  	case KeyEvent.VK_RIGHT:
	   	  		this.touchesCliques = 2;
	   	  		ClientEmetteur.setMessage("direction:" + touchesCliques);
	   	  		break;
	   	  	//Si une autre touche a été appuyé, l'utlisateur est estimé avoir voulu arrêté son Pacman. 
	   	  	default:
	   	  		this.touchesCliques = 4;
	   	  		ClientEmetteur.setMessage("direction:" + touchesCliques);
	   	  		break;
	   	  }

    }
	
	/**
	 * Getteur sur la dernière touche prise en compte pressée.
	 * @return : La dernière touche prise en compte pressée.
	 */
	public int getToucheClique(){
		return touchesCliques;
	}

	//Méthode non utilisé devant être implémentée.
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	//Méthode non utilisé devant être implémentée.
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
    