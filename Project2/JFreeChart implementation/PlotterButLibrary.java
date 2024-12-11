import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.Random;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class PlotterButLibrary extends JFrame
{
    /**
     * Defines the function that will be plotted.
     * @param a
     * @param x
     * @param h
     * @param k
     * @return
     */
    public double function(double a, double x, double h, double k)
    {
        double y = a * Math.pow((x - h), 2) + k;

        return y;
    }
    /**
     * This function will take in a series and smooth it by taking the average of the points around it.
     * and then reutrn a new series with the smoothed data.
     * @param series
     * @param range
     * @param name
     * @return
     */
    public XYSeries Smooth(XYSeries series, int range, String name)
    {
        XYSeries smoothedSeries = new XYSeries("" + name);
        for(int i = 0; i < series.getItemCount(); i++)
        {
            double sum = 0;
            int count = 0;
            for(int j = Math.max(0, i - range); j <= Math.min(series.getItemCount() - 1, i + range); j++)
            {
                sum += series.getY(j).doubleValue();
                count++;
            }
            double average = sum / count;
            smoothedSeries.add(series.getX(i), average);
        }
        return smoothedSeries;
    }

    /**
     * This function will plot the data points and the smoothed data points.
     */
    public void Plot()
    {
        //This will create the series for the data points, the salted data points, and the smoothed data points.
        XYSeries seriesPlot = new XYSeries("PlotGraph");
        XYSeries seriesSalt = new XYSeries("SaltGraph");
        XYSeries seriesSmooth = new XYSeries("SmoothGraph");
        XYSeries seriesSecondSmooth = new XYSeries("SecondSmoothGraph");
        XYSeries seriesThirdSmooth = new XYSeries("ThirdSmoothGraph");
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        //This will prompt the user for the lower bound, upper bound, and increment of the range they want to plot.
        System.out.println("What is the lower end of the range you wish to start at?");
        double lowerBound = sc.nextDouble();
        System.out.println("What is the upper end of the range you wish to end at?");
        double upperBound = sc.nextDouble();
        System.out.println("How much would you like to increment by ");
        double increment = sc.nextDouble();
        System.out.println("How many variables do you want to incorporate into each points moving average? (the range of the average)");
        int range = sc.nextInt();
        
        //This loop will iterate through what you inputted between the lower and upper bounds at a rate of increment. adding the data points to the plot series
        for(double i = lowerBound; i < upperBound; i = i + increment)
        {
            seriesPlot.add(i, function(2, i, 3, 4));
            //This will add a random number to the data points to add noise to the data points.
            Double salt = rand.nextDouble(-10000, 10000);
            seriesSalt.add(i, function(2, i, 3, 4) + salt);
        }
        //This will smooth the data points and add them to the smooth series.
        seriesSmooth = Smooth(seriesSalt, range, "SmoothGraph");
        seriesSecondSmooth = Smooth(seriesSmooth, range, "SecondSmoothGraph");
        seriesThirdSmooth = Smooth(seriesSecondSmooth, range, "ThirdSmoothGraph");
        
        //Creates a dataset for the original data points and the salted points for comparison.
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(seriesPlot);
        dataset.addSeries(seriesSalt);
        //Creates a dataset for the salted data points, smoothed data points, and second smoothed data points for comparison.
        XYSeriesCollection saltDataset = new XYSeriesCollection();
        saltDataset.addSeries(seriesSalt);
        saltDataset.addSeries(seriesSmooth);
        saltDataset.addSeries(seriesSecondSmooth);
        //Creates a dataset for the smoothed data points, second smoothed data points, and third smoothed data points for comparison.
        XYSeriesCollection SmoothDataset = new XYSeriesCollection();
        SmoothDataset.addSeries(seriesPlot);
        SmoothDataset.addSeries(seriesSmooth);
        SmoothDataset.addSeries(seriesSecondSmooth);
        SmoothDataset.addSeries(seriesThirdSmooth);

        /**Alternate way to smooth data using JFreeChart library
        XYDataset smoothedDataset = MovingAverage.createMovingAverage(saltDataset, "Smoothed", range, 0);
        XYDataset smoothedDataset2 = MovingAverage.createMovingAverage(smoothedDataset, " twice", range, 0);
        */

        //Creates charts for the three datasets.
        JFreeChart chart = ChartFactory.createXYLineChart("Data", "X", "Y", dataset, PlotOrientation.VERTICAL, true, true, false);
        JFreeChart SaltAndSmoothChart = ChartFactory.createXYLineChart("Salted Data", "X", "Y", saltDataset, PlotOrientation.VERTICAL, true, true, false);
        JFreeChart SmoothChart = ChartFactory.createXYLineChart("Smoothed Data", "X", "Y", SmoothDataset, PlotOrientation.VERTICAL, true, true, false);

        /** Alternate way to represent data using JFreeChart library by manually setting the colors of the lines
        XYPlot plot = SaltAndSmoothChart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.PINK);
        plot.setRenderer(renderer);
        */

        //Creates panels for the three charts.
        ChartPanel chartPanel = new ChartPanel(chart);
        ChartPanel saltChartPanel = new ChartPanel(SaltAndSmoothChart);
        ChartPanel smoothChartPanel = new ChartPanel(SmoothChart);
        
        //Creates a panel to hold the three charts with a grid layout of 3 rows and 1 column.
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(chartPanel);
        panel.add(saltChartPanel);
        panel.add(smoothChartPanel);

        setContentPane(panel);
    }
}
