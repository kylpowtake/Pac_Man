package com.sdzee.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sdzee.beans.Partie;

/**
 * Classe implémentant PartieDao.
 * Contient les méthodes permettant d'intérargir avec la table Partie dans la base de données Pacman.
 * @author JournalDuCoin
 */
public class PartieDaoImpl implements PartieDao{

	/**
	 * Permet de créer une nouvelle partie en l'ajoutant à la base de données Pacman dans la table Partie.
	 * @param partie : nouvelle partie à ajouter à la table Partie.
	 * @throws DAOException : L'exception pouvant être renvoyée.
	 */
	@Override
	public void CreerPartie(Partie partie) throws DAOException {
    	//Créer la sessionfactory à partir du fichier de configuration d'hibernate donnée permettant la calibration sur la base de données.
    	SessionFactory sessionFactory = new Configuration().configure("/com/sdzee/hibernate/hibernate.cfg.xml").buildSessionFactory();
    	//Ouvre la session.
    	Session session = sessionFactory.openSession();
    	//Commence la transaction.
    	session.beginTransaction();    	
    	//Enregistre la partie dans la table Partie de la base de données Pacman.
    	session.save(partie);
    	//Applique la transaction.
        session.getTransaction().commit();
        //Ferme la session.
    	session.close();
    }

	
    /**
     * Permet de trouver les parties de l'utilisateur dans la base de données Pacman dans la table Partie selon l'id donnée.
     * @param id : id de l'utilisateur dont les parties sont recherchées.
     * @return ArrayList<Partie> : Les parties recherchées
	 * @throws DAOException : L'exception pouvant être renvoyée.
     */
	@Override
	public ArrayList<Partie> TrouverPartiesAUtilisateur(long idUtilisateur) throws DAOException {
    	//Créer la sessionfactory à partir du fichier de configuration d'hibernate donnée permettant la calibration sur la base de données.
    	SessionFactory sessionFactory = new Configuration().configure("/com/sdzee/hibernate/hibernate.cfg.xml").buildSessionFactory();
    	//Ouvre la session.
    	Session session = sessionFactory.openSession();
    	//Commence la transaction.
    	session.beginTransaction();
    	//Créer l'arraylist à rendre allant contenir toutes les parties. 
    	ArrayList<Partie> parties = new ArrayList<>();
    	//Récupère toutes les parties de la table.
    	List partiesTemp = session.createQuery("From Partie").list();
    	//Pour chaque partie : 
    	for (Iterator iterator =  partiesTemp.iterator(); iterator.hasNext();) {
    		Partie partieTemp = (Partie) iterator.next();
    		//On test si c'est une partie de l'utilisateur indiqué par l'id donnée.
    		if(partieTemp.getIdUtilisateur() == idUtilisateur) {
    		//Si c'est une de ses parties, on l'ajoute la partie à l'arraylist.
    		parties.add(partieTemp);
    		}
    	}    	
    	//Applique la transaction.
        session.getTransaction().commit();
        //Ferme la session.
    	session.close();
    	    	
		return parties;
	}

	
    /**
     * Permet de trouver toutes les parties dans la base de données Pacman dans la table Partie.
     * @return ArrayList<Partie> : Toutes les parties recherchées
	 * @throws DAOException : L'exception pouvant être renvoyée.
     */
	@Override
	public ArrayList<Partie> TrouverParties() throws DAOException {
    	//Créer la sessionfactory à partir du fichier de configuration d'hibernate donnée permettant la calibration sur la base de données.
    	SessionFactory sessionFactory = new Configuration().configure("/com/sdzee/hibernate/hibernate.cfg.xml").buildSessionFactory();
    	//Ouvre la session.
    	Session session = sessionFactory.openSession();
    	//Commence la transaction.
    	session.beginTransaction();
    	//Créer l'arraylist à rendre allant contenir toutes les parties. 
    	ArrayList<Partie> parties = new ArrayList<>();
    	//Récupère toutes les parties de la table.
    	List partiesTemp = session.createQuery("From Partie").list();
    	//Pour chaque partie : 
    	for (Iterator iterator =  partiesTemp.iterator(); iterator.hasNext();) {
    		Partie partieTemp = (Partie) iterator.next();
    		//On ajoute la partie à l'arraylist.
    		parties.add(partieTemp);
    	}
    	//Applique la transaction.
        session.getTransaction().commit();
        //Ferme la session.
    	session.close();
    	
    	return parties;
	}

}
