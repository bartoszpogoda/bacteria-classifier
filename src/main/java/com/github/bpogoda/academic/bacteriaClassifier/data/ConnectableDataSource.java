package com.github.bpogoda.academic.bacteriaClassifier.data;

import java.sql.SQLException;
import java.util.List;

import com.github.bpogoda.academic.bacteriaClassifier.model.Examined;
import com.github.bpogoda.academic.bacteriaClassifier.model.Flagella;
import com.github.bpogoda.academic.bacteriaClassifier.model.Toughness;

public interface ConnectableDataSource {
	
	void connect(String path) throws SQLException;
	
	List<Examined> getAllExamined() throws SQLException;
	
	List<Flagella> getAllFlagella() throws SQLException;
	
	List<Toughness> getAllToughness() throws SQLException;

	void saveExamined(List<Examined> examined) throws SQLException;
	
}
