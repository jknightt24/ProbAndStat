import java.math.BigInteger;
public class StatsLibrary
{ 
    /** 
     * This Method is a helper method in order to calculate the factorial of a number n
     * @param n
     * @return double
     */
    public double Factorial(int n)
    {
        BigInteger factorial = BigInteger.ONE;
        for (int i = 1; i <= n; i++)
        {
            factorial = factorial.multiply(BigInteger.valueOf(i));
        }
        return factorial.doubleValue();
    }

    /** 
     * This method calculates the intersection between two given priobabilities A and B
     * @param probabilityA
     * @param probabilityB
     * @return double
     */
    public double Intersection(double probabilityA, double probabilityB)
    {
        return probabilityA * probabilityB;
    }

    /** 
     * This method calculates the mean of the data set data
     * @param data
     * @return double
     */
    public double Mean(double[] data)
    {
        double sum = 0;
        for (int i = 0; i < data.length; i++)
        {
            sum += data[i];
        }
        return sum / data.length;
    }

    /** 
     * Returns the median of the data set data
     * @param data
     * @return double
     */
    public double Median(double[] data)
    {
        if (data.length % 2 == 0)
        {
            return (data[data.length / 2] + data[data.length / 2 - 1]) / 2;
        }
        else
        {
            return data[data.length / 2];
        }
    }

    /** 
     * Returns the mode of the data set data
     * @param data
     * @return double
     */
    public double Mode(double[] data)
    {
        double count = 0;
        double countCompare = 0;
        double currentMode = 0;
        for (int i = 0; i < data.length; i++)
        {
            count = 0;
            for (int j = 0; j < data.length; j++)
            {
                if (data[i] == data[j])
                {
                    count++;
                }
            }
            if (count > countCompare)
            {
                countCompare = count;
                currentMode = data[i];
            }
        }
        return currentMode;
    }

    /** 
     * Return the expected variance of the data set data
     * @param data
     * @return double
     */
    public double Variance(double[] data)
    {
        double mean = Mean(data);
        double sum = 0;
        for (int i = 0; i < data.length; i++)
        {
            sum += Math.pow(data[i] - mean, 2);
        }
        return sum / data.length;
    }

    /** returns the standard deviation of the data set data
     * @param data
     * @return double
     */
    public double StandardDeviation(double[] data)
    {
        return Math.sqrt(Variance(data));
    }

    /** 
     * returns the probability of a given value in the data set data
     * @param data
     * @return double
     */
    public double DefinitionOfProbability(double[] data, double value)
    {
        double count = 0;
        for (int i = 0; i < data.length; i++)
        {
            if (data[i] == value)
            {
                count++;
            }
        }
        return count / data.length;
    }

    /** 
     * returns how many ways you can order n objects in r ways
     * @param data
     * @return double
     */
    public double Permutation(int n, int r)
    {
        return Factorial(n) / Factorial(n - r);
    }

    /** 
     * returns how many combinations of n objects can be made taking r at a time
     * @param n
     * @param r
     * @return double
     */
    public double combination(int n, int r)
    {
        return Factorial(n) / (Factorial(r) * Factorial(n - r));
    }

    /** 
     * Probability of an event A given that event B has occurred
     * @param A
     * @param B
     * @return double
     */
    public double ConditionalProbability(double probabilityOfA, double probabilityOfB)
    {
        return Intersection(probabilityOfA, probabilityOfB) / probabilityOfB;
    }

    /** 
     * Returns true if the events are independent and false if not
     * @param A
     * @param B
     * @return boolean
     */
    public boolean independentEvents(double A, double B)
    {
        if(ConditionalProbability(A, B) == A)
        {
            return true;
        }
        else if(ConditionalProbability(B, A) == B)
        {
            return true;
        }
        else if(ConditionalProbability(A, B) == A * B)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /** 
     * Returns the complement of the probability A
     * @param A
     * @return double
     */
    public double complementRule(double A)
    {
        return 1 - A;
    }

    /** 
     * Returns the probability of the intersection of two events A and B
     * @param A
     * @param B
     * @return double
     */
    public double multiplacitiveLawofProbability(double A, double B)
    {
        if(independentEvents(A, B))
        {
            return A * B;
        }
        else
        {
            return ConditionalProbability(A, B) * B;
        }
    }

    /** 
     * Returns 
     * @param A
     * @param B
     * @return double
     */
    public double additiveLawofProbability(double A, double B)
    {
        return A + B - ConditionalProbability(A, B);
    }

    /** 
     * Returns the total probability of A using A given B
     * @param A
     * @param B
     * @return double
     */
    public double lawOfTotalProbability(double A, double B)
    {
        return ConditionalProbability(A, B) * B + ConditionalProbability(A, complementRule(B)) * complementRule(B);
    }

    /**
     * @param A
     * @param B
     * @return Returns the probability of A given B using Bayes Theorem
     */
    public double bayesTheorem(double A, double B)
    {
        return ConditionalProbability(A, B) * B / lawOfTotalProbability(A, B);
    }

    /**
     * @param n
     * @param y
     * @param p
     * @return Returns the binomial distribution of a given n, y, and p
     */
    public double binomialDistribution(int n, int y, double p)
    {
        return combination(n, y) * Math.pow(p, y) * Math.pow(1 - p, n - y);
    }
}