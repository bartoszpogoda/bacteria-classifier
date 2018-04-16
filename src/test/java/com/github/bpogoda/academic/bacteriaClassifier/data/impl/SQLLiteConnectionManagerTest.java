package com.github.bpogoda.academic.bacteriaClassifier.data.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class SQLLiteConnectionManagerTest {

	private SQLLiteConnectionManager connectionManager;
	
	private String testDBPath = "db/database.db";
	
	@Before
	public void setUp() {
		this.connectionManager = new SQLLiteConnectionManager(testDBPath);
	}
	
	@Test
	public void shouldConnectWithoutException() throws SQLException {
		connectionManager.connect();
	}
	
}
