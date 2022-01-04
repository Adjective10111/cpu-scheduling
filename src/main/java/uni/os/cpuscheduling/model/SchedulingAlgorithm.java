package uni.os.cpuscheduling.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface SchedulingAlgorithm {
	void getNewProcess(Process process);
	void selectProcess();
	Process getRunningProcess();
	default boolean hasPendingProcess() {
		return getRunningProcess() != null;
	}
	default void process() {
		selectProcess();
		if (hasPendingProcess())
			Process.execute(getRunningProcess());
	}
	Process[] getFinishedProcesses();
	
	default double Throughput() {
		// todo this function is empty
		return 0.0;
	}
	default double CPUUtilization() {
		// todo
		return 0.0;
	}
	default int averageWaitingTime() {
		try {
			return calcAverage(Process.class.getMethod(Process.GET_WAITING_TIME));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return -1;
		}
	}
	default int averageTurnaroundTime() {
		try {
			return calcAverage(Process.class.getMethod(Process.GET_SPAN_TIME));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return -1;
		}
	}
	default int averageResponseTime() {
		try {
			return calcAverage(Process.class.getMethod(Process.GET_RESPONSE_TIME));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return -1;
		}
	}
	private int calcAverage(Method method) {
		var processes = getFinishedProcesses();
		if (processes == null) {
			throw new ArrayStoreException();
		}
		int sum = 0;
		try {
			for (var process : processes)
				sum += (Integer) method.invoke(process);
		} catch (InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return sum / processes.length;
	}
}
