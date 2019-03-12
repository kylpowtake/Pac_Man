package com.sdzee.beans;

import java.sql.Timestamp;


/**
 * Classe utilisée pour représenter une partie.
 * @author auteur principal
 */

public class Partie {
		//L'id de la partie.
		private long id;
		//L'id de l'utilisateur ayant réalisé la partie.
		private long idUtilisateur;
		//Le score de la partie
		private long score;
		//le nombre de fantomes mangés
		private long fantomesManges;
		//le nombre de capsules mangés
		private long capsulesMangees;
		//le nombre de pac-Gommes manges
		private long pacGommesMangees;
		//le nombre de maps jouées
		private long mapsEffectuees;
		//le nombre de tours joués
		private long pasEffectues;
		//Le test du pseudo.
		private String pseudoUtilisateur;
		//Le temps de fin de la partie.
		private Timestamp date;
	
		
		//---------------------------------------------------------------------
		public long getid() {
			return this.id;
		}

		public void setid(long id) {
			this.id = id;
		}
		//---------------------------------------------------------------------
	    public String getPseudoUtilisateur() {
	        return pseudoUtilisateur;
	    }
	
	    public void setPseudoUtilisateur( String pseudoUtilisateur ) {
	        this.pseudoUtilisateur = pseudoUtilisateur;
	    }
	  //---------------------------------------------------------------------
		public long getIdUtilisateur() {
			return this.idUtilisateur;
		}

		public void setIdUtilisateur(long idUtilisateur) {
			this.idUtilisateur = idUtilisateur;
		}
		//---------------------------------------------------------------------
		public long getFantomesManges() {
			return this.fantomesManges;
		}
	
		public void setFantomesManges(long fantomesManges) {
			this.fantomesManges = fantomesManges;
		}
		//---------------------------------------------------------------------
		public long getCapsulesMangees() {
			return this.capsulesMangees;
		}
	
		public void setCapsulesMangees(long capsulesMangees) {
			this.capsulesMangees = capsulesMangees;
		}
		//---------------------------------------------------------------------
		public long getPacGommesMangees() {
			return this.pacGommesMangees;
		}
	
		public void setPacGommesMangees(long pacGommesMangees) {
			this.pacGommesMangees = pacGommesMangees;
		}
		//---------------------------------------------------------------------
		public long getMapsEffectuees() {
			return this.mapsEffectuees;
		}
	
		public void setMapsEffectuees(long mapsEffectuees) {
			this.mapsEffectuees = mapsEffectuees;
		}
		//---------------------------------------------------------------------
		public long getpasEffectues() {
			return this.pasEffectues;
		}
	
		public void setpasEffectues(long pasEffectues) {
			this.pasEffectues = pasEffectues;
		}
		//---------------------------------------------------------------------
		public long getScore() {
			return this.score;
		}
	
		public void setScore(long score) {
			this.score = score;
		}
		//---------------------------------------------------------------------
		public Timestamp getDate() {
			return date;
		}

		public void setDate(Timestamp date) {
			this.date = date;
		}
		
}
