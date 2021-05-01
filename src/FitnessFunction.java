import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.terminal.Variable;

public class FitnessFunction extends GPFitnessFunction {

    private Integer[] inputX;
    private Integer[] outputY;

    private Variable xVariable;

    public FitnessFunction(Integer[] input, Integer[] output, Variable x) {
        inputX = input;
        outputY = output;
        xVariable = x;
    }

    @Override
    protected double evaluate(IGPProgram igpProgram) {
        double result = 0;
        long longResult = 0;

        for(int i=0; i<inputX.length; i++){
            xVariable.set(inputX[i]);

            long value = igpProgram.execute_int(0,null);
            longResult += Math.abs(value - outputY[i]);
        }

        result = longResult;

        return result;
    }
}
