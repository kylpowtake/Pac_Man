import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

@SuppressWarnings("serial")
public class LineChart extends JPanel {

	
    public LineChart(Map<Integer, Double> tableau) {
        initUI(tableau);
    }
    
    
    private void initUI(Map<Integer, Double> tableau) {
        XYDataset dataset  = createDataset(tableau);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);
    }
    

    private XYDataset createDataset(Map<Integer, Double> tableau) {
        XYSeries series = new XYSeries("");   
        Set<Entry<Integer, Double>> setHm = tableau.entrySet();
	    Iterator<Entry<Integer, Double>> it = setHm.iterator();
        while(it.hasNext()){
	         Entry<Integer, Double> e = it.next();
	         series.add(e.getKey(),e.getValue());
	    }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        return dataset;
    }
    

    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Titre", 
                "Nombre d'éléments par feuille", 
                "Taux d'erreur", 
                dataset, 
                PlotOrientation.VERTICAL,
                true, 
                true, 
                false 
        );

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Titre", new Font("Serif", java.awt.Font.BOLD, 18)));
        return chart;
    }
}