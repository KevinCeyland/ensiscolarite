package ensiscolarite.com.domaine;

public class Note {

	private int id;
	private int idUser;
	private int idCours;
	private double note;
	
	public Note(int id, int idUser, int idCours, double note) {
		this.id = id;
		this.idUser = idUser;
		this.idCours = idCours;
		this.note = note;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdCours() {
		return idCours;
	}

	public void setIdCours(int idCours) {
		this.idCours = idCours;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}
	

}
