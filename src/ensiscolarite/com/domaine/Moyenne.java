package ensiscolarite.com.domaine;

public class Moyenne {
	private int id;
	private double moyenne;
	
	public Moyenne(int id, double moyenne) {
		this.id = id;
		this.moyenne = moyenne;
	}

	public int getId() {
		return id;
	}

	

	public void setId(int id) {
		this.id = id;
	}

	public double getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}
	
	@Override
	public String toString() {
		return "Moyenne [id=" + id + ", moyenne=" + moyenne + "]";
	}
	
}
