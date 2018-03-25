package edu.nchu.cs.ai.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edn.nchu.cs.utils.StringUtil;
import edu.nchu.cs.ai.evaluator.BinaryBitEvaluator;
import edu.nchu.cs.ai.transitor.NeighborTransitor;

public class HillClimbing implements SearchOptimization{
	private int[] bitArray;
	private NeighborTransitor transitor;
	private BinaryBitEvaluator evaluator;
	private int[] localOptimum;
	private int maxCount = -1;
	private int bitCount;
	private int neighborLimit;
	private int transitTimes;

	private HillClimbing() {
		//not allow initialize a constructor without arguments
	}
	public HillClimbing(int bitCount, int neighborLimit, int transitTimes) {
		this.bitCount = bitCount;
		this.neighborLimit = neighborLimit;
		this.transitTimes = transitTimes<0?bitCount:transitTimes;
	}

	@Override
	public OptimumSolution run() {
		List<Integer> detail = new ArrayList<>();
		int objValue = 0;
		boolean foundBetterSolution = false;
		//1. initialize
		this.init(this.bitCount, this.neighborLimit);

		while(objValue==0 || foundBetterSolution) {
			foundBetterSolution = false;
			//2. transition
			this.localOptimum = this.transitor.transit();
			//3. evaluation
			objValue = this.evaluator.evaluate(this.localOptimum);
			//4. determination
			if (objValue > this.maxCount) {
				foundBetterSolution = true;
				this.maxCount = objValue;
				this.bitArray = Arrays.copyOf(this.localOptimum, this.localOptimum.length);
			}
			detail.add(this.maxCount);
			this.transitTimes--;
		}
		OptimumSolution os = new OptimumSolution<>();
		os.setSolution(StringUtil.toString(this.bitArray));
		os.setObjectiveValue(this.maxCount);
		os.setExecuteDetail(detail);
		return os;
	}

	private void init(int bitCount, int neighborLimit) {
		this.bitArray = new int[bitCount];
		this.localOptimum = new int[bitCount];
		Random rnd = new Random();
		int cnt = 0;
		for (int i=0;i<this.bitArray.length;i++) {
			this.bitArray[i] = rnd.nextInt(2);
			cnt+=this.bitArray[i];
		}
		this.maxCount = cnt;
		this.transitor = new NeighborTransitor(bitCount, this.bitArray, neighborLimit);
		this.evaluator = new BinaryBitEvaluator();
	}
}
