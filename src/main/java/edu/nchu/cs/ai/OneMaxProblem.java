package edu.nchu.cs.ai;

import edu.nchu.cs.ai.algorithms.ExhaustiveSearch;
import edu.nchu.cs.ai.algorithms.HillClimbing;
import edu.nchu.cs.ai.algorithms.OptimumSolution;
import edu.nchu.cs.ai.algorithms.SearchOptimization;
import edu.nchu.cs.ai.algorithms.SimulatedAnnealing;

public class OneMaxProblem {

	public static void main(String[] args) {
		String algorithm = args[0];
		SearchOptimization so = null;
		long startTime = System.currentTimeMillis();
		int bitCount = Integer.valueOf(args[1]);
		int neighborLimit = -1;
		int transitTimes =  -1;
		if (args.length ==3) {
			transitTimes = Integer.valueOf(args[2]);
		}

		switch(algorithm) {
		case "ES":
			so = new ExhaustiveSearch(bitCount);
			break;
		case "HC":
			so = new HillClimbing(bitCount, transitTimes);
			break;
		case "SA":
			so = new SimulatedAnnealing(bitCount, transitTimes);
			break;
		default:
			System.out.println("You must specify an algorithm.");
		}

		OptimumSolution os = so.run();
		System.out.println("  The one-max solution of " + bitCount + " bit is:");
		System.out.println("  " + os.getSolution());
		System.out.println("  spent " + ((double) (System.currentTimeMillis()-startTime))/1000 + " seconds");
		System.out.println();

	}
}
