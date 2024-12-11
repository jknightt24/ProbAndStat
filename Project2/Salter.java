import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Salter 
{
    /**
     * This function will take in a file and add a random number to the data points in the file.
     * This will be used to add noise to the data points.
     * @param file
     * @throws Exception
     */
    public void salter(File file) throws Exception
    {
        Random rand = new Random();
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        File tempFile = new File("Salt.csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

        String line = br.readLine();
        // This loop will read the file and add a random number to the y value of each data point.
        // Then it will write the new data point to a new file.
        while(line != null)
        {
            double salt = rand.nextDouble(-10000, 10000);
            String[] values = line.split(",");
            double y = Double.parseDouble(values[1]);

            y = y + salt;
            values[1] = Double.toString(y);

            bw.write(values[0] + ", " + values[1]);
            bw.newLine();
            
            line = br.readLine();
        }

        br.close();
        bw.close();

        file.delete();
        tempFile.renameTo(file);
    }
}
