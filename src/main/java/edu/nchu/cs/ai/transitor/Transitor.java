package edu.nchu.cs.ai.transitor;

public interface Transitor<T> {
	public void setCurrent(T current);
	public <T> T transit();
	public boolean hasNext();
}
