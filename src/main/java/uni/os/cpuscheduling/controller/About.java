package uni.os.cpuscheduling.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class About implements Controller {
	@FXML
	private ImageView home;
	
	@FXML
	void initialize() {
		home.setOnMouseClicked(mouseEvent -> changeScene("main-menu"));
	}
	
	@Override
	public Scene getScene() {
		return this.home.getScene();
	}
}
