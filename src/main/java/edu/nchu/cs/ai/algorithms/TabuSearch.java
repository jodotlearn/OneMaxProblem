package edu.nchu.cs.ai.algorithms;

import java.util.Arrays;
import java.util.Random;

import edn.nchu.cs.utils.StringUtil;
import edu.nchu.cs.ai.evaluator.BinaryBitEvaluator;
import edu.nchu.cs.ai.transitor.MemoryTransitor;

public class TabuSearch implements SearchOptimization {
	private int bitCount;
	private int iteration;
	private int maxCount;
	private int[] bitArray;
	private int[] neighbor;
	private BinaryBitEvaluator evaluator;
	private MemoryTransitor transitor;
	private TabuSearch() {
		//not allow initialize a constructor without arguments
	}
	public TabuSearch(int bitCount, int iteration, int memoryLimit) {
		this.bitCount = bitCount;
		this.iteration = iteration;
		this.bitArray = new int[bitCount];
		this.neighbor = new int[bitCount];
		int cnt = 0;
		Random rnd = new Random();
		for (int i=0;i<this.bitArray.length;i++) {
			this.bitArray[i] = rnd.nextInt(2);
			cnt+=this.bitArray[i];
		}
		this.maxCount = cnt;
		this.evaluator = new BinaryBitEvaluator();
		this.transitor = new MemoryTransitor(bitCount, this.bitArray, memoryLimit);
	}
	@Override
	public OptimumSolution run() {
		int cnt = 0;
		int objValue = 0;
		while (cnt < this.iteration) {
			//1.transition
			this.transitor.setCurrent(this.bitArray);
			this.neighbor = this.transitor.transit();
			//2.evaluation
			objValue = this.evaluator.evaluate(this.neighbor);
			//3.determination
			if (objValue > this.maxCount) {
				this.maxCount = objValue;
				this.bitArray = Arrays.copyOf(this.neighbor, this.neighbor.length);
			}
			cnt++;
		}
		OptimumSolution os = new OptimumSolution();
		os.setObjectiveValue(this.maxCount);
		os.setSolution(StringUtil.toString(this.bitArray));
		return os;
	}

}
