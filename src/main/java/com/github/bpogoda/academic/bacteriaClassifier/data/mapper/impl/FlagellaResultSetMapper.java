package com.github.bpogoda.academic.bacteriaClassifier.data.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.bpogoda.academic.bacteriaClassifier.data.mapper.ResultSetMapper;
import com.github.bpogoda.academic.bacteriaClassifier.model.Flagella;

public class FlagellaResultSetMapper implements ResultSetMapper<Flagella> {

	@Override
	public Flagella mapOne(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Flagella> mapMany(ResultSet resultSet) throws SQLException {
		List<Flagella> mapped = new ArrayList<>();
		
		while (resultSet.next()) {
			Flagella flagella = new Flagella();
			
			flagella.setAlpha(resultSet.getInt("ALPHA"));
			flagella.setBeta(resultSet.getInt("BETA"));
			flagella.setNumber(resultSet.getInt("NUMBER"));
			
			mapped.add(flagella);
		}
		
		return mapped;
	}

}
