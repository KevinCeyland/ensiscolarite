package ensiscolarite.com.domaine;

public class Ecole {

    // Attributs

    private String nom;
    private String adresseMail;
    private String rue;
    private String ville;
    private String codePostal;
    private String numTel;
    private Directeur directeur;
    
    // Constructeur
    
    public Ecole(String nom, String adresseMail, String rue, String ville, String codePostal, String numTel) {
        super();
        this.nom = nom;
        this.adresseMail = adresseMail;
        this.rue = rue;
        this.ville = ville;
        this.codePostal = codePostal;
        this.numTel = numTel;
    }

    // Getters and Setters

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public Directeur getDirecteur() {
        return directeur;
    }

	@Override
	public String toString() {
		return "L'école " +  nom + " situé au " + rue + " à, " + ville	+ "," + codePostal + ". \n Numéro de téléphone : " + numTel + " adresse email : " + adresseMail + "\n";
	}
    
    

}
