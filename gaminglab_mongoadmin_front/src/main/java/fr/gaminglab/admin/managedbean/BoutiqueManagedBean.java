package fr.gaminglab.admin.managedbean;

import java.util.List;

import javax.annotation.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import fr.gaminglab.admin.dto.GenericDTO;
import fr.gaminglab.admin.dto.TotalDTO;
import fr.gaminglab.admin.service.IServiceBoutique;
import fr.gaminglab.admin.service.ServiceBoutiqueImpl;

@ManagedBean
public class BoutiqueManagedBean {

	private LineChartModel chartArticlesAchat;
	private ServiceBoutiqueImpl serviceBoutique;
	public List<GenericDTO> listeTopArticlesAchat;
	public List<TotalDTO> listeTotalArticlesAchat;

	public BoutiqueManagedBean() {
		serviceBoutique = new ServiceBoutiqueImpl();
		
		//Graph Article Achat : total Articles Achat par mois
		chartArticlesAchat = new LineChartModel();
		LineChartSeries serie = new LineChartSeries();
		listeTotalArticlesAchat = serviceBoutique.getTotalArticlesAchat();
		Integer minAchat = null;
		Integer maxAchat = null;
		
		serie.setLabel("Articles vendus");
		
		for (TotalDTO total : listeTotalArticlesAchat) {
			serie.set(total.getMois(), total.getNombre());
			
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
		
		//series1.setLabel("Series 1");
//		serie.set(1, 2);
//		serie.set(2, 1);
//		serie.set(3, 3);
//		serie.set(4, 6);
//		serie.set(5, 8);
//		LineChartSeries series2 = new LineChartSeries();
//		series2.setLabel("Series 2");
//		series2.set(1, 6);
//		series2.set(2, 3);
//		series2.set(3, 2);
//		series2.set(4, 7);
//		series2.set(5, 9);
		chartArticlesAchat.addSeries(serie);
		//chartArticlesAchat.addSeries(series2);
		chartArticlesAchat.setTitle("Articles vendus");
		chartArticlesAchat.setLegendPosition("e");
		Axis yAxis = chartArticlesAchat.getAxis(AxisType.Y);
		
		if (minAchat != null && maxAchat != null) {
			yAxis.setMin(minAchat);
			yAxis.setMax(maxAchat);	
		}
		
		//Liste des 5 articles les plus vendus
		listeTopArticlesAchat = serviceBoutique.getTop5ArticlesAchat();
	}

	public List<GenericDTO> getListeTopArticlesAchat() {
		return listeTopArticlesAchat;
	}
	
//	public List<TotalDTO> getListeTotalArticlesAchat() {
//		return listeTotalArticlesAchat;
//	}

	public LineChartModel getChartArticlesAchat() {
		return chartArticlesAchat;
	}

}
