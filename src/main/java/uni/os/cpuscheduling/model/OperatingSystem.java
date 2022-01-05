package uni.os.cpuscheduling.model;

import uni.os.cpuscheduling.CLI;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class OperatingSystem {
	public static PriorityQueue<Process> processes;
	public static ArrayList<SchedulingAlgorithm> algorithms;
	public static int time = 0;
	
	public static void init() {
		processes = RequestGenerator.readProcessData();
		time = 0;
		run();
	}
	public static void addAlgorithm(String algorithm) {
		switch (algorithm) {
			case "FIFO" -> algorithms.add(new SchedulingAlgorithm(Algorithm.FIFO));
			case "PSJF" -> algorithms.add(new SchedulingAlgorithm(Algorithm.PREEMPTIVE_SJF));
			case "SJF" -> algorithms.add(new SchedulingAlgorithm(Algorithm.NON_PREEMPTIVE_SJF));
			case "RR" -> algorithms.add(new SchedulingAlgorithm(Algorithm.ROUND_ROBIN));
			case "PP" -> algorithms.add(new SchedulingAlgorithm(Algorithm.PREEMPTIVE_PRIORITY));
			case "P" -> algorithms.add(new SchedulingAlgorithm(Algorithm.NON_PREEMPTIVE_PRIORITY));
			default -> throw new InputMismatchException();
		}
	}
	public static void addAlgorithm(String algorithm, int time_slice) {
		if (!algorithm.equals("RR"))
			throw new InputMismatchException();
		algorithms.add(new SchedulingAlgorithm(Algorithm.ROUND_ROBIN, time_slice));
	}
	
	private static void run() {
		while (isRunning()) {
			if (CLI.verbose) {
				System.out.println("----------------------------------------");
				System.out.println("Time: " + time);
			}
			checkProcesses();
			advance();
		}
		if (CLI.verbose)
			System.out.println("----------------------------------------");
		// todo: display the result
	}
	
	private static boolean isRunning() {
		boolean running = false;
		for (var algorithm : algorithms) {
			running |= algorithm.hasPendingProcess();
			if (CLI.verbose)
				System.out.println(
						"***************\n" +
						algorithm +
						"***************");
		}
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
		boolean found = false;
		while (!processes.isEmpty() && processes.peek().getArrivalTime() == time) {
			Process new_process = processes.poll();
			if (CLI.verbose) {
				if (!found)
					System.out.println("# New Process(es): ");
				System.out.println(new_process);
				found = true;
			}
			for (var algorithm : algorithms)
				algorithm.addNewProcess(new_process);
		}
		if (CLI.verbose) {
			if (!found)
				System.out.println("# No New Process\n");
			else
				System.out.println("# Added successfully\n");
		}
	}
}
