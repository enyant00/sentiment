import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class NaiveBayes
{
    //int nk; // Number of times particular word appears in document
    int n; // Number of total words
    int vocabuary; // Number of unique words
    double c = 1.0/3.0;

    HashMap<String, Integer> positiveFreq;
    HashMap<String, Double> positiveProb;

    HashMap<String, Integer> negativeitiveFreq;
    HashMap<String, Double> negativeProb;

    public NaiveBayes() throws IOException
    {
        File posFile = new File("pos.txt");
        File negativeFile = new File("negative.txt");
        FileReader frp = new FileReader(posFile);
        FileReader frn = new FileReader(negativeFile);
        BufferedReader brp = new BufferedReader(frp);
        BufferedReader brn = new BufferedReader(frn);

        positive(brp);
        negative(brn);

        brp.close();
        brn.close();
        frp.close();
        frn.close();
    }

    private void positive(BufferedReader br) throws IOException
    {
        n = 0;
        vocabuary = 0;
        String line = "";
        String[] tokens;
        positiveFreq = new HashMap<String, Integer>();
        positiveProb = new HashMap<String, Double>();

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
            String key = entry.getKey();
            double value = entry.getValue();
            double d = (n + vocabuary); // denominator
            double prob = (positiveFreq.get(key) + 1) / d;

            positiveProb.put(key, prob);
        }

        System.out.println("Total postive words: " + n);
        System.out.println("Total unique positive words: " + vocabuary);
    }

    private void negative(BufferedReader br) throws IOException
    {
        n = 0;
        vocabuary = 0;
        String line = "";
        String[] tokens;
        negativeitiveFreq = new HashMap<String, Integer>();
        negativeProb = new HashMap<String, Double>();

        // Read pos.txt and store each unique word's count in the file
        while ((line = br.readLine()) != null)
        {
            line = line.toLowerCase();
            tokens = line.split(" ");

            for (int i = 0; i < tokens.length; i++)
            {
                if (negativeitiveFreq.get(tokens[i]) == null)
                {
                    negativeitiveFreq.put(tokens[i], 1);
                    vocabuary++;
                    n++;
                }
                else
                {
                    int freq = negativeitiveFreq.get(tokens[i]);
                    freq++;
                    negativeitiveFreq.put(tokens[i], freq);
                    n++;
                }
            }
        }


        for (Map.Entry<String, Integer> entry : negativeitiveFreq.entrySet())
        {
            String key = entry.getKey();
            double value = entry.getValue();
            double d = (n + vocabuary); // denominator
            double prob = (negativeitiveFreq.get(key) + 1) / d;

            negativeProb.put(key, prob);
        }

        System.out.println("Total negative words: " + n);
        System.out.println("Total unique negative words: " + vocabuary);
    }
}
