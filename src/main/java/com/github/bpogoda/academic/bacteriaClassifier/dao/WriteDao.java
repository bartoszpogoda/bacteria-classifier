package com.github.bpogoda.academic.bacteriaClassifier.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author BPOGODA
 *
 * @param <K>
 *            K is the primary key
 * @param <T>
 *            T is the stored object class
 */
public interface WriteDao<K, T extends Serializable> {
	
	void save(T toSave);
	
	void saveAll(List<T> toSave);
	
}
