package uni.os.cpuscheduling;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uni.os.cpuscheduling.model.OperatingSystem;
import uni.os.cpuscheduling.model.RequestGenerator;
import uni.os.cpuscheduling.model.SchedulingAlgorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class CPUSchedulingSimulator extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(CPUSchedulingSimulator.class.getResource("main-menu.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 320, 240);
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		if (args.length == 0)
			launch();
		else
			CLI.launch(args);
	}
}
