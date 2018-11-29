import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Touches extends JPanel implements KeyListener {

	public int toucheClique = 4;

	
	@Override
    public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode())
	   	  {
	   	  	case KeyEvent.VK_UP :
	   	  		this.toucheClique = 0;
	   	  		System.out.println("UP key pressed  " + toucheClique);
	   	  		break;
	   	  	case KeyEvent.VK_DOWN:
	   	  		this.toucheClique = 1;
	   	  		System.out.println("DOWN key pressed  " + toucheClique);
	   	  		break;
	   	  	case KeyEvent.VK_LEFT:
	   	  		this.toucheClique = 3;
	   	  		System.out.println("LEFT key pressed  " + toucheClique);
	   	  		break;
	   	  	case KeyEvent.VK_RIGHT:
	   	  		this.toucheClique = 2;
	   	  		System.out.println("RIGHT key pressed  " + toucheClique);
	   	  		break;
	   	  	default:
	   	  		this.toucheClique = 4;

	   	  }

    }
	
	public int getToucheClique(){
		return this.toucheClique;
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
    