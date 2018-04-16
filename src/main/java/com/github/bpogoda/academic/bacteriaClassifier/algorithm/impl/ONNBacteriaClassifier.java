package com.github.bpogoda.academic.bacteriaClassifier.algorithm.impl;

import java.util.ArrayList;
import java.util.List;

import com.github.bpogoda.academic.bacteriaClassifier.algorithm.BacteriaClassifier;
import com.github.bpogoda.academic.bacteriaClassifier.model.Examined;
import com.github.bpogoda.academic.bacteriaClassifier.model.Flagella;
import com.github.bpogoda.academic.bacteriaClassifier.model.Toughness;

public class ONNBacteriaClassifier implements BacteriaClassifier {

	private List<Flagella> learnedFlagellaData = new ArrayList<>();

	private List<Toughness> learnedToughnessData = new ArrayList<>();

	@Override
	public void learnFlagella(List<Flagella> flagellaData) {
		learnedFlagellaData.addAll(flagellaData);
	}

	@Override
	public void learnToughness(List<Toughness> toughnessData) {
		learnedToughnessData.addAll(toughnessData);
	}

	@Override
	public Examined classify(String genotype) {

		if (genotype.length() != 6) {
			throw new IllegalArgumentException("Genotype must be 6 digits long.");
		}

		String[] genes = genotype.split("");

		int alpha = Integer.parseInt(genes[0]) * 10 + Integer.parseInt(genes[5]);
		int beta = Integer.parseInt(genes[1]) * 10 + Integer.parseInt(genes[4]);
		int gamma = Integer.parseInt(genes[2]) * 10 + Integer.parseInt(genes[3]);

		Flagella closestFlagella = findClosestFlagella(alpha, beta);
		Toughness closestToughness = findClosestToughness(beta, gamma);

		return new Examined(genotype, Integer.toString(closestFlagella.getNumber()) + closestToughness.getRank());
	}

	private Flagella findClosestFlagella(int alpha, int beta) {
		Flagella closestFlagella = null;
		double minimumDistnace = Double.MAX_VALUE;

		for (Flagella flagella : learnedFlagellaData) {
			double currentDistance = getEuclidesDistance(alpha, beta, flagella.getAlpha(), flagella.getBeta());

			if (currentDistance <= minimumDistnace) {
				minimumDistnace = currentDistance;
				closestFlagella = flagella;
			}
		}

		return closestFlagella;
	}

	private Toughness findClosestToughness(int beta, int gamma) {
		Toughness closestToughness = null;
		double minimumDistnace = Double.MAX_VALUE;

		for (Toughness toughness : learnedToughnessData) {
			double currentDistance = getEuclidesDistance(beta, gamma, toughness.getBeta(), toughness.getGamma());

			if (currentDistance <= minimumDistnace) {
				minimumDistnace = currentDistance;
				closestToughness = toughness;
			}
		}

		return closestToughness;
	}

	private double getEuclidesDistance(int xa, int ya, int xb, int yb) {
		return Math.sqrt(Math.pow(xa - xb, 2) + Math.pow(ya - yb, 2));
	}

}
