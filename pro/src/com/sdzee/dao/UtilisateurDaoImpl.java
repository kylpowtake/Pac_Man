package com.sdzee.dao;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.sdzee.beans.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {

    UtilisateurDaoImpl() {
    }

    /**
     * Permet de trouver un utilisateur dans la base de données Pacman dans la table Utilisateur selon le pseudo donnée.
     * @param pseudo : Pseudo de l'utilisateur recherché.
     * @return Utilisateur : L'utilisateur recherché.
	 * @throws DAOException : L'exception pouvant être renvoyée.
     */
    @Override
    public Utilisateur TrouverUtilisateur( String pseudo ) throws DAOException {
    	//Créer la sessionfactory à partir du fichier de configuration d'hibernate donnée permettant la calibration sur la base de données.
    	SessionFactory sessionFactory = new Configuration().configure("/com/sdzee/hibernate/hibernate.cfg.xml").buildSessionFactory();
    	//Ouvre la session.
    	Session session = sessionFactory.openSession();
    	//Commence la transaction.
    	session.beginTransaction();
    	//initialise l'id de 'lutilisateur.
    	//Si le pseudo n'est as dans la table l'id restera -1 sinon on le changera pour celui de l'utilisateur trouvé..
    	long id = -1;
    	//On prend la liste des utilisateurs dans la table.
        List utilisateurs = session.createQuery("FROM Utilisateur").list(); 
        //Pour chacun de ces utilisateurs : 
        for (Iterator iterator = utilisateurs.iterator(); iterator.hasNext();){        	
        	Utilisateur utilisateur = (Utilisateur) iterator.next(); 
        	//On test si c'est le bon utilisateur.
        	if(utilisateur.getPseudo().equals(pseudo)) {
        		//Si c'est le bon, on change la valeur de id à l'id de l'utilisateur.
        		id =  utilisateur.getId();
        	}
        }
        if(id != -1) {
        	//Si le pseudo a été trouvé dans la table.
        	//Cherche l'utilisateur dans le tableau Utilisateur selon l'id donnée.
        	Utilisateur utilisateur = session.get(Utilisateur.class, id);
        	//Applique la transaction.
        	session.getTransaction().commit();
            //Ferme la session.
        	session.close();
        	//Retourne l'utilisateur trouvé.
        	return utilisateur;
        } else {
        	//si le pseudo n'a pas été trouvé dans la table.
            Utilisateur utilisateur = null;
        	//Applique la transaction.
        	session.getTransaction().commit();
            //Ferme la session.
        	session.close();
        	//Retourne un utilisateur null car il n'a pas été trouvé.
        	return utilisateur;
        }
    }

    
    /**
     * Permet de trouver un utilisateur dans la base de données Pacman dans la table Utilisateur selon l'id donnée.
     * @param id : id de l'utilisateur recherché.
     * @return Utilisateur : L'utilisateur recherché.
	 * @throws DAOException : L'exception pouvant être renvoyée.
     */
    @Override
    public Utilisateur TrouverUtilisateur(long id) throws DAOException {
    	//Créer la sessionfactory à partir du fichier de configuration d'hibernate donnée permettant la calibration sur la base de données.
    	SessionFactory sessionFactory = new Configuration().configure("/com/sdzee/hibernate/hibernate.cfg.xml").buildSessionFactory();
    	//Ouvre la session.
    	Session session = sessionFactory.openSession();
    	//Commence la transaction.
    	session.beginTransaction();
    	//Cherche l'utilisateur dans le tableau Utilisateur selon l'id donnée.
        Utilisateur utilisateur = session.get(Utilisateur.class, id);
    	//Applique la transaction.
        session.getTransaction().commit();
        //Ferme la session.
        session.close();
    	//Retourne l'utilisateur trouvé.
        return utilisateur;
    }
    
    
    /**
     * Permet de "supprimer" un utilisateur en passant son boolean activite de vrai(1 dans la base de données) à faux(0 dans la base de donnée).
     * Ainsi on pourra toujours afficher son pseudo avec ses scores mais il ne pourra plus se connecter.
     * @param utilisateur : l'utilisateur voulant supprimer son compte.
     * @throws DAOException
     */
	@Override
	public void SupprimerUtilisateur(Utilisateur utilisateur) throws DAOException {
    	//Créer la sessionfactory à partir du fichier de configuration d'hibernate donnée permettant la calibration sur la base de données.
    	SessionFactory sessionFactory = new Configuration().configure("/com/sdzee/hibernate/hibernate.cfg.xml").buildSessionFactory();
    	//Ouvre la session.
    	Session session = sessionFactory.openSession();
    	//Commence la transaction.
    	Transaction transaction = session.beginTransaction();
    	//On charge l'utilisateur ayant l'id donné en paramètre à load, ce qui nous permet de le mettre à jour.
    	utilisateur = session.load(Utilisateur.class, utilisateur.getId());
    	//Modification de l'activité.
    	utilisateur.setActivite(false);
    	//Applique la transaction.
        transaction.commit();
        //Ferme la session.
    	sessionFactory.close();
	}
    
    
	@Override
	public void ChangerUtilisateur(Utilisateur utilisateur, String pseudo, String motDePasse) throws DAOException {
    	//Créer la sessionfactory à partir du fichier de configuration d'hibernate donnée permettant la calibration sur la base de données.
    	SessionFactory sessionFactory = new Configuration().configure("/com/sdzee/hibernate/hibernate.cfg.xml").buildSessionFactory();
    	//Ouvre la session.
    	Session session = sessionFactory.openSession();
    	//Commence la transaction.
    	Transaction transaction = session.beginTransaction();
    	//On charge l'utilisateur ayant l'id donné en paramètre à load, ce qui nous permet de le mettre à jour.
    	utilisateur = session.load(Utilisateur.class, utilisateur.getId());
    	//Début du test des différentes situations possibles et de leurs applications.
		if(pseudo.isEmpty()) {
			//Le cas où le pseudo est vide, il ne faut donc changer que le mot de passe.
			utilisateur.setMotDePasse(motDePasse);
		} else if (motDePasse.isEmpty()) {
			//Le cas où le mot de passe est vide, il ne faut donc changer que le pseudo.
			utilisateur.setPseudo(pseudo);
		} else {
			//Le cas où les deux string ne sont pas vides et il faut changer les deux. 
			utilisateur.setMotDePasse(motDePasse);
			utilisateur.setPseudo(pseudo);
		}
		//Fin du test des différentes situtions possibles et de leurs applications.
    	//Applique la transaction.
        transaction.commit();
        //Ferme la session.
    	sessionFactory.close();
	}
    
    
	/**
	 * Permet de créer un nouveau utilisateur en l'ajoutant à la base de données Pacman dans la table Utilisateur.
	 * @param utilisateur : nouveau utilisateur à ajouter à la table Utilisteur.
	 * @throws DAOException : L'exception pouvant être renvoyée.
	 */
    @Override
    public void CreerUtilisateur( Utilisateur utilisateur ) throws DAOException {
    	//Créer la sessionfactory à partir du fichier de configuration d'hibernate donnée permettant la calibration sur la base de données.
    	SessionFactory sessionFactory = new Configuration().configure("/com/sdzee/hibernate/hibernate.cfg.xml").buildSessionFactory();
    	//Ouvre la session.
    	Session session = sessionFactory.openSession();
    	//Commence la transaction.
    	session.beginTransaction();
    	//Initialise la valeur de la date d'inscription de l'utilisateur au moment actuel.
    	utilisateur.setDateInscription(new Timestamp( new Date().getTime()));
    	utilisateur.setActivite(true);
    	//Ajoute l'utilisateur donnée en paramètre à la base de données.
    	session.save(utilisateur);    	
    	//Applique la transaction.
        session.getTransaction().commit();
        //Ferme la session.
    	session.close();
    }

    
}
