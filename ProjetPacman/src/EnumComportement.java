
public enum EnumComportement {
	//comportement aléatoires 
	PACMAN_INITIAL,
	FANTOME_INITIAL,
	
	//comportements Pacman
	  	// -> normal le pacman fuis les fantomes
		// -> yolo invinsible traque les fantomes 
	PACMAN_NORMAL,
	PACMAN_YOLO,
	PACMAN_NORMAL_ALGO,
	PACMAN_YOLO_ALGO,
	
	//comportements Fantome
		// -> normal le fantome traque les pacmans
		// -> effraye, pacman invinsible fuis les pacmans 
	FANTOME_NORMAL,
	FANTOME_EFFRAYE,
	FANTOME_NORMAL_ALGO,
	FANTOME_EFFRAYE_ALGO
	
	//les comportements avec ALGO font les mêmes actions mais sont implémentés avec le A*
}
