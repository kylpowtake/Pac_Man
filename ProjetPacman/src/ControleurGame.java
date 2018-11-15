
public class ControleurGame implements InterfaceController{
	
	private PacmanGame game;
	private View view;
	
	public ControleurGame(PacmanGame game) { 	
    	this.game = game; 	
        view = new View(this,game);
    }
	
	@Override
	public void start(){
		this.game.launch();
		System.out.println("start");
    }
	
	@Override
    public void restart(){
		this.game.init();
		System.out.println("restart");
    	
    }
	
	@Override
    public void step(){
		this.game.step();
		System.out.println("step");
    	
    }
	
	@Override
    public void pause(){
		this.game.stop();
		System.out.println("stop");
    }
	
	@Override
	public void changement(String chemin){
		this.game.changement(chemin);
	}
	
	public View GetView(){
		return this.view;
	}
	
	public void SetView(View view){
		this.view = view;
	}

	@Override
	public void SetGame(Game game) {
		this.game = (PacmanGame) game;
		
	}

	@Override
	public Game GetGame() {
		return this.game;
	}
}
