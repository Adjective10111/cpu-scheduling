package uni.os.cpuscheduling.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SchedulingAlgorithm {
	private final Queue<Process> processes;
	private Process running_process = null;
	private final ArrayList<Process> finished_processes = new ArrayList<>();
	private final Algorithm algorithm;
	private int time_slice = 0;
	
	SchedulingAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
		switch (algorithm) {
			case FIFO ->
					processes = new PriorityQueue<>(new ArrivalComparator());
			case PREEMPTIVE_SJF, NON_PREEMPTIVE_SJF ->
					processes = new PriorityQueue<>(new RemainingBurstComparator());
			case ROUND_ROBIN ->
					processes = new LinkedList<>();
			case PREEMPTIVE_PRIORITY, NON_PREEMPTIVE_PRIORITY ->
					processes = new PriorityQueue<>(new PriorityComparator());
			default ->
					throw new EnumConstantNotPresentException(Algorithm.class, algorithm.name());
		}
	}
	SchedulingAlgorithm(Algorithm algorithm, int time_slice) {
		this(algorithm);
		this.time_slice = time_slice;
	}
	
	public void addNewProcess(Process process) {
		processes.add(process);
	}
	public boolean hasPendingProcess() {
		return running_process != null;
	}
	
	private void selectProcess() {
		if (!hasPendingProcess())
			running_process = processes.poll();
		else if (running_process.isDone() || algorithm.isPreemptive()) {
			processes.add(running_process);
			running_process = processes.poll();
		} else if (algorithm == Algorithm.ROUND_ROBIN &&
				running_process.getExecutionTime() % time_slice == 0) {
			processes.add(running_process);
			running_process = processes.poll();
		}
	}
	public void process() {
		selectProcess();
		if (hasPendingProcess())
			Process.execute(running_process);
	}
	
	public double Throughput() {
		// todo this function is empty
		return 0.0;
	}
	public double CPUUtilization() {
		// todo
		return 0.0;
	}
	public int averageWaitingTime() {
		try {
			return calcAverage(Process.class.getMethod(Process.GET_WAITING_TIME));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return -1;
		}
	}
	public int averageTurnaroundTime() {
		try {
			return calcAverage(Process.class.getMethod(Process.GET_SPAN_TIME));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return -1;
		}
	}
	public int averageResponseTime() {
		try {
			return calcAverage(Process.class.getMethod(Process.GET_RESPONSE_TIME));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return -1;
		}
	}
	private int calcAverage(Method method) {
		int sum = 0;
		try {
			for (var process : finished_processes)
				sum += (Integer) method.invoke(process);
		} catch (InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return sum / finished_processes.size();
	}
}

