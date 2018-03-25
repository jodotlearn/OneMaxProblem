package edu.nchu.cs.ai.evaluator;

public class BinaryBitEvaluator implements Evaluator<Integer, int[]>{

	@Override
	public Integer evaluate(int[] bitArray) {
		int cnt = 0;
		for (int i = 0;i < bitArray.length;i++) {
			cnt += bitArray[i];
		}
		return cnt;
	}

}
