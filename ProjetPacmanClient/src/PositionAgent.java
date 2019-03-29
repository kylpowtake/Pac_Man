
/**
 * Classe représentant la position et la direction d'un agent.
 * @author etudiant
 */
public class PositionAgent {
	//La première valeur d'emplacement de l'agent.
	private int x;
	//La deuxième valeur d'emplacement de l'agent.
	private int y;
	//La valeur représentant la direction vers laquelle est dirigé l'agent, utilisé lors de l'affichage de l'agent.
	private int dir;
	
	/**
	 * Instanciation de la position d'un agent.
	 * @param x : Première valeur de l'emplacement d'un agent.
	 * @param y : Deuxième valeur de l'emplacement d'un agent.
	 * @param dir : Direction vers laquelle l'agent est dirigé l'agent.
	 */
	public PositionAgent(int x, int y, int dir) {
		this.x=x;
		this.y=y;
		this.dir=dir;
	}
	
	/**
	 * Instanciation de la position d'un agent par copie d'une autre position.
	 * @param position : Position copiée pour instancier la nouvelle position.
	 */
	public PositionAgent(PositionAgent position){
		this.x = position.getX();
		this.y = position.getY();
		this.dir = position.getDir();
	}

	/**
	 * Getteur de la première valeur d'emplacement de l'agent.
	 * @return : La première valeur d'emplacement de l'agent.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Setteur de la première valeur d'emplacement de l'agent.
	 * @param x : Nouvelle valeur de la première valeur de l'emplacement de l'agent.
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Getteur de la deuxième valeur d'emplacement de l'agent.
	 * @return : La deuxième valeur d'emplacement de l'agent.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setteur de la deuxième valeur d'emplacement de l'agent.
	 * @param y : Nouvelle valeur de la deuxième valeur de l'emplacement de l'agent.
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Getteur de la direction de l'agent.
	 * @return : La direction de l'agent.
	 */
	public int getDir() {
		return dir;
	}

	/**
	 * Setteur de la direction de l'agent.
	 * @param dir : Nouvelle valeur de la direction de l'agent.
	 */
	public void setDir(int dir) {
		this.dir = dir;
	}
	
}