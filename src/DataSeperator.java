import java.io.*;
import java.util.LinkedList;

public class DataSeperator
{
    public DataSeperator() throws IOException
    {
        File positiveFile = new File("pos.txt");
        File negativeFile = new File("negative.txt");
        File neutralFile = new File("neutral.txt");

        File positiveTraining = new File("positiveTraining.txt");
        File positiveTesting = new File("positiveTesting.txt");

        FileReader fr = new FileReader(positiveFile);
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter(positiveTraining);
        BufferedWriter bw = new BufferedWriter(fw);

        LinkedList<String> documents = new LinkedList<String>();

        String line = "";
        while((line = br.readLine()) != null)
        {
            documents.add(line);
        }

        for (int i = 0; i < documents.size() * 0.8; i++)
        {
            bw.write(documents.get(i));
        }

        for (int i = (int)(documents.size() * 0.8); i < documents.size(); i++)
        {
            bw.write(documents.get(i));
        }

        br.close();
        bw.close();
    }
}
