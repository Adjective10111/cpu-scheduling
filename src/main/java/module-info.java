module uni.os.cpuscheduling {
	requires javafx.controls;
	requires javafx.fxml;
	
	
	opens uni.os.cpuscheduling to javafx.fxml;
	exports uni.os.cpuscheduling;
}