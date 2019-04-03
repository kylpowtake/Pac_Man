import java.util.ArrayList;
import java.util.Collections;


public class StructureUltime {

	//La grille contenant la présence des batiments.
	private ArrayList<ArrayList<Boolean>> _grille;
	
	//Contient les batiments et leurs tailles.
	private ArrayList<Double> _batiments;
	
	//Contient les positions des batiments dans la grille (le x et le y).
	private ArrayList<Double> _positions;
	
	//La taille de la grille.
	private Double _tailleGrille;
	
	/**
	 * Initialise la structure et ses valeurs sans les instancier.
	 */
	public StructureUltime(){
		setGrille(new ArrayList<ArrayList<Boolean>>());
		setBatiments(new ArrayList<Double>());
		setPositions(new ArrayList<Double>());
	}

	public StructureUltime(ArrayList<Double> batiments){
		setGrille(new ArrayList<ArrayList<Boolean>>());
		setBatiments(batiments);
		setPositions(new ArrayList<Double>());
	}

	
	/**
	 * Instancie la structure avec les valeurs données.
	 * @param grille : La grille avec la précense des batiments.
	 * @param batiments : Les batiments et leurs tailles.
	 * @param positions : Les positions des batimens dans la grille.
	 */
	public StructureUltime(ArrayList<ArrayList<Boolean>> grille, ArrayList<Double> batiments, ArrayList<Double> positions){
		setGrille(grille);
		setBatiments(batiments);
		setPositions(positions);
	}
	
	/**
	 * Instancie les valeurs de la structure à partir de celles de la structure passé en paramètre.
	 * @param structureACopier : Contient les donées à copier.
	 */
	public StructureUltime(StructureUltime structureACopier){
		_grille = structureACopier.getGrille();
		_batiments = structureACopier.getBatiments();
		_positions = structureACopier.getPositions();
	}

	public ArrayList<ArrayList<Boolean>> getGrille() {
		return _grille;
	}

	public void setGrille(ArrayList<ArrayList<Boolean>> grille) {
		this._grille = grille;
	}

	public ArrayList<Double> getBatiments() {
		return _batiments;
	}

	public void setBatiments(ArrayList<Double> batiments) {
		this._batiments = batiments;
	}

	public ArrayList<Double> getPositions() {
		return _positions;
	}

	public void setPositions(ArrayList<Double> positions) {
		this._positions = positions;
	}

	public Double getTailleGrille() {
		return _tailleGrille;
	}

	public void setTailleGrille(Double tailleGrille) {
		this._tailleGrille = tailleGrille;
	}
	
	
	/**
	 * Méthode rajoutant le batiment donnée dans la grille à la position donnée.
	 * @param batiment
	 * @param position
	 * @return
	 */
	public boolean PlacageBatimentPosition(Double batiment, Double position){
		if(EstPlacable(batiment, position)){
			Double positionFuture = new Double(position.getPremier() + batiment.getPremier(), position.getSecond() + batiment.getSecond());
			return RemplieGrilleParBatiment(position, positionFuture);
		} else {
			return false;
		}
	}
	
	/**
	 * Méthode vérifiant que le batiment donnée puisse être placé à la position donnée.
	 * @param batiment : Le batiment à placer.
	 * @param position : La position où placer le batiment.
	 * @return : La possibilité de placer le batiment donnée à la position donnée.
	 */
	public boolean EstPlacable(Double batiment, Double position){
		//La future position est celle de fin du rectangle
		Double positionFuture = new Double(position.getPremier() + batiment.getPremier(), position.getSecond() + batiment.getSecond());
		//On test si les cases voulues sont vides.
		if(EstVide(position, positionFuture)){
			//Si les cases sont vides on retourne vrai.
			return true;
		} else {
			//Si les cases voulues ne sont pas vides, on retourne faux.
			return false;
		}
	}
	
