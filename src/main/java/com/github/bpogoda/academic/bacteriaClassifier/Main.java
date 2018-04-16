package com.github.bpogoda.academic.bacteriaClassifier;

import java.io.IOException;

import com.github.bpogoda.academic.bacteriaClassifier.app.AppController;
import com.github.bpogoda.academic.bacteriaClassifier.data.impl.SQLLiteDataSource;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	private static final String APP_TITLE = "Bacteria classifier";
	private static final String ICON_PATH = "icon.jpeg";
	private static final String CSS_PATH = "application.css";

	private Stage primaryStage;
	private BorderPane appView;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle(APP_TITLE);
		this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(ICON_PATH)));

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("app/AppView.fxml"));
			appView = (BorderPane) loader.load();


			AppController appController = (AppController) loader.getController();
			appController.setStage(primaryStage);
			appController.setDataSource(new SQLLiteDataSource());

			Scene scene = new Scene(appView);
			scene.getStylesheets().add(getClass().getResource(CSS_PATH).toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
