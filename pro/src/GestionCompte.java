

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

	private static final long serialVersionUID 			  = 2602287818998353428L;
	public static final String  VUE_HOME				  = "/restreint/accesRestreint.jsp";
	private UtilisateurDao     utilisateurDao;
    private PartieDao		   partieDao;
    public static final String  ATT_SESSION_USER          = "sessionUtilisateur";

	public static final String CONF_DAO_FACTORY = "daofactory";
    
    public void init() throws ServletException {
        // Récupération d'une instance des DAO
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
        this.partieDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPartieDao();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur user = (Utilisateur) session.getAttribute(ATT_SESSION_USER);
		request.setAttribute("partie",partieDao.TrouverPartiesAUtilisateur(user.getId()));

		if(request.getParameter("supprimer") != null){
			this.utilisateurDao.SupprimerUtilisateur(user);
		}
		
		this.getServletContext().getRequestDispatcher( VUE_HOME ).forward( request, response );
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GestionCompteForm form = new GestionCompteForm( utilisateurDao ); 
		//Traitement de la requête et récupération du bean en résultant
        Utilisateur utilisateur = form.ChangerUtilisateur( request );
            
        //Récupération de la session depuis la requête 
        HttpSession session = request.getSession();
        
        //session.getAttribute(name);


        if ( form.getErreurs().isEmpty() && utilisateur.getMotDePasse() != null && !utilisateur.getMotDePasse().isEmpty()) {    	
            session.setAttribute( ATT_SESSION_USER, utilisateur );
            //response.sendRedirect( URL_REDIRECTION );

        } else {
            session.setAttribute( ATT_SESSION_USER, null );
            //request.setAttribute( ATT_FORM, form );
            //request.setAttribute("partie",returnParties());
            //request.setAttribute( ATT_USER, utilisateur );
            //this.getServletContext().getRequestDispatcher( VUE_ACCUEIL ).forward( request, response );

        }

     
    }

}
