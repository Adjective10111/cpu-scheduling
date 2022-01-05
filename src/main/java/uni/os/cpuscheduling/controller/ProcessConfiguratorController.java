package uni.os.cpuscheduling.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import uni.os.cpuscheduling.model.RequestGenerator;

public class ProcessConfiguratorController implements Controller {
	@FXML
	private ImageView back;
	
	@FXML
	private TextField number_of_processes;
	@FXML
	private TextField min_burst;
	@FXML
	private TextField max_burst;
	@FXML
	private TextField min_priority;
	@FXML
	private TextField max_priority;
	@FXML
	private TextField min_arrival;
	@FXML
	private TextField max_arrival;
	
	@FXML
	private Text number_error;
	@FXML
	private Button generate_processes;
	@FXML
	private Button save;
	
	@FXML
	void initialize() {
		number_error.setOpacity(0);
		back.setOnMouseClicked(mouseEvent -> changeScene("main-menu"));
		generate_processes.setOnAction(actionEvent -> {
			try {
				config();
				RequestGenerator.generateProcessData();
				changeScene("main-menu");
			} catch (NumberFormatException e) {
				number_error.setOpacity(1);
			}
		});
		save.setOnAction(actionEvent -> {
			try {
				config();
				changeScene("main-menu");
			} catch (NumberFormatException e) {
				number_error.setOpacity(1);
			}
		});
	}
	
	private void config() {
		if (!number_of_processes.getText().isEmpty())
			RequestGenerator.number_of_processes = Integer.parseInt(number_of_processes.getText());
		if (!min_burst.getText().isEmpty())
			RequestGenerator.min_burst = Integer.parseInt(min_burst.getText());
		if (!max_burst.getText().isEmpty())
			RequestGenerator.max_burst = Integer.parseInt(max_burst.getText());
		if (!min_priority.getText().isEmpty())
			RequestGenerator.min_priority = Integer.parseInt(min_priority.getText());
		if (!max_priority.getText().isEmpty())
			RequestGenerator.max_priority = Integer.parseInt(max_priority.getText());
		if (!min_arrival.getText().isEmpty())
			RequestGenerator.min_arrival = Integer.parseInt(min_arrival.getText());
		if (!max_arrival.getText().isEmpty())
			RequestGenerator.max_arrival = Integer.parseInt(max_arrival.getText());
	}
	
	@Override
	public Scene getScene() {
		return this.back.getScene();
	}
}
