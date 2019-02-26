package model;

public class ControleurGame implements InterfaceControleur{
	
	private Game game;
	
	public ControleurGame(PacmanGame game) { 
		this.game = game;
    }
	
	public Game getGame(){
		return this.game;
	}
	
	@Override
	public void start(){
		this.game.play();
    }
	
	@Override
    public void pause(){
		this.game.stop();
    }
	
	@Override
	public void changement(String chemin){
		this. game.changement(chemin);
	}

	@Override
	public void slider(int valeur) {
		this.game.setNbToursSecondes(valeur);
		
	}	
}
