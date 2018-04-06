package edu.nchu.cs.ai.transitor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class MemoryTransitor implements Transitor<int[]> {
	private int[] currentArray;
	private int bitCount;
	private int memoryLimit;
	private Queue<int[]> memoryList;

	public MemoryTransitor(int bitCount, int[] currentArray, int memoryLimit) {
		this.currentArray = Arrays.copyOf(currentArray, currentArray.length);
		this.bitCount = bitCount;
		this.memoryLimit = memoryLimit;
		this.memoryList = new LinkedList<int[]>();
	}

	@Override
	public void setCurrent(int[] current) {
		this.currentArray = Arrays.copyOf(current, current.length);
	}

	@Override
	public int[] transit() {
		int[] tempArray;
		Random rnd = new Random();
		boolean found = true;
		while(found) {
			tempArray = Arrays.copyOf(this.currentArray, this.currentArray.length);
			int idx = rnd.nextInt(this.bitCount);
			tempArray[idx] = tempArray[idx]^1;//0^1=1, 1^1=0
			if (!this.inMemoryList(tempArray, this.memoryList)) {
				this.currentArray = Arrays.copyOf(tempArray, tempArray.length);
				this.addToMemoryList(tempArray);
				found = false;
			}
		}
		return this.currentArray;
	}

	private boolean inMemoryList(int[] target, Queue<int[]> memoryList) {
		for (int[] element:memoryList) {
			if (Arrays.equals(target, element)) {
				return true;
			}
		}
		return false;
	}

	private void addToMemoryList(int[] target) {
		if (this.memoryList.size() == this.memoryLimit) {
			this.memoryList.remove();
		}
		this.memoryList.add(target);
	}

	@Override
	public boolean hasNext() {
		return true;
	}

}
