
package charts;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

/**
 *
 * @author kelly
 */
@ManagedBean
@SessionScoped
public class TransactionsChartModel {

    private LineChartModel lineModel1;
    
    @PostConstruct
    public void init(){
        createLineModels();
    }
    
    private void createLineModels(){
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Year Sales");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setLabel("Amount in 000s");
        Axis xAxis = lineModel1.getAxis(AxisType.X);
        xAxis.setLabel("Months");
        yAxis.setMin(20000);
        yAxis.setMax(100000);
    }

    /**
     * @return the lineModel1
     */
    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    private LineChartModel initLinearModel(){
        LineChartModel model = new LineChartModel();
        LineChartSeries series = new LineChartSeries("Sales");
        series.set(1,21500);
        series.set(2,60000);
        series.set(3,33450);
        series.set(4,28000);
        series.set(5,24900);
        series.set(6,34500);
        series.set(7,56000);
        series.set(8,78500);
        series.set(9,67800);
        series.set(10,89300);
        model.addSeries(series);
        return model;
    }
}
