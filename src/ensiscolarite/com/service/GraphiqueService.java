package ensiscolarite.com.service;

import java.util.ArrayList;

import ensiscolarite.com.dao.ApplicationDao;
import ensiscolarite.com.domaine.Etudiant;
import ensiscolarite.com.domaine.Note;

import ensiscolarite.com.exception.ServiceException;

public class GraphiqueService {
	private UtilisateurService utilServ = new UtilisateurService();
	private ApplicationDao dao = new ApplicationDao();
	private ArrayList<Note> lesNotes = new ArrayList<Note>();
	private ArrayList<Etudiant> lesEtudiants = new ArrayList<Etudiant>();
	
	public String afficherGraphique() throws ServiceException {
		try {
			lesEtudiants = utilServ.listerEtudiants();
			lesNotes = dao.recupererNotesEnBase();
		}catch (Exception e)
    		{
    			throw new ServiceException(e.getMessage());
    		}
		return null;
		
	}
}
