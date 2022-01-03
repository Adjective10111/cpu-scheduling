package uni.os.cpuscheduling.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface SchedulingAlgorithm {
	void getNewProcess(Process process);
	void selectProcess();
	void process();
	Process[] getProcesses();
	
	default double calcThroughput() {
		// todo this function is empty
		return 0.0;
	}
	default int calcAverageWaitingTime() {
		try {
			return calcAverage(Process.class.getMethod(Process.GET_WAITING_TIME));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return -1;
		}
	}
	default int calcAverageTurnaroundTime() {
		try {
			return calcAverage(Process.class.getMethod(Process.GET_SPAN_TIME));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return -1;
		}
	}
	default int calcAverageResponseTime() {
		try {
			return calcAverage(Process.class.getMethod(Process.GET_RESPONSE_TIME));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			return -1;
		}
	}
	private int calcAverage(Method method) {
		var processes = getProcesses();
		if (processes == null)
			throw new ArrayStoreException();
		int sum = 0;
		try {
			for (var process : processes)
				sum += (Integer) method.invoke(process);
		} catch (InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return sum / processes.length;
	}
	// todo CPU Utilization
}
