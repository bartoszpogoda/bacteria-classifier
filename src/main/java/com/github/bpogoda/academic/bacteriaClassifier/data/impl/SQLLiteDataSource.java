package com.github.bpogoda.academic.bacteriaClassifier.data.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.github.bpogoda.academic.bacteriaClassifier.data.ConnectableDataSource;
import com.github.bpogoda.academic.bacteriaClassifier.data.mapper.impl.ExaminedResultSetMapper;
import com.github.bpogoda.academic.bacteriaClassifier.data.mapper.impl.FlagellaResultSetMapper;
import com.github.bpogoda.academic.bacteriaClassifier.data.mapper.impl.ToughnessResultSetMapper;
import com.github.bpogoda.academic.bacteriaClassifier.model.Examined;
import com.github.bpogoda.academic.bacteriaClassifier.model.Flagella;
import com.github.bpogoda.academic.bacteriaClassifier.model.Toughness;

public class SQLLiteDataSource implements ConnectableDataSource {

	private SQLLiteConnectionManager connectionManager;

	public SQLLiteDataSource() {

	}

	@Override
	public void connect(String path) throws SQLException {
		if (this.connectionManager != null) {
			this.connectionManager.closeConnection();
		}

		this.connectionManager = new SQLLiteConnectionManager(path);
		this.connectionManager.connect();

	}

	@Override
	public List<Examined> getAllExamined() throws SQLException {

		List<Examined> allExamined = new ArrayList<>();

		this.connectionManager.execute(false, connection -> {

			try {
				PreparedStatement getAllExamined = connection.prepareStatement("SELECT * FROM `EXAMINED`");
				
				ResultSet resultSet = getAllExamined.executeQuery();

				allExamined.addAll(new ExaminedResultSetMapper().mapMany(resultSet));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

		return allExamined;
	}

	@Override
	public List<Flagella> getAllFlagella() throws SQLException {
		List<Flagella> allFlagella = new ArrayList<>();

		this.connectionManager.execute(false, connection -> {

			try {
				PreparedStatement getAllFlagella = connection.prepareStatement("SELECT * FROM `FLAGELLA`");
				
				ResultSet resultSet = getAllFlagella.executeQuery();

				allFlagella.addAll(new FlagellaResultSetMapper().mapMany(resultSet));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

		return allFlagella;
	}

	@Override
	public List<Toughness> getAllToughness() throws SQLException {
		List<Toughness> allToughness = new ArrayList<>();

		this.connectionManager.execute(false, connection -> {

			try {
				PreparedStatement getAllToughness = connection.prepareStatement("SELECT * FROM `TOUGHNESS`");
				
				ResultSet resultSet = getAllToughness.executeQuery();

				allToughness.addAll(new ToughnessResultSetMapper().mapMany(resultSet));

			} catch (SQLException e) {
				e.printStackTrace();
			}
		});

		return allToughness;
	}

	@Override
	public void saveExamined(List<Examined> examined) throws SQLException {
		
		// TODO add or update
		
	}

}
