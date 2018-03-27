package edu.nchu.cs.ai.transitor;

import java.util.Arrays;
import java.util.Random;

/**
 * Random Neighbor Transitor
 * generate a neighbor of the current solution randomly.
 * @author Jo
 *
 */
public class NeighborTransitor<T> implements Transitor{
	private int[] currentArray;
	private int bitCount;
	private int transitTimes;
	public NeighborTransitor(int bitCount, int[] currentArray) {
		this.currentArray = Arrays.copyOf(currentArray, currentArray.length);
		this.bitCount = bitCount;
	}

	@Override
	public void setCurrent(int[] current) {
		this.currentArray = Arrays.copyOf(current, current.length);
	}

	@Override
	public int[] transit(){
//		int localOptimum = 0;
//		int objValue = 0;
//		int keepIdx = -1;
//		List<Integer> pos = new ArrayList<>();
//		List<int[]> neighbors = new ArrayList<>();
//		for (int i=0;i<this.bitCount;i++) {
		int[] tempArray = Arrays.copyOf(this.currentArray, this.currentArray.length);
		Random rnd = new Random();
		int idx = rnd.nextInt(this.bitCount);
		tempArray[idx] = tempArray[idx]^1;//0^1=1, 1^1=0
//			pos.add(idx);
//			neighbors.add(tempArray);
//		}
//
//		for (int i=0;i<neighbors.size();i++) {
//			objValue = this.eval(neighbors.get(i));
//			if (objValue > localOptimum) {
//				localOptimum = objValue;
//				keepIdx = i;
//			}
//		}
//		if (keepIdx >= 0) {
//			int [] keepArray = neighbors.get(keepIdx);
//			this.currentArray = Arrays.copyOf(keepArray, keepArray.length);
//		}
		this.currentArray = Arrays.copyOf(tempArray, tempArray.length);
		return this.currentArray;
	}

	@Override
	public boolean hasNext() {
		return true;
	}

	private int eval(int[] arr) {
		int val = 0;
		for (int i=0;i<arr.length;i++) {
			val += arr[i];
		}
		return val;
	}


}
