/*
 * ADAPTED THIS FROM JGAP EXAMPLES CODE
 * This file is part of JGAP.
 *
 * JGAP offers a dual license model containing the LGPL as well as the MPL.
 *
 * For licensing information please see the file license.txt included with JGAP
 * or have a look at the top of class org.jgap.Chromosome which representatively
 * includes the JGAP license policy applicable for any file delivered with JGAP.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.jgap.gp.*;
import org.jgap.gp.impl.*;

import part3.GPMathProblem;

public class BinaryClassification {
	private static JFileChooser fileChooser = new JFileChooser();
	private static final String TRAINSET_FILENAME = "cancer-training.txt";
	private static final String TESTSET_FILENAME = "cancer-test.txt";
	private static final int MAX_GENERATIONS = 1000;
	
	//Inputs are the attributes from the file. 
	private static ArrayList<CancerPatient> inputs;
	//Expected classes: 2 or 4 from the instances
	private static ArrayList<Integer> correctOutputs;
	
	public static void main(String[] args) throws Exception {
		chooseDir();
		
		GPConfiguration config = new GPConfiguration();
		config.setGPFitnessEvaluator(new DefaultGPFitnessEvaluator());
		//config.setMinInitDepth(6);
		config.setMaxInitDepth(6);
		config.setPopulationSize(200);
		config.setMaxCrossoverDepth(6);
		config.setFitnessFunction(new GPMathProblem.FormulaFitnessFunction());
		config.setStrictProgramCreation(true);
		GPMathProblem problem = new GPMathProblem(config, inputs, correctOutputs);
		GPGenotype gp = problem.create();
		gp.setVerboseOutput(true);
		
		System.out.println("Maximum of " + MAX_GENERATIONS + " generations");
		for (int i = 0; i < MAX_GENERATIONS; i++) {
			gp.evolve(1);
			if (gp.getAllTimeBest() != null && gp.getAllTimeBest().getFitnessValue() >= 98) {
				System.out.println("\nFound a program with desirable fitness after " + i + " generations\n");
				break;
			}
		}
		
		gp.outputSolution(gp.getAllTimeBest());
	}
	
	private static void chooseDir() {
		File train = null, test = null;

		// set up the file chooser
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setDialogTitle("Select input directory");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		// run the file chooser and check the user didn't hit cancel
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			// get the files in the selected directory and match them to
			// the files we need.
			File directory = fileChooser.getSelectedFile();
			File[] files = directory.listFiles();

			for (File f : files) {
				if (f.getName().equals(TRAINSET_FILENAME)) {
					train = f;
				} else if (f.getName().equals(TESTSET_FILENAME)) {
					test = f;
				}
			}

			// check none of the files are missing, and call the load
			// method in your code.
			if (train == null || test == null) {
				JOptionPane.showMessageDialog(null, "Directory does not contain correct files", "Error",
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			} else {
				//TODO change this to train or test depending on what you wanna do
				readFile(train);
			}
		}
	}
	
	private static void readFile(File file) {
		try {
			String line = null;
			inputs = new ArrayList<CancerPatient>();
			correctOutputs = new ArrayList<Integer>();
			
			System.out.println("Loading training.");
			BufferedReader data = new BufferedReader(new FileReader(file));
			while ((line = data.readLine()) != null) {
				String[] values = line.split(",");
				int i = 0;
				inputs.add(new CancerPatient(values[i++], values[i++], values[i++], 
						values[i++], values[i++], values[i++],values[i++], 
						values[i++], values[i++], values[i++]));
				correctOutputs.add(Integer.parseInt(values[i++]));
			}
			data.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
