package com.github.bpogoda.academic.bacteriaClassifier.data.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Consumer;

public class SQLLiteConnectionManager {

	private String dbPath;
	
	private Connection connection;
	
	public SQLLiteConnectionManager(String dbPath) {
		super();
		this.dbPath = dbPath;
	}

	public void connect() throws SQLException {
		this.connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", this.dbPath));
		
		if(this.connection == null) {
			throw new SQLException();
		}
	}
	
	public void closeConnection() throws SQLException {
		if(connection == null) return;
		if(connection.isClosed()) return;
		
		connection.close();
	}
	
	public void execute(boolean transactional, Consumer<Connection> connectionConsumer) throws SQLException {
		
		connectIfNotConnected();
		
		if(transactional) {
			executeTransactional(connectionConsumer);
		} else {
			executeNotTransactional(connectionConsumer);
		}
		
	}
	
	private void executeTransactional(Consumer<Connection> connectionConsumer) throws SQLException {
		connection.setAutoCommit(false);
		
		connectionConsumer.accept(connection);
		
		connection.commit();
	}
	
	private void executeNotTransactional(Consumer<Connection> connectionConsumer) throws SQLException {
		connection.setAutoCommit(true);
		
		connectionConsumer.accept(connection);
	}
	
	private void connectIfNotConnected() throws SQLException {
		if(this.connection == null || this.connection.isClosed()) {
			connect();
		}
	}
	
}
