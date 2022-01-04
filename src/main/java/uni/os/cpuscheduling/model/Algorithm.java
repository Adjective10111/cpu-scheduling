package uni.os.cpuscheduling.model;

public enum Algorithm {
	FIFO, PREEMPTIVE_SJF, NON_PREEMPTIVE_SJF, ROUND_ROBIN, PREEMPTIVE_PRIORITY, NON_PREEMPTIVE_PRIORITY;
	
	public boolean isPreemptive() {
		return this == PREEMPTIVE_SJF || this == PREEMPTIVE_PRIORITY;
	}
}
