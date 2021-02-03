package ensiscolarite.com.domaine;

import java.util.ArrayList;

public class  Directeur extends ResponsableEtude {

    //  Constructeur héritant des propriétés de la class Utilisateur

	public Directeur(int id, String nom, String prenom, String adresseMail, String numTel, String rue,String ville ,String codePostal) {
        super(id, nom, prenom, adresseMail, numTel, rue, ville, codePostal);
    }
    public Directeur(int id, String nom, String prenom, String adresseMail, String numTel, String rue,String ville ,String codePostal, String typeUser, String login, String motDePasse) {
        super(id, nom, prenom, adresseMail, numTel, rue, ville, codePostal, typeUser, login, motDePasse);
    }
    public Directeur(Utilisateur utilisateur) {
    	super(utilisateur.getId(), utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getTelephone(), utilisateur.getRue(), utilisateur.getVille(), utilisateur.getCodepostal());
    }



	@Override
	public String toString() {
		return "le Directeur s'appelle " + nom + " " + prenom;
	}
    

}
