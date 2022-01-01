package uni.os.cpuscheduling;

import java.util.Comparator;

public class Process {
	private static int id_generator = 1;
	
	private int id;
	public int arrival_time;
	private int priority;
	private int burst_time;
	
	Process(int id, int arrival_time, int priority, int burst_time) {
		this.id = id;
		this.arrival_time = arrival_time;
		this.priority = priority;
		this.burst_time = burst_time;
	}
	
	Process(int arrival_time, int priority, int burst_time) {
		this(id_generator++, arrival_time, priority, burst_time);
	}
	
}

class ProcessComparator implements Comparator<Process> {
	@Override
	public int compare(Process p1, Process p2) {
		return p1.arrival_time - p2.arrival_time;
	}
}
