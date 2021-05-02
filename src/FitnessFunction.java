import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.terminal.Variable;

public class FitnessFunction extends GPFitnessFunction {

    private double[] inputX;
    private double[] outputY;

    private Variable xVariable;

    private static Object[] NO_ARGS = new Object[0];

    public FitnessFunction(double[] input, double[] output, Variable x) {
        inputX = input;
        outputY = output;
        xVariable = x;
    }

    @Override
    protected double evaluate(final IGPProgram igpProgram) {
        double ans = 0.;

        for(int i=0; i<inputX.length; i++){
            xVariable.set(inputX[i]);

            double value =  igpProgram.execute_double(0,NO_ARGS);
            ans += Math.abs(value - outputY[i]);

        }
        return ans;
    }
}
