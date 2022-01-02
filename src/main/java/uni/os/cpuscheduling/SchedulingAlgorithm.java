package uni.os.cpuscheduling;

import java.util.List;

public interface SchedulingAlgorithm {
	void getNewProcess(Process process);
	void process();
	private void selectProcess() { }
}
