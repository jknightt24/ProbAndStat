import java.util.ArrayList;
import java.util.Random;

public class BirthdayOdds
{
    int birthdayTarget;
    int random;
    Random rand = new Random(); 
    ArrayList<Integer> Birthdays = new ArrayList<Integer>();

    /**
     * Generate random birthdays for a given number of people and add them to the list Birthdays
     * @param numberOfPeople
     */
    public void GenerateBirthdays(int numberOfPeople)
    {
        for(int i = 0; i < numberOfPeople; i++)
        {
            random = rand.nextInt(365);
            Birthdays.add(random);
        }
    }

    /**
     * Checks if two people have the same birthday in the list Birthdays
     * @return true if two people have the same birthday, false otherwise
     */
    public boolean CheckSameBirthday()
    { 
        for(int i = 0; i < Birthdays.size(); i++)
        {
            for(int j = i + 1; j < Birthdays.size(); j++)
            {
                if(Birthdays.get(i) == Birthdays.get(j))
                {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Run a number of trials "numberOfTrals" to see the probability of two people having the same birthday in a group sized "numberOfPeople"
     * @param numberOfTrals
     * @param numberOfPeople
     */
    public void runTrial(double numberOfTrals, int numberOfPeople)
    {
        double count = 0;

        for(int i = 0; i < numberOfTrals; i++)
        {
            GenerateBirthdays(numberOfPeople);
            
            if(CheckSameBirthday())
            {
                count++;
            }

            Birthdays.clear();
        }
        
        System.out.println("The probability of two people having the same birthday is: " + (count / numberOfTrals) * 100 + "%");
    }

}
