package com.sdzee.dao;

import java.sql.Timestamp;
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
    	
		
    	SessionFactory sessionFactory = new Configuration().configure("/com/sdzee/hibernate/hibernate.cfg.xml").buildSessionFactory();
    	Session session = sessionFactory.openSession();
	
		List<Object[]> yourList = session.createSQLQuery("Select p.score, p.fantomesManges, p.capsulesMangees, p.pacGommesMangees, p.mapsEffectuees, p.pasEffectues, p.date, u.pseudo From Partie p inner join Utilisateur u on p.idUtilisateur = u.id where p.idUtilisateur = " + idUtilisateur).list();
		Iterator<Object[]> it = yourList.iterator();
		ArrayList<Partie> parties = new ArrayList<>();
		while (it.hasNext()) {
			Partie partie = new Partie();
			long score     = -1;
			long fantomesManges  = -1;
			long capsulesMangees = -1;
			long pacGommesMangees = -1;
			long mapsEffectuees = -1;
			long pasEffectues = -1;
			Timestamp date = null;
			String pseudo    = "";
			
			Object[] row = it.next();
			
			if(row[0]!=null){
				score = Long.parseLong(row[0].toString());
			}
			if(row[1]!=null){
				fantomesManges  = Long.parseLong(row[1].toString());
			}
			if(row[2]!=null){
				capsulesMangees  = Long.parseLong(row[2].toString());
			}
			if(row[3]!=null){
				pacGommesMangees  = Long.parseLong(row[3].toString());
			}
			if(row[4]!=null){
				mapsEffectuees  = Long.parseLong(row[4].toString());
			}
			if(row[5]!=null){
				pasEffectues  = Long.parseLong(row[5].toString());
			}
			if(row[6]!=null){
				date = Timestamp.valueOf(row[6].toString());
			}
			if(row[7]!=null){
				pseudo  = row[7].toString();
			}
			partie.setScore(score);
			partie.setFantomesManges(fantomesManges);
			partie.setCapsulesMangees(capsulesMangees);
			partie.setPacGommesMangees(pacGommesMangees);
			partie.setMapsEffectuees(mapsEffectuees);
			partie.setpasEffectues(pasEffectues);
			partie.setDate(date);
			partie.setPseudoUtilisateur(pseudo);
			parties.add(partie);
		}
    	
    	return parties;
	}
	
    /**
     * Permet de trouver toutes les parties dans la base de données Pacman dans la table Partie.
     * @return ArrayList<Partie> : Toutes les parties recherchées
	 * @throws DAOException : L'exception pouvant être renvoyée.
     */
	@Override
	public ArrayList<Partie> TrouverParties() throws DAOException {
		
		
    	SessionFactory sessionFactory = new Configuration().configure("/com/sdzee/hibernate/hibernate.cfg.xml").buildSessionFactory();
    	Session session = sessionFactory.openSession();
	
    	List<Object[]> yourList = session.createSQLQuery("Select p.score, u.pseudo, p.date From Partie p inner join Utilisateur u on p.idUtilisateur = u.id").list();
		Iterator<Object[]> it = yourList.iterator();
		ArrayList<Partie> parties = new ArrayList<>();
		while (it.hasNext()) {
		Partie partie = new Partie();
		long score     = -1;
		String pseudo    = "";
		Timestamp date = null;
		Object[] row = it.next();
		if(row[0]!=null){
		score = Long.parseLong(row[0].toString());
		}
		if(row[1]!=null){
		pseudo = row[1].toString();
		}
		if(row[2]!=null){
		date = Timestamp.valueOf(row[2].toString());
		}
		partie.setScore(score);
		partie.setPseudoUtilisateur(pseudo);
		partie.setDate(date);
		parties.add(partie);
		}
		
		return parties;
	}
	
	
	public ArrayList<Partie> TrouverBestPlayers() throws DAOException {
		
		
    	SessionFactory sessionFactory = new Configuration().configure("/com/sdzee/hibernate/hibernate.cfg.xml").buildSessionFactory();
    	Session session = sessionFactory.openSession();
	
    	List<Object[]> yourList = session.createSQLQuery("Select p.score, u.pseudo, p.date From Partie p inner join Utilisateur u on p.idUtilisateur = u.id  ORDER BY p.score DESC LIMIT 3").list();
		Iterator<Object[]> it = yourList.iterator();
		ArrayList<Partie> parties = new ArrayList<>();
		while (it.hasNext()) {
		Partie partie = new Partie();
		long score     = -1;
		String pseudo    = "";
		Timestamp date = null;
		Object[] row = it.next();
		if(row[0]!=null){
		score = Long.parseLong(row[0].toString());
		}
		if(row[1]!=null){
		pseudo = row[1].toString();
		}
		if(row[2]!=null){
		date = Timestamp.valueOf(row[2].toString());
		}
		partie.setScore(score);
		partie.setPseudoUtilisateur(pseudo);
		partie.setDate(date);
		parties.add(partie);
		}
		
		return parties;
	}

}
