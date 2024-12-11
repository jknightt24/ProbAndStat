public class Plotter
{
    double x;
    double y;
    /**
     * This function will take in the values of a, x, h, and k and return the value of y.
     * This function will be used throught the program to plot data points.
     */
    public double function(double a, double x, double h, double k)
    {
        y = a * Math.pow((x - h), 2) + k;

        return y;
    }
}