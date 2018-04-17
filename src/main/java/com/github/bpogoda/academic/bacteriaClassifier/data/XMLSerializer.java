package com.github.bpogoda.academic.bacteriaClassifier.data;

import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.bind.JAXBException;

import com.github.bpogoda.academic.bacteriaClassifier.model.Examined;

public interface XMLSerializer {
	void save(List<Examined> examinedSpecies) throws JAXBException, FileNotFoundException;
}
