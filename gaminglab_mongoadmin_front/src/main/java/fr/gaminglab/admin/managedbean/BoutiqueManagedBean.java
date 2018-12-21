package fr.gaminglab.admin.managedbean;

import java.util.List;

import javax.annotation.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import fr.gaminglab.admin.dto.GenericDTO;
import fr.gaminglab.admin.service.IServiceBoutique;
import fr.gaminglab.admin.service.ServiceBoutiqueImpl;

@ManagedBean
public class BoutiqueManagedBean {

	private LineChartModel lineChartModel;
	private ServiceBoutiqueImpl serviceBoutique;
	public List<GenericDTO> listeTopArticlesAchat;

	public BoutiqueManagedBean() {
		serviceBoutique = new ServiceBoutiqueImpl();
		
		//Graph
		lineChartModel = new LineChartModel();
		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");
		series1.set(1, 2);
		series1.set(2, 1);
		series1.set(3, 3);
		series1.set(4, 6);
		series1.set(5, 8);
		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("Series 2");
		series2.set(1, 6);
		series2.set(2, 3);
		series2.set(3, 2);
		series2.set(4, 7);
		series2.set(5, 9);
		lineChartModel.addSeries(series1);
		lineChartModel.addSeries(series2);
		lineChartModel.setTitle("Linear Chart");
		lineChartModel.setLegendPosition("e");
		Axis yAxis = lineChartModel.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(10);
		
		//Liste des 5 articles les plus vendus
		listeTopArticlesAchat = serviceBoutique.getTop5ArticlesAchat();
	}

	public List<GenericDTO> getListeTopArticlesAchat() {
		return listeTopArticlesAchat;
	}

	public LineChartModel getModel() {
		return lineChartModel;
	}

}
