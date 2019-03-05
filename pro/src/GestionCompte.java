

import java.io.IOException;
import java.util.List;

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
import com.sdzee.forms.GestionCompteForm;

/**
 * Servlet implementation class GestionCompte
 */
@WebServlet("/gestionCompte")
public class GestionCompte extends HttpServlet {

	private static final long 	serialVersionUID 		  =  2602287818998353428L;
	public static final String 	CONF_DAO_FACTORY 		  =  "daofactory";
	public static final String  ATT_SESSION_USER          =  "sessionUtilisateur";
	public static final String  ATT_FORM                  =  "form";
	public static final String  VUE				  		  =  "/WEB-INF/accesRestreint.jsp";
	public static final String 	URL_REDIRECTION 		  =  "http://localhost:8080/pro/deconnexion";
	
	private UtilisateurDao     utilisateurDao;
    private PartieDao		   partieDao;
    

    public void init() throws ServletException {
        // Récupération d'une instance des DAO
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
        this.partieDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPartieDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute(ATT_SESSION_USER);
		
		//récupération du tableau de score de l'utilisateur 
		request.setAttribute("partie",partieDao.TrouverPartiesAUtilisateur(user.getId()));
		
		//si demande de suppression appel a la methode supprimer de utilisateur et redirection vers la page de deconnexio
		if(request.getParameter("supprimer") != null){
			this.utilisateurDao.SupprimerUtilisateur(user);
			response.sendRedirect( URL_REDIRECTION );
		} else {		
			this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GestionCompteForm form = new GestionCompteForm( utilisateurDao ); 
		
		//Traitement de la requête et récupération du bean en résultant
        Utilisateur utilisateur = form.ChangerUtilisateur( request );
            
        //Récupération de la session depuis la requête 
        HttpSession session = request.getSession();
        
        //reset de la session de l'utilisateur 
        session.setAttribute(ATT_SESSION_USER,utilisateur);
        
        if(!form.getErreurs().isEmpty()) {
        	request.setAttribute( ATT_FORM, form);
        	System.out.println("\n\n\n\n\n  dans échoué " + form.getResultat() + form.getErreurs().get("ancienMotDePasse"));
        } else {
        	System.out.println("\n\n\n\n\n  dans réussi" + form.getResultat());
        }
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

    }

}
