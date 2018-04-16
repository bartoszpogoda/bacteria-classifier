package com.github.bpogoda.academic.bacteriaClassifier.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

@XmlRootElement  
public class Examined implements Serializable {

	private static final long serialVersionUID = -1365622517986756290L;

	private final SimpleStringProperty specie = new SimpleStringProperty();

	private final SimpleIntegerProperty genotype = new SimpleIntegerProperty();

	public Examined() {
		// TODO Auto-generated constructor stub
	}
	
	public Examined(String genotype, String specie) {
		this.genotype.set(Integer.parseInt(genotype));
		this.specie.set(specie);
	}
	
	public SimpleStringProperty specie() {
		return this.specie;
	}
	
	public SimpleIntegerProperty genotype() {
		return this.genotype;
	}
	
	public String getSpecie() {
		return specie.getValue();
	}

	public void setSpecie(String specie) {
		this.specie.setValue(specie);
	}

	public int getGenotype() {
		return this.genotype.get();
	}

	public void setGenotype(int genotype) {
		this.genotype.set(genotype);
	}

}
