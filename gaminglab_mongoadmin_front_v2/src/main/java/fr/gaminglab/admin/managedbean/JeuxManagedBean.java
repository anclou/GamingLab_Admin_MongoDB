package fr.gaminglab.admin.managedbean;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import fr.gaminglab.admin.data.GenerationDataMongo;
import fr.gaminglab.admin.dto.GenericDTO;
import fr.gaminglab.admin.dto.TotalDTO;
import fr.gaminglab.admin.service.ServiceJeuxImpl;
import fr.gaminglab.admin.utils.AdminGamingLabUtils;

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
		
		chartJeuJouer.setTitle("Nombre de parties lancées par mois");
		chartJeuJouer.setLegendPosition("e");
		List<TotalDTO> listeTotalJeuxJouerTrierParMois = listeTotalJeuxJouer.stream().sorted(TotalDTO.byMois).collect(Collectors.toList());
		for (TotalDTO total : listeTotalJeuxJouerTrierParMois) {
			serieJeux.set(AdminGamingLabUtils.MONTH[total.getMois()], total.getNombre());
			
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
		serieJeux.setLabel("Nb parties lancées / mois");	
		chartJeuJouer.addSeries(serieJeux);
		chartJeuJouer.getAxes().put(AxisType.X, new CategoryAxis("Mois"));
		Axis yAxisJeux = chartJeuJouer.getAxis(AxisType.Y);
		
		if (minJeux != null && maxJeux != null) {
			yAxisJeux.setMin(0);
			yAxisJeux.setMax(maxJeux+GenerationDataMongo.NOMBRE_DONNEES_MIN_PAR_MOIS);
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
