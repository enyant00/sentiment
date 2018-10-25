//=================================================================================================
// Program		: Sentiment Analysis
// Class		: NaiveBayes.java
// Developer	: Zachary Rowton, Brandon Edwards
// Abstract		:
//=================================================================================================

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;

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
    private HashMap<String, Integer> totalFreq = new HashMap<String, Integer>();
    private LinkedList<String> positiveDocuments;
    private LinkedList<String> negativeDocuments;
    private LinkedList<String> neutralDocuments;
    
    /*
     * NOTES
     * 
     * TF-IDF:
     * TF = Frequency of word in document. (Number of times word appears in the given sentence
     * IDF = N / df where N is total number of documents (sentences) in collection and 
     * df is number of documents (sentences) where word appears.
     * 
     */

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
        positiveDocuments = new LinkedList<String>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // Read pos.txt and store each unique word's count in the file
        while ((line = br.readLine()) != null)
        {
            line = line.toLowerCase();
            positiveDocuments.add(line);
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
                
                if (totalFreq.get(tokens[i]) == null)
                {
                	totalFreq.put(tokens[i], 1);
                }
                else
                {
                	int freq = totalFreq.get(tokens[i]);
                	freq++;
                	totalFreq.put(tokens[i], freq);
                }
                
                calcWeight(line, tokens[i]); // TF-IDF weight calculation
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
        negativeDocuments = new LinkedList<String>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // Read pos.txt and store each unique word's count in the file
        while ((line = br.readLine()) != null)
        {
            line = line.toLowerCase();
            negativeDocuments.add(line);
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
                
                if (totalFreq.get(tokens[i]) == null)
                {
                	totalFreq.put(tokens[i], 1);
                }
                else
                {
                	int freq = totalFreq.get(tokens[i]);
                	freq++;
                	totalFreq.put(tokens[i], freq);
                }
                
                calcWeight(line, tokens[i]); // TF-IDF weight calculation
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
        neutralDocuments = new LinkedList<String>();

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        // Read pos.txt and store each unique word's count in the file
        while ((line = br.readLine()) != null)
        {
            line = line.toLowerCase();
            neutralDocuments.add(line);
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
                
                if (totalFreq.get(tokens[i]) == null)
                {
                	totalFreq.put(tokens[i], 1);
                }
                else
                {
                	int freq = totalFreq.get(tokens[i]);
                	freq++;
                	totalFreq.put(tokens[i], freq);
                }
                
                calcWeight(line, tokens[i]); // TF-IDF weight calculation
            }
        }

        br.close();
        fr.close();
    }
    
    private void calcWeight(String document, String word)
    {
    	double tf;
    	double idf;
    	double tfidf;
    }

    public String classify(String review)
    {
        String[] token = review.toLowerCase().split(" ");
        double positiveProbability = linesPositive / (linesPositive + linesNegative + linesNeutral);
        double negativeProbability = linesNegative / (linesPositive + linesNegative + linesNeutral);
        double neutralProbability = linesNeutral / (linesPositive + linesNegative + linesNeutral);
        double numerator;
        double denominator;
        int threshold = 10000;

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
            
            if (neutralFreq.get(word) == null)
            {
            	// do not compute
            }
            
            else if (neutralFreq.get(word) < threshold)
            {
            	numerator = neutralFreq.getOrDefault(word, 0) + 1;
            	denominator = neutralN;
            	neutralProbability *= numerator / denominator;
            }
        }

        //System.out.println("Positive: " + positiveProbability);
        //System.out.println("Negative: " + negativeProbability);
        System.out.println("Input: " + review);
        System.out.printf("Positive: %.10f%% \n", positiveProbability * 100.00);
        System.out.printf("Negative: %.10f%% \n", negativeProbability * 100.00);
        System.out.printf("Neutral: %.10f%% \n", neutralProbability * 100.00);

        if (positiveProbability > negativeProbability && positiveProbability > neutralProbability)
            return "Positive";
        else if (negativeProbability > positiveProbability && negativeProbability > neutralProbability)
        	return "Negative";
        else if (neutralProbability > positiveProbability && neutralProbability > negativeProbability)
        	return "Neutral";
        else
        	return null;
    }
}
