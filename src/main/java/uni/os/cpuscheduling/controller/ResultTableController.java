package uni.os.cpuscheduling.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ResultTableController implements Controller {
	@FXML
	private ImageView back;
	@FXML
	private Button goto_chart;
	
	@FXML
	void initialize() {
		back.setOnMouseClicked(mouseEvent -> changeScene("main-menu"));
		goto_chart.setOnAction(actionEvent -> changeScene("chart"));
		
		
	}
	
	@Override
	public Scene getScene() {
		return this.back.getScene();
	}
}
