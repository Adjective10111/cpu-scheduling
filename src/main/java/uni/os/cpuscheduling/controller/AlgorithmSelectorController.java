package uni.os.cpuscheduling.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import uni.os.cpuscheduling.model.OperatingSystem;

import java.util.ArrayList;

public class AlgorithmSelectorController implements Controller {
	@FXML
	private ImageView back;
	
	@FXML
	private CheckBox fifo_option;
	@FXML
	private CheckBox round_robin_option;
	@FXML
	private CheckBox non_preemptive_priority_option;
	@FXML
	private CheckBox preemptive_priority_option;
	@FXML
	private CheckBox non_preemptive_sjf_option;
	@FXML
	private CheckBox preemptive_sjf_option;
	@FXML
	private TextField time_slice;
	@FXML
	private Text number_error;
	
	@FXML
	private Button save_config;
	@FXML
	private Button start_simulation;
	
	
	@FXML
	void initialize() {
		number_error.setOpacity(0);
		back.setOnMouseClicked(mouseEvent -> changeScene("main-menu"));
		save_config.setOnAction(actionEvent -> {
			try {
				select();
				changeScene("main-menu");
			} catch (NumberFormatException e) {
				number_error.setOpacity(1);
			}
		});
		start_simulation.setOnAction(actionEvent -> {
			try {
				select();
				changeScene("result-table");
			} catch (NumberFormatException e) {
				number_error.setOpacity(1);
			}
		});
	}
	
	private void select() {
		OperatingSystem.algorithms = new ArrayList<>();
		if (fifo_option.isSelected())
			OperatingSystem.addAlgorithm("FIFO");
		if (round_robin_option.isSelected())
			OperatingSystem.addAlgorithm("RR", Integer.parseInt(time_slice.getText()));
		if (non_preemptive_priority_option.isSelected())
			OperatingSystem.addAlgorithm("P");
		if (preemptive_priority_option.isSelected())
			OperatingSystem.addAlgorithm("PP");
		if (non_preemptive_sjf_option.isSelected())
			OperatingSystem.addAlgorithm("SJF");
		if (preemptive_sjf_option.isSelected())
			OperatingSystem.addAlgorithm("PSJF");
	}
	
	@Override
	public Scene getScene() {
		return this.back.getScene();
	}
}
