/**
 * @author etudiant
 * Classe utilisé pour associé deux valeurs facilement.
 */
public class Double {

	//Le premier int.
	private int _premier;
	
	//Le deuxième int.
	private int _second;

	/**
	 * Initialise le double sans instancier ses valeurs.
	 */
	public Double(){
	}
	
	/**
	 * Initialise le double avec les deux valeurs données.
	 * @param premier : La première valeur.
	 * @param second : La deuxième valeur.
	 */
	public Double(int premier, int second){
		setPremier(premier);
		setSecond(second);
	}
	
	/**
	 * Instancie le double avec les valeurs du double a copier passer en paramètre.
	 * @param doubleACopier : Double dont les valeurs doivent être copier dans le double actuel.
	 */
	public Double(Double doubleACopier){
		_premier = doubleACopier.getPremier();
		_second = doubleACopier.getSecond();
	}

	public int getPremier() {
		return _premier;
	}

	public void setPremier(int premier) {
		_premier = premier;
	}

	public int getSecond() {
		return _second;
	}

	public void setSecond(int second) {
		_second = second;
	}

}
