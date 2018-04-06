package edu.nchu.cs.ai.algorithms;

import edn.nchu.cs.utils.StringUtil;
import edu.nchu.cs.ai.evaluator.BinaryBitEvaluator;
import edu.nchu.cs.ai.transitor.IncreaseTransitor;

public class ExhaustiveSearch implements SearchOptimization{
	private IncreaseTransitor transitor;
	private BinaryBitEvaluator evaluator;
	private int bitCount;
	private ExhaustiveSearch() {
		//not allow initialize a constructor without arguments
	}
	public ExhaustiveSearch(int bitCount) {
		this.bitCount = bitCount;
	}
	@Override
	public OptimumSolution run() {
		int objValue = 0;
		int maxValue = -1;
		int[] bitArray;
		int[] keepValue;
		bitArray = new int[this.bitCount];
		keepValue = new int[this.bitCount];
		objValue = 0;
		maxValue = -1;
		//1. initialization
		this.init(this.bitCount);
		while (this.transitor.hasNext()) {
			//2. transition
			bitArray = this.transitor.transit();
			//3. evaluation
			objValue = this.evaluator.evaluate(bitArray);
			//4. determination
			if (objValue > maxValue) {
				maxValue = objValue;
				System.arraycopy(bitArray, 0, keepValue, 0, bitArray.length);
			}
		}
		OptimumSolution os = new OptimumSolution<>();
		os.setSolution(StringUtil.toString(bitArray));
		os.setObjectiveValue(maxValue);
		return os;
	}
	private void init(int bitCount) {
		this.transitor = new IncreaseTransitor(bitCount);
		this.evaluator = new BinaryBitEvaluator();
	}
}
