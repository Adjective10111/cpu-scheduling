package uni.os.cpuscheduling.model;

import java.util.ArrayList;

public class NonPreemptiveSJF implements SchedulingAlgorithm {
	private final ArrayList<Process> processes;
	private Process running_process;
	private final ArrayList<Process> finished_processes;

	public NonPreemptiveSJF() {
		processes = new ArrayList<>();
		running_process = null;
		finished_processes = new ArrayList<>();
	}

	@Override
	public void getNewProcess(Process process) {
		processes.add(new Process(process));
		processes.sort(Process::compareByBurstTimeTo);
	}
	
	@Override
	public void selectProcess() {
		if (!hasPendingProcess())
			running_process = processes.remove(0);
		else if (running_process.isDone()) {
			finished_processes.add(running_process);
			running_process = processes.remove(0);
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
}
