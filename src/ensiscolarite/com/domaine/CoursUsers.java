package ensiscolarite.com.domaine;

public class CoursUsers {
	
	
	//Attributs
	private int id;
	private int idUser;
	private int idCours;
	
	//Constructeurs
	
	public CoursUsers() {
		
	}
	
	public CoursUsers(int id, int idUser, int idCours) {
		this.id = id;
		this.idUser = idUser;
		this.idCours = idCours;
	}
	
	//Getters and Setters
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

	@Override
	public String toString() {
		return "CoursUsers [id=" + id + ", idUser=" + idUser + ", idCours=" + idCours + "]";
	}
	
	

}
