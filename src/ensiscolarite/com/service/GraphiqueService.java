package ensiscolarite.com.service;

import java.util.ArrayList;

import ensiscolarite.com.dao.ApplicationDao;
import ensiscolarite.com.domaine.Etudiant;
import ensiscolarite.com.domaine.Moyenne;
import ensiscolarite.com.domaine.Note;

import ensiscolarite.com.exception.ServiceException;

public class GraphiqueService {
	private UtilisateurService utilServ = new UtilisateurService();
	private ApplicationDao dao = new ApplicationDao();
	private ArrayList<Moyenne> lesMoyennes = new ArrayList<Moyenne>();
	private ArrayList<Etudiant> lesEtudiants = new ArrayList<Etudiant>();
	
	public String afficherGraphique() throws ServiceException {
		try {
			ArrayList<Etudiant> etudiantsMoyenne = new ArrayList<Etudiant>();
			lesEtudiants = utilServ.listerEtudiants();
			lesMoyennes = dao.recupererMoyenneEnBase();
			
			
			
			
			
		}catch (Exception e)
    		{
    			//throw new ServiceException(e.getMessage());
			e.printStackTrace();
    		}
		return null;
		
	}
}
