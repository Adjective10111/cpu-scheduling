package uni.os.cpuscheduling.model;

public class Process implements Comparable<Process> {
	private static int id_generator = 1;
	private final static int START = 0;
	private final static int FINISH = 1;
	public final static String GET_WAITING_TIME = "getWaitingTime";
	public final static String GET_SPAN_TIME = "getSpanTime";
	public final static String GET_RESPONSE_TIME = "getResponseTime";
	
	private final int id;
	public final int arrival_time;
	private final int priority;
	private final int burst_time;
	
	private int execution_time;
	private int[ ] span_time;
	
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
	@Override
	public int compareTo(Process other) {
		return arrival_time - other.arrival_time;
	}
}
