import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Smoother 
{
    /**
     * This method will smooth the data in the file by averaging the values within a certain specified range.
     * @param file
     * @param range
     * @throws Exception
     */
    public void smoother(File file, int range) throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(file));
        File tempFile = new File("Smooth.csv");
        BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
        
        List<String> lines = new ArrayList<>();
        String line = br.readLine();
        while (line != null) 
        {
            lines.add(line);
            line = br.readLine();
        }
        br.close();

        for (int i = 0; i < lines.size(); i++) 
        {
            String[] values = lines.get(i).split(",");
            double sum = 0;
            int count = 0;
            // This loop will iterate through the values within the range and calculate the sum of the values.
            for (int j = Math.max(0, i - range); j <= Math.min(lines.size() - 1, i + range); j++) 
            {
                String[] tempValues = lines.get(j).split(",");
                sum += Double.parseDouble(tempValues[1]);
                count++;
            }

            double average = sum / count;
            values[1] = Double.toString(average);

            bw.write(values[0] + ", " + values[1]);
            bw.newLine();
        }

        bw.close();
    }
}
