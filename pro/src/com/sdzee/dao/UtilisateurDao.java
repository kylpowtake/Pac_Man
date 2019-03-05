package com.sdzee.dao;

import java.util.ArrayList;

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
     * Permet de changer un utilisateur dans la base de données pacman dans la table utilisateur.
     * @param utilisateur : L'utilisateur devant être changé.
     * @param pseudo : Le nouveau pseudo, soit un string vide et il ne faut pas remplacer l'ancien soit un string non vide et il faut remplacer l'ancien.
     * @param motDePasse : Le nouveau mot de passe, soit un string vide et il ne faut pas remplacer l'ancien soit un string non vide et il faut remplacer l'ancien.
     * @throws DAOException
     */
    void ChangerUtilisateur ( Utilisateur utilisateur, String pseudo, String motDePasse) throws DAOException;
    
    
    /**
     * Permet de "supprimer" un utilisateur en passant son boolean activite de vrai(1 dans la base de données) à faux(0 dans la base de donnée).
     * Ainsi on pourra toujours afficher son pseudo avec ses scores mais il ne pourra plus se connecter.
     * @param utilisateur : l'utilisateur voulant supprimer son compte.
     * @throws DAOException
     */
    void SupprimerUtilisateur (Utilisateur utilisateur) throws DAOException;
    
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