package fr.gaminglab.admin.managedbean;

import java.util.List;

import javax.annotation.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import afu.org.checkerframework.checker.units.qual.min;
import fr.gaminglab.admin.dto.GenericDTO;
import fr.gaminglab.admin.dto.TotalDTO;
import fr.gaminglab.admin.service.ServiceBoutiqueImpl;
import fr.gaminglab.admin.service.ServiceJeuxImpl;

@ManagedBean
public class JeuxManagedBean {
	private LineChartModel chartJeuJouer;
	private ServiceJeuxImpl serviceJeux;
	private List<GenericDTO> listeTopJeuxJouer;
	private List<TotalDTO> listeTotalJeuxJouer;
	private List<GenericDTO> listeTopJoueurJouer;
	
	public JeuxManagedBean() {
		serviceJeux = new ServiceJeuxImpl();
		
		// CHART TOTAL JEUX 
		
		listeTotalJeuxJouer = serviceJeux.getTotalJeuxJouer();
		chartJeuJouer = new LineChartModel();
		LineChartSeries serieJeux = new LineChartSeries();
		Integer minJeux = null;
		Integer maxJeux = null;
		
		chartJeuJouer.setTitle("Nombre de parties de jeux lancés");
		chartJeuJouer.setLegendPosition("e");
		
		for (TotalDTO total : listeTotalJeuxJouer) {
			serieJeux.set(total.getMois(), total.getNombre());
			
			if (minJeux != null) {
				if (total.getNombre() < minJeux) {
					minJeux = total.getNombre();
				}
			} else {
				minJeux = total.getNombre();
			}
			
			if (maxJeux != null) {
				if (total.getNombre() > maxJeux) {
					maxJeux = total.getNombre();
				}
			} else {
				maxJeux = total.getNombre();
			}
		}
		serieJeux.setLabel("Jeux");	
		chartJeuJouer.addSeries(serieJeux);
		
		Axis yAxisJeux = chartJeuJouer.getAxis(AxisType.Y);
		
		if (minJeux != null && maxJeux != null) {
			yAxisJeux.setMin(minJeux-10);
			yAxisJeux.setMax(maxJeux+10);
		}
		
		// TABLEAU TOP JEUX
		
		listeTopJeuxJouer = serviceJeux.getTop5Jeux();
		
		// TABLEAU TOP JOUEUR
		
		listeTopJoueurJouer = serviceJeux.getTop5Joueurs();
	}

	public LineChartModel getChartJeuJouer() {
		return chartJeuJouer;
	}

	public List<GenericDTO> getListeTopJeuxJouer() {
		return listeTopJeuxJouer;
	}

	public List<GenericDTO> getListeTopJoueurJouer() {
		return listeTopJoueurJouer;
	}
	
}
