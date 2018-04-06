package edu.nchu.cs.ai.algorithms;

import java.util.Arrays;
import java.util.Random;

import edn.nchu.cs.utils.StringUtil;
import edu.nchu.cs.ai.evaluator.BinaryBitEvaluator;
import edu.nchu.cs.ai.transitor.CrossoverTransitor;
import edu.nchu.cs.ai.transitor.MutationTransitor;

public class Genetic implements SearchOptimization{
	private int bitCount;
	private int[][] population;
	private int iteration;
	private SelectionType type;
	private BinaryBitEvaluator evaluator;
	private CrossoverTransitor crossoverTransitor;
	private MutationTransitor mutationTransitor;

	private Genetic() {
		//not allow initialize a constructor without arguments
	}
	public Genetic(int bitCount, int iteration, int populationSize, SelectionType type, double allowCrossoverRate, double allowMutationRate) {
		this.bitCount = bitCount;
		this.population = new int[populationSize][bitCount];
		this.iteration = iteration;
		this.type = type;
		this.crossoverTransitor = new CrossoverTransitor(bitCount, this.population, allowCrossoverRate);
		this.mutationTransitor = new MutationTransitor(bitCount, this.population, allowMutationRate);
	}
	@Override
	public OptimumSolution run() {
		this.init();
		//1. initialize population
		this.initializePopulation();
		int cnt = 0;
		while(cnt < this.iteration) {
			//2. selection (determination)
			int[] objValues = this.fitness(this.population);
			int[][] candidates = this.selection(this.type, this.population, objValues);
			//3. crossover
			this.crossoverTransitor.setCurrent(candidates);
			int[][] childs = this.crossoverTransitor.transit();
			//4. mutation
			this.mutationTransitor.setCurrent(childs);
			int[][] mutateChilds = this.mutationTransitor.transit();
			for (int i=0;i<mutateChilds.length;i++) {
				this.population[i] = Arrays.copyOf(mutateChilds[i], this.bitCount);
			}
			cnt++;
		}
		//5. determination
		int objValue = 0;
		int maxValue = 0;
		int keepIdx = -1;
		for (int i=0;i<this.population.length;i++) {
			System.out.println(StringUtil.toString(this.population[i]));
			objValue = this.evaluator.evaluate(this.population[i]);
			if (objValue > maxValue) {
				maxValue = objValue;
				keepIdx = i;
			}
		}
		OptimumSolution os = new OptimumSolution();
		os.setObjectiveValue(maxValue);
		os.setSolution(StringUtil.toString(this.population[keepIdx]));
		return os;
	}
	private void init() {
		this.evaluator = new BinaryBitEvaluator();
	}

	private void initializePopulation(){
		for (int i=0;i<this.population.length;i++) {
			for (int j=0;j<this.bitCount;j++) {
				this.population[i][j] = (int) Math.round(Math.random());
			}
		}
	}

	private int[] fitness(int[][] population) {
		int[] objValueArr = new int[population.length];
		for (int i=0;i<population.length;i++) {
			objValueArr[i] = this.evaluator.evaluate(population[i]);
		}
		return objValueArr;
	}

	private int[][] selection(SelectionType selectType, int[][] candidates, int[] candidateValue) {
		switch(selectType) {
		case ROULETTEWHEEL:
			return this.rouletteWheelSelect(candidates, candidateValue);
		case TOURNAMENT:
			return this.tournamentSelect(candidates, candidateValue);
		default:
			return this.tournamentSelect(candidates, candidateValue);//default
		}
	}

	private int[][] rouletteWheelSelect(int[][] candidates, int[] candidateValue){
		int sum = 0;
		int[][] newCandidates = new int[this.population.length][this.bitCount];
		float[] cdf = new float[candidateValue.length];
		for (int i=0;i<candidateValue.length;i++) {
			sum += candidateValue[i];
		}
		for (int j=0;j<candidateValue.length;j++){
			if (j > 0) {
				cdf[j] = ((float) candidateValue[j] / sum) + cdf[j-1];
				continue;
			}
			cdf[j] = (float) candidateValue[j] / sum;
		}
		for (int m=0;m<candidates.length;m++) {
			_loop:
			for (int n=0;n<cdf.length;n++) {
				if (Math.random() <= cdf[n]) {
					newCandidates[m] = candidates[n];
					break _loop;
				}
			}
		}
		return newCandidates;
	}

	private int[][] tournamentSelect(int[][] candidates, int[] candidateValue){
		int[][] newCandidates = new int[this.population.length][this.bitCount];
		Random rnd = new Random();
		int op1, op2;
		for (int i=0;i<candidates.length;i++) {
			op1 = rnd.nextInt(this.population.length);
			op2 = rnd.nextInt(this.population.length);
			newCandidates[i] = candidateValue[op1] > candidateValue[op2]?candidates[op1]:candidates[op2];
		}
		return newCandidates;
	}

}
