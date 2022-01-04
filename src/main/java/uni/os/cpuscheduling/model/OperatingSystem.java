package uni.os.cpuscheduling.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class OperatingSystem {
	public static PriorityQueue<Process> processes;
	public static ArrayList<SchedulingAlgorithm> algorithms;
	public static int time = 0;
	
	public static void init() {
		processes = RequestGenerator.readProcessData();
		initializeMembers();
	}
	public static void init(String manualData) {
		processes = RequestGenerator.readProcessData(manualData);
		initializeMembers();
	}
	private static void initializeMembers() {
		algorithms.add(new SchedulingAlgorithm(Algorithm.FIFO));
		algorithms.add(new SchedulingAlgorithm(Algorithm.PREEMPTIVE_SJF));
		algorithms.add(new SchedulingAlgorithm(Algorithm.NON_PREEMPTIVE_SJF));
		algorithms.add(new SchedulingAlgorithm(Algorithm.ROUND_ROBIN, 8));
		algorithms.add(new SchedulingAlgorithm(Algorithm.PREEMPTIVE_PRIORITY));
		algorithms.add(new SchedulingAlgorithm(Algorithm.NON_PREEMPTIVE_PRIORITY));
		
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
	private static void checkProcesses() {
		addProcesses();
		for (var algorithm : algorithms)
			algorithm.process();
	}
	private static void advance() {
		for (int i = 0, temp = 0; i < 10_000; ++i)
			if (i % 2 == 0)
				temp = i / 2;
			else
				temp = i * 2;
		time++;
	}
	
	private static void addProcesses() {
		while (!processes.isEmpty() && processes.peek().getArrivalTime() == time) {
			Process new_process = processes.poll();
			for (var algorithm : algorithms)
				algorithm.addNewProcess(new_process);
		}
	}
}
