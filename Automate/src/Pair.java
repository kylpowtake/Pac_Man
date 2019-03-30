
public class Pair {
	private Etat _etat;
	private String _label;
	
	Pair(Etat etat, String label){
		setEtat(etat);
		setLabel(label);
	}
	
	public Etat getEtat() {
		return _etat;
	}
	public void setEtat(Etat etat) {
		_etat = etat;
	}
	public String getLabel() {
		return _label;
	}
	public void setLabel(String label) {
		_label = label;
	}
	
	
	
}
