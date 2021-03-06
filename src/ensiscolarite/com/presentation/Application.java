package ensiscolarite.com.presentation;

import java.util.ArrayList;

import java.util.Scanner;

import javax.swing.JFrame;

import org.apache.log4j.Logger;

import ensiscolarite.com.UI.MainFrm;
import ensiscolarite.com.domaine.Directeur;
import ensiscolarite.com.domaine.Etudiant;
import ensiscolarite.com.domaine.ResponsableEtude;
import ensiscolarite.com.domaine.Utilisateur;
import ensiscolarite.com.exception.DatabaseException;
import ensiscolarite.com.exception.ServiceException;
import ensiscolarite.com.exception.ServiceValidateDateException;
import ensiscolarite.com.service.ConnexionService;
import ensiscolarite.com.service.GraphiqueService;
import ensiscolarite.com.service.UtilisateurService;

public class Application {

	public final static Logger databaseLogger = Logger.getLogger("database");
	public final static Logger serviceLogger = Logger.getLogger("service");

	public static void main(String[] args) throws ClassNotFoundException, DatabaseException, ServiceException {
		
		try {
			
			Utilisateur utilisateurConnecter = new Utilisateur();
			System.out.println(
					"\n ----------------------------------------------- | Bienvenue sur Ensiscolarite |  -----------------------------------------------\n\n\n-----------------------------------------------Veuillez vous authentifier s'il vous plait : \n");

			/*
			 * on utilise la m�thode authentication de la class ConnexionService tant que
			 * l'objet utilisateurConnecter � ses propri�t�s nom, prenom et email null,
			 */
			while (utilisateurConnecter.getNom() == null && utilisateurConnecter.getPrenom() == null
					&& utilisateurConnecter.getEmail() == null) {

				ConnexionService connexService = new ConnexionService();

				utilisateurConnecter = connexService.authentication();

				String typeUser = utilisateurConnecter.getTypeUser();
				// Si le typeUser de l'utilisateur connecter est �gal � "ResponsableEtude" on
				// d�clare une variable respEtudes de type ResponsableEtude � laquel on affecte
				// l'instanciation d'un nouvel objet
				// prennant en param�tre de son constructeur les variables ci-dessus
				if (typeUser == "ResponsableEtude") {
					ResponsableEtude respEtudes = new ResponsableEtude(utilisateurConnecter);
				}
				// Sinon si le typeUser de l'utilisateur connecter est �gal � "Directeur" on
				// d�clare une variable directeur de type Directeur � laquel on affecte
				// l'instanciation d'un nouvel objet
				// prennant en param�tre de son constructeur les variables ci-dessus
				else if (typeUser == "Directeur") {

					Directeur directeur = new Directeur(utilisateurConnecter);

				}
			}

			UtilisateurService utilService = new UtilisateurService();
			GraphiqueService graphService = new GraphiqueService();
			Scanner sc = new Scanner(System.in);
			// On d�clare une variable tryagain de type Boolean � laquel on affecte la
			// valeur true.
			Boolean tryagain = true;
			while(true) {
				// On affiche le menu avec les diff�rentes options tant que tryagain est true.
				
				System.out.println(
						"----------------------------------------------- Que voulez-vous faire ? -----------------------------------------------  \n\n (VOUS DEVEZ RESPECTER LES MAJUSCULES) \n ----------------------- Ajouter : tapez A, \n ----------------------- Modifier : tapez M, \n ----------------------- Supprimer : tapez S, \n ----------------------- Rechercher : tapez R, \n ----------------------- Associer un cours à un étudiant : tapez ACE ");
				// Si l'utilisateur connecter est directeur on affiche en plus la possibilit�e
				// d'afficher la liste des utilisateurs
				if (utilisateurConnecter.getTypeUser().equals("Directeur")) {

					System.out.println(" ----------------------- Afficher la liste des utilisateurs : tapez L");
					System.out.println(" ----------------------- Afficher le graphique des notes des etudiants : tapez G ");

				}
				System.out.println(" ----------------------- Quitter l'application : tapez STOP");
				String reponse = sc.nextLine();
				switch (reponse) {
				case "A":
					System.out.println(
							"\n ----------------------------------------------- Ajout un nouvel �tudiant -----------------------------------------------");
					try {
						System.out.println(utilService.creerEtudiant());
					} catch (ServiceValidateDateException e) {
						UtilisateurService utilServiceA = new UtilisateurService();
						System.out.println(e.getMessage() + "OK");
						System.out.println(utilServiceA.creerEtudiant());
					}

					break;
				case "R":
					System.out.println(
							"\n ----------------------------------------------- Rechercher un �tudiant -----------------------------------------------");
					ArrayList<Etudiant> lesEtudiants = utilService.lireInformationEtudiant();
					lesEtudiants = utilService.coursUsers(lesEtudiants);
					for (Etudiant etudiant : lesEtudiants) {
						System.out.println(etudiant);
					}
					break;
				case "M":
					System.out.println(
							"\n----------------------------------------------- Rechercher l'�tudiant � modifier en entrant son nom et puis son pr�nom -----------------------------------------------");
					System.out.println(utilService.modifierEtudiant());

					break;
				case "S":
					System.out.println(
							"\n----------------------------------------------- Rechercher l'�tudiant � supprimer en entrant son nom et puis son pr�nom -----------------------------------------------");
					System.out.println(utilService.supprimerEtudiant());

					break;
				case "ACE":
					System.out.println(
							"\n----------------------------------------------- Rechercher l'�tudiant � associ� � un cours en entrant son nom et puis son pr�nom ----------------------------------------------- ");
					System.out.println(utilService.associerCoursEtudiant());

					break;

				case "STOP":
					System.out.println(
							"\n----------------------------------------------- Arr�t du programme -----------------------------------------------");
					System.exit(0);

					break;
				case "L":
					if (utilisateurConnecter.getTypeUser().equals("Directeur")) {
						System.out.println(
								"\n----------------------------------------------- Affichage des étudiants -----------------------------------------------");
						ArrayList<Etudiant> etudiants = utilService.listerEtudiants();
						etudiants = utilService.coursUsers(etudiants);
						for (Etudiant etudiant : etudiants) {
							System.out.println(etudiant);
						}
						break;
					} else {
						System.out.println(
								"----------------------------------------------- Vous n'êtes pas autoris� a faire cette action -----------------------------------------------");
					}
				case "G":
					
					try {
						System.out.println(
								"----------------------------------------------- Affichage du graphique en cours  -----------------------------------------------");
			            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			                if ("Nimbus".equals(info.getName())) {
			                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
			                    break;
			                }
			            }
			        } catch (ClassNotFoundException ex) {
			            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			        } catch (InstantiationException ex) {
			            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			        } catch (IllegalAccessException ex) {
			            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
			            java.util.logging.Logger.getLogger(MainFrm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			        }
			        //</editor-fold>
					ArrayList<Double> lesMoyennes = graphService.afficherGraphique();
			        /* Create and display the form */
			        java.awt.EventQueue.invokeLater(new Runnable() {
			            public void run() {
			                new MainFrm(lesMoyennes).setVisible(true);
			            }
			        });

					break;

				}
				
			
			}

	
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {

	
		}

	}
}
