package edu.nchu.cs.ai.transitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Random Neighbor Transitor
 * generate neighbors randomly and choose the neighbor that has the best objective value.
 * @author Jo
 *
 */
public class NeighborTransitor implements Transitor{
	private int[] currentArray;
	private int bitCount;
	private int neighborCount;
	private int transitTimes;
	public NeighborTransitor(int bitCount, int[] currentArray, int neighborCount) {
		this.currentArray = ArrayUtils.clone(currentArray);
		this.bitCount = bitCount;
		this.neighborCount = neighborCount==-1?bitCount:neighborCount;
	}
	@Override
	public int[] transit(){
		int localOptimum = 0;
		int objValue = 0;
		int keepIdx = -1;
		List<Integer> pos = new ArrayList<>();
		List<int[]> neighbors = new ArrayList<>();
		for (int i=0;i<this.neighborCount;i++) {
			int[] tempArray = ArrayUtils.clone(this.currentArray);
			Random rnd = new Random();
			int idx = rnd.nextInt(this.bitCount);
			while(pos.contains(idx)) {
				idx = rnd.nextInt(this.bitCount);
			}
			tempArray[idx] = tempArray[idx]^1;//0^1=1, 1^1=0
			pos.add(idx);
			neighbors.add(tempArray);
		}
		for (int i=0;i<neighbors.size();i++) {
			objValue = this.eval(neighbors.get(i));
			if (objValue > localOptimum) {
				localOptimum = objValue;
				keepIdx = i;
			}
		}
		if (keepIdx >= 0) {
			this.currentArray = ArrayUtils.clone(neighbors.get(keepIdx));
		}
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
