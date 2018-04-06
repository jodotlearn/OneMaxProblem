package edu.nchu.cs.ai;

import edu.nchu.cs.ai.algorithms.ExhaustiveSearch;
import edu.nchu.cs.ai.algorithms.Genetic;
import edu.nchu.cs.ai.algorithms.HillClimbing;
import edu.nchu.cs.ai.algorithms.OptimumSolution;
import edu.nchu.cs.ai.algorithms.SearchOptimization;
import edu.nchu.cs.ai.algorithms.SelectionType;
import edu.nchu.cs.ai.algorithms.SimulatedAnnealing;
import edu.nchu.cs.ai.algorithms.TabuSearch;

public class Algorithms {

	public static void main(String[] args) {
		String algorithm = args[0];
		SearchOptimization so = null;
		long startTime = System.currentTimeMillis();
		int bitCount = Integer.valueOf(args[1]);
		int transitTimes = Integer.valueOf(args[2]);

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
		case "TS":
			//TS 100 500 7
			int memoryLimit;
			try {
				memoryLimit = Integer.valueOf(args[3]);
			}catch(Exception e) {
				memoryLimit = 7;
			}
			so = new TabuSearch(bitCount, transitTimes, memoryLimit);
			break;
		case "GA":
			//GA 100 500 10 TOURNAMENT 0.5 0.1
			//select type: ROULETTEWHEEL, TOURNAMENT
			int populationSize;
			SelectionType type;
			double allowCrossoverRate;
			double allowMutationRate;
			try {
				populationSize = Integer.valueOf(args[3]);
				type = SelectionType.valueOf(args[4].toUpperCase());
				allowCrossoverRate = Double.valueOf(args[5]);
				allowMutationRate = Double.valueOf(args[6]);
			}catch(Exception e) {
				populationSize = 10;
				type = SelectionType.TOURNAMENT;
				allowCrossoverRate = 0.6;
				allowMutationRate = 0.1;
			}
			so = new Genetic(bitCount, transitTimes, populationSize, type, allowCrossoverRate, allowMutationRate);
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
