package ensiscolarite.com.domaine;

import java.util.ArrayList;

public class Cours {
	
	// Attributs
	int id;
	private String theme;
    private int nombreHeure;
    private ArrayList<Etudiant> lesEtudiants;

    // Getters et Setters
    
    public String getTheme() {
        return theme;
    }
    public void setTheme(String theme) {
        this.theme = theme;
    }
    public int getNombreHeure() {
        return nombreHeure;
    }
    public void setNombreHeure(int nombreHeure) {
        this.nombreHeure = nombreHeure;
    }
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArrayList<Etudiant> getLesEtudiants() {
		return lesEtudiants;
	}
	public void setLesEtudiants(ArrayList<Etudiant> lesEtudiants) {
		this.lesEtudiants = lesEtudiants;
	}
	
    
    // Constructeur 
	public Cours() {
		
	}
	
	public Cours(int id, String theme, int nombreHeure) {
		super();
		this.id = id;
		this.theme = theme;
		this.nombreHeure = nombreHeure;
	}
	
	public String toStringId() {
		return "(id : "+id+") cours de " + theme + " qui est un module de : " + nombreHeure + " heures";
	}
	@Override
	public String toString() {
		return "cours de " + theme + " qui est un module de : " + nombreHeure + " heures";
	}
	
	
	

    
}
