import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class GeneticProgramming {

    //private double [][]inputOutputArray = new double[20][1];

    private TreeMap<Float,Float> inputOutputMap = new TreeMap<>();

    public GeneticProgramming(){ }


    public void parseRegression()  {

        try {
            File file = new File("regression.txt");
            Scanner scanner = new Scanner(file);

            scanner.nextLine();
            scanner.nextLine();

            while (scanner.hasNextLine()) {

                float x = scanner.nextFloat();
                float y = scanner.nextFloat();

                inputOutputMap.put(x,y);
                scanner.nextLine();
            }
            scanner.close();

        }catch (Exception e){e.printStackTrace();}

    }


    public static void main(String[] args)  {
        GeneticProgramming gp = new GeneticProgramming();
        gp.parseRegression();
    }
}


