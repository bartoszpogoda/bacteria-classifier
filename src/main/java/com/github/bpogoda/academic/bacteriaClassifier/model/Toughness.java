package com.github.bpogoda.academic.bacteriaClassifier.model;

public class Toughness {
	private int beta;
	private int gamma;
	private char rank;

	public Toughness() {
	}

	public Toughness(int beta, int gamma, char rank) {
		super();
		this.beta = beta;
		this.gamma = gamma;
		this.rank = rank;
	}

	public int getBeta() {
		return beta;
	}

	public void setBeta(int beta) {
		this.beta = beta;
	}

	public int getGamma() {
		return gamma;
	}

	public void setGamma(int gamma) {
		this.gamma = gamma;
	}

	public char getRank() {
		return rank;
	}

	public void setRank(char rank) {
		this.rank = rank;
	}
}
