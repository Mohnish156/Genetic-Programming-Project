import java.io.File;
import java.util.Scanner;

public class Parser {

    private double[] inputX = new double[20];
    private double[] outputY = new double[20];

    public Parser() {
        parseRegression();
    }

    public void parseRegression()  {

        try {
            File file = new File("regression.txt");
            Scanner scanner = new Scanner(file);

            scanner.nextLine();
            scanner.nextLine();
            int i = 0;
            while (scanner.hasNextLine()) {

                double x = scanner.nextDouble();
                double y = scanner.nextDouble();

                inputX[i] = x;
                outputY[i] = y;
                scanner.nextLine();
                i++;
            }
            scanner.close();

        }catch (Exception e){e.printStackTrace();}

    }

    public double[] getInputX() {
        return inputX;
    }

    public double[] getOutputY() {
        return outputY;
    }
}



