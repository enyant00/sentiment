//=================================================================================================
// Program		: Sentiment Analysis
// Class		: DataSeparator.java
// Developer	: Zachary Rowton
// Abstract		: Reads in the data provided, randomizes the ordering of the sentences,
//                and splits the data into 80% training and 20% testing,
//                and writes to file for use in the NaiveBayes class.
//=================================================================================================
import java.io.*;
import java.util.LinkedList;

public class DataSeparator
{
    public DataSeparator() throws IOException
    {
        LinkedList<String> documents = new LinkedList<String>();

        FileManager.readIntoLinkedList("pos.txt", documents);
        FileManager.randomizeList(documents);
        FileManager.writeTrainingData("positiveTraining.txt", documents);
        FileManager.writeTestingData("positiveTesting.txt", documents);

        FileManager.readIntoLinkedList("negative.txt", documents);
        FileManager.randomizeList(documents);
        FileManager.writeTrainingData("negativeTraining.txt", documents);
        FileManager.writeTestingData("negativeTesting.txt", documents);

        FileManager.readIntoLinkedList("neutral.txt", documents);
        FileManager.randomizeList(documents);
        FileManager.writeTrainingData("neutralTraining.txt", documents);
        FileManager.writeTestingData("neutralTesting.txt", documents);
    }
}
