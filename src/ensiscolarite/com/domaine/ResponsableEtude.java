package ensiscolarite.com.domaine;

public class ResponsableEtude extends Utilisateur {
	
	// Attributs
 	protected String login;
    protected String motDePasse;
    
 
    // Getters and Setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getMotDePasse() {
        return motDePasse;
    }


    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }
    
    //  Constructeur héritant des propriétés de la class Utilisateur
    public ResponsableEtude(int id, String nom, String prenom, String email, String telephone, String rue,String ville,String codepostal) {
    	
        super(id, nom, prenom, email, telephone, rue, ville, codepostal);
    }
    public ResponsableEtude(int id, String nom, String prenom, String email, String telephone, String rue,String ville,String codepostal, String typeUser, String login, String motDePasse) {
    	
        super(id, nom, prenom, email, telephone, rue, ville, codepostal, typeUser);
        this.login= login;
        this.motDePasse= motDePasse;
    }
    public ResponsableEtude(Utilisateur utilisateur) {
    	
    	super(utilisateur.getId(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getTelephone(), utilisateur.getRue(), utilisateur.getVille(), utilisateur.getCodepostal());
    	
    }

	public String toString() {
		return "Le responsable d'études " + nom + " " + prenom + "";
	}

	
    
    
   

}

