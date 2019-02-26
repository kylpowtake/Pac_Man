package model;

public interface InterfaceControleur {
	public void init();
	public void start();
    public void pause();
    public void finJeu();
    public void slider(int valeur);
    public void changement(String chemin);
}
