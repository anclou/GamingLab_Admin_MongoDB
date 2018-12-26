package fr.gaminglab.admin.managedbean;

import java.util.List;

import javax.annotation.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import fr.gaminglab.admin.dto.TotalDTO;
import fr.gaminglab.admin.service.ServiceForumImpl;

@ManagedBean
public class ForumManagedBean {
	private LineChartModel chartCategoriesConsulter;
	private ServiceForumImpl serviceForum;
	private List<TotalDTO> listeTotalCategoriesConsulter;
	
	public ForumManagedBean() {
		serviceForum = new ServiceForumImpl();
		
		// * 
		// * CATEGORIE CONSULTER
		// *
		
		chartCategoriesConsulter = new LineChartModel();
		LineChartSeries serieCategories = new LineChartSeries();
		listeTotalCategoriesConsulter = serviceForum.getTotalCategoriesConsulter();
		Integer minAchat = null;
		Integer maxAchat = null;
		
		serieCategories.setLabel("Visites");
		
		for (TotalDTO total : listeTotalCategoriesConsulter) {
			serieCategories.set(total.getMois(), total.getNombre());
			
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

		chartCategoriesConsulter.addSeries(serieCategories);
		chartCategoriesConsulter.setTitle("Nombre de consultations du forum");
		chartCategoriesConsulter.setLegendPosition("e");
		Axis yAxisAchat = chartCategoriesConsulter.getAxis(AxisType.Y);
		
		if (minAchat != null && maxAchat != null) {
			yAxisAchat.setMin(minAchat-10);
			yAxisAchat.setMax(maxAchat+10);	
		}
	}

	public LineChartModel getChartCategoriesConsulter() {
		return chartCategoriesConsulter;
	}

	public List<TotalDTO> getListeTotalCategoriesConsulter() {
		return listeTotalCategoriesConsulter;
	}
	
	
}
