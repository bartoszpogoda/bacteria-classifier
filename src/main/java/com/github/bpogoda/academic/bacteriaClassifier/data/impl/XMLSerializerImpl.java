package com.github.bpogoda.academic.bacteriaClassifier.data.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.github.bpogoda.academic.bacteriaClassifier.data.XMLSerializer;
import com.github.bpogoda.academic.bacteriaClassifier.model.Examined;
import com.github.bpogoda.academic.bacteriaClassifier.model.XmlExaminedList;

public class XMLSerializerImpl implements XMLSerializer {

	@Override
	public void save(List<Examined> examinedSpecies) throws JAXBException, FileNotFoundException {
		JAXBContext contextObj = JAXBContext.newInstance(XmlExaminedList.class);
		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		XmlExaminedList xmlExaminedList = new XmlExaminedList(examinedSpecies);

		marshallerObj.marshal(xmlExaminedList, new FileOutputStream("pdfexport/items.xml"));
	}

}
