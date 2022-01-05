package uni.os.cpuscheduling.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import uni.os.cpuscheduling.model.OperatingSystem;
import uni.os.cpuscheduling.model.RequestGenerator;

public class MainMenuController implements Controller {
	@FXML
	private Button start_simulation;
	@FXML
	private Button generate_processes;
	@FXML
	private ImageView select_algorithms;
	@FXML
	private ImageView configure_process_generator;
	@FXML
	private ImageView about;
	
	@FXML
	void initialize() {
		start_simulation.setOnAction(actionEvent -> changeScene("result-table"));
		generate_processes.setOnAction(actionEvent -> RequestGenerator.generateProcessData());
		select_algorithms.setOnMouseClicked(mouseEvent -> changeScene("algorithm-selector"));
		configure_process_generator.setOnMouseClicked(mouseEvent -> changeScene("generator-config"));
		about.setOnMouseClicked(mouseEvent -> changeScene("about"));
	}
	
	@Override
	public Scene getScene() {
		return this.about.getScene();
	}
}