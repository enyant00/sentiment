import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class NaiveBayes
{
    //private int nk; // Number of times particular word appears in document
    private int positiveN, negativeN, neutralN; // Number of total words
    private int positiveV, negativeV, neutralV; // Number of unique words
    private double c = 1.0/3.0;

    private HashMap<String, Integer> positiveFreq;
    private HashMap<String, Integer> negativeFreq;
    private HashMap<String, Integer> neutralFreq;

    public NaiveBayes() throws IOException
    {
        File posFile = new File("pos.txt");
        File negativeFile = new File("negative.txt");
        File neutralFile = new File("neutral.txt");

        calcPositive(posFile);
        calcNegative(negativeFile);
        calcNeutral(neutralFile);
    }

    private void calcPositive(File file) throws IOException
    {
        positiveN = 0;
        positiveV = 0;
        String line = "";
        String[] tokens;
        positiveFreq = new HashMap<String, Integer>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // Read pos.txt and store each unique word's count in the file
        while ((line = br.readLine()) != null)
        {
            line = line.toLowerCase();
            tokens = line.split(" ");

            for (int i = 0; i < tokens.length; i++)
            {
                if (positiveFreq.get(tokens[i]) == null)
                {
                    positiveFreq.put(tokens[i], 1);
                    positiveV++;
                    positiveN++;
                }
                else
                {
                    int freq = positiveFreq.get(tokens[i]);
                    freq++;
                    positiveFreq.put(tokens[i], freq);
                    positiveN++;
                }
            }
        }

        br.close();
        fr.close();
    }

    private void calcNegative(File file) throws IOException
    {
        negativeN = 0;
        negativeV = 0;
        String line = "";
        String[] tokens;
        negativeFreq = new HashMap<String, Integer>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // Read pos.txt and store each unique word's count in the file
        while ((line = br.readLine()) != null)
        {
            line = line.toLowerCase();
            tokens = line.split(" ");

            for (int i = 0; i < tokens.length; i++)
            {
                if (negativeFreq.get(tokens[i]) == null)
                {
                    negativeFreq.put(tokens[i], 1);
                    negativeV++;
                    negativeN++;
                }
                else
                {
                    int freq = negativeFreq.get(tokens[i]);
                    freq++;
                    negativeFreq.put(tokens[i], freq);
                    negativeN++;
                }
            }
        }

        br.close();
        fr.close();
    }

    private void calcNeutral(File file) throws IOException
    {
        neutralN = 0;
        neutralV = 0;
        String line = "";
        String[] tokens;
        neutralFreq = new HashMap<String, Integer>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // Read pos.txt and store each unique word's count in the file
        while ((line = br.readLine()) != null)
        {
            line = line.toLowerCase();
            tokens = line.split(" ");

            for (int i = 0; i < tokens.length; i++)
            {
                if (neutralFreq.get(tokens[i]) == null)
                {
                    neutralFreq.put(tokens[i], 1);
                    neutralV++;
                    neutralN++;
                }
                else
                {
                    int freq = neutralFreq.get(tokens[i]);
                    freq++;
                    neutralFreq.put(tokens[i], freq);
                    neutralN++;
                }
            }
        }

        br.close();
        fr.close();
    }
}
