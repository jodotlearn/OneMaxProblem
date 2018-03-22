package edu.nchu.cs.ai.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

public class HillClimbing {
	private int[] bitArray;
	private int[] candidateArray;
	private int maxCount = -1;
	public OptimumSolution run(int runCount, int bitCount) {
		int cnt = 0;
		while(cnt < runCount) {
			init(bitCount);
			int objValue = 0;
			while(objValue > this.maxCount) {
				List<int[]> candidates = transit(bitCount);
				objValue = evaluate(candidates);
				if (objValue > this.maxCount) {
					this.bitArray = ArrayUtils.clone(this.candidateArray);
				}
			}
			cnt++;
			StringBuilder buf = new StringBuilder();
			for (int i = 0; i < bitArray.length; i++) {
				buf.append(bitArray[i]);
			}
			System.out.println("  The one-max objective value of " + bitCount + " bit is:");
			System.out.println("  " + buf.toString());
		}
		return null;
	}
	private void init(int bitCount) {
		this.bitArray = new int[bitCount];
		Random rnd = new Random();
		int cnt = 0;
		for (int i=0;i<bitArray.length;i++) {
			bitArray[i] = rnd.nextInt(2);
			cnt+=bitArray[i];
		}
		this.maxCount = cnt;
	}
	
	private List<int[]> transit(int bitCount) {
		int[] tempArray = this.bitArray;
		List<int[]> neighbors = new ArrayList<>();
		List<Integer> pos = new ArrayList<>();
		for (int i=0;i<bitCount;i++) {
			Random rnd = new Random();
			int idx = rnd.nextInt(bitCount);
			while(pos.contains(idx)) {
				idx = rnd.nextInt(bitCount);
			}
			tempArray[idx] = tempArray[idx]^1;
			pos.add(idx);
			neighbors.add(tempArray);
		}
		return neighbors;
	}
	
	private int evaluate(List<int[]> candidates) {
		int idx = -1;
		int max = -1;
		int cnt = 0;
		for (int i=0;i<candidates.size();i++) {
			cnt = 0;
			for (int j=0;j<candidates.get(i).length;j++) {
				cnt+=candidates.get(i)[j];
			}
			if (cnt > max) {
				max = cnt;
				this.candidateArray = ArrayUtils.clone(candidates.get(i));
			}
		}
		return max;
	}
	
	public static void main(String[] args) {
		HillClimbing hc = new HillClimbing();
		hc.run(10, 10);
	}
}
