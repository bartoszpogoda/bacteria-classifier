package com.github.bpogoda.academic.bacteriaClassifier.app;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.github.bpogoda.academic.bacteriaClassifier.algorithm.BacteriaClassifier;
import com.github.bpogoda.academic.bacteriaClassifier.algorithm.impl.ONNBacteriaClassifier;
import com.github.bpogoda.academic.bacteriaClassifier.data.ConnectableDataSource;
import com.github.bpogoda.academic.bacteriaClassifier.model.Examined;
import com.github.bpogoda.academic.bacteriaClassifier.model.XmlExaminedList;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;

public class AppController implements Initializable {

	private ConnectableDataSource dataSource;

	private Stage primaryStage;

	private BacteriaClassifier bacteriaClassifier;

	private final ObservableList<Examined> allExamined = FXCollections.observableArrayList();
	
	@FXML
	TextField tbConnectionPath;

	@FXML
	Label lblConnectionStatus;

	@FXML
	TableView<Examined> tableView;

	@FXML
	TableColumn<Examined, Integer> tableColumnGenotype;

	@FXML
	TableColumn<Examined, String> tableColumnClass;

	@FXML
	TextArea tbToClassify;

	@FXML Button btnClassify;

	@FXML Button btnExportXML;

	public void setDataSource(ConnectableDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setStage(Stage primaryStage) {
		this.primaryStage = primaryStage;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.tableView.setItems(allExamined);

		this.tableColumnClass.setCellValueFactory(cellData -> cellData.getValue().specie());
		this.tableColumnGenotype.setCellValueFactory(cellData -> cellData.getValue().genotype().asObject());
	}

	@FXML
	public void onConnect() {
		String connectionPath = tbConnectionPath.getText();

		try {
			dataSource.connect(connectionPath);
			
			this.lblConnectionStatus.setText("CONNECTED");
			this.lblConnectionStatus.setTextFill(Color.GREEN);
			this.btnClassify.disableProperty().set(false);
			this.btnExportXML.disableProperty().set(false);

			this.updateExamined();
			this.updateClassifier();
		} catch (SQLException e) {
			this.lblConnectionStatus.setText("NOT CONNECTED");
			this.lblConnectionStatus.setTextFill(Color.RED);
			showError("Connection error", e.getMessage());
		}
	}

	private void updateClassifier() throws SQLException {
		bacteriaClassifier = new ONNBacteriaClassifier();
		bacteriaClassifier.learnFlagella(dataSource.getAllFlagella());
		bacteriaClassifier.learnToughness(dataSource.getAllToughness());
	}

	private void updateExamined() {
		try {
			List<Examined> allExamined = dataSource.getAllExamined();

			this.allExamined.clear();
			this.allExamined.addAll(allExamined);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void showError(String title, String text) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(text);

		alert.show();
	}

	@FXML
	public void onClassify() throws SQLException {
		
		String toClassify = tbToClassify.getText();

		if (toClassify.contains(",")) {
			// classify group
			String[] splitToClassify = toClassify.split(",");

			List<Examined> examined = Stream.of(splitToClassify).filter(genotype -> genotype.length() == 6)
					.map(bacteriaToClassify -> bacteriaClassifier.classify(bacteriaToClassify))
					.collect(Collectors.toList());

			dataSource.saveExamined(examined);
		} else {
			// classify single
			if (toClassify.length() == 6) {

				Examined examinedSpecie = bacteriaClassifier.classify(toClassify);

				dataSource.saveExamined(Arrays.asList(examinedSpecie));
			} else {
				showError("Wrong genotype", "Genotype should be 6 digits long.");
			}
		}
		
		List<Examined> allExamined = dataSource.getAllExamined();
		this.allExamined.setAll(allExamined);
	}

	@FXML
	public void onExportXML() {
		try {
			JAXBContext contextObj = JAXBContext.newInstance(XmlExaminedList.class);
			Marshaller marshallerObj = contextObj.createMarshaller();
			marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			XmlExaminedList xmlExaminedList = new XmlExaminedList(this.allExamined);

			marshallerObj.marshal(xmlExaminedList, new FileOutputStream("items.xml"));
		} catch (Exception e) {
			showError("XML Serialization error", e.getMessage());
		}

	}

}
