import java.util.ArrayList;

public class Person
{
    double birthday;
    boolean sameBirthday;
    double random;
    int count;
    
    ArrayList<Double> Birthdays = new ArrayList<Double>();

    public void GenerateBirthdays(int value)
    {
        for(int i = 0; i < value; i++)
        {
            random = (int)(Math.random() * 364);
            Birthdays.add(random);
        }
        birthday = (int)(Math.random() * 364);
        System.out.println(birthday);
    }

    public boolean CheckProbabilty()
    {
        count = 0;
        for(int i = 0; i < Birthdays.size(); i++)
        {
            if(Birthdays.get(i) == birthday)
            {
                sameBirthday = true;
                break;
            }
            else
            {
                sameBirthday = false;
            }
        }
        return sameBirthday;
    }
}
