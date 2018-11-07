

/**
 * @author Tarzan
 *
 * Classe permettant de définir les actions d'un agent
 * 
 * direction est un int allant de 0 à 4 et représentant la direction de l'agent
 * (nord, sud, ouest, est ou pause)
 *
 */
public class AgentAction {
	
	/** attributes */
	//Vecteur de déplacement qui sera utile pour réaliser l'action dans le jeu
	private int vx;
	private int vy;
	
	 //Direction
	private int direction;
	
	/** constructor */
	public AgentAction(){}
	
	public AgentAction(int direction){
		if(direction > -1 && direction < 5){
			this.direction = direction;
			
			switch(direction){
			case Maze.NORTH:
				this.vx = 0;
				this.vy = 1;
				break;
			case Maze.SOUTH:
				this.vx = 0;
				this.vy = -1;
				break;
			case Maze.EAST:
				this.vx = 1;
				this.vy = 0;
				break;
			case Maze.WEST:
				this.vx = -1;
				this.vy = 0;
				break;
			case Maze.STOP:
				this.vx = 0;
				this.vy = 0;
				break;	
			}
		}
	}
	
	/** methods */
	public void setDirection(int direction){
		if(direction > -1 && direction < 5){
			this.direction = direction;
			
			switch(direction){
			case Maze.NORTH:
				this.vx = 0;
				this.vy = 1;
				break;
			case Maze.SOUTH:
				this.vx = 0;
				this.vy = -1;
				break;
			case Maze.EAST:
				this.vx = 1;
				this.vy = 0;
				break;
			case Maze.WEST:
				this.vx = -1;
				this.vy = 0;
				break;
			case Maze.STOP:
				this.vx = 0;
				this.vy = 0;
				break;	
			}
		}
	}
	
	public int getDirection(){
		return this.direction;
	}
	
	public int getVx(){
		return this.vx;
	}
	
	public int getVy(){
		return this.vy;
	}
	
	public String toString(){
		return("L'agent regardes vers le " + this.direction + "(" + this.vx + "," + this.vy + ")");
	}
	
}
