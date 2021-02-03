package ensiscolarite.com.domaine;

import java.util.ArrayList;

public class Etudiant extends Utilisateur {

	// Attributs 
	
	private String dateNaissance;
	private ArrayList<Cours> lesCours;
	// Getters et Setters
	
	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public ArrayList<Cours> getLesCours() {
		return lesCours;
	}

	public void setLesCours(ArrayList<Cours> lesCours) {
		this.lesCours = lesCours;
	}
	//  Constructeur héritant des propriétés de la class Utilisateur
	
	public Etudiant(int id, String nom, String prenom, String email, String telephone, String rue,String ville,String codepostal, String dateNaissance) {
		super(id, nom, prenom, email, telephone, rue, ville, codepostal);
		this.dateNaissance = dateNaissance;
	}
	public Etudiant(int id, String nom, String prenom, String email, String telephone, String rue,String ville,String codepostal, String typeUser, String dateNaissance, ArrayList<Cours> lesCours) {
		super(id, nom, prenom, email, telephone, rue, ville, codepostal, typeUser);
		this.dateNaissance = dateNaissance;
		this.lesCours = lesCours;
	}
	
	public String toString() {
		String str="\n Nom et prénom de l'étudiant : " + nom + " " + prenom + " née le : " + dateNaissance + "\n Adresse e-mail : " + email + ", numéro de téléphone : " + telephone + "\n Adresse : " + rue  + " " + ville + " " + codepostal + "\n";
		if(lesCours.isEmpty()) {
			
		}else {
			str="\n Nom et prénom de l'étudiant : " + nom + " " + prenom + " née le : " + dateNaissance + "\n Adresse e-mail : " + email + ", numéro de téléphone : " + telephone + "\n Adresse : " + rue  + " " + ville + " " + codepostal + "\n Et est inscrit au(x)  "+lesCours+"\n";
		} 
		return str;
	}

	
	
	
}
