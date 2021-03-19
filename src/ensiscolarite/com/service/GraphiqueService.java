package ensiscolarite.com.service;
import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;

import java.util.ArrayList;

import ensiscolarite.com.UI.MainFrm;
import ensiscolarite.com.dao.ApplicationDao;
import ensiscolarite.com.domaine.Moyenne;


import ensiscolarite.com.exception.ServiceException;
import javax.swing.JFrame;

public class GraphiqueService {
	private ApplicationDao dao = new ApplicationDao();
	private ArrayList<Moyenne> lesMoyennes = new ArrayList<Moyenne>();
	private ArrayList<Double> arrayReturnMoyennes = new ArrayList<Double>();
	
	public ArrayList<Double> afficherGraphique() throws ServiceException {

        
        
		try {
			
			//On récupére toutes les moyennes en base
			lesMoyennes = dao.recupererMoyenneEnBase();
			//On récupére le nombre totale de moyennes en base
			int nbrMoy = lesMoyennes.size();
			
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

			arrayReturnMoyennes.add(lesMauvais);
			arrayReturnMoyennes.add(lesMoyens);
			arrayReturnMoyennes.add(lesBons);
			
		}catch (Exception e)
    		{
    			//throw new ServiceException(e.getMessage());
			e.printStackTrace();
    		}
		//GRAPHIQUE
		
	
		return arrayReturnMoyennes;
		
	}
}
