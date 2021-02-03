package ensiscolarite.com.service;

import java.util.Scanner;

import ensiscolarite.com.dao.ApplicationDao;
import ensiscolarite.com.domaine.Utilisateur;
import ensiscolarite.com.exception.ServiceException;

public class ConnexionService {
	
	/*
	 * Methode permettant de l'authentification d'un utilisateur en lui demandant au pr�alable ses identifications de connexion.
	 * Elle prend en param�tre rien et elle renvoit un objet de type Utilisateur.
	 */
	public Utilisateur authentication() throws ClassNotFoundException, ServiceException {
		Utilisateur utilisateur = null;
		try {
			// D�claration et instanciation de variable afin de permettre la saisis d'information dans la console.
			Scanner sc = new Scanner(System.in);
			//D�claration et instanciation de la variable dao afin d'en utiliser ses m�thodes.
			ApplicationDao dao = new ApplicationDao();
			
			// R�cup�ration du login et mot de passe saisis
			System.out.println("----------------------------------------------- Login : -----------------------------------------------");
			String login = sc.nextLine();
			System.out.println("----------------------------------------------- Mot de passe : -----------------------------------------------");
			String password = sc.nextLine();
			// D�claration d'une variable utilisateur de type Utilsateur se voyant affecter le resultat de la methode authentication
			// de l'objet dao prennant en param�tre le login et le mot de passe.
			utilisateur = dao.authentication(login, password);
			// Condition permettant de v�rifier si l'utilisateur est trouv� dans la table connexion, on renvoit que le mot de passe est incorrect.
			// Sinon nous disons bonjour � notre utilisateur.
			if(utilisateur.getNom() == null && utilisateur.getPrenom() == null && utilisateur.getEmail() == null)
			{
				System.out.println("\n----------------------------------------------- Le mot de passe ou le login est incorrect.\n----------------------------------------------- Veuillez recommencer s'il vous plait ! -----------------------------------------------\n");
				
			}
			else
			{
				System.out.println("\n----------------------------------------------- Bonjour " + utilisateur.getPrenom() + "! -----------------------------------------------\n");
			}
			// On retourne l'utilisateur connect�.

		}
		catch (Exception e)
		{
			throw new ServiceException(e.getMessage());
		}
		return utilisateur;
		
		
	}
	

}
