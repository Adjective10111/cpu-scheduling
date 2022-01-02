package uni.os.cpuscheduling.model;

public interface SchedulingAlgorithm {
	void getNewProcess(Process process);
	private void selectProcess() { }
	void process();
	
	int calcTotalTime();
	double calcThroughput();
	int calcAverageWaitingTime();
	int calcAverageTurnaroundTime();;
	int calcAverageResponseTime();
	// todo CPU Utilization
}