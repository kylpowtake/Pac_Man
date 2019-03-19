package com.sdzee.dao;

import java.util.ArrayList;

import com.sdzee.beans.Partie;


/**
 * Interface contenant les méthodes permettant d'intérargir avec la base de données,
 * pour ce qui concerne les parties de la table Partie.
 * @author Cinéma du coin.
 */
public interface PartieDao {

	/**
	 * Permet de créer une nouvelle partie en l'ajoutant à la base de données Pacman dans la table Partie.
	 * @param partie : nouvelle partie à ajouter à la table Partie.
	 * @throws DAOException : L'exception pouvant être renvoyée.
	 */
	void CreerPartie (Partie partie) throws DAOException;
	
    /**
     * Permet de trouver les parties de l'utilisateur dans la base de données Pacman dans la table Partie selon l'id donnée.
     * @param id : id de l'utilisateur dont les parties sont recherchées.
     * @return ArrayList<Partie> : Les parties recherchées
	 * @throws DAOException : L'exception pouvant être renvoyée.
     */
	ArrayList<Partie> TrouverPartiesAUtilisateur (long idUtilisateur) throws DAOException;

    /**
     * Permet de trouver toutes les parties dans la base de données Pacman dans la table Partie.
     * @return ArrayList<Partie> : Toutes les parties recherchées
	 * @throws DAOException : L'exception pouvant être renvoyée.
     */
	ArrayList<Partie> TrouverParties () throws DAOException;
	
	
	/**
     * Permet de trouver les 5 meilleurs joueurs.
     * @return ArrayList<Partie> : Toutes les parties recherchées
	 * @throws DAOException : L'exception pouvant être renvoyée.
     */
	ArrayList<Partie> TrouverBestPlayers () throws DAOException;

}
