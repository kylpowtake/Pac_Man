import java.util.ArrayList;
import java.util.Collections;


public class StructureUltime {

	//La grille contenant la présence des batiments.
	private ArrayList<ArrayList<Boolean>> _grille;
	
	//Contient les batiments et leurs tailles.
	private ArrayList<Double> _batiments;
	
	//La taille de la grille.
	private Double _tailleGrille;
	
	//La position des bâtiments une fois placé.
	private Double[] _positionsBatiments;
		
	/**
	 * Instancie la structure avec les valeurs données.
	 * @param grille : La grille avec la précense des batiments.
	 * @param batiments : Les batiments et leurs tailles.
	 * @param positions : Les positions des batimens dans la grille.
	 */
	public StructureUltime(ArrayList<Double> batiments, Double tailleGrille){
		setGrille(new ArrayList<ArrayList<Boolean>>());
		setTailleGrille(tailleGrille);
		this.InstanciationGrille();
		setBatiments(batiments);
		setPositionsBatiments(new Double[batiments.size()]);
		this.InstanciationPositionsBatiments();
	}


	
	/**
	 * Instancie la structure avec les valeurs données.
	 * @param grille : La grille avec la précense des batiments.
	 * @param batiments : Les batiments et leurs tailles.
	 * @param positions : Les positions des batimens dans la grille.
 	 * @param tailleGrille : La taille en longueur et en largeur de la grille.
	 */
	public StructureUltime(ArrayList<ArrayList<Boolean>> grille, ArrayList<Double> batiments, Double tailleGrille){
		setGrille(grille);
		setTailleGrille(tailleGrille);
		InstanciationGrille();
		setBatiments(batiments);
		setPositionsBatiments(new Double[batiments.size()]);
		this.InstanciationPositionsBatiments();
	}
	
	
	
	/**
	 * Instancie les valeurs de la structure à partir de celles de la structure passé en paramètre.
	 * @param structureACopier : Contient les donées à copier.
	 */
	public StructureUltime(StructureUltime structureACopier){
		_grille = structureACopier.getGrille();
		_batiments = structureACopier.getBatiments();
		_positionsBatiments = structureACopier.getPositionsBatiments();
		_tailleGrille = structureACopier.getTailleGrille();
	}
	
	
	/**
	 * Instancie entièrement la grille à faux (vide) à partir de la taille voulue de la grille.
	 */
	public void InstanciationGrille(){
		for(int i = 0; i < getTailleGrille().getPremier(); i++){
			_grille.add(new ArrayList<Boolean>());
			for(int j = 0; j < getTailleGrille().getSecond(); j++){
				_grille.get(i).add(false);
			}
		}
	}
	
	public void Reinit(){
		for(int i = 0; i < _grille.size(); i++){
			for(int j = 0; j < _grille.get(i).size(); j++){
			_grille.get(i).set(j, false);
			}
		}
	}
	
	public void ReinitUltime(){
		Reinit();
		InstanciationPositionsBatiments();
	}
	
