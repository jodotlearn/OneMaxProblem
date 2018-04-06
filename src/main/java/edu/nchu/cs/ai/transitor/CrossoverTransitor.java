package edu.nchu.cs.ai.transitor;

import java.util.Arrays;
import java.util.Random;

public class CrossoverTransitor implements Transitor<int[][]> {
	private int bitCount;
	private int[][] current;
	private double rate;

	public CrossoverTransitor(int bitCount, int[][] current, double allowRate) {
		this.bitCount = bitCount;
		this.current = new int[current.length][bitCount];
		this.setCurrent(current);
		this.rate = allowRate;
	}

	@Override
	public void setCurrent(int[][] current) {
		for (int i=0;i<current.length;i++) {
			this.current[i] = Arrays.copyOf(current[i], current[i].length);
		}
	}

	@Override
	public int[][] transit() {
		Random rnd = new Random();
		int childIdx = 0;
		int[][] childs = new int[this.current.length][this.bitCount];
		while (childIdx < this.current.length) {
			int parent1Idx = rnd.nextInt(this.current.length);
			int parent2Idx = rnd.nextInt(this.current.length);
			if (rnd.nextDouble() <= this.rate) {
				int[] parent1 = this.current[parent1Idx];
				int[] parent2 = this.current[parent2Idx];
				int crossPos = rnd.nextInt(parent1.length) + 1;
				System.arraycopy(parent1, 0, childs[childIdx], 0, crossPos);
				System.arraycopy(parent2, crossPos, childs[childIdx], crossPos, this.bitCount - crossPos);
				childIdx++;
				System.arraycopy(parent2, 0, childs[childIdx], 0, crossPos);
				System.arraycopy(parent1, crossPos, childs[childIdx], crossPos, this.bitCount - crossPos);
				childIdx++;
			}
		}
		return childs;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

}
