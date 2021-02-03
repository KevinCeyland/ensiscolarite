package ensiscolarite.com.service;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ensiscolarite.com.dao.ApplicationDao;
import ensiscolarite.com.domaine.Cours;
import ensiscolarite.com.domaine.CoursUsers;
import ensiscolarite.com.domaine.Etudiant;
import ensiscolarite.com.exception.ServiceException;
import ensiscolarite.com.exception.ServiceValidateDateException;

public class UtilisateurService {
	
	// D�claration et instanciation d'une variable private dao afin d'en utiliser ses m�thodes dans toute les m�thodes de la class UtilisateurService.
	private ApplicationDao dao = new ApplicationDao();
	// D�claration et instanciation d'un ArrayList de type Etudiant private etudiants afin d'en utiliser ses m�thodes dans toute les m�thodes de la class UtilisateurService.
	private ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
	
	
	
	 public static boolean validateJavaDate(String strDate) throws ServiceValidateDateException
	   {
		/* Check if date is 'null' */
		if (strDate.trim().equals(""))
		{
		    return true;
		}
		/* Date is not 'null' */
		else
		{
		    /*
		     * Set preferred date format,
		     * For example MM-dd-yyyy, MM.dd.yyyy,dd.MM.yyyy etc.*/
		    SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
		    sdfrmt.setLenient(false);
		    /* Create Date object
		     * parse the string into date 
	             */
		    try
		    {
		        Date javaDate = sdfrmt.parse(strDate); 
		    }
		    /* Date format is invalid */
		    catch (ParseException e)
		    {
		    	throw new ServiceValidateDateException("Code 580 : Le format de date "+strDate+" n'est pas un format de date valide :YYYY-MM-DD ");
		        
		    }
		    /* Return true if date format is valid */
		    return true;
		}
	   }
	 	public Boolean validateEmail(String email) {
	 		
	 		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
	 		Matcher matcher = pattern.matcher(email);
	 		if(matcher.matches())
	 		{

	 			return true;
	 		}
	 		else
	 		{
	 			System.out.println("L'adresse email saisis n'est pas valide !");
	 			return false;
	 		}
	 	}
	
	
	/*
	 * Methode permettant de cr�e un �tudiant en demandant la saisis des propri�t�s d'un Etudiant
	 * Et d'�crire la requ�te permettant l'insertion dans la base de donn�es d'un �tudiant.
	 * Elle ne retourne une variable de type String contenant un message venu du Dao.
	 */
    public String creerEtudiant() throws ServiceException,ServiceValidateDateException{
    	
    		String resp = null;
    		try {
    			Boolean error = false;
        		String nom = null;
        		String prenom = null;
        		String email= null;
        		String telephone= null;
        		String rue= null;
        		String ville = null;
        		String codePostal = null;
        		String dateNaissance = null;
        		while(error == false) {
        			
        			Scanner sc = new Scanner(System.in);
            		System.out.println(" ----------------------- Nom : ");
            		nom = sc.nextLine();
            		System.out.println(" ----------------------- Prenom : ");
            		prenom = sc.nextLine();
            		System.out.println(" ----------------------- Email : ");
            		email = sc.nextLine();
            		System.out.println("----------------------- T�l�phone : ");
            		telephone = sc.nextLine();
            		System.out.println(" ----------------------- Rue : ");
            		rue = sc.nextLine();
            		System.out.println(" ----------------------- Ville : ");
            		ville = sc.nextLine();
            		System.out.println(" ----------------------- Code Postal : ");
            		codePostal = sc.nextLine();
            		System.out.println(" ----------------------- Date de naissance (YYYY-MM-DD) : ");
            		dateNaissance = sc.nextLine();
            		
            		error = true;
            		error = validateJavaDate(dateNaissance);
            		error = validateEmail(email);
            		
            		if(nom.equals("") || prenom.equals("")|| email.equals("") || telephone.equals("") || rue.equals("") || ville.equals("") || codePostal.equals("") || dateNaissance.equals("")) {
            			System.out.println("Tous les champs doivent �tre remplie ! Veuillez recommencer votre saisie !");
            			error = false;
            		}
            		
            		
        		}

        		
            	String sql = "INSERT INTO `users` (`nom`, `prenom`, `email`, `telephone`,`rue`, `ville`, `codePostal`, `dateNaissance`, `idTypeUser`) VALUES ('"+ nom +"','"+ prenom +"','"+ email +"','"+ telephone +"','"+ rue + "','"+ ville + "','"+ codePostal +"','"+ dateNaissance +"', 1)";
            	// R�cup�ration de ce que retourne la m�thode interactionEnBase et affectation a une variable resp de type String.
            	resp = dao.interactionEnBase(sql);
            	
            	return resp;
    		}catch (ServiceValidateDateException e) {
    			throw new ServiceValidateDateException(e.getMessage());
    			
    		}
    		catch (Exception e)
    		{
    			throw new ServiceException(e.getMessage());
    		}
    }

