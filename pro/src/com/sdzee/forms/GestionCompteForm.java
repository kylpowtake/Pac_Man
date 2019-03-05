package com.sdzee.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.sdzee.beans.Utilisateur;
import com.sdzee.dao.UtilisateurDao;

public class GestionCompteForm {
	private static final String CHAMP_PSEUDO = "pseudo";
	private static final String CHAMP_ANCIEN_PASS   = "ancienMotDePasse";
	private static final String CHAMP_NOUVEAU_PASS = "nouveauMotDePasse";
	private static final String CHAMP_CONF_PASS   = "confMotDePasse";

    public static final String  ATT_SESSION_USER          = "sessionUtilisateur";
    private static final String ALGO_CHIFFREMENT = "SHA-256";

    
	private String resultat;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private UtilisateurDao      utilisateurDao;


	public Map<String, String> getErreurs() {
		return erreurs;
	}


	public String getResultat() {
		return resultat;
	}

	public GestionCompteForm( UtilisateurDao utilisateurDao ) {
		this.utilisateurDao = utilisateurDao;
	}
	

	/**
	 * Méthode modifiant l'utilisateur dans la base de donnée.
	 * @param request : HttpServletRequest contenant un nouveau pseudo, un ancien mot de passe, un nouveau mot de passe et la confirmation du mot de passe,
	 * 		ces valeurs sont soit des string vides soit des string non-vides. 
	 * @return utilisateur : Un utilisateur ayant soit seulement un pseudo si il y a eu un problème avec le mot de passe,
	 * 				 soit un pseudo et un mot de passe si les changements ont étés accepté.
	 */
	public Utilisateur ChangerUtilisateur( HttpServletRequest request ) {
	    /* Récupération des champs du formulaire */
		//Récupération du nouveau pseudo de l'utilisateur voulant changer son pseudo.
	    String pseudo = getValeurChamp( request, CHAMP_PSEUDO );
		//Récupération de l'ancien mot de passe de l'utilisateur voulant changer son mot de passe.
	    String ancienMotDePasse = getValeurChamp( request, CHAMP_ANCIEN_PASS );
		//Récupération du nouveau mot de passe de l'utilisateur voulant changer son mot de passe.
	    String nouveauMotDePasse = getValeurChamp( request, CHAMP_NOUVEAU_PASS );
		//Récupération de la confirmation du mot de passe de l'utilisateur voulant changer son mot de passe.
	    String confMotDePasse = getValeurChamp( request, CHAMP_CONF_PASS );

    	//Initialisation du mot de passe chiffrer.
    	String motDePasseChiffre = "";
    	
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();

        //On récupère dans la session l'utilisateur s'étant connecté.
        Utilisateur utilisateur = (Utilisateur) session.getAttribute(ATT_SESSION_USER);
        
        
        /*
         * ancienMotDePasse = obligatoire.
         * pseudo ou (nouveauMotDePasse + confMotDePasse) obligatoire
         * pseudo et (nouveauMotDePasse + confMotDePasse) possible
         */

    	/* Validation du champ ancien mot de passe. */
    	try {
        	validationMotDePasseActuelle( ancienMotDePasse, utilisateur.getMotDePasse());
    	} catch ( Exception e ) {
        	setErreur( CHAMP_ANCIEN_PASS, e.getMessage() );
    	}
    	//L'ancien mot de passe n'est pas erroné, on peut donc tester si c'est celui associé à l'utilisateur.
        
    	//On vérifie que le pseudo ne soit pas nul et soit déjà dans labase de données.
        try {
            validationPseudo( pseudo );
        } catch ( FormValidationException e ) {
            setErreur( CHAMP_PSEUDO, e.getMessage() );
        }
        utilisateur.setPseudo( pseudo );
    	
    	//Ici, il y en a au moins un qui n'est pas nul, or il faut que les deux ne soient pas nuls.
	    if((nouveauMotDePasse != null && !nouveauMotDePasse.isEmpty()) || (confMotDePasse != null && !confMotDePasse.isEmpty())) {
	    	//Il faut donc les tester en vérifiant leur validité. 
	        try {
	            validationMotsDePasse( nouveauMotDePasse, confMotDePasse );
	        } catch ( FormValidationException e ) {
	            setErreur( CHAMP_NOUVEAU_PASS, e.getMessage() );
	            setErreur( CHAMP_CONF_PASS, null );
	        }
	        /*
	         * Le nouveau mot de passe a été vérifié.
	         * Utilisation de la bibliothèque Jasypt pour chiffrer le nouveau mot de passe
	         * efficacement.
	         * 
	         * L'algorithme SHA-256 est ici utilisé, avec par défaut un salage
	         * aléatoire et un grand nombre d'itérations de la fonction de hashage.
	         * 
	         * La String retournée est de longueur 56 et contient le hash en Base64.
	         */
	        ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
	        passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );
	        passwordEncryptor.setPlainDigest( false );
	        motDePasseChiffre = passwordEncryptor.encryptPassword( nouveauMotDePasse );
	    	//La confirmation du mot de passe n'est pas erroné, on peut donc tester si c'est le même que le nouveau.	
	    }
	    
