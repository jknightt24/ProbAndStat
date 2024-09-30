import java.io.IOException;

public class BirthdayTester
{
    public static void main(String[] args) throws IOException
    {
        Person Students = new Person();

        System.out.println("Enter the number of trials you would like to do: ");
        int runs = System.in.read();

        for(int i = 0; i < runs ; i++)
        {
            Students.GenerateBirthdays(31);
            System.out.println((boolean)Students.CheckProbabilty());
            System.out.println(); 
        }
    }
}
