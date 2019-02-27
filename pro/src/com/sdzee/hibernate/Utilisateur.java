package com.sdzee.hibernate;

import java.sql.Timestamp;

public class Utilisateur {
    private Long      id;
    private String    motDePasse;
    private String    pseudo;
    private Timestamp dateInscription;

    public Long getId() {
        return id;
    }
    public void setId( Long id ) {
        this.id = id;
    }

    public void setMotDePasse( String motDePasse ) {
        this.motDePasse = motDePasse;
    }
    public String getMotDePasse() {
        return motDePasse;
    }

    public void setPseudo( String pseudo ) {
        this.pseudo = pseudo;
    }
    public String getPseudo() {
        return pseudo;
    }

    public Timestamp getDateInscription() {
        return dateInscription;
    }
    public void setDateInscription( Timestamp dateInscription ) {
        this.dateInscription = dateInscription;
    }
}
