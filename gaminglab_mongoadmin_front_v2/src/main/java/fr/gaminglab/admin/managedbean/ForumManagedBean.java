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
import fr.gaminglab.admin.service.ServiceForumImpl;
import fr.gaminglab.admin.utils.AdminGamingLabUtils;

@ManagedBean
public class ForumManagedBean {
	private LineChartModel chartCategoriesConsulter;
	private ServiceForumImpl serviceForum;
	private List<TotalDTO> listeTotalCategoriesConsulter;
	private List<GenericDTO> topCategoriesConsulter;
	private List<GenericDTO> topSujetsCommenter;
	private List<GenericDTO> topJoueursCommenter;
	private List<GenericDTO> topSujetsNoter;
	
	public ForumManagedBean() {
		serviceForum = new ServiceForumImpl();
		
		// * 
		// * CATEGORIE CONSULTER
		// *
		
		//Total Catégories Consulter
		chartCategoriesConsulter = new LineChartModel();
		LineChartSeries serieCategories = new LineChartSeries();
		listeTotalCategoriesConsulter = serviceForum.getTotalCategoriesConsulter();
		Integer minAchat = null;
		Integer maxAchat = null;
		
		serieCategories.setLabel("Visites");
		
		List<TotalDTO> listeTotalCategoriesConsulterTrierParMois = listeTotalCategoriesConsulter.stream().sorted(TotalDTO.byMois).collect(Collectors.toList());
		
		for (TotalDTO total : listeTotalCategoriesConsulterTrierParMois) {
			serieCategories.set(AdminGamingLabUtils.MONTH[total.getMois()], total.getNombre());
			
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
		chartCategoriesConsulter.getAxes().put(AxisType.X, new CategoryAxis("Mois"));
		Axis yAxisAchat = chartCategoriesConsulter.getAxis(AxisType.Y);
		
		if (minAchat != null && maxAchat != null) {
			yAxisAchat.setMin(0);
			yAxisAchat.setMax(maxAchat+GenerationDataMongo.NOMBRE_DONNEES_MIN_PAR_MOIS);	
		}
		
		//Top Catégories Consulter
		topCategoriesConsulter = serviceForum.getTop5CategoriesConsulter();
		
		//Top Sujets Commenter
		topSujetsCommenter = serviceForum.getTop5SujetsCommenter();
		
		//Top Sujets Noter
		topSujetsNoter = serviceForum.getTop5SujetsNoter();
		
		//Top Joueurs Commenter
		topJoueursCommenter = serviceForum.getTop5JoueurCommenter();
	}

	public LineChartModel getChartCategoriesConsulter() {
		return chartCategoriesConsulter;
	}

	public List<TotalDTO> getListeTotalCategoriesConsulter() {
		return listeTotalCategoriesConsulter;
	}

	public List<GenericDTO> getTopCategoriesConsulter() {
		return topCategoriesConsulter;
	}

	public List<GenericDTO> getTopSujetsCommenter() {
		return topSujetsCommenter;
	}

	public List<GenericDTO> getTopJoueursCommenter() {
		return topJoueursCommenter;
	}

	public List<GenericDTO> getTopSujetsNoter() {
		return topSujetsNoter;
	}
	
}
