
public abstract class ComportementAgent {
	
	public abstract void comportement(Agent agent, Game game);
	
	public abstract void comportementSOS(Agent agent, Game game);

	public int[] getTableauOrdre(int[] tableau){
		int[] ordre = new int[4];
		if(tableau[0] >= tableau[1]){
			ordre[0] = 0;
			ordre[1] = 1;
			ordre[2] = 2;
			ordre[3] = 3;
		} else {
			ordre[0] = 1;
			ordre[1] = 0;
			ordre[2] = 2;
			ordre[3] = 3;
		}
		for(int i = 2; i < 4; i++){
			if(tableau[i] >= tableau[ordre[0]]){
				ordre[3] = ordre[2];
				ordre[2] = ordre[1];
				ordre[1] = ordre[0];
				ordre[0] = i;
			} else if (tableau[i] >= tableau[ordre[1]]){
				ordre[3] = ordre[2];
				ordre[2] = ordre[1];
				ordre[1] = i;
			} else if (tableau[i] >= tableau[ordre[2]]){
				ordre[3] = ordre[2];
				ordre[2] = i;
			} else {
				ordre[3] = i;
			}
		}
		return ordre;
	}
}
