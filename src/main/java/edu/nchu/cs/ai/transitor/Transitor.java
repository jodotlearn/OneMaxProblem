package edu.nchu.cs.ai.transitor;

public interface Transitor {
	public void setCurrent(int[] current);
	public <T> T transit();
	public boolean hasNext();
}
