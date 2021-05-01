
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.function.Add;
import org.jgap.gp.function.Multiply;
import org.jgap.gp.impl.DefaultGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.terminal.Constant;
import org.jgap.gp.terminal.Terminal;
import org.jgap.gp.terminal.Variable;

import java.io.File;
import java.util.Scanner;

public class GeneticProblem extends GPProblem {


    private double[] inputX = new double[20];
    private double[] outputY = new double[20];

    private Constant zero;
    private Variable xVariable;

    public GeneticProblem() throws InvalidConfigurationException {
        super(new GPConfiguration());
        GPConfiguration config = getGPConfiguration();
        Parser parser = new Parser();

        inputX = parser.getInputX();
        outputY = parser.getOutputY();

        xVariable = Variable.create(config, "X", CommandGene.IntegerClass);

        config.setPopulationSize(1000);
        config.setGPFitnessEvaluator(new DefaultGPFitnessEvaluator());
        zero = new Constant(config, CommandGene.IntegerClass, 0);
    }

    @Override
    public GPGenotype create() throws InvalidConfigurationException {

        GPConfiguration config = getGPConfiguration();

        Class[] types = {CommandGene.IntegerClass};
        Class[][] argTypes = { {} };

        CommandGene[][] nodeSets = {
                {
                    xVariable,
                    new Add(config, CommandGene.IntegerClass),
                    new Multiply(config,CommandGene.IntegerClass),
                    new Terminal(config,CommandGene.IntegerClass,-2.00,2.75,false)

                }
        };

        return GPGenotype.randomInitialGenotype(config,types,argTypes,nodeSets,20,true);
    }


    public static void main(String[] args) throws InvalidConfigurationException {
        GPProblem problem = new GeneticProblem();

    }
}
