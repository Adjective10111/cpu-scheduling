package uni.os.cpuscheduling.model;

import java.util.ArrayList;
import java.util.Queue;

public class OperatingSystem {
	public static Queue<Process> processes;
	public static ArrayList<SchedulingAlgorithm> algorithms;
	public static int time = 0;
	
	public static void init() {
		processes = RequestGenerator.readProcessData();
		time = 0;
		run();
	}
	public static void init(String manualData) {
		processes = RequestGenerator.readProcessData(manualData);
		time = 0;
		run();
	}
	
	private static void run() {
		while (isRunning()) {
			checkProcesses();
			advance();
		}
	}
	
	private static boolean isRunning() {
		boolean running = false;
		for (var algorithm : algorithms)
			running |= algorithm.hasPendingProcess();
		return running || !processes.isEmpty();
	}
	
	private static void advance() {
		for (int i = 0, temp = 0; i < 10_000; ++i)
			if (i % 2 == 0)
				temp = i / 2;
			else
				temp = i * 2;
		time++;
	}
	
	private static void checkProcesses() {
		addProcesses();
		for (var algorithm : algorithms)
			algorithm.process();
	}
	
	private static void addProcesses() {
		while (!processes.isEmpty() && processes.peek().arrival_time == time) {
			Process new_process = processes.poll();
			for (var algorithm : algorithms)
				algorithm.getNewProcess(new_process);
		}
	}
}
