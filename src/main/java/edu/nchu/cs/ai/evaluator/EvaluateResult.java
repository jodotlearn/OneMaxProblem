package edu.nchu.cs.ai.evaluator;

public class EvaluateResult<T> {
	private T result;
	public void setEvaluateResult(T result) {
		this.result = result;
	}
	public T getEvaluateResult() {
		return this.result;
	}
}
