import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
public class PlotterTester 
{
    public static void main(String[] args) throws Exception
    {
        Plotter plot = new Plotter();
        Salter salt = new Salter();
        Smoother smooth = new Smoother();
        Scanner sc = new Scanner(System.in);
        File obj = new File("Data.csv");
        PrintStream toFile = new PrintStream(obj);
        //This will prompt the user if they want to make a new base file to configure the function they want to plot.
        System.out.println("Would you like to make a new base file? (y/n)");
        String response = sc.next();
        //This will prompt the user for the lower bound, upper bound, and increment of the range they want to plot. if they say yes.
        if(response.equalsIgnoreCase("y"))
        {
            System.out.println("What is the lower end of the range you wish to start at?");
            double lowerBound = sc.nextDouble();
            System.out.println("What is the upper end of the range you wish to end at?");
            double upperBound = sc.nextDouble();
            System.out.println("How much would you like to increment by ");
            double increment = sc.nextDouble();
            
            PrintStream originalOut = System.out;
            System.setOut(toFile);
            // This loop will iterate through what you inputted between the lower and upper bounds at a rate of increment.
            // Then it will print the x and y values to the file.
            
            for(double i = lowerBound; i < upperBound; i = i + increment)
            {
                System.out.println("" + i + ", " + plot.function(2, i, 3, 4));
            }
            
            System.setOut(originalOut);
        }
        
        System.out.println("What is the range of the smoother? ");
        int range = sc.nextInt();

        salt.salter(obj);
        smooth.smoother(obj, range);
    }
}
