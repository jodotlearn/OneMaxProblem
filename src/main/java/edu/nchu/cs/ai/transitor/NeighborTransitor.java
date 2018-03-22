package edu.nchu.cs.ai.transitor;

import java.util.Random;

public class NeighborTransitor {
	private int[] nextArray;
	public NeighborTransitor(int[] currentArray, int neighborCount) {
		this.nextArray = currentArray;
	}
	public void transit(){
		Random generator = new Random(); 
		int i = generator.nextInt(10);
		
	}
	
	public boolean hasNext() {
		return true;
	}
}
