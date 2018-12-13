import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;



//a redefinir par un partern fabrique pour le mode multi


public class Touches extends JPanel implements KeyListener {

	public int[] touchesCliques = {4,4,4,4};

	
	@Override
    public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode())
	   	  {
	   	  	case KeyEvent.VK_UP :
	   	  		this.touchesCliques[0] = 0;
	   	  		break;
	   	  	case KeyEvent.VK_DOWN:
	   	  		this.touchesCliques[0] = 1;
	   	  		break;
	   	  	case KeyEvent.VK_LEFT:
	   	  		this.touchesCliques[0] = 3;
	   	  		break;
	   	  	case KeyEvent.VK_RIGHT:
	   	  		this.touchesCliques[0] = 2;
	   	  		break;
	   	  	case KeyEvent.VK_Z :
	   	  		this.touchesCliques[1] = 0;
	   	  		break;
	   	  	case KeyEvent.VK_Q :
	   	  		this.touchesCliques[1] = 3;
	   	  		break;
	   	  	case KeyEvent.VK_S :
		   	  	this.touchesCliques[1] = 1;
	   	  		break;
	   	  	case KeyEvent.VK_D :
		   	  	this.touchesCliques[1] = 2;
	   	  		break;
	   	  	default:
	   	  		this.touchesCliques[0] = 4;
	   	  		this.touchesCliques[1] = 4;
	   	  		break;
	   	  }

    }
	
	public int[] getToucheClique(){
		return touchesCliques;
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
    