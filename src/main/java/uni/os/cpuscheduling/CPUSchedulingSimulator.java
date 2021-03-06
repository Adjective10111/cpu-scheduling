package uni.os.cpuscheduling;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uni.os.cpuscheduling.controller.CommandLineInterface;

import java.io.IOException;

public class CPUSchedulingSimulator extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(CPUSchedulingSimulator.class.getResource("main-menu.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 705, 605);
		stage.setTitle("CPU Scheduling Simulator");
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) {
		if (args.length == 0)
			launch();
		else
			CommandLineInterface.launch(args);
	}
}
