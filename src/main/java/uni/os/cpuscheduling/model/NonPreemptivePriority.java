package uni.os.cpuscheduling.model;

public class NonPreemptivePriority implements SchedulingAlgorithm {
	@Override
	public void getNewProcess(Process process) {
	
	}
	
	@Override
	public void process() {
	
	}
	
	@Override
	public int calcTotalTime() {
		return 0;
	}
	
	@Override
	public double calcThroughput() {
		return 0;
	}
	
	@Override
	public int calcAverageWaitingTime() {
		return 0;
	}
	
	@Override
	public int calcAverageTurnaroundTime() {
		return 0;
	}
	
	@Override
	public int calcAverageResponseTime() {
		return 0;
	}
}