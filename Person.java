import java.util.ArrayList;

public class Person
{
    double birthday;
    int count;
    ArrayList<Double> Birthdays = new ArrayList<Double>();

    public void GenerateBirthdays(int value)
    {
        for(int i = 0; i < value; i++)
        {
            Birthdays.add(Math.random() * 364);
        }
        birthday = (int)(Math.random() * 364);
    }

    public int CheckProbabilty(int runs)
    {
        for(int i = 0; i < runs; i++)
        {
            if(birthday == Birthdays.get(i))
            {
                count++;
            }
            System.out.println(count);
        }
        return count;
        
    }
}
