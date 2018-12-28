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
import fr.gaminglab.admin.service.IServiceBoutique;
import fr.gaminglab.admin.service.ServiceBoutiqueImpl;
import fr.gaminglab.admin.utils.AdminGamingLabUtils;

@ManagedBean
public class BoutiqueManagedBean {

	
	private LineChartModel chartArticlesAchat;
	private ServiceBoutiqueImpl serviceBoutique;
	private List<GenericDTO> listeTopArticlesAchat;
	private List<TotalDTO> listeTotalArticlesAchat;
	private LineChartModel chartArticlesVisite;
	private List<GenericDTO> listeTopArticlesVisite;
	private List<TotalDTO> listeTotalArticlesVisite;

	public BoutiqueManagedBean() {
		serviceBoutique = new ServiceBoutiqueImpl();
		
		// *
		// * ARTICLE ACHAT
		// *
		
		//Graph Article Achat : total Articles Achat par mois
		chartArticlesAchat = new LineChartModel();
		LineChartSeries serieAchat = new LineChartSeries();
		listeTotalArticlesAchat = serviceBoutique.getTotalArticlesAchat();
		Integer minAchat = null;
		Integer maxAchat = null;
		
		serieAchat.setLabel("Nb d'articles vendus / mois");
		
		List<TotalDTO> listeTotalArticlesAchatTrierParMois = listeTotalArticlesAchat.stream().sorted(TotalDTO.byMois).collect(Collectors.toList());
		for (TotalDTO total : listeTotalArticlesAchatTrierParMois) {
			serieAchat.set(AdminGamingLabUtils.MONTH[total.getMois()], total.getNombre());
			
			if (minAchat != null) {
				if (total.getNombre() < minAchat) {
					minAchat = total.getNombre();
				}
			} else {
				minAchat = total.getNombre();
			}
			
			if (maxAchat != null) {
				if (total.getNombre() > maxAchat) {
					maxAchat = total.getNombre();
				}
			} else {
				maxAchat = total.getNombre();
			}
		}

		chartArticlesAchat.addSeries(serieAchat);
		chartArticlesAchat.setTitle("Nombre d'articles vendus par mois");
		chartArticlesAchat.setLegendPosition("e");
		
		chartArticlesAchat.getAxes().put(AxisType.X, new CategoryAxis("Mois"));
		
		Axis yAxisAchat = chartArticlesAchat.getAxis(AxisType.Y);
		
		if (minAchat != null && maxAchat != null) {
			yAxisAchat.setMin(0);
			yAxisAchat.setMax(maxAchat+GenerationDataMongo.NOMBRE_DONNEES_MIN_PAR_MOIS);	
		}
		
		//Liste des 5 articles les plus vendus
		listeTopArticlesAchat = serviceBoutique.getTop5ArticlesAchat();
		
		// *
		// * ARTICLES VISITE
		// *
		
		//Graph Article Achat : total Articles Achat par mois
		chartArticlesVisite = new LineChartModel();
		LineChartSeries serieVisite = new LineChartSeries();
		listeTotalArticlesVisite = serviceBoutique.getTotalArticlesVisite();
		Integer minVisite = null;
		Integer maxVisite = null;
		
		serieVisite.setLabel("Nb de visites / mois");
		
		List<TotalDTO> listeTotalArticlesVisiteTrierParMois = listeTotalArticlesVisite.stream().sorted(TotalDTO.byMois).collect(Collectors.toList());
		
		for (TotalDTO total : listeTotalArticlesVisiteTrierParMois) {
			serieVisite.set(AdminGamingLabUtils.MONTH[total.getMois()], total.getNombre());
			
			if (minVisite != null) {
				if (total.getNombre() < minVisite) {
					minVisite = total.getNombre();
				}
			} else {
				minVisite = total.getNombre();
			}
			
			if (maxVisite != null) {
				if (total.getNombre() > maxVisite) {
					maxVisite = total.getNombre();
				}
			} else {
				maxVisite = total.getNombre();
			}
		}

		chartArticlesVisite.addSeries(serieVisite);
		chartArticlesVisite.setTitle("Nombre de visites par mois");
		chartArticlesVisite.setLegendPosition("e");
		chartArticlesVisite.getAxes().put(AxisType.X, new CategoryAxis("Mois"));
		Axis yAxisVisite = chartArticlesVisite.getAxis(AxisType.Y);
		
		if (minVisite != null && maxVisite != null) {
			yAxisVisite.setMin(0);
			yAxisVisite.setMax(maxVisite+GenerationDataMongo.NOMBRE_DONNEES_MIN_PAR_MOIS);	
		}
		
		//Liste des 5 articles les plus visités
		listeTopArticlesVisite = serviceBoutique.getTop5ArticlesVisite();
	}

	public List<GenericDTO> getListeTopArticlesAchat() {
		return listeTopArticlesAchat;
	}

	public LineChartModel getChartArticlesAchat() {
		return chartArticlesAchat;
	}
	
	public LineChartModel getChartArticlesVisite() {
		return chartArticlesVisite;
	}

	public List<GenericDTO> getListeTopArticlesVisite() {
		return listeTopArticlesVisite;
	}

}
