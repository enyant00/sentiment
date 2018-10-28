import java.io.IOException;
import java.util.LinkedList;

public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        DataSeparator dataSeparator = new DataSeparator();
        NaiveBayes naiveBayes = new NaiveBayes();

        LinkedList<String> testingData = new LinkedList<String>();
        FileManager.readIntoLinkedList("positiveTesting.txt", testingData);

        for (String line : testingData)
        {
            System.out.println(naiveBayes.classify(line));
        }
    }
}
