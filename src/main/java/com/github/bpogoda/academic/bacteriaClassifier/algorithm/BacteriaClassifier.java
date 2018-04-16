package com.github.bpogoda.academic.bacteriaClassifier.algorithm;

import java.util.List;

import com.github.bpogoda.academic.bacteriaClassifier.model.Examined;
import com.github.bpogoda.academic.bacteriaClassifier.model.Flagella;
import com.github.bpogoda.academic.bacteriaClassifier.model.Toughness;

public interface BacteriaClassifier {
	
	void learnFlagella(List<Flagella> flagellaData);
	
	void learnToughness(List<Toughness> toughnessData);
	
	Examined classify(String genotype);
	
}
