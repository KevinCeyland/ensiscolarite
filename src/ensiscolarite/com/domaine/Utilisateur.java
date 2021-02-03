package ensiscolarite.com.domaine;

public class Utilisateur {
	
	// Attributs 
	
	protected int id;
    protected String nom;
    protected String prenom;
    protected String email;
    protected String telephone;
    protected String rue;
    protected String ville;
    protected String codepostal;
    protected String typeUser;
    
    // Getters et Setters 
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }
    public void setVille(String ville) {
        this.ville = ville;
    }
    public String getCodepostal() {
        return codepostal;
    }
    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	
	public String getTypeUser() {
		return typeUser;
	}
	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}
	
	
	// Constructeurs 
	
	public Utilisateur() {
		
		
	}
	public Utilisateur(int id, String nom, String prenom, String email, String telephone, String rue, String ville,
			String codepostal) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.ville = ville;
		this.codepostal = codepostal;
	}
	public Utilisateur(int id, String nom, String prenom, String email, String telephone, String rue, String ville,
			String codepostal, String typeUser) {
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.ville = ville;
		this.codepostal = codepostal;
		this.typeUser = typeUser;
	}
	@Override
	public String toString() {
		return "\n Utilisateur : " + nom + " " + prenom + " " + "\n adresse email : " + email + " numéro de téléphone : " + telephone + "\n Adresse : " + rue  + " " + ville + " " + codepostal + "\n";
	}
	
}
