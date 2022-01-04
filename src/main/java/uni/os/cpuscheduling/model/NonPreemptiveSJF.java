package uni.os.cpuscheduling.model;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class NonPreemptiveSJF implements SchedulingAlgorithm {
	private final PriorityQueue<BurstProcess> processes = new PriorityQueue<>();
	private Process running_process = null;
	private final ArrayList<Process> finished_processes = new ArrayList<>();

	@Override
	public void getNewProcess(Process process) {
		processes.add(new BurstProcess(process));
	}
	@Override
	public Process getRunningProcess() {
		return running_process;
	}
	
	@Override
	public void selectProcess() {
		if (!hasPendingProcess())
			running_process = processes.poll();
		else if (running_process.isDone()) {
			finished_processes.add(running_process);
			running_process = processes.poll();
		}
	}
	
	@Override
	public Process[] getFinishedProcesses() {
		return finished_processes.toArray(new Process[0]);
	}
}
