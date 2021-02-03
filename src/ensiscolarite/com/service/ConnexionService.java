package ensiscolarite.com.service;

import java.util.Scanner;

import ensiscolarite.com.dao.ApplicationDao;
import ensiscolarite.com.domaine.Utilisateur;
import ensiscolarite.com.exception.ServiceException;

public class ConnexionService {
	
	/*
	 * Methode permettant de l'authentification d'un utilisateur en lui demandant au préalable ses identifications de connexion.
	 * Elle prend en paramètre rien et elle renvoit un objet de type Utilisateur.
	 */
	public Utilisateur authentication() throws ClassNotFoundException, ServiceException {
		Utilisateur utilisateur = null;
		try {
			// Déclaration et instanciation de variable afin de permettre la saisis d'information dans la console.
			Scanner sc = new Scanner(System.in);
			//Déclaration et instanciation de la variable dao afin d'en utiliser ses méthodes.
			ApplicationDao dao = new ApplicationDao();
			
			// Récupération du login et mot de passe saisis
			System.out.println("----------------------------------------------- Login : -----------------------------------------------");
			String login = sc.nextLine();
			System.out.println("----------------------------------------------- Mot de passe : -----------------------------------------------");
			String password = sc.nextLine();
			// Déclaration d'une variable utilisateur de type Utilsateur se voyant affecter le resultat de la methode authentication
			// de l'objet dao prennant en paramètre le login et le mot de passe.
			utilisateur = dao.authentication(login, password);
			// Condition permettant de vérifier si l'utilisateur est trouvé dans la table connexion, on renvoit que le mot de passe est incorrect.
			// Sinon nous disons bonjour à notre utilisateur.
			if(utilisateur.getNom() == null && utilisateur.getPrenom() == null && utilisateur.getEmail() == null)
			{
				System.out.println("\n----------------------------------------------- Le mot de passe ou le login est incorrect.\n----------------------------------------------- Veuillez recommencer s'il vous plait ! -----------------------------------------------\n");
				
			}
			else
			{
				System.out.println("\n----------------------------------------------- Bonjour " + utilisateur.getPrenom() + "! -----------------------------------------------\n");
			}
			// On retourne l'utilisateur connecté.

		}
		catch (Exception e)
		{
			throw new ServiceException(e.getMessage());
		}
		return utilisateur;
		
		
	}
	

}
