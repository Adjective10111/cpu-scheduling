package uni.os.cpuscheduling.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;

public class ProcessConfiguratorController implements Controller {
	@FXML
	private ImageView back;
	
	@Override
	public Scene getScene() {
		return this.back.getScene();
	}
}
