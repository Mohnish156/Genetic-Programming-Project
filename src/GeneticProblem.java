
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.function.*;
import org.jgap.gp.impl.DefaultGPFitnessEvaluator;
import org.jgap.gp.impl.DeltaGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.terminal.Constant;
import org.jgap.gp.terminal.Terminal;
import org.jgap.gp.terminal.Variable;

import java.io.File;
import java.util.Scanner;

public class GeneticProblem extends GPProblem {


    private static final int maxCrossoverDepth = 10;
    private static final int maxInitDepth = 6;
    private static final int populationSize = 1000;
    private static final int evolutions = 1000;

    private double[] inputX;
    private double[] outputY;

    private Variable xVariable;

    public GeneticProblem() throws InvalidConfigurationException {
        super(new GPConfiguration());
        GPConfiguration config = getGPConfiguration();
        Parser parser = new Parser();

        inputX = parser.getInputX();
        outputY = parser.getOutputY();

        xVariable = Variable.create(config, "X", CommandGene.DoubleClass);

        config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
        config.setMaxInitDepth(maxInitDepth);
        config.setPopulationSize(populationSize);
        config.setMaxCrossoverDepth(maxCrossoverDepth);
        config.setFitnessFunction(new FitnessFunction(inputX,outputY,xVariable));
        config.setStrictProgramCreation(true);

    }

    @Override
    public GPGenotype create() throws InvalidConfigurationException {

        GPConfiguration config = getGPConfiguration();

        Class[] types = {CommandGene.DoubleClass};
        Class[][] argTypes = { {} };

        CommandGene[][] nodeSets = {
                {
                    xVariable,
                    new Add(config, CommandGene.DoubleClass),
                    new Multiply(config,CommandGene.DoubleClass),
                    new Subtract(config,CommandGene.DoubleClass),
                    new Pow(config,CommandGene.DoubleClass),
                    new Terminal(config,CommandGene.DoubleClass,0.0,10.0,false)
                }
        };

        return GPGenotype.randomInitialGenotype(config,types,argTypes,nodeSets,20,true);
    }


    public static void main(String[] args) throws InvalidConfigurationException {
        GPProblem problem = new GeneticProblem();
        GPGenotype gp = problem.create();
        gp.setVerboseOutput(true);
        gp.evolve(evolutions);
        gp.outputSolution(gp.getAllTimeBest());

    }
}
