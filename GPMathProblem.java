import java.util.ArrayList;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.GPProblem;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.function.Add;
import org.jgap.gp.function.Add3;
import org.jgap.gp.function.Divide;
import org.jgap.gp.function.Exp;
import org.jgap.gp.function.Multiply;
import org.jgap.gp.function.Multiply3;
import org.jgap.gp.function.Pow;
import org.jgap.gp.function.Sine;
import org.jgap.gp.function.Subtract;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.terminal.Terminal;
import org.jgap.gp.terminal.Variable;

public class GPMathProblem  extends GPProblem {
	public static Variable a1;
	public static Variable a2;
	public static Variable a3;
	public static Variable a4;
	public static Variable a5;
	public static Variable a6;
	public static Variable a7;
	public static Variable a8;
	public static Variable a9;
	private GPConfiguration config;
	private static ArrayList<CancerPatient> inputs;
	private static ArrayList<Integer> correctOutputs;
		
	public GPMathProblem(GPConfiguration config, ArrayList<CancerPatient> inputs, ArrayList<Integer> correctOutputs) {
		this.config = config;
		GPMathProblem.inputs = inputs;
		GPMathProblem.correctOutputs = correctOutputs;
	}

	@Override
	public GPGenotype create()	throws InvalidConfigurationException {
		Class[] types = {CommandGene.IntegerClass,	CommandGene.IntegerClass};
		Class[][] argTypes = {
				{},
				{CommandGene.IntegerClass, CommandGene.IntegerClass, CommandGene.IntegerClass}
		};
		CommandGene[][] nodeSets = { 
				{
					a1 = Variable.create(config, "A1", CommandGene.IntegerClass),
					a2 = Variable.create(config, "A2", CommandGene.IntegerClass),
					a3 = Variable.create(config, "A3", CommandGene.IntegerClass),
					a4 = Variable.create(config, "A4", CommandGene.IntegerClass),
					a5 = Variable.create(config, "A5", CommandGene.IntegerClass),
					a6 = Variable.create(config, "A6", CommandGene.IntegerClass),
					a7 = Variable.create(config, "A7", CommandGene.IntegerClass),
					a8 = Variable.create(config, "A8", CommandGene.IntegerClass),
					a9 = Variable.create(config, "A9", CommandGene.IntegerClass),
					new Multiply(config, CommandGene.IntegerClass),
					//new Multiply3(config, CommandGene.IntegerClass),
					new Divide(config, CommandGene.IntegerClass),
					//new Sine(config, CommandGene.FloatClass),
					//new Exp(config, CommandGene.IntegerClass),
					new Subtract(config, CommandGene.IntegerClass),
					new Add(config, CommandGene.IntegerClass),
					new Pow(config, CommandGene.IntegerClass),
					new Terminal(config, CommandGene.IntegerClass,-10, 10, true),
				},
				{ new Add3(config, CommandGene.IntegerClass), }
		};
		return GPGenotype.randomInitialGenotype(config, types, argTypes, nodeSets,
				20, true);
	}

	public static class FormulaFitnessFunction extends GPFitnessFunction {
		protected double evaluate(final IGPProgram subject) {
			return computeRawFitness(subject);
		}
		public double computeRawFitness(final IGPProgram ind) {
			double totalCorrect = 0.0f;
			Object[] noargs = new Object[0];
			for (int i = 0; i < inputs.size(); i++) {
				// Put in the inputs which are the 9 attributes
				// -------------------------------------------------------------
				a1.set(inputs.get(i).a1);
				a2.set(inputs.get(i).a2);
				a3.set(inputs.get(i).a3);
				a4.set(inputs.get(i).a4);
				a5.set(inputs.get(i).a5);
				a6.set(inputs.get(i).a6);
				a7.set(inputs.get(i).a7);
				a8.set(inputs.get(i).a8);
				a9.set(inputs.get(i).a9);
				try {
					int result = ind.execute_int(0, noargs);
					if (result >= 0){		
						//Benign
						result = 2; 
					} else if (result < 0){	
						//Malignant
						result = 4;
					}
					//Check if correct classification
					if (result == correctOutputs.get(i)){
						totalCorrect++;
					} 
				} catch (ArithmeticException ex) {
					// This should not happen, some illegal operation was executed.
					// ------------------------------------------------------------
					System.out.println("x = " + inputs.get(i).toString());
					System.out.println(ind);
					throw ex;
				}
			}
			//Return the classification percentage
			return (totalCorrect/(inputs.size()) * 100);
		}
	}
}
