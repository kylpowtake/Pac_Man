package com.sdzee.beans;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.TypeDef;
import org.jasypt.hibernate3.type.EncryptedStringType;

/**
 * Classe utilisée pour représenté un utilisateur.
 * @author auteur principal
 */
//Indique que la classe est une entité.
@Entity
//Donne le nom de la table relié à cette entité pour son utilisation avec hibernate
@Table( name = "Utilisateur" )
public class Utilisateur {
	//L'id de l'utilisateur pour le rendre unique.
    private Long      id;
    //Le mot de passe de l'utilisateur.
    private String    motDePasse;
    //Le pseudo de l'utilisateur.
    private String    pseudo;
    //La date de son inscription.
    private Timestamp dateInscription;

    
	//Indique à hibernate que cette valeur s'incrémente et correspond à la colomne increment du tableau(l'id).
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	/**
	 * Permet d'obtenir l'id de l'utilisateur.
	 * @return l'ide de l'utilisateur.
	 */
    public Long getId() {
        return id;
    }
	/**
	 * Permet de changer l'id de l'utilisateur.
	 * @param id : nouveau id de l'utilisateur.
	 */
    public void setId( Long id ) {
        this.id = id;
    }

    
	//indique à hibernate la colomne associé à cette variable.
	@Column(name = "mot_de_passe")
	/**
	 * Permet d'obtenir le mot de passe de l'utilisateur.
	 * @return le mot de passe de l'utilisateur.
	 */
    public String getMotDePasse() {
        return motDePasse;
    }
	/**
	 * Permet de changer le mot depasse de l'utilisateur.
	 * @param motDePasse : nouveau mot de passe de l'utilisateur.
	 */
    public void setMotDePasse( String motDePasse ) {
        this.motDePasse = motDePasse;
    }


	//indique à hibernate la colomne associé à cette variable.
	@Column(name = "pseudo")
	/**
	 * Permet d'obtenir le pseudo de l'utilisateur.
	 * @return le pseudo de l'utilisateur.
	 */
    public String getPseudo() {
        return pseudo;
    }
	/**
	 * Permet de changer le pseudo de l'utilisateur.
	 * @param pseudo : nouveau pseudo de l'utilisateur.
	 */
    public void setPseudo( String pseudo ) {
        this.pseudo = pseudo;
    }

	
	//indique à hibernate la colomne associé à cette variable.
	@Column(name = "date_inscription")
	/**
	 * Permet d'obtenir la date d'inscription de l'utilisateur.
	 * @return la date d'inscription de l'utilisateur.
	 */
    public Timestamp getDateInscription() {
        return dateInscription;
    }
	/**
	 * Permet de changer la date d'inscription de l'utilisateur.
	 * @param dateInscription : nouvelle date d'inscription de l'utilisateur.
	 */
    public void setDateInscription( Timestamp dateInscription ) {
        this.dateInscription = dateInscription;
    }
}