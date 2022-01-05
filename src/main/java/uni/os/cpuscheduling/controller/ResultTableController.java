package uni.os.cpuscheduling.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class ResultTableController implements Controller {
	@FXML
	private ImageView back;
	
	@FXML
	void initialize() {
		back.setOnMouseClicked(mouseEvent -> changeScene("main-menu"));
	}
	
	@Override
	public Scene getScene() {
		return this.back.getScene();
	}
}
