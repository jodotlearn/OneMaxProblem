package edu.nchu.cs.ai.transitor;

import java.util.Arrays;
import java.util.Random;

/**
 * Random Neighbor Transitor
 * generate a neighbor of the current solution randomly.
 * @author Jo
 *
 */
public class RandomNeighborTransitor implements Transitor<int[]>{
	private int[] currentArray;
	private int bitCount;
	public RandomNeighborTransitor(int bitCount, int[] currentArray) {
		this.currentArray = Arrays.copyOf(currentArray, currentArray.length);
		this.bitCount = bitCount;
	}

	@Override
	public void setCurrent(int[] current) {
		this.currentArray = Arrays.copyOf(current, current.length);
	}

	@Override
	public int[] transit(){
		int[] tempArray = Arrays.copyOf(this.currentArray, this.currentArray.length);
		Random rnd = new Random();
		int idx = rnd.nextInt(this.bitCount);
		tempArray[idx] = tempArray[idx]^1;//0^1=1, 1^1=0
		this.currentArray = Arrays.copyOf(tempArray, tempArray.length);
		return this.currentArray;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

}
