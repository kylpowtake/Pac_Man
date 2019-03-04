
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdzee.beans.Utilisateur;
import com.sdzee.dao.DAOFactory;
import com.sdzee.dao.UtilisateurDao;
import com.sdzee.dao.PartieDao;
import com.sdzee.forms.InscriptionForm;

@WebServlet( "/inscription" )
public class Inscription extends HttpServlet {

	private static final long serialVersionUID  = 3697220712479015561L;
	public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String VUE_ACCUEIL      = "/WEB-INF/connexion.jsp";
    public static final String VUE_INSCRIPTION  = "/WEB-INF/inscription.jsp";

    private UtilisateurDao     utilisateurDao;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.utilisateurDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUtilisateurDao();
    }

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher( VUE_INSCRIPTION ).forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        InscriptionForm form = new InscriptionForm( utilisateurDao );

        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        /* Traitement de la requête et récupération du bean en résultant */
        Utilisateur utilisateur = form.inscrireUtilisateur( request );

        /* Stockage du formulaire et du bean dans l'objet request */
        request.setAttribute( ATT_FORM, form );
        request.setAttribute( ATT_USER, utilisateur );

        //Test si il n'y a pas eu d'erreurs et si le mot de passe a été accepté, c'est à dire qu'il n'est pas vide.
        if(form.getErreurs().isEmpty() && !utilisateur.getMotDePasse().isEmpty()) {
        	PartieDao partieDao;
            partieDao = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getPartieDao();
        	//Si l'inscription a réussi et l'utilisateur a été rajouté à la base de données, on passe à la page de connection.
            request.setAttribute("partie", partieDao.TrouverParties());            
            this.getServletContext().getRequestDispatcher( VUE_ACCUEIL ).forward( request, response );
        } else {
        	//Si l'inscription a échoué, on reste sur la page d'inscription.
            this.getServletContext().getRequestDispatcher( VUE_INSCRIPTION ).forward( request, response );
        }
    }
}