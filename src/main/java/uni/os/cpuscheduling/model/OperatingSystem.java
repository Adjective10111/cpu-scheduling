package uni.os.cpuscheduling.model;

import uni.os.cpuscheduling.controller.CommandLineInterface;

import java.io.FileNotFoundException;
import java.util.*;

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
			if (CommandLineInterface.verbose) {
				System.out.println("----------------------------------------");
				System.out.println("Time: " + time);
			}
			checkProcesses();
			advance();
		}
		if (CommandLineInterface.verbose)
			System.out.println("----------------------------------------");
		if (CommandLineInterface.verbose)
			displayResult();
		logResult();
	}
	
	private static boolean isRunning() {
		boolean running = false;
		for (var algorithm : algorithms) {
			running |= algorithm.hasPendingProcess();
			if (CommandLineInterface.verbose)
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
			if (CommandLineInterface.verbose) {
				if (!found)
					System.out.println("# New Process(es): ");
				System.out.println(new_process);
				found = true;
			}
			for (var algorithm : algorithms)
				algorithm.addNewProcess(new_process);
		}
		if (CommandLineInterface.verbose) {
			if (!found)
				System.out.println("# No New Process\n");
			else
				System.out.println("# Added successfully\n");
		}
	}
	
	private static void displayResult() {
		System.out.println("************************************\n" +
				"Result:");
		for (var algorithm : algorithms) {
			System.out.println("------------------------------------");
			System.out.println(algorithm.name());
			System.out.println("Throughput: " + algorithm.throughput());
			System.out.println("CPU Utilization: " + algorithm.CPUUtilization());
			System.out.println("Average Response time: " + algorithm.averageResponseTime());
			System.out.println("Average Waiting time: " + algorithm.averageWaitingTime());
			System.out.println("Average Turnaround time: " + algorithm.averageTurnaroundTime());
		}
		System.out.println("************************************");
	}
	
	private static void logResult() {
		Formatter writer;
		try {
			writer = new Formatter("simulationAt_" +
					Calendar.getInstance().getTime().toString() + ".log");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		for (var algorithm : algorithms) {
			writer.format("------------------------------------\n" +
					algorithm.name() + '\n' +
					"Throughput: " + algorithm.throughput() + '\n' +
					"CPU Utilization: " + algorithm.CPUUtilization() + '\n' +
					"Average Response time: " + algorithm.averageResponseTime() + '\n' +
					"Average Waiting time: " + algorithm.averageWaitingTime() + '\n' +
				"Average Turnaround time: " + algorithm.averageTurnaroundTime() + '\n');
		}
	}
}
