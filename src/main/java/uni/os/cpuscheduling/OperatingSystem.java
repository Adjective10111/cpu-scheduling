package uni.os.cpuscheduling;

import java.util.ArrayList;
import java.util.Queue;

public class OperatingSystem {
	public static int time = 0;
	public static Queue<Process> processes;
	public static ArrayList<SchedulingAlgorithm> algorithms;
	
	public static void init() {
		processes = RequestGenerator.readProcessData();
		run();
	}
	public static void init(String manualData) {
		processes = RequestGenerator.readProcessData(manualData);
		run();
	}
	
	private static void addProcesses() {
		while (processes.peek() != null && processes.peek().arrival_time == time) {
			Process new_process = processes.poll();
			for (var algorithm : algorithms)
				algorithm.getNewProcess(new_process);
		}
	}
	
	private static void checkProcesses() {
		addProcesses();
//		check what to do
	}
	
	private static void advance() {
		for (int i = 0, temp = 0; i < 10_000; ++i)
			if (i % 2 == 0)
				temp = i / 2;
			else
				temp = i * 2;
		time++;
	}
	
	private static void run() {
		while (!processes.isEmpty()) {
			advance();
			checkProcesses();
		}
	}
}
