//=================================================================================================
// Program		: Sentiment Analysis
// Class		: FileManager.java
// Developer	: Zachary Rowton
// Abstract		: Class for various file reading and writing tasks, and data preparation.
//=================================================================================================
import java.util.LinkedList;
import java.io.*;
import java.util.Random;

public class FileManager
{
    public static void readIntoLinkedList(String fileName, LinkedList<String> list) throws IOException
    {
        list.clear();
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = "";
        while((line = br.readLine()) != null)
        {
            list.add(line);
        }
        fr.close();
        br.close();
    }

    public static void writeTrainingData(String fileName, LinkedList<String> list) throws IOException
    {
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < list.size() * 0.8; i++)
        {
            bw.write(list.get(i) + "\n");
        }

        //fw.close();
        bw.close();
    }

    public static void writeTestingData(String fileName, LinkedList<String> list) throws IOException
    {
        File file = new File(fileName);
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = (int)(list.size() * 0.8); i < list.size(); i++)
        {
            bw.write(list.get(i) + "\n");
        }

        //fw.close();
        bw.close();
    }

    public static void randomizeList(LinkedList<String> list)
    {
        LinkedList<String> randomizedList = new LinkedList<>();
        Random random = new Random();
        int index;

        while (!list.isEmpty())
        {
            index = random.nextInt(list.size());

            randomizedList.add(list.get(index));
            list.remove(index);

        }

        for (int i = 0; i < randomizedList.size(); i++)
        {
            list.add(randomizedList.get(i));
        }
    }
}
