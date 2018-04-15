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
public interface ReadDao<K, T extends Serializable> {

	List<T> getAll();

	T getById(K id);

}
