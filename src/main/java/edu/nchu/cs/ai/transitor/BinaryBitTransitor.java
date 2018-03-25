package edu.nchu.cs.ai.transitor;

public class BinaryBitTransitor implements Transitor{
	private long maxCount;
	private long current;
	private int[] bitArray;
	public BinaryBitTransitor(int bitCount){
		this.maxCount = 1 << bitCount;
		this.current = 1;
		this.bitArray = new int[bitCount];
	}

	@Override
	public int[] transit() {
		for (int i=this.bitArray.length-1;i>=0;i--) {
			if (this.bitArray[i] == 0) {
				this.bitArray[i] = 1;
				this.current++;
				return this.bitArray;
			}
			this.bitArray[i] = 0;
		}
		this.current++;
		return this.bitArray;
	}

	@Override
	public boolean hasNext() {
		return this.current < this.maxCount;
	}
}
