package uni.os.cpuscheduling.model;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PreemptivePriority implements SchedulingAlgorithm {
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
	
	private boolean isRunningProcessPrioritized() {
		if  (processes.peek() == null)
			return true;
		return running_process.getPriority() <= processes.peek().getPriority();
	}
	@Override
	public void selectProcess() {
		if (!hasPendingProcess())
			running_process = processes.poll();
		else if (running_process.isDone()) {
			finished_processes.add(running_process);
			running_process = processes.poll();
		}
		else if(!isRunningProcessPrioritized()){
			processes.add(running_process);
			running_process = processes.poll();
		}
	}
}
