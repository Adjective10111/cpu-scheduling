package uni.os.cpuscheduling.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class FIFO implements SchedulingAlgorithm {
	private final Queue<Process> processes = new LinkedList<>();
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
