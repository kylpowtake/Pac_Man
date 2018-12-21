
public class ControleurGame implements InterfaceController{
	
	private View view;
	
	public ControleurGame(PacmanGame game) { 	
        view = new View(this,game);
    }
	
	@Override
	public void start(){
		this.view.game.launch();
    }
	
	@Override
    public void restart(){
		this.view.game.init();    	
    }
	
	@Override
    public void step(){
		this.view.game.step();    	
    }
	
	@Override
    public void pause(){
		this.view.game.stop();
    }
	
	@Override
	public void changement(String chemin){
		this. view.game.changement(chemin);
	}
	
	public View GetView(){
		return this.view;
	}
	
	public void SetView(View view){
		this.view = view;
	}
}
