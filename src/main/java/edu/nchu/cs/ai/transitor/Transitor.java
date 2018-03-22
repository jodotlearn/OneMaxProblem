package edu.nchu.cs.ai.transitor;

public abstract class Transitor {
	public abstract void setTransitor();
	abstract Transitor getTransitor();
	
	public abstract <T> T transit();
	public abstract boolean hasNext();
}