	/**
	 * Méthode vérifiant que chaque case du rectangle formé par les deux extrémités données est bien vide.
	 * @param positionDebut : Première extrémité du rectangle à vérifié.
	 * @param positionFin : Deuxième extrémité du rectangle à vérifié. 
	 * @return : la vérification que chaque case est vide ou non.
	 */
	public boolean EstVide(Double positionDebut, Double positionFin){
		//On va du x de la première position au x de la deuxième position
		for(int i = (int) positionDebut.getPremier(); i < (int) positionFin.getPremier(); i++){
			//On va du y de la première position au y de la deuxième position			
			for(int j = (int) positionDebut.getSecond(); j < (int) positionFin.getSecond(); j++){
				if(i > 0 && j > 0 && i < (int) _tailleGrille.getPremier() && j < (int) _tailleGrille.getSecond()){
					//Si la case n'est pas vide, on retourne faux.
					if(_grille.get(i).get(j)){
						return false;
					}
				} else {
					//Si les coordonnées sont hors limite on renvoie faux.
					return false;
				}
			}
		}
		return true;
	}
	

	/**
	 * Méthode rempissant une partie de la grille où on ajoute un batiment représenté par les deux position passés en paramètre.
	 * @param positionDebut : Partie Haute/Gauche du batiment.
	 * @param positionFin : Partie Basse/Droite du batiment. 
	 * @return Si la méthode c'est bien fini.
	 */
	public boolean RemplieGrilleParBatiment(Double positionDebut, Double positionFin){
		//On va du x de la première position au x de la deuxième position
		for(int i = (int) positionDebut.getPremier(); i < (int) positionFin.getPremier(); i++){
			//On va du y de la première position au y de la deuxième position			
			for(int j = (int) positionDebut.getSecond(); j < (int) positionFin.getSecond(); j++){
				if(i > 0 && j > 0 && i < (int) _tailleGrille.getPremier() && j < (int) _tailleGrille.getSecond()){
					//On remplie la case.
					_grille.get(i).set(j, true);
				} else {
					//Si les coordonnées sont hors limite on renvoie faux.
					return false;
				}
			}
		}
		//Si tous ce passe bien on retourne vrai.
		return true;
	}
	
	/**
	 * tri les bâtiments par surface décroissante
	 */
	public void OrganizedBySurface(){
		for(int i = 0; i < _batiments.size(); i++){
			
			int surface = ReturnSurface(_batiments.get(i));
			int indiceBatiment = i;
			
			for(int j = i; j < _batiments.size(); j++){
				int surfaceTemp = ReturnSurface(_batiments.get(j));
				if(surface < surfaceTemp){
					indiceBatiment = j;
				}
			}
			
			Double batimentTemp = _batiments.get(i);	
			
			_batiments.set(i,_batiments.get(indiceBatiment));
			_batiments.set(indiceBatiment,batimentTemp);
		}
	}
	
	/**
	 * tri les bâtiments par encombrement décroissant 
	 */
	public void OrganizedBySize(){
		for(int i = 0; i< _batiments.size();i++){
			
			int size = ReturnSize(_batiments.get(i));
			int indiceBatiment = i;
			
			for(int j=i; j < _batiments.size(); j++){
				int sizeTemp = ReturnSize(_batiments.get(j));
				if(size < sizeTemp){
					indiceBatiment = j;
				}
			}
			
			Double batimentTemp = _batiments.get(i);
			
			_batiments.set(i, _batiments.get(indiceBatiment));
			_batiments.set(indiceBatiment,batimentTemp);
		}
	}
	
	/**
	 * tri les bâtiments aléatoirement
	 */
	public void OrganisedRandom(){
		Collections.shuffle(_batiments);
	}
	
	
	/**
	 * retourne la surface d'un bâtiment 
	 * @param batiment
	 * @return surface
	 */
	public int ReturnSurface(Double batiment){
		int surface = batiment.getPremier()*batiment.getSecond();
		return surface;
	}
	
	/**
	 * retourne l'encombrement d'un bâtiment
	 * @param batiment
	 * @return size
	 */
	public int ReturnSize(Double batiment){
		int size = batiment.getPremier()+batiment.getSecond();
		return size;
	}
}
