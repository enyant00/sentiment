import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        DataSeperator dataSeperator = new DataSeperator();
        NaiveBayes naiveBayes = new NaiveBayes();
        System.out.println(naiveBayes.classify("i really love this cake"));
    }
}
