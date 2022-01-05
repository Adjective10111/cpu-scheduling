package uni.os.cpuscheduling.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class ProcessConfiguratorController implements Controller {
	@FXML
	private ImageView back;
	@FXML
	private Button config;
	
	@FXML
	void initialize() {
		back.setOnMouseClicked(mouseEvent -> changeScene("main-menu"));
		config.setOnAction(actionEvent -> {
			// todo: config
			changeScene("main-menu");
		});
	}
	
	@Override
	public Scene getScene() {
		return this.back.getScene();
	}
}
