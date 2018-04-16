package com.github.bpogoda.academic.bacteriaClassifier.data.mapper.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.bpogoda.academic.bacteriaClassifier.data.mapper.ResultSetMapper;
import com.github.bpogoda.academic.bacteriaClassifier.model.Toughness;

public class ToughnessResultSetMapper implements ResultSetMapper<Toughness> {

	@Override
	public Toughness mapOne(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Toughness> mapMany(ResultSet resultSet) throws SQLException {
		List<Toughness> mapped = new ArrayList<>();

		while (resultSet.next()) {
			Toughness toughness = new Toughness();

			toughness.setBeta(resultSet.getInt("BETA"));
			toughness.setGamma(resultSet.getInt("GAMMA"));
			toughness.setRank(resultSet.getString("RANK").charAt(0));

			mapped.add(toughness);
		}

		return mapped;
	}

}
