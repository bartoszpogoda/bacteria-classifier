package com.github.bpogoda.academic.bacteriaClassifier.data;

import java.util.List;

import com.github.bpogoda.academic.bacteriaClassifier.model.Examined;

public interface XMLSerializer {
	void save(List<Examined> examinedSpecies);
}
