package ensiscolarite.com.service;

import java.util.ArrayList;


import ensiscolarite.com.dao.ApplicationDao;
import ensiscolarite.com.domaine.Moyenne;


import ensiscolarite.com.exception.ServiceException;

public class GraphiqueService {
	private ApplicationDao dao = new ApplicationDao();
	private ArrayList<Moyenne> lesMoyennes = new ArrayList<Moyenne>();

	
	public String afficherGraphique() throws ServiceException {
		try {
			//On récupére toutes les moyennes en base
			lesMoyennes = dao.recupererMoyenneEnBase();
			//On récupére le nombre totale de moyennes en base
			int nbrMoy = lesMoyennes.size();
			
			//On utilise stream afin de calculer le nombre de personnes Mauvaises
			//( donc avec une moyenne inférieure a 8)
			double lesMauvais= lesMoyennes.stream()
			.filter(m -> m.getMoyenne() < 8)
			.count();
			
			//On utilise stream afin de calculer le nombre de personnes moyennes
			//( donc avec une moyenne entre 8 et 14)
			double lesMoyens= lesMoyennes.stream()
			.filter(m ->m.getMoyenne() < 14)
			.filter(m ->m.getMoyenne() >= 8)
			.count();
			
			//On utilise stream afin de calculer le nombre de personnes avec de bonnes moyennes
			//( donc avec une moyenne supérieure a 14)
			double lesBons= lesMoyennes.stream()
			.filter(m ->m.getMoyenne() >= 14)
			.count();
			
			//On réalise les pourcentages par catégorie sur le nombre totale de moyennes
			double moyenneMauvais = (lesMauvais / nbrMoy) *100 ;
			double moyenneMoyens = (lesMoyens / nbrMoy) *100 ;
			double moyenneBons = (lesBons / nbrMoy) *100 ;
			
			
			//GRAPHIQUE
			
		
			
			
		}catch (Exception e)
    		{
    			//throw new ServiceException(e.getMessage());
			e.printStackTrace();
    		}
		return null;
		
	}
}
