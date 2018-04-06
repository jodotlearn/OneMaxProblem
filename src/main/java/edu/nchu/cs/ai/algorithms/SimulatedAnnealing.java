package edu.nchu.cs.ai.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import edn.nchu.cs.utils.StringUtil;
import edu.nchu.cs.ai.evaluator.BinaryBitEvaluator;
import edu.nchu.cs.ai.transitor.RandomNeighborTransitor;

public class SimulatedAnnealing implements SearchOptimization{
	private int[] bitArray;
	private RandomNeighborTransitor transitor;
	private BinaryBitEvaluator evaluator;
	private int[] neighbor;
	private int maxCount = -1;
	private int bitCount;
	private int transitTimes;

	private SimulatedAnnealing() {
		//not allow initialize a constructor without arguments
	}
	public SimulatedAnnealing(int bitCount, int transitTimes) {
		this.bitCount = bitCount;
		this.transitTimes = transitTimes;
	}
	@Override
	public OptimumSolution run() {
		int objValue = 0;
		List<Integer> detail = new ArrayList<Integer>();
		double tempture = 1.0;
		double delta = 0.99;
		double minTempture = 0.0001;
		double p = 0.0;
		double cp = 0.0;
		//1. initialization
		this.init(this.bitCount);
		while (this.transitor.hasNext()) {
			//2. transition
			this.neighbor = this.transitor.transit();
			//3. evaluation
			objValue = this.evaluator.evaluate(this.neighbor);
			//4. determination
			if (objValue > this.maxCount) {
				this.maxCount = objValue;
				this.bitArray = Arrays.copyOf(this.neighbor, this.neighbor.length);
			}else {
				p = Math.exp((objValue-this.maxCount)/tempture);
				cp = new Random().nextDouble();
				if (cp < p) {
					this.maxCount = objValue;
					this.bitArray = Arrays.copyOf(this.neighbor, this.neighbor.length);
				}
			}
			this.transitor.setCurrent(this.bitArray);
			detail.add(this.maxCount);
			this.transitTimes--;
			tempture *= delta;
			if (tempture < minTempture || this.transitTimes < 0) {
				break;
			}
		}
		OptimumSolution os = new OptimumSolution<Object, Object, Object>();
		os.setSolution(StringUtil.toString(this.bitArray));
		os.setObjectiveValue(this.maxCount);
		os.setExecuteDetail(detail);
		return os;
	}

	private void init(int bitCount) {
		this.bitArray = new int[bitCount];
		this.neighbor = new int[bitCount];
		int cnt = 0;
		for (int i=0;i<this.bitArray.length;i++) {
			Random rnd = new Random();
			this.bitArray[i] = rnd.nextInt(2);
			cnt+=this.bitArray[i];
		}
		this.maxCount = cnt;
		this.transitor = new RandomNeighborTransitor(bitCount, this.bitArray);
		this.evaluator = new BinaryBitEvaluator();
	}
}
