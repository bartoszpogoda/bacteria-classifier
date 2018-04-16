package com.github.bpogoda.academic.bacteriaClassifier.data.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ResultSetMapper<T> {
	T mapOne(ResultSet resultSet) throws SQLException;
	
	List<T> mapMany(ResultSet resultSet) throws SQLException;
}