	    /* Initialisation du résultat global de la validation. */
	    /*Si il n'y a pas d'erreur, c'est que les mots depasse données sont tous valides ou tous nuls,
	     * et l'on peut donc effectuer les changements.
	     */
	    if ( erreurs.isEmpty()) {
	    	//On n'active pas le changement si le pseudo et un des mots de passe est nul(ils le sont alors tous car il n'y a pas d'erreurs).
	    	if((pseudo == null || pseudo.isEmpty()) && (nouveauMotDePasse == null || nouveauMotDePasse.isEmpty())) {
	    		resultat = "Échec du changement, il faut au moins une valeur à changer pour le faire.";
	    	} else {
	    		//Si le pseudo n'est pas vide ou/et les mots de passe ne sont pas vides, on peut appliquer un changement. 
	    			utilisateurDao.ChangerUtilisateur(utilisateur, pseudo, motDePasseChiffre);	    		
	    	}
	    } else {
	    	//Il y a des erreurs, il y a donc parmis les trois mots de passe au moins qui a une erreur.
	    	resultat = "Échec de la connexion. Il y a des erreurs dans le formulaire.";
	    }
	    return utilisateur;
	}
	

/**
 * Valide le mot de passe saisi.
 */
private void validationMotDePasseActuelle( String motDePasse, String motDePasseActuelle) throws Exception {
	//Il y a une erreur si le mot de passe est nul.
    if ( motDePasse != null ) {
    	//Il y a une erreur si le mot de passe ne fait pas au moins trois caractères.
        if ( motDePasse.length() < 3 ) {
            throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
        }
    } else {
        throw new Exception( "Merci de saisir votre mot de passe." );
    }
    if(!motDePasseActuelle.equals(motDePasse)){
    	throw new Exception( "Le mot de passe rentrer n'est pas le votre." );
    }
}


/*
 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
 */
private void setErreur( String champ, String message ) {
    erreurs.put( champ, message );
}

/*
 * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
 * sinon.
 */
private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
    String valeur = request.getParameter( nomChamp );
    if ( valeur == null || valeur.trim().length() == 0 ) {
        return null;
    } else {
        return valeur;
    }
}

/* Validation des mots de passe */
private void validationMotsDePasse( String motDePasse, String confirmation ) throws FormValidationException {
    if ( motDePasse != null && confirmation != null ) {
        if ( !motDePasse.equals( confirmation ) ) {
            throw new FormValidationException( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
        } else if ( motDePasse.length() < 3 ) {
            throw new FormValidationException( "Les mots de passe doivent contenir au moins 3 caractères." );
        }
    } else {
        throw new FormValidationException( "Merci de saisir et confirmer votre mot de passe." );
    }
}

/* Validation du pseudo */
private void validationPseudo( String pseudo ) throws FormValidationException {
    if ( pseudo != null) {
    	if(pseudo.length() >= 3) {
    		if ( utilisateurDao.TrouverUtilisateur( pseudo ) != null ) {
            	throw new FormValidationException( "Ce pseudo est déjà utilisé, merci d'en choisir un autre." );
        	}
    	} else {
        	throw new FormValidationException( "Le pseudo doit faire au moins trois caractères" );
    	}
    }
}

}



