import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class NaiveBayes
{
    int nk = 0; // Number of times particular word appears in document
    int n = 0; // Number of total words
    int vocabuary = 0; // Number of unique words
    double c = 1.0/3.0;

    HashMap<String, Integer> positiveFreq;

    public NaiveBayes() throws IOException
    {
        File posFile = new File("pos.txt");
        FileReader fr = new FileReader(posFile);
        BufferedReader br = new BufferedReader(fr);

        positive(br);

        br.close();
        fr.close();
    }

    private void positive(BufferedReader br) throws IOException
    {
        String line = "";
        String[] tokens;
        positiveFreq = new HashMap<String, Integer>();

        while ((line = br.readLine()) != null)
        {
            line = line.toLowerCase();
            tokens = line.split(" ");

            for (int i = 0; i < tokens.length; i++)
            {
                if (positiveFreq.get(tokens[i]) == null)
                {
                    positiveFreq.put(tokens[i], 1);
                    vocabuary++;
                    n++;
                }
                else
                {
                    int freq = positiveFreq.get(tokens[i]);
                    freq++;
                    positiveFreq.put(tokens[i], freq);
                    n++;
                }
            }
        }

        for (Map.Entry<String, Integer> entry : positiveFreq.entrySet())
        {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        System.out.println("Total words: " + n);
        System.out.println("Total unique words: " + vocabuary);
    }
}