	public void InstanciationPositionsBatiments(){
		Double position = new Double(-1,-1);
		for(int i = 0; i < _positionsBatiments.length; i++){
			_positionsBatiments[i] = position;
		}
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
	public Double getTailleGrille() {
		return _tailleGrille;
	}

	public void setTailleGrille(Double tailleGrille) {
		this._tailleGrille = tailleGrille;
		this.InstanciationGrille();
	}	
	public void setTailleGrille(){
		Double tailleGrille = new Double();
		tailleGrille.setPremier(_grille.size());
		tailleGrille.setSecond(_grille.get(0).size());
		this._tailleGrille = tailleGrille;
	}
	public Double[] getPositionsBatiments() {
		return _positionsBatiments;
	}

	public void setPositionsBatiments(Double[] positionsBatiments) {
		this._positionsBatiments = positionsBatiments;
	}

	/**
	 * Méthode rajoutant le batiment donnée dans la grille à la position donnée.
	 * @param batiment : Le batiment à ajouter.
	 * @param position : La position où ajouter le bâtiment.
	 * @Param indiceBatiment : Le batiment qui est placé.
	 * @return : La réussite de la méthode.
	 */
	public boolean PlacageBatimentPosition(Double batiment, Double position, int indiceBatiment){
		//On regarde si il est plaçable.
		if(EstPlacable(batiment, position)){
			Double positionFuture = new Double(position.getPremier() + batiment.getPremier(), position.getSecond() + batiment.getSecond());
			//On le place
			if(RemplieGrilleParBatiment(position, positionFuture)){
				//On ajoute sa position au tableau d'enregistrement de batiments.
				this._positionsBatiments[indiceBatiment] = position;
				return true;
			} else {
				System.out.println("On passe dans cdscuihd" + positionFuture.toString() + "   " + position.toString());
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	/**
	 * Méthode vérifiant si le batiment dont l'indice est passé en paramètre est déjà placé ou non.
	 * @param indiceBatiment : L'indice du batiment à tester.
	 * @return : Si le batiment est déjà placer ou non.
	 */
	public boolean BatimentPlacer(int indiceBatiment){
		if(_positionsBatiments[indiceBatiment].getPremier() != -1 && _positionsBatiments[indiceBatiment].getSecond() != -1 ){
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * Méthode retournant le premier bâtiment trouver n'étant pas placer et pouvant être placer à la position donnée.
	 * @param position : La position ou un bâtiment doit être placé.
	 * @return : l'indice du batiment pouvant être placé et si aucun n'est trouvé renvoie -1.
	 */
	public int ChercheBatimentPlacablePosition(Double position){
		for(int i = 0; i < _positionsBatiments.length; i++){
			if(!BatimentPlacer(i) && EstPlacable(_batiments.get(i), position)){
				return i;
			}
		}
		return -1;
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
				if(i >= 0 && j >= 0 && i < (int) _tailleGrille.getPremier() && j < (int) _tailleGrille.getSecond()){
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
	 * Méthode remplissant une partie de la grille où on ajoute un batiment représenté par les deux position passés en paramètre.
	 * @param positionDebut : Partie Haute/Gauche du batiment.
	 * @param positionFin : Partie Basse/Droite du batiment. 
	 * @return Si la méthode c'est bien fini.
	 */
	public boolean RemplieGrilleParBatiment(Double positionDebut, Double positionFin){
		//On vérifie que chaque coordonnée n'est pas hors limite.
		if(positionDebut.getPremier() >= 0 && positionDebut.getSecond() >= 0 &&
				positionFin.getPremier() >= 0 && positionFin.getSecond() >= 0 &&
				positionFin.getPremier() <= (int) _tailleGrille.getPremier() && positionFin.getSecond() <= (int) _tailleGrille.getSecond() &&
				positionDebut.getPremier() <= (int) _tailleGrille.getPremier() && positionDebut.getSecond() <= (int) _tailleGrille.getSecond()){
			//On va du x de la première position au x de la deuxième position
			for(int i = (int) positionDebut.getPremier(); i < (int) positionFin.getPremier(); i++){
				//On va du y de la première position au y de la deuxième position			
				for(int j = (int) positionDebut.getSecond(); j < (int) positionFin.getSecond(); j++){
						//On remplie la case.
						_grille.get(i).set(j, true);
				}
			}
		} else {
			//Une des coordonées est hors limite.
			return false;
		}
		//Si tous ce passe bien on retourne vrai.
		return true;
	}
	
	/**
	 * trie les bâtiments par surface decroissante
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
	
	public void Affichage(){
		System.out.println("Taille de la grille : " + _tailleGrille.getPremier() + "   " + _tailleGrille.getSecond() + "\n");
		AffichageBatiments();
		System.out.println("\n");
		AffichagePositions();
		System.out.println("\n");
		AffichageGrille();
		System.out.println("\n");
	}
	
	public void AffichagePositions(){
		for(int i = 0; i < _positionsBatiments.length; i++){
			System.out.println("Batiment numéro : " + i + "   de position : " + _positionsBatiments[i].getPremier() + "   " + _positionsBatiments[i].getSecond());
		}
	}
	
	public void AffichageBatiments(){
		for(int i = 0; i < _batiments.size(); i++){
			System.out.println("Batiment numéro : " + i + "   de taille : " + _batiments.get(i).getPremier() + "   " + _batiments.get(i).getSecond());
		}
	}
	
	public void AffichageGrille(){
		for(int i = 0; i < _grille.size(); i++){
			for(int j = 0; j < _grille.get(i).size(); j++){
				if(_grille.get(i).get(j)){
					System.out.print("x ");
				} else {
					System.out.print("  ");				
				}
			}
			System.out.println("\n");
		}
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
	
	/**
	 * Méthode renvoyant la surface remplie de la grille.
	 * @return surface rempllie de la grille.
	 */
	public int ReturnSurfaceGrille(){
		int surfaceGrille = 0;
		for(int i = 0; i < _grille.size(); i++){
			for(int j = 0; j < _grille.get(i).size(); j++){
				if(_grille.get(i).get(j)){
					surfaceGrille++;
				}
			}
		}
		return surfaceGrille;
	}
}
