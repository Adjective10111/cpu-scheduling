package uni.os.cpuscheduling.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class MainMenuController {
	@FXML
	private Button start_simulation;
	@FXML
	private Button generate_processes;
	@FXML
	private ImageView configure_algorithms;
	@FXML
	private ImageView configure_process_generator;
	@FXML
	private ImageView about;
	
	@FXML
	protected void onHelloButtonClick() {
		start_simulation.setText("Welcome to JavaFX Application!");
	}
}