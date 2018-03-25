package edu.nchu.cs.ai.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import edn.nchu.cs.utils.StringUtil;
import edu.nchu.cs.ai.evaluator.BinaryBitEvaluator;
import edu.nchu.cs.ai.transitor.NeighborTransitor;

public class SimulatedAnnealing implements SearchOptimization{
	private int[] bitArray;
	private NeighborTransitor transitor;
	private BinaryBitEvaluator evaluator;
	private int[] localOptimum;
	private int maxCount = -1;
	private int bitCount;
	private int neighborLimit;
	private int transitTimes;

	private SimulatedAnnealing() {
		//not allow initialize a constructor without arguments
	}
	public SimulatedAnnealing(int bitCount, int neighborLimit, int transitTimes) {
		this.bitCount = bitCount;
		this.neighborLimit = neighborLimit;
		this.transitTimes = transitTimes;
	}
	@Override
	public OptimumSolution run() {
		int objValue = 0;
		List<Integer> detail = new ArrayList<>();
		double tempture = 10.0;
		double delta = 0.99;
		double minTempture = 0.0001;
		double p = 0.0;
		double cp = 0.0;
		//1. initialization
		this.init(this.bitCount, this.neighborLimit);
		while (this.transitor.hasNext()) {
			//2. transition
			this.localOptimum = this.transitor.transit();
			//3. evaluation
			objValue = this.evaluator.evaluate(this.localOptimum);
			//4. determination
			if (objValue > this.maxCount) {
				this.maxCount = objValue;
				this.bitArray = ArrayUtils.clone(this.localOptimum);
			}else {
				p = Math.exp((objValue-this.maxCount)/tempture);
				cp = new Random().nextDouble();
//				System.out.println("objValue=" + objValue + ",max="+ this.maxCount +",p="+p +",cp="+cp+",result="+(p>cp));
				if (cp < p) {
					this.maxCount = objValue;
					this.bitArray = ArrayUtils.clone(this.localOptimum);
				}
			}
			tempture *= delta;
			detail.add(this.maxCount);
			this.transitTimes--;
			if (tempture < minTempture || this.transitTimes == 0) {
				break;
			}
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
