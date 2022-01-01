package uni.os.cpuscheduling;

import java.util.ArrayList;
import java.util.Queue;

public class OperatingSystem {
	public static int time = 0;
	public static ArrayList<Process> processes;
	public static Queue<Process> ready;
	
	public OperatingSystem() {
		processes = RequestGenerator.readProcessData();
		run();
	}
	public OperatingSystem(String manualData) {
		processes = RequestGenerator.readProcessData(manualData);
		run();
	}
	
	private void addProcesses() {
		while (processes.get(0).arrival_time == time) {
			ready.add(processes.get(0));
			processes.remove(0);
		}
	}
	
	private void checkProcesses() {
		addProcesses();
//		check what to do
	}
	
	private void advance() {
		for (int i = 0, temp = 0; i < 10_000; ++i)
			if (i % 2 == 0)
				temp = i / 2;
			else
				temp = i * 2;
		time++;
	}
	
	private void run() {
		while (!processes.isEmpty()) {
			advance();
			checkProcesses();
		}
	}
}
