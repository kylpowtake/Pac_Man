package com.sdzee.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.sdzee.beans.Utilisateur;
import com.sdzee.dao.PartieDao;
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

    public Utilisateur connecterUtilisateur( HttpServletRequest request ) {
        /* Récupération des champs du formulaire */
        String pseudo = getValeurChamp( request, CHAMP_PSEUDO );
        String motDePasse = getValeurChamp( request, CHAMP_PASS );

        Utilisateur utilisateur = new Utilisateur();

        /* Validation du champ pseudo. */
        try {
            validationPseudo( pseudo );
        } catch ( Exception e ) {
            setErreur( CHAMP_PSEUDO, e.getMessage() );
        }
        utilisateur.setPseudo( pseudo );

        /* Validation du champ mot de passe. */
        try {
            validationMotDePasse( motDePasse );
        } catch ( Exception e ) {
            setErreur( CHAMP_PASS, e.getMessage() );
        }
        utilisateur.setMotDePasse( motDePasse );

        /* Initialisation du résultat global de la validation. */
        if ( erreurs.isEmpty() ) {
            Utilisateur utilisateurTemp = utilisateurDao.TrouverUtilisateur(utilisateur.getPseudo());
            if(utilisateurTemp != null) {
                System.out.println("Nous y sommeau debbugagge  yyoolloo \n\n\n\n\n\n" + motDePasse);
                System.out.println("Nous y sommeau debbugagge  yyoolloo \n\n\n\n\n\n" + utilisateurTemp.getMotDePasse() + "    " + utilisateurTemp.getMotDePasse().length());


                ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
                if(passwordEncryptor.checkPassword(motDePasse, utilisateur.getMotDePasse())){
                    System.out.println("Nous y sommeau debbugagge  yyoolloo \n\n\n\n\n\n décryptage réussi");
                    resultat = "Succès de la connexion.";
                } else {
                    System.out.println("Nous y sommeau debbugagge  yyoolloo \n\n\n\n\n\n décriptage echioué");
                    resultat = "Échec de la connexion. Ce pseudo ou mot de passe est incorrecte.";
                }
            }else {
                resultat = "Échec de la connexion. Ce pseudo ou mot de passe est incorrecte.";
            }
        } else {
            resultat = "Échec de la connexion. Ily a des erreurs dans le formulaire.";
        }

        return utilisateur;
    }
    
    
    /**
     * Valide le pseudo saisie.
     */
    private void validationPseudo( String pseudo ) throws Exception {
        if ( pseudo == null ) {
            throw new Exception( "Merci de saisir votre mot de passe." );
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void validationMotDePasse( String motDePasse ) throws Exception {
        if ( motDePasse != null ) {
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