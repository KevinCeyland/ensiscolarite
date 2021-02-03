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
	 * Methode permettant l'authentification d'un utilisateur sur la plateforme Ensiscolarite prennant en param�tre : le login, et le mot de passe
	 * et retournant un objet de type Utilisateur
	 */
	public Utilisateur authentication(String login, String password) throws DatabaseDriverException,DatabaseException, ClassNotFoundException {
		// D�claration  et instanciation d'un objet de type Utilisateur et affectation dans la variable utilisateur.
        Utilisateur utilisateur = new Utilisateur();
		try { 
			// URL et identifiant de connexion � la base de donn�es MySQL..
	         String jdbcURL = "jdbc:mysql://localhost:3306/ensiscolarite";
	         String dbUser = "root";
	         String dbPassword = "";
	         // Class permettant de charger les Driver de connexion � MySQL
	         try {
	         Class.forName("com.mysql.jdbc.Driver");
	         }catch (ClassNotFoundException e) {
	        	 throw new DatabaseDriverException("Code 510: Probl�me lors du chargement du driver, contactez un administrateur");
	         }
	         // Objet de type Connection permettant d'�tablir la connexion avec la base.
	         Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
	         // Requ�tes permettant de v�rifier si l'utilisateur a bien des identifiants de connexion dans la table "connexion" et d'ensuite r�cup�rer son id afin 
	         // de r�cup�rer ses informations dans la table "users"
	         String sql = "SELECT idUser FROM connexion WHERE login = ? and password = ?";
	         String sql1 = "SELECT * FROM USERS u INNER JOIN TYPE_USER tu ON u.idTypeUser = tu.id WHERE u.id = ? ";
	         // Pr�paration des requ�tes.
	         PreparedStatement statement = connection.prepareStatement(sql);
	         PreparedStatement st = connection.prepareStatement(sql1);
	         // Parametre de la premi�re requ�te.
	         statement.setString(1, login);
	         statement.setString(2, password);
	  
	         // �x�cution et r�cup�ration du resultat de la premi�re requ�te
	         ResultSet result = statement.executeQuery();
	         
	         // Si la premi�re requ�te retourne un r�sultat on met "idUser" dans le param�tre de la deuxi�me requ�te sql
	         if (result.next()) {
	            
	             st.setInt(1, result.getInt("idUser"));
	             
	         }
	         // Sinon on retourne la variable l'utilisateur vide.
	         else {
	        	 return utilisateur;
	         }
	         // �x�cution et r�cup�ration du resultat de la deuxi�me requ�te
	         ResultSet rs = st.executeQuery();
	        
	         // Si la deuxi�me requ�te retourne un resultat, on r�cup�rer ses informations et on les affectes � des v�riables afin de construire notre
	         // objet Utilisateur avec ces propri�t�s.
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
	         // Fermeture de la connection � la base de donn�es
	         connection.close();
	         // On retourne l'objet utilisateur compl�t�.
	         return utilisateur;
		} 
		catch (SQLException e)
		{
			throw new DatabaseException("CODE 500 : Une erreur est survenu lors de la connexion � la base de donn�es");
		}

			
	
	}
	/*
	 * Methode permettant de faire une action en base de donn�es nec�ssitant pas de retourner de valeur provenant de la base.
	 * Elle prend en param�tre une requ�te sql de type String que l'on aura �crite au pr�alable.
	 * Et elle retourne une variable de type string confirmant la r�ussite de l'int�raction
	 * 
	 */
	public String interactionEnBase(String sql) throws Exception,DatabaseDriverException,DatabaseInsertionException
	{
		// URL et identifiant de connexion � la base de donn�es MySQL.
		String url = "jdbc:mysql://localhost/ensiscolarite";
		String login = "root";
		String passwd = "";
		String reponse = "";
		// Objet de type Connection permettant d'�tablir la connexion avec la base.
		Connection cn = null;
		// Execute le code et si il rencontre une erreur dans le try, l'erreur est alors captur�e dans le catch et un message nous parvient
		try {
			// Class permettant de charger les Driver de connexion � MySQL
			Class.forName("com.mysql.jdbc.Driver");
			// Objet de type Connection permettant d'�tablir la connexion avec la base.
			cn = DriverManager.getConnection(url, login, passwd);
			 // Pr�paration de la requ�te.
			PreparedStatement prep1 =  cn.prepareStatement(sql);
			// Execution de la requ�te
			prep1.executeUpdate();
			reponse = "Action bien effectu�e !";
		}catch (SQLException e){
			throw new DatabaseInsertionException(" Code 506 : Une erreur est survenue avec lors de l'insertion des donn�es");
			
		}catch (ClassNotFoundException e){
			throw new DatabaseDriverException("CODE 505  : Classe Driver introuvable, contactez l'administrateur");
			
		}catch (Exception e){
			throw new DatabaseException("CODE 504  : Probl�me lors de l'int�raction avec la base de donn�es");
			
		}
		 
		{
			try {
				// Lib�rer les ressources de la m�moire, en fermant la connexion � la base.
				cn.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return reponse;
	}
	/*
	 * Methode permettant de r�cup�rer des donn�es concernant des �tudiants en base.
	 * Elle prend en param�tre une variable de type string contenant la requ�te SQL.
	 * Et elle retourne un ArrayList de type Etudiant. (Le choix d'un ArrayList permet tout aussi bien de r�cup�rer un �tudiant ou plusieurs,
	 * cela permet de ne pas �crire une deuxi�me m�thode pour �a).
	 */
	public ArrayList<Etudiant> recuperationEnBase(String sql) throws DatabaseException, DatabaseDriverException {
		
		// D�claration de varaible
		Connection conn = null;
		ResultSet rs= null;
		Etudiant etudiantTrouver = null;
		// D�claration et instanciation d'une variable �tudiants de type ArrayList d'�tudiant 
		ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
		
	      try {
	         try {
	        	// Objet de type Connection permettant d'�tablir la connexion avec la base.
	            Class.forName("com.mysql.jdbc.Driver");
	         } catch (ClassNotFoundException e) {
	            throw new DatabaseDriverException(" Code 507 : Classe Driver introuvable, contactez un administrateur ");
	         }
	         // Objet de type Connection permettant d'�tablir la connexion avec la base.
	         conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ensiscolarite", "root", "");
	         // Pr�paration de la requ�te.
	         PreparedStatement prep1 = conn.prepareStatement(sql);
	         // �x�cution et r�cup�ration du resultat de la requ�te
	         rs = prep1.executeQuery();
	         // Tant que la requ�te retourne un resultat, on r�cup�rer ses informations et on les affectes � des v�riables afin de construire notre
	         // objet Utilisateur avec ces propri�t�s puis on affecte l'objet � l'ArrayList etudiants.
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
	    	  throw new DatabaseRecuperationException("CODE 504  : Probl�me lors de la r�cup�ration dans la base de donn�es");
	      } catch (Exception excep) {
	    	  System.out.println("CODE 500  : Probl�me lors de la r�cup�ration dans la base de donn�es");
	      } finally {
	         try {
	        	// Lib�rer les ressources de la m�moire, en fermant la connexion � la base.
	            conn.close();
	         } catch (SQLException se) {}
	         try {
	            if (conn != null)
	            // Lib�rer les ressources de la m�moire, en fermant la connexion � la base.
	            conn.close();
	         } catch (SQLException se) {
	            se.printStackTrace();
	         }
	      }
	      // On retourne l'ArrayList etudiants
	      return etudiants;
		   
		}
	/*
	 * Methode permettant de r�cup�rer des donn�es concernant des cours en base.
	 * Elle prend en param�tre une variable de type string contenant la requ�te SQL.
	 * Et elle retourne un ArrayList de type Cours. 
	 */
	public ArrayList<Cours> recuperationCoursEnBase(String sql) throws DatabaseException {
		// D�claration de varaible
		Connection conn = null;
		ResultSet rs= null;
		Cours coursTrouver = null;
		// D�claration et instanciation d'une variable cours de type ArrayList de Cours 
		ArrayList<Cours> lesCours = new ArrayList<Cours>();
		
	      try {
	         try {
	        	// Objet de type Connection permettant d'�tablir la connexion avec la base
	            Class.forName("com.mysql.jdbc.Driver");
	         } catch (Exception e) {
	            System.out.println(e);
	         }
	         // Objet de type Connection permettant d'�tablir la connexion avec la base.
	         conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ensiscolarite", "root", "");
	         // Pr�paration de la requ�te.
	         PreparedStatement prep1 = conn.prepareStatement(sql);
	         // �x�cution et r�cup�ration du resultat de la requ�te
	         rs = prep1.executeQuery();
	         // Tant que la requ�te retourne un resultat, on r�cup�rer ses informations et on les affectes � des v�riables afin de construire notre
	         // objet Cours avec ces propri�t�s puis on affecte l'objet � l'ArrayList Cours.
	         while(rs.next())
	         {
	        	 int id = rs.getInt("id");
	        	 String theme = rs.getString("theme");
	        	 int nbHeure = rs.getInt("nbHeure");
	        	 coursTrouver = new Cours(id, theme, nbHeure);
		         lesCours.add(coursTrouver);

	         }
	       
	         
	      } catch (SQLException excep) {
	    	  throw new DatabaseRecuperationException("CODE 504  : Probl�me lors de la r�cup�ration dans la base de donn�es");
	      } catch (Exception excep) {
	         excep.printStackTrace();
	      } finally {
	         try {
	        	// Lib�rer les ressources de la m�moire, en fermant la connexion � la base.
	            conn.close();
	         } catch (SQLException se) {}
	         try {
	            if (conn != null)
	            	// Lib�rer les ressources de la m�moire, en fermant la connexion � la base.
	            conn.close();
	         } catch (SQLException se) {
	            se.printStackTrace();
	         }
	      }
	   // On retourne l'ArrayList Cours
	      return lesCours;
		   
		}
	//On tente de r�cup�rer la liste de tout les cours associ�s aux users
	public ArrayList<CoursUsers> recupererListeCoursUsers(String sql) throws DatabaseException{
		Connection conn = null;
		ResultSet rs= null;
		CoursUsers laListe = null;
		ArrayList<CoursUsers> lesCoursUsers= new ArrayList<CoursUsers>();;
		
		 try {
	         try {
	        	// Objet de type Connection permettant d'�tablir la connexion avec la base
	            Class.forName("com.mysql.jdbc.Driver");
	         } catch (Exception e) {
	            System.out.println(e);
	         }
	         // Objet de type Connection permettant d'�tablir la connexion avec la base.
	         conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/ensiscolarite", "root", "");
	         // Pr�paration de la requ�te.
	         PreparedStatement prep1 = conn.prepareStatement(sql);
	         // �x�cution et r�cup�ration du resultat de la requ�te
	         rs = prep1.executeQuery();
	         // Tant que la requ�te retourne un resultat, on r�cup�rer ses informations et on les affectes � des v�riables afin de construire notre
	         // objet Cours avec ces propri�t�s puis on affecte l'objet � l'ArrayList Cours.
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
	    	  throw new DatabaseRecuperationException("CODE 504  : Probl�me lors de la r�cup�ration dans la base de donn�es");
	      } catch (Exception excep) {
	    	  System.out.println("CODE 500  : Probl�me lors de la r�cup�ration dans la base de donn�es");
	      } finally {
	         try {
	        	// Lib�rer les ressources de la m�moire, en fermant la connexion � la base.
	            conn.close();
	         } catch (SQLException se) {}
	         try {
	            if (conn != null)
	            	// Lib�rer les ressources de la m�moire, en fermant la connexion � la base.
	            conn.close();
	         } catch (SQLException se) {
	            se.printStackTrace();
	         }
	      }
		
		return lesCoursUsers;
	}
}
