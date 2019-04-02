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
		game.PartieEnvoieDonnerGameOver();
		this.game.changement(chemin);
	}

	@Override
	public void slider(int valeur) {
		this.game.setNbToursSecondes(valeur);
	}

	@Override
	public void init() {
		this.game.init();
	}

	@Override
	public void finJeu(){
		game.finThread = true;
		game.setIsRunnin(false);
		Bdd.sendScore(game.getIdentifiant(),game.getNbPoints(), game.getNbFantomesManges(), game.getCapsulesMangees(), game.getPacGommesMangees(), game.getMapsEffectuees(), game.getNbTours());
		
	}

}
