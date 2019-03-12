package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;


final public class Bdd {
	
	static String url = "jdbc:mysql://localhost:3306/Pacman";
    static String utilisateur = "root";
    static String motDePasse = "";
    static Connection connexion = null;
    static Statement statement = null;
    static ResultSet resultat = null;
    private static final String ALGO_CHIFFREMENT = "SHA-256";

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
			String query = "SELECT * FROM Utilisateur WHERE pseudo = '" + pseudo + "';" ;
			resultat = statement.executeQuery(query);
			
			
			
			if (!resultat.next() ) {
				System.out.println("connexion échouée le compte n'existe pas");
				return -1;
			} else {
					//verification du mot de passe 
				 	ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
	                passwordEncryptor.setAlgorithm( ALGO_CHIFFREMENT );         
	                passwordEncryptor.setPlainDigest( false );
	                String motDePasse = resultat.getString("mot_de_passe");
	                
	                if(passwordEncryptor.checkPassword(mdp,motDePasse)){
	                	int identifiant = resultat.getInt( "id" );
	                	return identifiant;
	                } else {
	                	System.out.println("connexion échouée le mot de passe n'est pas le bon");
	                	return -1;
	                }
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
	static public void sendScore(int identifiant,int score, int fantomesManges, int capsulesMangees, int pacGommesMangees, int mapsEffectuees, int pasEffectues){
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
			connexion = DriverManager.getConnection( url, utilisateur, motDePasse );
			statement = connexion.createStatement();
			statement.executeUpdate( "INSERT INTO Partie (idUtilisateur,score,fantomesManges, capsulesMangees, pacGommesMangees, mapsEffectuees, pasEffectues,date) " + "VALUES ('" + identifiant + "','" + score + "','" + fantomesManges + "','" + capsulesMangees + "','" + pacGommesMangees + "','" + mapsEffectuees + "','" + pasEffectues  + "', NOW());" );
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