    /*
     * Methode permettant en demandant la saisis d'un nom et pr�nom d'un Etudiant de le rechercher dans la base et ensuite
     * d'utiliser la m�thode r�cup�rationEnBase du dao afin de retourner toute les informations de l'�tudiant rechercher.
     */
    public ArrayList<Etudiant> lireInformationEtudiant() throws ServiceException {
    	try
    	{
    		Scanner sc = new Scanner(System.in);
    		System.out.println(" ----------------------- Nom : ");
    		String nom = sc.nextLine();
    		System.out.println(" ----------------------- Prenom : ");
    		String prenom = sc.nextLine();
        	String sql = "SELECT * from users where nom = '"+  nom + "' and prenom = '"+ prenom +"' and idTypeUser = 1";
        	
        	etudiants = dao.recuperationEnBase(sql);
        	// Si l'ArrayList d'�tudiant est vide, nous affichons un message et nous retournons l'ArrayList vide
        	if(etudiants.isEmpty())
        	{
        		System.out.println("----------------------------------------------- Aucun �tudiant trouv� ! -----------------------------------------------");
        		return etudiants;
        	}
        	// Sinon nous retournons l'ArrayList d'�tudiants contenant l'�tudiant trouv�.
        	else
        	{
        		return etudiants;
        	}
        	
    	}
    	catch (Exception e)
    	{
    		throw new ServiceException(e.getMessage());
    	}
    }
    /*
     * M�thode permettant de rechercher � l'aide de la m�thode lireInformationEtudiant ci-dessus un �tudiant, puis
     * d'afficher les cours puis de proposer a l'utilisateur de choisir lequel des cours aff�ct� a l'�tudiant.
     * Elle ne prend pas de param�tre et retourne un message de confirmation.
     */
    public String associerCoursEtudiant() throws ServiceException {
    	String response = null;
    	try {
    		// On d�clare une variable etudiants de type ArrayList d'�tudiant qui se verra affect� un ArrayList d'�tudiant provenant de la m�thode lireInformationEtudiant
        	// ArrayList contenant bien sur un �tudiant.
        	ArrayList<Etudiant> etudiants = lireInformationEtudiant();
        	Etudiant etudiant = null;
        	if(etudiants.isEmpty()) {
        		return "----------------------------------------------- Veuillez entrer les informations d'un �tudiant pr�sent dans la base -----------------------------------------------";
        	}
        	else
        	{
        		// On r�cup�re le premier etudiant de la liste etudiants et on l'affecte � une variable etudiant de type Etudiant
            	etudiant = etudiants.get(0);
        	}
        	ArrayList<Cours> lesCours = null;
        	Scanner sc = new Scanner(System.in);
        	
        	// On r�cup�re l'id de l'�tudiant et on l'affecte a une variable idUser de type int
        	int idUser = etudiant.getId();
        	String sql = "SELECT * FROM COURS";
        	// On utilise la m�thode recuperationCoursEnBase qui nous permet de r�cup�rer un ArrayList de Cours.
        	lesCours = dao.recuperationCoursEnBase(sql);
        	// On fait un for permettant l'affichage de chaque cours de l'ArrayList lesCours.
        	for(Cours cours : lesCours)
        	{
        		System.out.println(cours.toStringId());
        	}
        	System.out.println("----------------------------------------------- Quel cours voulez vous associ� a cet �tudiant ? (tapez l'id du cours) -----------------------------------------------");
        	String idCours = sc.nextLine();
        	// Requ�te permettant de associ� un �tudiant � un cours
        	String sql2 = "INSERT INTO COURS_USER (idUser, idCours) VALUES ('"+ idUser +"', '"+ idCours +"')";
        	// R�cup�ration de la r�ponse du dao dans une variable response de type String
        	response = dao.interactionEnBase(sql2);
        	
        	return response;
    	}
    	catch (Exception e)
    	{
    		throw new ServiceException(e.getMessage());
    	}
    }

