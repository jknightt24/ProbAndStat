import java.io.IOException;

public class BirthdayTester
{
    public static void main(String[] args) throws IOException
    {
        BirthdayOdds Students = new BirthdayOdds();

        Students.runTrial(100000, 20);
    }
}
