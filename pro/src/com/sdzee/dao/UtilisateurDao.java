package com.sdzee.dao;

import com.sdzee.beans.Utilisateur;

/**
 * Interface contenant les méthodes permettant d'intérargir avec la base de données,
 * pour ce qui concerne les utilisateurs de la table Utilisateur.
 * @author Pizzéria du coin.
 */
public interface UtilisateurDao {

	/**
	 * Permet de créer un nouveau utilisateur en l'ajoutant à la base de données Pacman dans la table Utilisateur.
	 * @param utilisateur : nouveau utilisateur à ajouter à la table Utilisteur.
	 * @throws DAOException : L'exception pouvant être renvoyée.
	 */
    void CreerUtilisateur( Utilisateur utilisateur ) throws DAOException;

    /**
     * Permet de trouver un utilisateur dans la base de données Pacman dans la table Utilisateur selon le pseudo donnée.
     * @param pseudo : Pseudo de l'utilisateur recherché.
     * @return Utilisateur : L'utilisateur recherché.
	 * @throws DAOException : L'exception pouvant être renvoyée.
     */
    Utilisateur TrouverUtilisateur( String pseudo ) throws DAOException;

    /**
     * Permet de trouver un utilisateur dans la base de données Pacman dans la table Utilisateur selon l'id donnée.
     * @param id : id de l'utilisateur recherché.
     * @return Utilisateur : L'utilisateur recherché.
	 * @throws DAOException : L'exception pouvant être renvoyée.
     */
    Utilisateur TrouverUtilisateur( long id ) throws DAOException;

}