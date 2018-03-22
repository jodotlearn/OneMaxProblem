package edu.nchu.cs.ai.transitor;

public class BinaryBitTransitor {
	private long maxCount;
	private long current;
	private int[] bitArray;
	public BinaryBitTransitor(int bitCount){
		maxCount = 1 << bitCount;
		current = 1;
		bitArray = new int[bitCount];
	}
	
	public int[] transit() {
		for (int i=bitArray.length-1;i>=0;i--) {
			if (bitArray[i] == 0) {
				bitArray[i] = 1;
				current++;
				return bitArray;
			}
			bitArray[i] = 0;
		}
		current++;
		return bitArray;
	}
	
	public boolean hasNext() {
		return current < maxCount;
	}
}
