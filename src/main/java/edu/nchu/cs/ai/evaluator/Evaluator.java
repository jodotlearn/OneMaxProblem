package edu.nchu.cs.ai.evaluator;

public interface Evaluator<V, D> {
	public V evaluate(D data);
}
