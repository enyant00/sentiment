import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        NaiveBayes naiveBayes = new NaiveBayes();
        System.out.println(naiveBayes.classify("this is a very good cake"));
    }
}
