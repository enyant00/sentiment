import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        System.out.println(naiveBayes.classify("a very wonderful cake"));
    }
}
