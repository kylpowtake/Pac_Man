package com.sdzee.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.sdzee.beans.Utilisateur;
import com.sdzee.dao.UtilisateurDao;


public final class ConnexionForm {
	
    private static final String CHAMP_PSEUDO= "pseudo";
    private static final String CHAMP_PASS   = "motdepasse";
    
    private static final String ALGO_CHIFFREMENT = "SHA-256";

    
    private String resultat;
    private Map<String, String> erreurs = new HashMap<String, String>();
    private UtilisateurDao      utilisateurDao;

    public ConnexionForm( UtilisateurDao utilisateurDao ) {
        this.utilisateurDao = utilisateurDao;
    }
    
    public Map<String, String> getErreurs() {
        return erreurs;
    }
    
    public String getResultat() {
        return resultat;
    }

    /**
     * Méthode allant chercher dans la base de données l'utilisateur passé en paramètre et vérifiant, si il y est,
     * si le mot de passe donnée est bien celui lié à l'utilisateur, en cas de réponse positive renvoie l'utilisateur.
     * Si il y a une erreur dans le pseudo ou mot de passe données alors des messages d'erreurs seront renvoyés.
     * De même si lemot de passe n'est pas le bon.
     * @param request : HttpServletRequest contenant le pseudo et le mot de passe de l'utilisateur voulant se connecter.
     * @return utilisateur : Un utilisateur ayant soit seulement un pseudo si il y a eu un problème soit un pseudo et un mot de passe si il a été trouvé dans la base de données.
     */
    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
    	//Récupération du pseudo de l'utilisateur voulant se connecter.
        String pseudo = getValeurChamp( request, CHAMP_PSEUDO );
    	//Récupération du mot de passe de l'utilisateur voulant se connecter.
        String motDePasse = getValeurChamp( request, CHAMP_PASS );

        //Instanciation de l'utilisateur allant être retourné à la fin de la méthode.
        Utilisateur utilisateur = new Utilisateur();

        /* Validation du champ pseudo. */
        try {
            validationPseudo( pseudo );
        } catch ( Exception e ) {
            setErreur( CHAMP_PSEUDO, e.getMessage() );
        }
        //Le pseudo n'a pas de problème, on l'ajoute à l'utilisateur allant être retourner.
        utilisateur.setPseudo( pseudo );

        /* Validation du champ mot de passe. */
        try {
            validationMotDePasse( motDePasse );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        //Le mot de passe n'est pas erroné, on peut donc tester si c'est le bon.

        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
        	//On cherche l'utilisateur correspondant au pseudo dans la base de données.
            Utilisateur utilisateurTemp = utilisateurDao.TrouverUtilisateur(utilisateur.getPseudo());
            //Si il est dans la base de données, il n'est pas nul et on peut vérifier son mot de passe.
            if(utilisateurTemp != null) {
            	//On utilise le même passwordEncryptor utiliser pour crypter les mot de passes.
                ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
                //On ajoute l'algorithme utilisé, le même que celui pour crypter les mot de passes..
                passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );         
                //On ajoute l'option de digest, la même valeur que celle pour crypter les mot de passes.
                passwordEncryptor.setPlainDigest( false );

                //On test si on peut bien obtenir le mot de passe chiffre de la base de données à partir du motde passe de l'utilisateur voulant se connecter.
                if(passwordEncryptor.checkPassword(motDePasse, utilisateurTemp.getMotDePasse())){
                	//Si le résultat est positif, on connecte l'utilisateur en ajoutant son mot de passe à l'utilisateur renvoyé.
                    resultat = "Ce compte  à été supprimé.";
                    utilisateur.setId(utilisateurTemp.getId());
                    utilisateur.setMotDePasse( motDePasse );
                    utilisateur.setActivite(utilisateurTemp.getActivite());
                } else {
                	//Si le résultat est négatif on ne rajoute pas le mot de passe à l'utilisateur renvoyé, il ne sera donc pas considré connecté.
                    resultat = "Échec de la connexion. Ce pseudo ou mot de passe est incorrecte.";
                }
            }else {
            	//Si il y a une erreur dans le mot de passe ou le pseudo donné par l'utilisateur voulant connecter.
                resultat = "Échec de la connexion. Ce pseudo ou mot de passe est incorrecte.";
            }
        } else {
        	//Si l'utilisateur renvoyé par la base de données est nul, alors c'est qu'il n'a pas étét trouvé et donc le pseudo n'est pas le bon, il ne faut donc pas le connecter.
            resultat = "Échec de la connexion. Il y a des erreurs dans le formulaire.";
        }
        return utilisateur;
    }
    
    
    /**
     * Valide le pseudo saisie.
     */
    private void validationPseudo( String pseudo ) throws Exception {
    	//Il y a une erreur si le pseudo est nul.
        if ( pseudo == null ) {
            throw new Exception( "Merci de saisir votre pseudo." );
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse( String motDePasse ) throws Exception {
    	//Il y a une erreur si le mot de passe est nul.
        if ( motDePasse != null ) {
        	//Il y a une erreur si le mot de passe ne fait pas au moins trois caractères.
            if ( motDePasse.length() < 3 ) {
                throw new Exception( "Le mot de passe doit contenir au moins 3 caractères." );
            }
        } else {
            throw new Exception( "Merci de saisir votre mot de passe." );
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
}