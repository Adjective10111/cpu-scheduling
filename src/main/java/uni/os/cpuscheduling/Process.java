package uni.os.cpuscheduling;

import java.util.Comparator;

public class Process {
	private static int id_generator = 1;
	
	private int id;
	public int arrival_time;
	private int priority;
	private int burst_time;
	private int execution_time;
	private int span_time;
	
	public Process(int id, int arrival_time, int priority, int burst_time) {
		this.id = id;
		id_generator = id + 1;
		this.arrival_time = arrival_time;
		this.priority = priority;
		this.burst_time = burst_time;
		execution_time = 0;
		span_time = 0;
	}
	
	public Process(int arrival_time, int priority, int burst_time) {
		this(id_generator, arrival_time, priority, burst_time);
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
}

class ProcessComparator implements Comparator<Process> {
	@Override
	public int compare(Process p1, Process p2) {
		return p1.arrival_time - p2.arrival_time;
	}
}
