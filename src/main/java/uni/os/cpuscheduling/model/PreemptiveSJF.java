package uni.os.cpuscheduling.model;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class PreemptiveSJF implements SchedulingAlgorithm {
	private final PriorityQueue<RemainingBurstProcess> processes;
	private RemainingBurstProcess running_process;
	private final ArrayList<Process> finished_processes;

	public PreemptiveSJF() {
		processes = new PriorityQueue<>();
		running_process = null;
		finished_processes = new ArrayList<>();
	}
	@Override
	public void getNewProcess(Process process) {
		processes.add(new RemainingBurstProcess(process));
	}
	
	@Override
	public void selectProcess() {
		if (!hasPendingProcess())
			running_process = processes.poll();
		else if (running_process.isDone()) {
			finished_processes.add(running_process);
			running_process = processes.poll();
		}
		else if(!isRunningProcessTheShortest()){
			processes.add(running_process);
			running_process = processes.poll();
		}
	}
	@Override
	public boolean hasPendingProcess() {
		return running_process != null;
	}
	@Override
	public void process() {
		selectProcess();
		if (hasPendingProcess())
			Process.execute(running_process);
	}
	
	@Override
	public Process[] getProcesses() {
		return finished_processes.toArray(new Process[0]);
	}

	private boolean isRunningProcessTheShortest(){
		if  (processes.peek() == null)
			return true;
		return running_process.getRemainingBurstTme() <= processes.peek().getRemainingBurstTme();
	}
}