    /*
     * Methode permettant de modifier les informations concernant un �tudiant en particulier, 
     * Elle utilise la m�thode lireInforationEtudiant afin de rechercher et lister dans un premier temps toute les informations
     * concernant un �tudiant puis propose � l'utilisateur d'entr�e les nouvelles valeurs et enfin utilise la m�thode
     * int�ractionEnBase du dao pour enregistrer les nouvelles donn�es dans la base de donn�es.
     * Elle retourne la confirmation de la modification re�u de la part du dao.
     */
    public String modifierEtudiant() throws ServiceException {
    	String resp = null;
    	try 
    	{
    		ArrayList<Etudiant> etudiants = lireInformationEtudiant();
        	etudiants=coursUsers(etudiants);
        	Etudiant etudiant = null;
    		Boolean error = false;
    		String nom = null;
    		String prenom = null;
    		String email= null;
    		String telephone= null;
    		String rue= null;
    		String ville = null;
    		String codePostal = null;
    		String dateNaissance = null;
        	if(etudiants.isEmpty()) {
        		return "----------------------------------------------- Veuillez entrer les informations d'un �tudiant pr�sent dans la base -----------------------------------------------";
        	}
        	else
        	{
        		etudiant = etudiants.get(0);
        	}
        	System.out.println(etudiant + "\n----------------------------------------------- Modifier maintenant ses informations -----------------------------------------------");
        	
        	while(error == false) {
        		
        		Scanner sc = new Scanner(System.in);
        		System.out.println(" ----------------------- Nom : ");
        		nom = sc.nextLine();
        		System.out.println(" ----------------------- Prenom : ");
        		prenom = sc.nextLine();
        		System.out.println(" ----------------------- Email : ");
        		email = sc.nextLine();
        		System.out.println("----------------------- T�l�phone : ");
        		telephone = sc.nextLine();
        		System.out.println(" ----------------------- Rue : ");
        		rue = sc.nextLine();
        		System.out.println(" ----------------------- Ville : ");
        		ville = sc.nextLine();
        		System.out.println(" ----------------------- Code Postal : ");
        		codePostal = sc.nextLine();
        		System.out.println(" ----------------------- Date de naissance (YYYY-MM-DD) : ");
        		dateNaissance = sc.nextLine();
        		
        		error = true;
        		error = validateJavaDate(dateNaissance);
        		error = validateEmail(email);
        		
        		if(nom.equals("") || prenom.equals("")|| email.equals("") || telephone.equals("") || rue.equals("") || ville.equals("") || codePostal.equals("") || dateNaissance.equals("")) {
        			System.out.println("Tous les champs doivent �tre remplie ! Veuillez recommencer votre saisie !");
        			error = false;
        		}
        		
        		
        	}
        	
        	etudiant.setNom(nom);
    		etudiant.setPrenom(prenom);
    		etudiant.setEmail(email);
    		etudiant.setTelephone(telephone);
    		etudiant.setRue(rue);
    		etudiant.setVille(ville);
    		etudiant.setCodepostal(codePostal);
    		etudiant.setDateNaissance(dateNaissance);
    		
    		
        	String sql = "update users set nom='"+ etudiant.getNom() +"',prenom='"+ etudiant.getPrenom() +"',email='"+ etudiant.getEmail() +"',telephone='"+ etudiant.getTelephone() +"', rue='"+ etudiant.getRue() +"',ville='"+ etudiant.getVille() +"',codePostal='"+ etudiant.getCodepostal() +"',dateNaissance='"+ etudiant.getDateNaissance() +"' where id = " + etudiant.getId() + "";
        	resp = dao.interactionEnBase(sql);
        	return resp;
    	}
    	catch (Exception e)
    	{
    		throw new ServiceException(e.getMessage());
    	}
    }
	/*
	 * M�thode permettant de supprimer un �tudiant, elle utilise dans un premier temps la m�thode lireInformationEtudiant qui permet de rechercher et afficher toute les donn�es d'un �tudiant
	 * Puis propose a l'utilisateur de confirmer ou non la suppr�ssion de celui-ci. Si oui elle utilise la m�thode interactionEnBase du dao pour supprimer l'utilisateur en base 
	 * et elle ne confirme que l'�tudiant est supprim� sinon elle retourne le message signifiant que l'�tudiant n'est pas supprim�.
	 */
    public String supprimerEtudiant() throws ServiceException{
    	String resp = "----------------------------------------------- Etudiant non-supprimer -----------------------------------------------";
    	try {
    		ArrayList<Etudiant> etudiants = lireInformationEtudiant();
        	etudiants=coursUsers(etudiants);
        	Etudiant etudiant = null;
        	Scanner sc = new Scanner(System.in);
        	if(etudiants.isEmpty()) {
        		return "----------------------------------------------- Veuillez entrer les informations d'un �tudiant pr�sent dans la base -----------------------------------------------";
        	}
        	else
        	{
        		etudiant = etudiants.get(0);
        	}
        	System.out.println(etudiant + "\n----------------------------------------------- Voulez-vous vraiment supprimer cet �tudiants. (O/N en majuscule) -----------------------------------------------");
        	String reponse = sc.nextLine();
        	if(reponse.equals("O")) {
        		//on supprime tout les cours au quel il etait associ�
        		String sql= "delete from cours_user where idUser='"+etudiant.getId()+"' ";
        		dao.interactionEnBase(sql);
        		//puis on le supprime dans la table users
        		String sql2 = "delete from users where id = '" + etudiant.getId() + "' and idTypeUser = 1";
            	resp = dao.interactionEnBase(sql2);
            	
            	return resp;
        		
        	} else {
        		
        		return resp;
        	}
        	
    	}
    	catch (Exception e)
    	{
    		throw new ServiceException(e.getMessage());
    	}

    }
    /*
     * Methode permettant de r�cup�rer la liste de tous les �tudiants enregistr� dans la base
     * Cette m�thode n'est acc�ssible que par le Directeur 
     * Elle retourne donc un ArrayList d'�tudiants r�cup�rer de la m�thode recuperationEnBase 
     * sinon elle affiche un message notifiant qu'aucun �tudiant est trouv� et elle retourne une liste d'�tudiants vide
     */
    public ArrayList<Etudiant> listerEtudiants() throws ServiceException {

    	try {
    		String sql = "SELECT * from users where idTypeUser = 1";
        	etudiants = dao.recuperationEnBase(sql);
        	if(etudiants.isEmpty())
        	{
        		System.out.println("----------------------------------------------- Aucun �tudiant trouv� ! -----------------------------------------------");
        		return etudiants;
        	}
        	else
        	{
        		return etudiants;
        	} 
    	}
    	catch (Exception e) {
    		throw new ServiceException(e.getMessage());
        }
    	
    }
    
