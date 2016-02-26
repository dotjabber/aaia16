package priv.dotjabber.aaia;

import java.util.Random;

public class Individual implements Comparable<Individual> {
	private final static Random RAND = new Random();
	
	private double[] coeficients;
	private double[] exponents;
	private double[] regulators;
	private double[] addictors;
	private double[] totalitarians;
	
	// score
	private double previous = -1;
	private double score;
	private int topOccurance;
	
	public Individual(int length) {
		coeficients = new double[length];
		exponents = new double[length];
		regulators = new double[length];
		addictors = new double[length];
		totalitarians = new double[length];

		for(int i = 0; i < length; i++) {	
			addictors[i] = RAND.nextDouble() * Main.RANGE - Main.RANGE / 2;
			regulators[i] = RAND.nextDouble();
			coeficients[i] = RAND.nextDouble() * Main.RANGE - Main.RANGE / 2;
			exponents[i] = RAND.nextInt(Main.MAX_POW + 1);
			totalitarians[i] = RAND.nextDouble();
		}
	}

	public double[] getRegulators() {
		return regulators;
	}

	public double[] getCoeficients() {
		return coeficients;
	}

	public double[] getExponents() {
		return exponents;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		if(previous == -1) previous = score;
		this.score = (score + previous) / 2;
	}

	public double[] getAddictors() {
		return addictors;
	}

	@Override
	public int compareTo(Individual ind) {
		if(score > ind.score) return 1;
		else if(score == ind.score) return 0;
		else return -1;
	}

	public int getTopOccurance() {
		return topOccurance;
	}

	public void incTopOccurance() {
		this.topOccurance ++;
	}

	public double[] getTotalitarians() {
		return totalitarians;
	}
}