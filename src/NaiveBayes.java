//=================================================================================================
// Program		: Sentiment Analysis
// Class		: NaiveBayes.java
// Developer	: Zachary Rowton
// Abstract		:
//=================================================================================================

import java.io.*;
import java.util.HashMap;

public class NaiveBayes
{
    //private int nk; // Number of times particular word appears in document
    private int positiveN, negativeN, neutralN; // Number of total words
    private int positiveV, negativeV, neutralV, totalV; // Number of unique words
    private double linesPositive = 0.0;
    private double linesNeutral = 0.0;
    private double linesNegative = 0.0;

    private HashMap<String, Integer> positiveFreq;
    private HashMap<String, Integer> negativeFreq;
    private HashMap<String, Integer> neutralFreq;

    public NaiveBayes() throws IOException
    {
        File positiveFile = new File("pos.txt");
        File negativeFile = new File("negative.txt");
        File neutralFile = new File("neutral.txt");

        positiveFreq(positiveFile);
        negativeFreq(negativeFile);
        neutralFreq(neutralFile);
        //System.out.println("Positive lines: " + linesPositive);
        //System.out.println("Negative lines: " + linesNegative);
    }

    private void positiveFreq(File file) throws IOException
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
            linesPositive++;

            for (int i = 0; i < tokens.length; i++)
            {
                if (positiveFreq.get(tokens[i]) == null) // new positive word
                {
                    positiveFreq.put(tokens[i], 1);
                    positiveV++;
                    positiveN++;
                    totalV++;
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

    private void negativeFreq(File file) throws IOException
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
            linesNegative++;

            for (int i = 0; i < tokens.length; i++)
            {
                if (negativeFreq.get(tokens[i]) == null) // new negative word
                {
                    negativeFreq.put(tokens[i], 1);
                    negativeV++;
                    negativeN++;
                    totalV++;
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

    private void neutralFreq(File file) throws IOException
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
            linesNeutral++;

            for (int i = 0; i < tokens.length; i++)
            {
                if (neutralFreq.get(tokens[i]) == null) // new neutral word
                {
                    neutralFreq.put(tokens[i], 1);
                    neutralV++;
                    neutralN++;
                    totalV++;
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

    public String classify(String review)
    {
        String[] token = review.toLowerCase().split(" ");
        double positiveProbability = linesPositive / (linesNegative + linesPositive);
        double negativeProbability = linesNegative / (linesPositive + linesNegative);
        double numerator;
        double denominator;
        int threshold = 10;

        for (String word : token)
        {
            if (positiveFreq.get(word) == null)
            {
                // do not compute
            }
            else if (positiveFreq.get(word) < threshold)
            {
                numerator = positiveFreq.getOrDefault(word, 0) + 1;
                denominator = positiveN;
                positiveProbability *= numerator / denominator;
            }

            if (negativeFreq.get(word) == null)
            {
                // do not compute
            }
            else if (negativeFreq.get(word) < threshold)
            {
                numerator = negativeFreq.getOrDefault(word, 0) + 1;
                denominator = negativeN;
                negativeProbability *= numerator / denominator;
            }
        }

        //System.out.println("Positive: " + positiveProbability);
        //System.out.println("Negative: " + negativeProbability);
        System.out.println("Input: " + review);
        System.out.printf("Positive: %.10f%% \n", positiveProbability * 100.00);
        System.out.printf("Negative: %.10f%% \n", negativeProbability * 100.00);

        if (positiveProbability > negativeProbability)
            return "Positive";
        else
            return "Negative";
    }
}
