package uni.os.cpuscheduling.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public interface Controller {
	Scene getScene();
	
	@FXML
	default void changeScene(String viewName) {
		var loader = new FXMLLoader(getClass().getResource
				("/main/resources/uni/os/cpuscheduling/" + viewName + ".fxml"));
		try {
			getScene().setRoot(loader.load());
		} catch (IOException ioException) { ioException.printStackTrace(); }
	}
}
