import java.util.Vector;


public class Etat {
	private Vector<String> 	_labels; 
	private Vector<Etat>	_etats;
	
	public Etat(){
		_labels = new Vector<String>();
		_etats = new Vector<Etat>();
	}
	
	public Etat(Vector<String> labels, Vector<Etat> etats){
		_labels = labels;
		_etats = etats;
	}
	
	public Vector<String> getLabels(){
		return _labels;
	}
	
	public Vector<Etat> getEtats(){
		return _etats;
	}
	
	public void setLabels(Vector<String> labels){
		_labels = labels;
	}
	
	public void setEtats(Vector<Etat> etats ){
		_etats = etats;
	}
	
	/**
	 * Ajoutes les états liées à tous les mots données.
	 * @param mots : Lesmots constituants l'échantillon.
	 */
	public void InitialiserAccepteurMots(Vector<String> mots){
		for(int i = 0; i < mots.size(); i++){
			InitialiserAccepterLettres(mots.get(i));
		}
	}
	
	/**
	 * Ajoute les états liées au mot données si non existant.
	 * @param mot : un des mots de l'échantillon.
	 */
	public void InitialiserAccepterLettres(String mot){
		Etat temp = new Etat();
		for(int i = 0; i < mot.length(); i++){
			//Si un label existe déjà pour un caractère, on y va.
			//Sinon on l'ajoute.
			System.out.println("i : " + i + " mot : " + mot + " sous mot : " + mot.substring(i,i+1));
			if(_labels.contains(mot.substring(i,i+1))){
				//On avance dans le caractère en bougeant d'étape.
				//Ici nous cherchons son indice.
				int indice = _labels.indexOf(mot.substring(i,i+1));
				//On utilise son indice pour savoir l'état associé.
				temp = _etats.get(indice);
			} else {
				//On ajoute le caractère.
				_labels.add(mot.substring(i, i+1));
				//On ajoute l'état vide associé au caractère.
				Etat nouveauEtat = new Etat();
				_etats.add(nouveauEtat);
				//Et on y bouge.
				temp = _etats.get(_etats.size()-1);
			}
		}
	}
	
}
