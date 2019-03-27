import java.util.ArrayList;


public class StructureUltime {

	//La grille contenant la présence des batiments.
	private ArrayList<ArrayList<Boolean>> _grille;
	
	//Contient les batiments et leurs tailles.
	private ArrayList<Double> _batiments;
	
	//Contient les positions des batiments dans la grille (le x et le y).
	private ArrayList<Double> _positions;
	
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
}
