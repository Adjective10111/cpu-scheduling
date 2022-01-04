package uni.os.cpuscheduling.model;

import java.util.Comparator;

public class Process {
	private static int id_generator = 1;
	private final static int START = 0;
	private final static int FINISH = 1;
	public final static String GET_WAITING_TIME = "getWaitingTime";
	public final static String GET_SPAN_TIME = "getSpanTime";
	public final static String GET_RESPONSE_TIME = "getResponseTime";
	
	private final int id;
	protected final int arrival_time;
	protected final int priority;
	protected final int burst_time;
	
	private int execution_time;
	private final int[ ] span_time;
	
	public Process(int arrival_time, int priority, int burst_time) {
		this(id_generator, arrival_time, priority, burst_time);
	}
	public Process(int id, int arrival_time, int priority, int burst_time) {
		this.id = id;
		id_generator = id + 1;
		this.arrival_time = arrival_time;
		this.priority = priority;
		this.burst_time = burst_time;
		execution_time = 0;
		span_time = new int[ ] {-1, -1};
	}
	public Process(Process other) {
		this.id = other.id;
		this.arrival_time = other.arrival_time;
		this.priority = other.priority;
		this.burst_time = other.burst_time;
		this.execution_time = 0;
		this.span_time = new int[ ] {-1, -1};
	}
	
	public int getArrivalTime() {
		return arrival_time;
	}
	public int getPriority() {
		return priority;
	}
	public int getBurstTime() {
		return burst_time;
	}
	public int getRemainingBurstTme(){
		return burst_time - execution_time;
	}
	public int getSpanTime() {
		if (isDone())
			return span_time[FINISH] - span_time[START];
		else
			// not finished
			return -1;
	}
	public int getWaitingTime() {
		return (span_time[START] - arrival_time) + (getSpanTime() - burst_time);
	}
	public int getResponseTime() {
		return span_time[START] - arrival_time;
	}
	public boolean isDone() {
		return execution_time == burst_time;
	}

	public static void execute(Process process) {
		if (process.execution_time == 0)
			process.span_time[START] = OperatingSystem.time;
		
		if (process.execution_time < process.burst_time) {
			process.execution_time++;
			if (process.execution_time == process.burst_time)
				process.span_time[FINISH] = OperatingSystem.time;
		}
	}
}

// p1 < p2: -, p1 == p2: 0, p1 > p2: +
class ArrivalComparator implements Comparator<Process> {
	@Override
	public int compare(Process p1, Process p2) {
		return p1.getArrivalTime() - p2.getArrivalTime();
	}
}
class PriorityComparator implements Comparator<Process> {
	@Override
	public int compare(Process p1, Process p2) {
		return p1.getPriority() - p2.getPriority();
	}
}
class BurstComparator implements Comparator<Process> {
	@Override
	public int compare(Process p1, Process p2) {
		return p1.getBurstTime() - p2.getBurstTime();
	}
}
class RemainingBurstComparator implements Comparator<Process> {
	@Override
	public int compare(Process p1, Process p2) {
		return p1.getRemainingBurstTme() - p2.getRemainingBurstTme();
	}
}
