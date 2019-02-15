package model;

public class ControleurGame implements InterfaceControleur{
	
	private Game game;
	
	public ControleurGame(PacmanGame game) { 
		this.game = game;
    }
	
	@Override
	public void start(){
		this.game.play();
    }
	
	@Override
    public void restart(){
		this.game.init();    	
    }
	
	@Override
    public void step(){
		this.game.step();    	
    }
	
	@Override
    public void pause(){
		this.game.stop();
    }
	
	@Override
	public void changement(String chemin){
		this. game.changement(chemin);
	}

	public Game getGame(){
		return this.game;
	}
}
