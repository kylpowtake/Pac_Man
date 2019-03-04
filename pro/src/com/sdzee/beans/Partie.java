package com.sdzee.beans;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * Classe utilisée pour représenter une partie.
 * @author auteur principal
 */
//Indique que la classe est une entité.
@Entity
//Donne le nom de la table relié à cette entité pour son utilisation avec hibernate
@Table(name = "Partie")
public class Partie {
		//L'id de la partie.
		private long idPartie;
		//L'id de l'utilisateur ayant réalisé la partie.
		private long idUtilisateur;
		//Le score de la partie
		private long score;
		//Le temps de fin de la partie.
		private Timestamp date;
	
		
		//Indique à hibernate que cette valeur s'incrémente et correspond à la colomne increment du tableau(l'id).
		@Id
		@GeneratedValue(generator="increment")
		@GenericGenerator(name="increment", strategy = "increment")
		/**
		 * Permet d'obtenir l'id  de la partie.
		 * @return  l'id de la partie.
		 */
		public long getIdPartie() {
			return this.idPartie;
		}
		/**
		 * Permet de changer l'id de la partie.
		 * @param id : nouveau identifiant de la partie.
		 */
		public void setIdPartie(long idPartie) {
			this.idPartie = idPartie;
		}
		
		
		
		//indique à hibernate la colomne associé à cette variable.
		@Column(name = "id_utilisateur")
		/**
		 * Permet d'obtenir l'id de l'utilisateur de la partie.
		 * @return  l'id de l'utilisateur de la partie.
		 */
		public long getIdUtilisateur() {
			return this.idUtilisateur;
		}
		/**
		 * Permet de changer l'id de l'utilisateur de la partie.
		 * @param id : nouveau identifiant de l'utilisateur ayant fait la partie.
		 */
		public void setIdUtilisateur(long idUtilisateur) {
			this.idUtilisateur = idUtilisateur;
		}

		
		//indique à hibernate la colomne associé à cette variable.
		@Column(name = "score")
		/**
		 * Permet d'obtenir le score de la partie.
		 * @return le score de la partie.
		 */
		public long getScore() {
			return this.score;
		}
		/**
		 * Permet de changer le score de la partie.
		 * @param score : nouveau score de la partie.
		 */
		public void setScore(long score) {
			this.score = score;
		}
		
		
		//indique à hibernate la colomne associé à cette variable.
		@Column(name = "date")
		/**
		 * Permet d'obtenir la date de la partie.
		 * @return la date de la partie.
		 */
		public Timestamp getDate() {
			return date;
		}
		/**
		 * Permet de changer la date de la partie.
		 * @param date : la date de la partie.
		 */
		public void setDate(Timestamp date) {
			this.date = date;
		}
		
}
