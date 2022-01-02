package uni.os.cpuscheduling;

import java.util.Comparator;

public class Process implements Comparable<Process> {
	private static int id_generator = 1;
	
	private final int id;
	public final int arrival_time;
	private final int priority;
	private final int burst_time;
	
	private int execution_time;
	private int span_time;
	
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
		span_time = 0;
	}
	public Process(Process other) {
		this.id = other.id;
		this.arrival_time = other.arrival_time;
		this.priority = other.priority;
		this.burst_time = other.burst_time;
		this.execution_time = 0;
		this.span_time = 0;
	}
	
	public int getArrival_time() {
		return arrival_time;
	}
	public int getPriority() {
		return priority;
	}
	public int getSpanTime() {
		return span_time;
	}
	public boolean isDone() {
		return execution_time == burst_time;
	}
	
	public static void execute(Process process) {
		if (process.execution_time == 0)
			process.span_time = OperatingSystem.time;
		
		if (process.execution_time < process.burst_time) {
			process.execution_time++;
			if (process.execution_time == process.burst_time)
				process.span_time += process.burst_time;
		}
	}
	
	@Override
	public int compareTo(Process other) {
		return arrival_time - other.arrival_time;
	}
}
