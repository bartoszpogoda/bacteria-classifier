package com.github.bpogoda.academic.bacteriaClassifier.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "examinedList")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlExaminedList {

	@XmlElement(name = "examined")
	private List<Examined> examinedList;

	public XmlExaminedList() { }
	
	public XmlExaminedList(List<Examined> examinedList) {
		this.examinedList = examinedList;
	}

	public List<Examined> getExaminedList() {
		return examinedList;
	}

	public void setExaminedList(List<Examined> examinedList) {
		this.examinedList = examinedList;
	}
}
