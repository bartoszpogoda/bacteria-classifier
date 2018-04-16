package com.github.bpogoda.academic.bacteriaClassifier.model;

public class Flagella {
	
	private int alpha;
	private int beta;
	private int number;
	
	public Flagella() { }

	public Flagella(int alpha, int beta, int number) {
		super();
		this.alpha = alpha;
		this.beta = beta;
		this.number = number;
	}

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public int getBeta() {
		return beta;
	}

	public void setBeta(int beta) {
		this.beta = beta;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
}
