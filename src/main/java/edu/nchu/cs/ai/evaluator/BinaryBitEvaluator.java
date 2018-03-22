package edu.nchu.cs.ai.evaluator;

public class BinaryBitEvaluator {
	
	public int evaluate(int[] bitArray) {
		int cnt = 0;
		for (int i = 0;i < bitArray.length;i++) {
			cnt += bitArray[i];
		}
		return cnt;
	}
}
