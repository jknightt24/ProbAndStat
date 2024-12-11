public class StatsLibraryButTheSecondOne 
{
    private double factorial(double n)
    {
        if(n == 0)
        {
            return 1;
        }
        else
        {
            return n * factorial(n - 1);
        }
    }
    // Without regard to order
    private double nCr(double n, double r)
    {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }
    // With regard to order
    private double nPr(double n, double r)
    {
        return factorial(n) / factorial(n - r);
    }
    /**
     * negativeBinomial distribution calculator without regard to order
     * @param r number of successes targeting
     * @param p probability of success
     * @param x number of trials to reach r successes 
     * @return
     */
    public double negativeBinomial(double r, double p, double x)
    {
        return nCr((x - 1), (r - 1)) * Math.pow(p, r) * Math.pow(1 - p, x - r);
    }

    /**
     * negativeBinomial distribution average
     * @param r number of successes targeting
     * @param p probability of success
     * @return
     */
    public double negativeBinomialAverage(double r, double p)
    {
        return r / p;
    }
    /**
     * negativeBinomial distribution variance
     * @param r number of successes targeting
     * @param p probability of success
     * @return
     */
    public double negativeBinomialVariance(double r, double p)
    {
        return r * (1 - p) / (p * p);
    }
    /**
     * hypergeometric distribution calculator
     * @param N 
     * @param n
     * @param r
     * @param x
     * @return
     */
    public double hypergeometric(double N, double n, double r, double x)
    {
        return nCr(r, x) * nCr(N - r, n - x) / nCr(N, n);
    }

    public double hypergeometricAverage(double N, double n, double r)
    {
        return n * r / N;
    }

    public double hypergeometricVariance(double N, double n, double r)
    {
        return n * r * (N - r) * (N - n) / (N * N * (N - 1));
    }

    public double poisson(double lambda, double x)
    {
        return Math.pow(lambda, x) * Math.exp(-lambda) / factorial(x);
    }

    public double tchebysheff(double k, double variance)
    {
        return 1 - 1 / Math.pow(k, 2);
    }
    
    public double continuousUniform(double a, double b, double x)
    {
        return 1 / (b - a);
    }
}
