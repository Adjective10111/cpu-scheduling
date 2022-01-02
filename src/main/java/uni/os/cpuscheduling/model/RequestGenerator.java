package uni.os.cpuscheduling.model;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class RequestGenerator {
	public static Queue<Process> readProcessData() {
		ArrayList<Process> processes = new ArrayList<>();
		return new PriorityQueue<Process>(processes);
	}
	public static Queue<Process> readProcessData(String filename) {
		return null;
	}
}
