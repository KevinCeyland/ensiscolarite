package ensiscolarite.com.dao;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;

import ensiscolarite.com.domaine.Cours;
import ensiscolarite.com.domaine.CoursUsers;
import ensiscolarite.com.domaine.Etudiant;
import ensiscolarite.com.domaine.Utilisateur;
import ensiscolarite.com.exception.DatabaseDriverException;
import ensiscolarite.com.exception.DatabaseException;
import ensiscolarite.com.exception.DatabaseInsertionException;
import ensiscolarite.com.exception.DatabaseRecuperationException;

public class ApplicationDao {
	
	/*
	 * Methode permettant l'authentification d'un utilisateur sur la plateforme Ensiscolarite prennant en paramètre : le login, et le mot de passe
	 * et retournant un objet de type Utilisateur
	 */
	public Utilisateur authentication(String login, String password) throws DatabaseDriverException,DatabaseException, ClassNotFoundException {
		// Déclaration  et instanciation d'un objet de type Utilisateur et affectation dans la variable utilisateur.
        Utilisateur utilisateur = new Utilisateur();
		try { 
			// URL et identifiant de connexion à la base de données MySQL..
	         String jdbcURL = "jdbc:mysql://localhost:3306/ensiscolarite";
	         String dbUser = "root";
	         String dbPassword = "";
	         // Class permettant de charger les Driver de connexion à MySQL
	         try {
	         Class.forName("com.mysql.jdbc.Driver");
	         }catch (ClassNotFoundException e) {
	        	 throw new DatabaseDriverException("Code 510: Problème lors du chargement du driver, contactez un administrateur");
	         }
	         // Objet de type Connection permettant d'établir la connexion avec la base.
	         Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
	         // Requêtes permettant de vérifier si l'utilisateur a bien des identifiants de connexion dans la table "connexion" et d'ensuite récupérer son id afin 
	         // de récupérer ses informations dans la table "users"
	         String sql = "SELECT idUser FROM connexion WHERE login = ? and password = ?";
	         String sql1 = "SELECT * FROM USERS u INNER JOIN TYPE_USER tu ON u.idTypeUser = tu.id WHERE u.id = ? ";
	         // Préparation des requêtes.
	         PreparedStatement statement = connection.prepareStatement(sql);
	         PreparedStatement st = connection.prepareStatement(sql1);
	         // Parametre de la première requête.
	         statement.setString(1, login);
	         statement.setString(2, password);
	  
	         // Éxécution et récupération du resultat de la première requête
	         ResultSet result = statement.executeQuery();
	         
	         // Si la première requête retourne un résultat on met "idUser" dans le paramètre de la deuxième requête sql
	         if (result.next()) {
	            
	             st.setInt(1, result.getInt("idUser"));
	             
	         }
	         // Sinon on retourne la variable l'utilisateur vide.
	         else {
	        	 return utilisateur;
	         }
	         // Éxécution et récupération du resultat de la deuxième requête
	         ResultSet rs = st.executeQuery();
	        
	         // Si la deuxième requête retourne un resultat, on récupérer ses informations et on les affectes à des vériables afin de construire notre
	         // objet Utilisateur avec ces propriétés.
	         if (rs.next()) {
	            
	        	 int id = rs.getInt("id");
	        	 String nom = rs.getString("nom");
		         String prenom = rs.getString("prenom");
		         String email = rs.getString("email");
		         String telephone = rs.getString("telephone");
		         String rue = rs.getString("rue");
		         String ville = rs.getString("ville");
		         String codePostal = rs.getString("codePostal");
		         String typeUser = rs.getString("libelle");
		        
		         utilisateur.setId(id);
		         utilisateur.setNom(nom);
		         utilisateur.setPrenom(prenom);
		         utilisateur.setEmail(email);
		         utilisateur.setTelephone(telephone);
		         utilisateur.setRue(rue);
		         utilisateur.setVille(ville);
		         utilisateur.setCodepostal(codePostal);
		         utilisateur.setTypeUser(typeUser);

	         }
	         // Fermeture de la connection à la base de données
	         connection.close();
	         // On retourne l'objet utilisateur complété.
	         return utilisateur;
		} 
		catch (SQLException e)
		{
			throw new DatabaseException("CODE 500 : Une erreur est survenu lors de la connexion à la base de données");
		}

			
	
	}
	/*
	 * Methode permettant de faire une action en base de données necéssitant pas de retourner de valeur provenant de la base.
	 * Elle prend en paramètre une requête sql de type String que l'on aura écrite au préalable.
	 * Et elle retourne une variable de type string confirmant la réussite de l'intéraction
	 * 
	 */
	public String interactionEnBase(String sql) throws Exception,DatabaseDriverException,DatabaseInsertionException
	{
		// URL et identifiant de connexion à la base de données MySQL.
		String url = "jdbc:mysql://localhost/ensiscolarite";
		String login = "root";
		String passwd = "";
		String reponse = "";
		// Objet de type Connection permettant d'établir la connexion avec la base.
		Connection cn = null;
		// Execute le code et si il rencontre une erreur dans le try, l'erreur est alors capturée dans le catch et un message nous parvient
		try {
			// Class permettant de charger les Driver de connexion à MySQL
			Class.forName("com.mysql.jdbc.Driver");
			// Objet de type Connection permettant d'établir la connexion avec la base.
			cn = DriverManager.getConnection(url, login, passwd);
			 // Préparation de la requête.
			PreparedStatement prep1 =  cn.prepareStatement(sql);
			// Execution de la requête
			prep1.executeUpdate();
			reponse = "Action bien effectuée !";
		}catch (SQLException e){
			throw new DatabaseInsertionException(" Code 506 : Une erreur est survenue avec lors de l'insertion des données");
			
		}catch (ClassNotFoundException e){
			throw new DatabaseDriverException("CODE 505  : Classe Driver introuvable, contactez l'administrateur");
			
		}catch (Exception e){
			throw new DatabaseException("CODE 504  : Problème lors de l'intéraction avec la base de données");
			
		}
		 
		{
			try {
				// Libérer les ressources de la mémoire, en fermant la connexion à la base.
				cn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return reponse;
	}
	/*
	 * Methode permettant de récupérer des données concernant des étudiants en base.
	 * Elle prend en paramètre une variable de type string contenant la requête SQL.
	 * Et elle retourne un ArrayList de type Etudiant. (Le choix d'un ArrayList permet tout aussi bien de récupérer un étudiant ou plusieurs,
	 * cela permet de ne pas écrire une deuxième méthode pour ça).
	 */
	public ArrayList<Etudiant> recuperationEnBase(String sql) throws DatabaseException, DatabaseDriverException {
		
		// Déclaration de varaible
		Connection conn = null;
		ResultSet rs= null;
		Etudiant etudiantTrouver = null;
		// Déclaration et instanciation d'une variable étudiants de type ArrayList d'étudiant 
		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
		
	      try {
	         try {
	        	// Objet de type Connection permettant d'établir la connexion avec la base.
	            Class.forName("com.mysql.jdbc.Driver");
	         } catch (ClassNotFoundException e) {
	            throw new DatabaseDriverException(" Code 507 : Classe Driver introuvable, contactez un administrateur ");
	         }
	         // Objet de type Connection permettant d'établir la connexion avec la base.
	         conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ensiscolarite", "root", "");
	         // Préparation de la requête.
	         PreparedStatement prep1 = conn.prepareStatement(sql);
	         // Éxécution et récupération du resultat de la requête
	         rs = prep1.executeQuery();
	         // Tant que la requête retourne un resultat, on récupérer ses informations et on les affectes à des vériables afin de construire notre
	         // objet Utilisateur avec ces propriétés puis on affecte l'objet à l'ArrayList etudiants.
	         while(rs.next())
	         {
	        	 int id = rs.getInt("id");
	        	 String nom = rs.getString("nom");
		         String prenom = rs.getString("prenom");
		         String email = rs.getString("email");
		         String telephone = rs.getString("telephone");
		         String rue = rs.getString("rue");
		         String ville = rs.getString("ville");
		         String codePostal = rs.getString("codePostal");
		         String dateNaissance = rs.getString("dateNaissance");
		         etudiantTrouver = new Etudiant(id, nom, prenom, email, telephone, rue,ville, codePostal, dateNaissance);
		         etudiants.add(etudiantTrouver);
	         }
	       
	 
	      } catch (SQLException excep) {
	    	  throw new DatabaseRecuperationException("CODE 504  : Problème lors de la récupération dans la base de données");
	      } catch (Exception excep) {
	    	  System.out.println("CODE 500  : Problème lors de la récupération dans la base de données");
	      } finally {
	         try {
	        	// Libérer les ressources de la mémoire, en fermant la connexion à la base.
	            conn.close();
	         } catch (SQLException se) {}
	         try {
	            if (conn != null)
	            // Libérer les ressources de la mémoire, en fermant la connexion à la base.
	            conn.close();
	         } catch (SQLException se) {
	            se.printStackTrace();
	         }
	      }
	      // On retourne l'ArrayList etudiants
	      return etudiants;
		   
		}
	/*
	 * Methode permettant de récupérer des données concernant des cours en base.
	 * Elle prend en paramètre une variable de type string contenant la requête SQL.
	 * Et elle retourne un ArrayList de type Cours. 
	 */
	public ArrayList<Cours> recuperationCoursEnBase(String sql) throws DatabaseException {
		// Déclaration de varaible
		Connection conn = null;
		ResultSet rs= null;
		Cours coursTrouver = null;
		// Déclaration et instanciation d'une variable cours de type ArrayList de Cours 
		ArrayList<Cours> lesCours = new ArrayList<Cours>();
		
	      try {
	         try {
	        	// Objet de type Connection permettant d'établir la connexion avec la base
	            Class.forName("com.mysql.jdbc.Driver");
	         } catch (Exception e) {
	            System.out.println(e);
	         }
	         // Objet de type Connection permettant d'établir la connexion avec la base.
	         conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ensiscolarite", "root", "");
	         // Préparation de la requête.
	         PreparedStatement prep1 = conn.prepareStatement(sql);
	         // Éxécution et récupération du resultat de la requête
	         rs = prep1.executeQuery();
	         // Tant que la requête retourne un resultat, on récupérer ses informations et on les affectes à des vériables afin de construire notre
	         // objet Cours avec ces propriétés puis on affecte l'objet à l'ArrayList Cours.
	         while(rs.next())
	         {
	        	 int id = rs.getInt("id");
	        	 String theme = rs.getString("theme");
	        	 int nbHeure = rs.getInt("nbHeure");
	        	 coursTrouver = new Cours(id, theme, nbHeure);
		         lesCours.add(coursTrouver);

	         }
	       
	         
	      } catch (SQLException excep) {
	    	  throw new DatabaseRecuperationException("CODE 504  : Problème lors de la récupération dans la base de données");
	      } catch (Exception excep) {
	         excep.printStackTrace();
	      } finally {
	         try {
	        	// Libérer les ressources de la mémoire, en fermant la connexion à la base.
	            conn.close();
	         } catch (SQLException se) {}
	         try {
	            if (conn != null)
	            	// Libérer les ressources de la mémoire, en fermant la connexion à la base.
	            conn.close();
	         } catch (SQLException se) {
	            se.printStackTrace();
	         }
	      }
	   // On retourne l'ArrayList Cours
	      return lesCours;
		   
		}
	//On tente de récupérer la liste de tout les cours associés aux users
	public ArrayList<CoursUsers> recupererListeCoursUsers(String sql) throws DatabaseException{
		Connection conn = null;
		ResultSet rs= null;
		CoursUsers laListe = null;
		ArrayList<CoursUsers> lesCoursUsers= new ArrayList<CoursUsers>();;
		
		 try {
	         try {
	        	// Objet de type Connection permettant d'établir la connexion avec la base
	            Class.forName("com.mysql.jdbc.Driver");
	         } catch (Exception e) {
	            System.out.println(e);
	         }
	         // Objet de type Connection permettant d'établir la connexion avec la base.
	         conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ensiscolarite", "root", "");
	         // Préparation de la requête.
	         PreparedStatement prep1 = conn.prepareStatement(sql);
	         // Éxécution et récupération du resultat de la requête
	         rs = prep1.executeQuery();
	         // Tant que la requête retourne un resultat, on récupérer ses informations et on les affectes à des vériables afin de construire notre
	         // objet Cours avec ces propriétés puis on affecte l'objet à l'ArrayList Cours.
	         while(rs.next())
	         {
	        	 int id = rs.getInt("id");
	        	 int idUser = rs.getInt("idUser");
	        	 int idCours = rs.getInt("idCours");
	        	 laListe = new CoursUsers(id, idUser, idCours);
		         lesCoursUsers.add(laListe);
	         }
	       
	         return lesCoursUsers;
	      } catch (SQLException excep) {
	    	  throw new DatabaseRecuperationException("CODE 504  : Problème lors de la récupération dans la base de données");
	      } catch (Exception excep) {
	    	  System.out.println("CODE 500  : Problème lors de la récupération dans la base de données");
	      } finally {
	         try {
	        	// Libérer les ressources de la mémoire, en fermant la connexion à la base.
	            conn.close();
	         } catch (SQLException se) {}
	         try {
	            if (conn != null)
	            	// Libérer les ressources de la mémoire, en fermant la connexion à la base.
	            conn.close();
	         } catch (SQLException se) {
	            se.printStackTrace();
	         }
	      }
		
		return lesCoursUsers;
	}
}
