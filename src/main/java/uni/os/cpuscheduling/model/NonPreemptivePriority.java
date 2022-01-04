package uni.os.cpuscheduling.model;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class NonPreemptivePriority implements SchedulingAlgorithm {
	private final PriorityQueue<Process> processes = new PriorityQueue<>(new PriorityComparator());
	private Process running_process = null;
	private final ArrayList<Process> finished_processes = new ArrayList<>();
	
	@Override
	public void setRunningProcess(Process process) {
		running_process = process;
	}
	@Override
	public Process getRunningProcess() {
		return running_process;
	}
	@Override
	public Queue<Process> getProcessesQueue() {
		return processes;
	}
	@Override
	public ArrayList<Process> getFinishedProcesses() {
		return finished_processes;
	}
}
