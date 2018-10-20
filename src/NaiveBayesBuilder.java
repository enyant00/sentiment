import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class NaiveBayesBuilder
{
    //int nk; // Number of times particular word appears in document
    int n; // Number of total words
    int vocabuary; // Number of unique words
    double c = 1.0/3.0;

    HashMap<String, Integer> positiveFreq;
    HashMap<String, Double>  positiveProb;

    HashMap<String, Integer> negativeFreq;
    HashMap<String, Double>  negativeProb;

    HashMap<String, Integer> neutralFreq;
    HashMap<String, Double>  neutralProb;

    public NaiveBayesBuilder() throws IOException
    {
        File posFile = new File("pos.txt");
        File negativeFile = new File("negative.txt");
        File neutralFile = new File("neutral.txt");

        calcPositive(posFile);
        calcNegative(negativeFile);
        calcNeutral(neutralFile);

        writeHashMaps();
    }

    private void calcPositive(File file) throws IOException
    {
        n = 0;
        vocabuary = 0;
        String line = "";
        String[] tokens;
        positiveFreq = new HashMap<String, Integer>();
        positiveProb = new HashMap<String, Double>();

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
        System.out.println("Total unique calcPositive words: " + vocabuary);

        br.close();
        fr.close();
    }

    private void calcNegative(File file) throws IOException
    {
        n = 0;
        vocabuary = 0;
        String line = "";
        String[] tokens;
        negativeFreq = new HashMap<String, Integer>();
        negativeProb = new HashMap<String, Double>();

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
                    vocabuary++;
                    n++;
                }
                else
                {
                    int freq = negativeFreq.get(tokens[i]);
                    freq++;
                    negativeFreq.put(tokens[i], freq);
                    n++;
                }
            }
        }

        for (Map.Entry<String, Integer> entry : negativeFreq.entrySet())
        {
            String key = entry.getKey();
            double value = entry.getValue();
            double d = (n + vocabuary); // denominator
            double prob = (negativeFreq.get(key) + 1) / d;

            negativeProb.put(key, prob);
        }

        System.out.println("Total calcNegative words: " + n);
        System.out.println("Total unique calcNegative words: " + vocabuary);

        br.close();
        fr.close();
    }

    private void calcNeutral(File file) throws IOException
    {
        n = 0;
        vocabuary = 0;
        String line = "";
        String[] tokens;
        neutralFreq = new HashMap<String, Integer>();
        neutralProb = new HashMap<String, Double>();

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
                    vocabuary++;
                    n++;
                }
                else
                {
                    int freq = neutralFreq.get(tokens[i]);
                    freq++;
                    neutralFreq.put(tokens[i], freq);
                    n++;
                }
            }
        }

        for (Map.Entry<String, Integer> entry : neutralFreq.entrySet())
        {
            String key = entry.getKey();
            double value = entry.getValue();
            double d = (n + vocabuary); // denominator
            double prob = (neutralFreq.get(key) + 1) / d;

            neutralProb.put(key, prob);
        }

        System.out.println("Total calcNeutral words: " + n);
        System.out.println("Total unique calcNeutral words: " + vocabuary);

        br.close();
        fr.close();
    }

    private void writeHashMaps() throws IOException
    {
        File posFreqOut = new File("posFreq.map");
        File posProbOut = new File("posProbOut.map");

        File negativeFreqOut = new File("negativeFreq.map");
        File negativeProbOut = new File("negativeProb.map");

        File neutralFreqOut = new File("neutralFreq.map");
        File neutralProbOut = new File("neutralProb.map");

        writeObject(posFreqOut, positiveFreq);
        writeObject(posProbOut, positiveProb);
        writeObject(negativeFreqOut, negativeFreq);
        writeObject(negativeProbOut, negativeProb);
        writeObject(neutralFreqOut, neutralFreq);
        writeObject(neutralProbOut, neutralProb);
    }

    private void writeObject(File file, HashMap map) throws IOException
    {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(map);
        oos.close();
    }
}
