import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Partie;
import com.sdzee.beans.Utilisateur;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.PartieDao;
import com.sdzee.dao.UtilisateurDao;
import com.sdzee.forms.ConnexionForm;

@WebServlet( "/connexion" )
public class Connexion extends HttpServlet {
	
    public static final String 	CONF_DAO_FACTORY 		  = "daofactory";
	private static final long   serialVersionUID 		  = 954882317930586448L;
	public static final String  ATT_USER                  = "utilisateur";
    public static final String  ATT_FORM                  = "form";
    public static final String  ATT_SESSION_USER          = "sessionUtilisateur";
    public static final String  VUE_ACCUEIL               = "/WEB-INF/connexion.jsp";
    public static final String 	URL_REDIRECTION			  = "http://localhost:8080/pro/gestionCompte";
    
    private UtilisateurDao     utilisateurDao;
    private PartieDao		   partieDao;

    public void init() throws ServletException {
        // Récupération d'une instance des DAO
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
        this.partieDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPartieDao();
    }

    
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
    	request.setAttribute("partie",returnParties());
        this.getServletContext().getRequestDispatcher( VUE_ACCUEIL ).forward( request, response );
    }


	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        //Préparation de l'objet formulaire
        ConnexionForm form = new ConnexionForm( utilisateurDao ); 

        //Traitement de la requête et récupération du bean en résultant
        Utilisateur utilisateur = form.connecterUtilisateur( request );

        //Récupération de la session depuis la requête 
        HttpSession session = request.getSession();

        /**
         * Si aucune erreur de validation n'a eu lieu, alors ajout du bean
         * Utilisateur à la session, sinon suppression du bean de la session.
         */
        if ( form.getErreurs().isEmpty() && utilisateur.getMotDePasse() != null && !utilisateur.getMotDePasse().isEmpty() && utilisateur.getActivite()) {    	
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            response.sendRedirect( URL_REDIRECTION );

        } else {
            session.setAttribute( ATT_SESSION_USER, null );
            request.setAttribute( ATT_FORM, form );
            request.setAttribute("partie",returnParties());
            request.setAttribute( ATT_USER, utilisateur );
            this.getServletContext().getRequestDispatcher( VUE_ACCUEIL ).forward( request, response );
        }
    }
	
	/**
	 * Retourne toutes les parties
	 * @return
	 */
	 public ArrayList<Partie> returnParties() {
	    	ArrayList<Partie> parties = partieDao.TrouverParties();
	    	return parties;
	    }
}