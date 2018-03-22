package edu.nchu.cs.ai.algorithms;

import edu.nchu.cs.ai.evaluator.BinaryBitEvaluator;
import edu.nchu.cs.ai.transitor.BinaryBitTransitor;

public class ExhaustiveSearch{
	private BinaryBitTransitor transitor;
	private BinaryBitEvaluator evaluator;
	public OptimumSolution run(int runCount, int bitCount) {
		int cnt = 0;
		int objValue = 0;
		int maxValue = -1;
		int[] bitArray;
		int[] keepValue;
		long startTime = 0;
		while (cnt < runCount) {
			System.out.println("prepare to execute " + bitCount + " bit");
			startTime = System.currentTimeMillis();
			bitArray = new int[bitCount];
			keepValue = new int[bitCount];
			objValue = 0;
			maxValue = -1;
			//1. initialization
			init(bitCount);
			while (transitor.hasNext()) {
				//2. transition
				bitArray = transitor.transit();
				//3. evaluation
				objValue = evaluator.evaluate(bitArray);
				//4. determination
				if (objValue > maxValue) {
					maxValue = objValue;
					System.arraycopy(bitArray, 0, keepValue, 0, bitArray.length);
				}
			}
			StringBuilder buf = new StringBuilder();
			for (int i = 0; i < keepValue.length; i++) {
				buf.append(keepValue[i]);
			}
			System.out.println("  The one-max objective value of " + bitCount + " bit is:");
			System.out.println("  " + buf.toString());
			System.out.println("  spent " + ((double) (System.currentTimeMillis()-startTime))/1000 + " seconds");
			System.out.println();
			cnt++;
		}
		return null;
	}
	private void init(int bitCount) {
		transitor = new BinaryBitTransitor(bitCount);
		evaluator = new BinaryBitEvaluator();
	}
}
