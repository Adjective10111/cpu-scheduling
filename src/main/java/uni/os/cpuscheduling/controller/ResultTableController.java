package uni.os.cpuscheduling.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import uni.os.cpuscheduling.model.OperatingSystem;
import uni.os.cpuscheduling.model.SchedulingAlgorithm;

public class ResultTableController implements Controller {
	@FXML
	private TableColumn<?, ?> name;
	@FXML
	private TableColumn<?, ?> number_of_process;
	@FXML
	private TableColumn<?, ?> total_time;
	@FXML
	private TableColumn<?, ?> throughput;
	@FXML
	private TableColumn<?, ?> cpu_utilization;
	@FXML
	private TableColumn<?, ?> avg_wait;
	@FXML
	private TableColumn<?, ?> avg_turnaround;
	@FXML
	private TableColumn<?, ?> avg_response;
	
	@FXML
	private Button goto_chart;
	@FXML
	private Button homeButton;
	@FXML
	private ImageView homeImage;
	
	ObservableList<SchedulingAlgorithm> algorithm_result_set;
	
	
	@FXML
	void initialize() {
		homeButton.setOnAction(actionEvent -> changeScene("main-menu"));
		homeImage.setOnMouseClicked(mouseEvent -> changeScene("main-menu"));
		goto_chart.setOnAction(actionEvent -> changeScene("chart"));
		
		algorithm_result_set = FXCollections.observableArrayList();
		OperatingSystem.init();
		algorithm_result_set.addAll(OperatingSystem.algorithms);
	}
	
	@Override
	public Scene getScene() {
		return this.homeButton.getScene();
	}
}
