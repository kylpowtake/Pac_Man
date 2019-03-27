/**
 * @author etudiant
 * Classe utilisé pour associé deux valeurs facilement.
 */
public class Double {

	//Le premier object.
	private Object _premier;
	
	//Le deuxième object.
	private Object _second;

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
	public Double(Object premier, Object second){
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

	public Object getPremier() {
		return _premier;
	}

	public void setPremier(Object premier) {
		_premier = premier;
	}

	public Object getSecond() {
		return _second;
	}

	public void setSecond(Object second) {
		_second = second;
	}

}
