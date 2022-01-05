package uni.os.cpuscheduling.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class AlgorithmSelectorController implements Controller {
	@FXML
	private ImageView back;
	@FXML
	private Button select;
	
	@FXML
	void initialize() {
		back.setOnMouseClicked(mouseEvent -> changeScene("main-menu"));
		select.setOnAction(actionEvent -> {
			// todo: select
			changeScene("main-menu");
		});
	}
	
	@Override
	public Scene getScene() {
		return this.back.getScene();
	}
}
