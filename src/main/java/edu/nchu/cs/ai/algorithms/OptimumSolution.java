package edu.nchu.cs.ai.algorithms;

public class OptimumSolution<T1, T2, T3> {
	private T1 solution;
	private T2 objectiveValue;
	private T3 detail;

	void setSolution(T1 solution) {
		this.solution = solution;
	}

	void setObjectiveValue(T2 objValue) {
		this.objectiveValue = objValue;
	}

	public T1 getSolution() {
		return this.solution;
	}

	public T2 getObjectiveValue() {
		return this.objectiveValue;
	}

	public void setExecuteDetail(T3 detail) {
		this.detail = detail;
	}

	public T3 getExecuteDetail() {
		return this.detail;
	}

}
