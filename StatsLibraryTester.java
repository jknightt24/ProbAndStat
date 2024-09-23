import java.util.ArrayList;
import java.util.Random;
public class StatsLibraryTester 
{
    public static void main(String[] args)
    {
        ArrayList<Integer> ListOfNumbers = new ArrayList<>();
        StatsLibrary Tester = new StatsLibrary();

        for(int i = 0; i < 100; i++)
        {
           ListOfNumbers.add(new Random().nextInt(100));
        }

        System.out.println(ListOfNumbers);
        System.out.println(Tester.Mean(ListOfNumbers));
        System.out.println(Tester.Median(ListOfNumbers));
        System.out.println(Tester.Mode(ListOfNumbers));
    }

}
