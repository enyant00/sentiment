import java.util.LinkedList;
import java.io.*;

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
}
