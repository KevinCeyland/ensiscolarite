package ensiscolarite.com.domaine;

public class Enseignant extends Utilisateur{
	
	// 	Attributs
	private String matiere;
	
	// Getters et Setters 
	public String getMatiere() {
		return matiere;
	}

	public void setMatiere(String matiere) {
		this.matiere = matiere;
	}
	
	// Constructeur héritant des propriétés de la class Utilisateur
	public Enseignant(int id, String nom, String prenom, String email, String telephone,String rue,String ville,String codepostal, String matiere) {
		super(id, nom, prenom, email, telephone, rue, ville, codepostal);
		this.matiere = matiere;
	}
	public Enseignant(int id, String nom, String prenom, String email, String telephone,String rue,String ville,String codepostal,String typeUser, String matiere) {
		super(id, nom, prenom, email, telephone, rue, ville, codepostal, typeUser);
		this.matiere = matiere;
	}

	@Override
	public String toString() {
		return "L'enseignant " + nom + " " + prenom + "";
	}

	
}
