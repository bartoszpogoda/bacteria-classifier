package com.github.bpogoda.academic.bacteriaClassifier.data.mapper.impl;

import com.github.bpogoda.academic.bacteriaClassifier.model.Examined;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.bpogoda.academic.bacteriaClassifier.data.mapper.ResultSetMapper;

public class ExaminedResultSetMapper implements ResultSetMapper<Examined> {

	@Override
	public Examined mapOne(ResultSet resultSet) {
		return null;
	}

	@Override
	public List<Examined> mapMany(ResultSet resultSet) throws SQLException {
		List<Examined> mapped = new ArrayList<>();
		
		while (resultSet.next()) {
			Examined examined = new Examined();
			
			examined.setSpecie(resultSet.getString("CLASS"));
			examined.setGenotype(resultSet.getInt("GENOTYPE"));

			mapped.add(examined);
		}
		
		return mapped;
	}

}
