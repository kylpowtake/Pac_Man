import java.util.ArrayList;
import java.util.Vector;


public class Etat {
	private Vector<String> 	_labels; 
	private Vector<Etat>	_etats;
	private boolean fin;
	private int id;
	private boolean visite = false;
	private static int id_total = 0;
	
	public Etat(){
		_labels = new Vector<String>();
		_etats = new Vector<Etat>();
		fin = false;
		id = id_total+1;
		id_total++;
	}
	
	public Etat(Vector<String> labels, Vector<Etat> etats){
		_labels = labels;
		_etats = etats;
		fin = false;
		id = id_total+1;
		id_total++;
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
	 * Méthode modifiant e1 en lui ajoutant les valeurs de e2.
	 * @param e1 : etat modifié.
	 * @param e2 : etat modifiant.
	 */
	public void ModificationEtat(Etat e1, Etat e2){
		if(e2.id == 1){
			ModificationEtat(e2,e1);
		} else {
			System.out.println("Dans la modifEtat \n\n\n\n");
			for(int i = 0; i < e2._labels.size(); i++){
				if(!e1._etats.contains(e2._etats.get(i))){
					e1._labels.add(e2._labels.get(i));
					e1._etats.add(e2._etats.get(i));
					if(!e1.fin){
						e1.fin = e2.fin;
					}
					e2.id = e1.id;
				}
			}
		}
	}
	
	public boolean equalsEtat(Etat etat){
		if(etat._etats.size() != _etats.size() || etat._labels.size() != _labels.size() || etat.id != id){
			return false;
		}
		for(int i = 0;i < _etats.size(); i++){
			if(_etats.get(i).id != etat._etats.get(i).id || !_labels.get(i).equals(_labels.get(i))){
				return false;
			}
		}
		return true;
	}
	/**
	 * Ajoutes les états liées à tous les mots données.
	 * @param mots : Lesmots constituants l'échantillon.
	 */
	public void InitialiserAccepteurMots(Vector<String> mots){
		this.fin = true;
		for(int i = 0; i < mots.size(); i++){
			InitialiserAccepterLettres(mots.get(i));
		}
	}
	
	/**
	 * Ajoute les états liées au mot données si non existant.
	 * @param mot : un des mots de l'échantillon.
	 */
	public void InitialiserAccepterLettres(String mot){
		Etat temp = this;
		for(int i = 0; i < mot.length(); i++){
			//Si un label existe déjà pour un caractère, on y va.
			//Sinon on l'ajoute.
			if(temp._labels.contains(mot.substring(i,i+1))){
				//On avance dans le caractère en bougeant d'étape.
				//Ici nous cherchons son indice.
				int indice = temp._labels.indexOf(mot.substring(i,i+1));
				//On utilise son indice pour savoir l'état associé.
				temp = temp._etats.get(indice);
			} else {
				//On ajoute le caractère.
				temp._labels.add(mot.substring(i, i+1));
				//On ajoute l'état vide associé au caractère.
				Etat nouveauEtat = new Etat();
				temp._etats.add(nouveauEtat);
				//Et on y bouge.
				temp = temp._etats.get(temp._etats.size()-1);
			}
			if(i+1 == mot.length()){
				temp.fin = true;
			}
		}
		temp.fin = true;
	}
	
	/**
	 * Vérifie si le mot passé est acceptable ou non.
	 * @param mot : à vérifié
	 * @return boolean : acceptabilité du mot.
	 */
	public boolean verif(String mot){
		Etat temp = this;
		for(int i = 0; i < mot.length(); i++){
			//Si un label existe déjà pour un caractère, on y va.
			//Sinon le mot n'est pas acceptable.
			if(temp._labels.contains(mot.substring(i,i+1))){
				//On avance dans le caractère en bougeant d'étape.
				//Ici nous cherchons son indice.
				int indice = temp._labels.indexOf(mot.substring(i,i+1));
				//On utilise son indice pour savoir l'état associé.
				temp = temp._etats.get(indice);
			} else {
				//Ce n'est pas acceptable.
				return false;
			}
			//Si le mot est fini on  test sa validité.
			if(i+1 == mot.length()){
				if(temp.fin){
					return true;
				}else {
					return false;
				}
			}
		}
		return this.fin;
	}
	
	public void GeneralfusionEtatsFinaux(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(id);
		for(int i = 0; i < _etats.size(); i++){
			if(_etats.get(i).fin){
				_etats.set(i, this);
			} else {
				FusionEtatsFinaux(_etats.get(i), list);
			}
		}
	}
	
	
	
	/**
	 * Méthode cherchant deux états menant à deux et les fusionnant
	 */
	public void FusionEtatsNonFinaux(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> listFuture = new ArrayList<Integer>();
		list.add(id);
		// Pour chaque etat possible, on appelle une fonction fusionnant les autres états allant au même état avec.
		for(int i = 0; i < _etats.size(); i++){
			FusionEtatsNonFinauxPartie(_etats.get(i), _labels.get(i), this, listFuture);
			if(!list.contains(_etats.get(i).id)){
			_etats.get(i).FusionEtatsNonFinaux(this, list);
			}
		}
	}
	
	
	public void FusionEtatsNonFinaux(Etat base, ArrayList<Integer> list){
		list.add(id);
		ArrayList<Integer> listFuture = new ArrayList<Integer>();
		// Pour chaque etat possible, on appelle une fonction fusionnant les autres états allant au même état avec.
		for(int i = 0; i < _etats.size(); i++){
			base.FusionEtatsNonFinauxPartie(_etats.get(i), _labels.get(i), this, listFuture);
			if(!list.contains(_etats.get(i).id)){
				_etats.get(i).FusionEtatsNonFinaux(base, list);
			}
		}
		/*
		System.out.println("On passe dans FusionEtatsNonFinaux.");
		ArrayList<Pair> listPair = RechercheDoublePair();
		System.out.println("Nombre paire : " + listPair.size());
		//On cherche deux états(différents) et labels(semblable) renvoyant vers le même état. Si les indices valent -1, il n'y a pas de label choisie pour l'instant.
		while(listPair.size() == 2){
			System.out.println("On passe dans le while de FusionEtatsNonFinaux.");
			ModificationEtat(listPair.get(0).getEtat(), listPair.get(1).getEtat());
			fusion(listPair.get(0), listPair.get(1));
			listPair = RechercheDoublePair();
		}
		*/
	}
	

	
	public void FusionEtatsNonFinauxPartie(Etat etatVerif, String label, Etat normal, ArrayList<Integer> list){
		list.add(id);
		for(int i = 0; i < _etats.size(); i++){
			if(_etats.get(i).equals(etatVerif) && label.equals(_labels.get(i)) && id != normal.id){
				System.out.println("\n\n\n\nOn est après modif : " + normal.id + " " + label + " " + etatVerif.id + "     " + id + " " + _labels.get(i) + " " + _etats.get(i).id);
				ModificationEtat(normal, this);
				
				System.out.println("On est après modif\n\n\n\n");
				//_etats.set(i, etatVerif);
			} else if(!list.contains(_etats.get(i).id)){
				_etats.get(i).FusionEtatsNonFinauxPartieSuite(etatVerif, label, normal, list, this, i);
			}
		}
	}
	
	
	public void FusionEtatsNonFinauxPartieSuite(Etat etatVerif, String label, Etat normal, ArrayList<Integer> list, Etat pere, int position){
		list.add(id);
		for(int i = 0; i < _etats.size(); i++){
			if(_etats.get(i).equals(etatVerif) && label.equals(_labels.get(i)) && id != normal.id){
				System.out.println("\n\n\n\nOn est après modif : " + normal.id + " " + label + " " + etatVerif.id + "     " + id + " " + _labels.get(i) + " " + _etats.get(i).id);
				ModificationEtat(normal, this);
				pere._etats.set(position, normal);
				System.out.println("On est après modif\n\n\n\n");
				//_etats.set(i, etatVerif);
			} else if(!list.contains(_etats.get(i).id)){
				_etats.get(i).FusionEtatsNonFinauxPartieSuite(etatVerif, label, normal, list, this, i);
			}
		}
	}
	
	public void fusion(Pair p1, Pair p2){
		for(int i = 0; i < _etats.size(); i++){
			if( _etats.get(i).equals(p2.getEtat()) ){
				_etats.set(i, p2.getEtat());
			}
			if(_etats.get(i).id != 1){
				_etats.get(i).fusion(p1, p2);
			}
		}
	}
	
	

	/**
	 * Méthode renvoyant soit un arrayList de deux pair état/label menant vers le même état soit un arrayList vide.
	 * @return : Une arrayList à deux ou zéro élément.
	 */
	public ArrayList<Pair> RechercheDoublePair(){
		ArrayList<Pair> list = new ArrayList<Pair>();
		Pair p1;
		Pair p2;
		ArrayList<Etat> listEtat = ListEtat();
		while(list.size() < 2){
			for(int i = 0; i < listEtat.size(); i++){
				for(int j = i+1; j < listEtat.size(); j++){
					String test = TestEtatFuture(listEtat.get(i), listEtat.get(j));
					//Test si les deux états on un état commun.
					if(!test.equals("")){	
						//Si oui, on renvoie ces deux états.
						p1 = new Pair(listEtat.get(i), test);
						p2 = new Pair(listEtat.get(j), test);
						list.add(p1);
						list.add(p2);
						return list;
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * Méthode regrdant si ls deux états on un label identique envoyant vers le même état.
	 * @param etat1 : Le premier état.
	 * @param etat2 : Le deuxième état.
	 * @return : Un string étant le label si un a été trouvé sinon un string vide.
	 */
	public String TestEtatFuture(Etat etat1, Etat etat2){
		for(int i = 0; i < etat1._etats.size(); i++){
			for(int j = 0; j < etat2._etats.size(); j++){
				if( (etat1._labels.get(i).equals(etat2._labels.get(j))) && (etat1._etats.get(i).equals(etat2._etats.get(j))) ){
					return etat1._labels.get(i);
				}
			}
		}
		return "";
	}

	/**
	 * Méthode renvoyant la liste de tous les états accessibles à partie de l'état appellant la fonction.
	 * @return : Une liste d'états.
	 */
	public ArrayList<Etat> ListEtat(){
		ArrayList<Etat> listEtat = new ArrayList<Etat>();
		listEtat.add(this);
		for(int i = 0; i < _etats.size() ; i++){
			if(_etats.get(i).id != 1){
				ArrayList<Etat> listEtatTemp = _etats.get(i).ListEtat() ;
				for(int j = 0; j < listEtatTemp.size(); j++){
					listEtat.add(listEtatTemp.get(j));
				}
			}
		}
		return listEtat;
	}
	
	public void FusionEtatsFinaux(Etat temp, ArrayList<Integer> list){
		list.add(temp.id);
		for(int i = 0; i < temp._etats.size(); i++){
			if(temp._etats.get(i).fin){
				temp._etats.set(i, this);
			} else if(!list.contains(temp._etats.get(i).id)){
				FusionEtatsFinaux(temp._etats.get(i), list);
			}
		}
	}
	
	
	public void Affichage(){
		ArrayList<Integer> listInt = new ArrayList<Integer>(); 
		listInt.add(id);
		System.out.println("Etat : " + id + "  et finalité : " + fin + "   nombre de fils : "+ _etats.size()+ "  fils : " );
		for(int i = 0; i < this._labels.size(); i++){
			System.out.println("   Num : " + i + "  d'id  : " + _etats.get(i).id + "  label : " + _labels.get(i));
		}
		for(int i = 0; i < this._labels.size(); i++){
			System.out.println("Etat : " + id);
			String mot = "" + _labels.get(i);
			if(this.equals(_etats.get(i)) || _etats.get(i).id == 1){
				System.out.println(mot);
			} else {
				AffichageEtat(_etats.get(i), mot, this, 0, listInt);
			}
		}
		System.out.print("\n\n");
	}
	
	public void AffichageEtat(Etat temp, String mot, Etat passe, int indice, ArrayList<Integer> listInt){
		listInt.add(temp.id);
		System.out.println("indice :   " + indice + "  Etat : " + temp.id + " et finalité  :  " + temp.fin + "   nombre de fils : " + temp._etats.size() + " " + temp._labels.size()  +   "  fils : " );
		for(int i = 0; i < temp._labels.size(); i++){
			System.out.println("   Num : " + i + "  d'id  : " + temp._etats.get(i).id + "  label : " + temp._labels.get(i));
		}
		for(int i = 0; i < temp._labels.size(); i++){
			String motTemp = mot;
			motTemp += temp._labels.get(i);	
			if(equalsEtat(temp._etats.get(i)) || indice > 4 || temp._etats.get(i).id == 1 || passe.equals(temp._etats.get(i)) || listInt.contains(temp._etats.get(i).id)){				
				System.out.println(motTemp + "  " + equalsEtat(temp._etats.get(i)) + "  " + indice + "  " +  temp._etats.get(i).visite + "  " +  temp._etats.get(i).id);
			} else {
				System.out.println(indice);
				temp.visite = true;
				AffichageEtat(temp._etats.get(i), motTemp, temp, indice++, listInt);
			}
		}
	}
	
	public void NullageVisite(){
		visite = false;
		for(int i = 0; i < _etats.size(); i++){
			
		}
	}
}
