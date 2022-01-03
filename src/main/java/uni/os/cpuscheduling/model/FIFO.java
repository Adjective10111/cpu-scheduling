package uni.os.cpuscheduling.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FIFO implements SchedulingAlgorithm {
	private final Queue<Process> processes;
	private Process running_process;
	private final ArrayList<Process> finished_processes;
	
	public FIFO() {
		processes = new LinkedList<>();
		running_process = null;
		finished_processes = new ArrayList<>();
	}
	
	@Override
	public void getNewProcess(Process process) {
		processes.add(process);
	}
	
	@Override
	public void selectProcess() {
		if (running_process == null)
			running_process = processes.poll();
		else if (running_process.isDone()) {
			finished_processes.add(running_process);
			running_process = processes.poll();
		}
	}
	
	@Override
	public void process() {
		if (running_process != null)
			Process.execute(running_process);
	}
	
	@Override
	public Process[] getProcesses() {
		return finished_processes.toArray(new Process[0]);
	}
}
