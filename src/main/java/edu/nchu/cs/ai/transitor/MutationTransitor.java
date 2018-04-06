package edu.nchu.cs.ai.transitor;

import java.util.Arrays;
import java.util.Random;

public class MutationTransitor implements Transitor<int[][]> {
	private int bitCount;
	private int[][] current;
	private double rate;

	public MutationTransitor(int bitCount, int[][] current, double allowRate) {
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
		int[][] mutationChilds = new int[this.current.length][this.bitCount];
		for (int i=0;i<this.current.length;i++) {
			mutationChilds[i] = Arrays.copyOf(this.current[i], this.bitCount);
		}
		Random rnd = new Random();
		for (int i=0;i<mutationChilds.length;i++) {
			if (rnd.nextDouble() <= this.rate) {
				int idx = rnd.nextInt(this.bitCount);
				mutationChilds[i][idx] ^= 1;
			}
		}
		return mutationChilds;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

}
