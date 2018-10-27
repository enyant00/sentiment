import java.io.*;
import java.util.LinkedList;

public class DataSeperator
{
    public DataSeperator() throws IOException
    {
        LinkedList<String> documents = new LinkedList<String>();

        FileManager.readIntoLinkedList("pos.txt", documents);
        FileManager.writeTrainingData("positiveTraining.txt", documents);
        FileManager.writeTestingData("positiveTesting.txt", documents);

        FileManager.readIntoLinkedList("negative.txt", documents);
        FileManager.writeTrainingData("negativeTraining.txt", documents);
        FileManager.writeTestingData("negativeTesting.txt", documents);

        FileManager.readIntoLinkedList("neutral.txt", documents);
        FileManager.writeTrainingData("neutralTraining.txt", documents);
        FileManager.writeTestingData("neutralTesting.txt", documents);
    }
}