    public Cours trouverUnCour(String sql) throws ServiceException{
    	Cours cour=null;
    	try {
    		ArrayList<Cours> lesCours = new ArrayList<Cours>();
        	
        	lesCours=dao.recuperationCoursEnBase(sql);
        	cour= lesCours.get(0);
        	return cour;
        }
    	catch (Exception e) {
    		throw new ServiceException(e.getMessage());
    	}
    
    }
    	
  
    public ArrayList<Etudiant> coursUsers(ArrayList<Etudiant> lesEtudiants) throws ServiceException {
    	try
    	{
    		String sql="select * from cours_user";
        	ArrayList<CoursUsers> laListeDesCoursAffectes = new ArrayList<CoursUsers>();
        	laListeDesCoursAffectes= dao.recupererListeCoursUsers(sql);
        	for(Etudiant etd : lesEtudiants) {
        		ArrayList<Cours> lesCours= new ArrayList<Cours>();
        		for(CoursUsers cour: laListeDesCoursAffectes) {
        			if(etd.getId()==cour.getIdUser()) {
        				String sql1="select c.id, c.theme, c.nbHeure FROM cours c, cours_user cu WHERE cu.idUser='"+cour.getIdUser()+"' and cu.idCours='"+cour.getIdCours()+"' and  cu.idCours=c.id";
        				lesCours.add(trouverUnCour(sql1));
        			}
        		}
        		//ici ajouter la collec a l'etd
        		etd.setLesCours(lesCours);
        		
        		
        	}  	
        	return lesEtudiants;
        	
    	}catch (Exception e)
    	{
    		throw new ServiceException(e.getMessage());
    	}
 
    }
    
    
    
}
