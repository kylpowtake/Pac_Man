package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

final public class Bdd {
	
	static String url = "jdbc:mysql://localhost:3306/Pacman";
    static String utilisateur = "root";
    static String motDePasse = "";
    static Connection connexion = null;
    static Statement statement = null;
    static ResultSet resultat = null;

    /**
     * @param pseudo,mdp
     * @return int : -1 si aucun resultat trouvé sinon retourne identifiant 
     * methode permettant de verifier si une personne a un compte
     */
	static public int connect(String pseudo, String mdp){
		try{
			Class.forName( "com.mysql.jdbc.Driver" );
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
			statement = connexion.createStatement();
			String query = "SELECT * FROM Utilisateur WHERE pseudo = '" + pseudo + "'  AND mot_de_passe = '" + mdp + "';" ;
			resultat = statement.executeQuery(query);
			
			if (!resultat.next() ) {
				System.out.println("connexion échouée");
				return -1;
			} else {
		         int identifiant = resultat.getInt( "id" );
		         return identifiant;
			}
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
	    }catch (SQLException e) {
			e.printStackTrace();
		}finally {
            if(resultat != null ) {
                try{
                    resultat.close();
                }catch (SQLException ignore){
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                }catch(SQLException ignore) {
                }
            }
            if(connexion != null) {
                try{
                    connexion.close();
                }catch(SQLException ignore) {
                }
            }
        }
		return -1;
	}
	
	/**
     * @param score
     * methode permettant d'enregistrer le score d'une partie dans la BDD 
     */
	static public void sendScore(int identifiant,int score){
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
			statement = connexion.createStatement();
			statement.executeUpdate( "INSERT INTO Partie (id,score,date) " + "VALUES ('" + identifiant + "','" + score + "', NOW());" );
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
	    }catch (SQLException e) {
			e.printStackTrace();
		}finally {
            if ( resultat != null ) {
                try {
                    resultat.close();
                } catch ( SQLException ignore ) {
                }
            }
            if ( statement != null ) {
                try {
                    statement.close();
                } catch ( SQLException ignore ) {
                }
            }
            if ( connexion != null ) {
                try {
                    connexion.close();
                } catch ( SQLException ignore ) {
                }
            }
        }
	}
}


