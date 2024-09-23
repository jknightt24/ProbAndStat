import java.util.ArrayList;

public class StatsLibrary
{
    private double sum;
    private double Count;
    private double CountCompare;
    private double CurrentMode;
    private double Mean;

    public double Mean(ArrayList<Integer> ListOfNumbers)
    {
        
        for(int singleNumber : ListOfNumbers)
        {
            sum = sum + singleNumber;
        }
        return sum / ListOfNumbers.size();
    }

    public double Median(ArrayList<Integer> ListOfNumbers)
    {
        ListOfNumbers.sort(null);

        if(ListOfNumbers.size() % 2 == 0)
        {
            return (ListOfNumbers.get(ListOfNumbers.size() / 2) + ListOfNumbers.get(ListOfNumbers.size() / 2 - 1)) / 2;
        }
        else
        {
            return ListOfNumbers.get(ListOfNumbers.size() / 2);
        }
    }

    public double Mode(ArrayList<Integer> ListOfNumbers)
    {
        for(int i = 0; i < ListOfNumbers.size(); i++)
        {
            Count = 0;
            for(int j = 0; j < ListOfNumbers.size(); j++)
            {
                if(ListOfNumbers.get(i) == ListOfNumbers.get(j))
                {
                    Count++;
                }
            }
            if(Count > CountCompare)
            {
                CountCompare = Count;
                CurrentMode = ListOfNumbers.get(i);
            }
        }
        return CurrentMode;
    }

    public double StandardDeviation(ArrayList<Integer> ListOfNumbers)
    {
        Mean = Mean(ListOfNumbers);
        sum = 0;
        for(int singleNumber : ListOfNumbers)
        {
            sum = sum + Math.pow(singleNumber - Mean, 2);
        }
        return Math.sqrt(sum / ListOfNumbers.size());
    }
}
