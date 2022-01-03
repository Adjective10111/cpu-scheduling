package uni.os.cpuscheduling.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RequestGenerator {
	public static int number_of_processes = 100;
	public static int max_burst = 30;
	
	public static void generateProcessData() {
		try {
			var writeCSV = new Formatter("processes_data.csv");
			generateProcessData(writeCSV);
			writeCSV.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void generateProcessData(Formatter writer) {
		writer.format("%s,%s,%s,%s\n", "ID", "ArrivalTime", "Priority", "BurstTime");
		for (int process_id = 1; process_id <= number_of_processes; process_id++) {
			int randomPriority = ThreadLocalRandom.current()
					.nextInt(0, 255 + 1);
			int randomArrival = ThreadLocalRandom.current()
					.nextInt(0, max_burst * number_of_processes + 1);
			int randomBurst = ThreadLocalRandom.current()
					.nextInt(1, max_burst + 1);
			
			writer.format("%d,%d,%d,%d\n",
					process_id, randomArrival, randomPriority, randomBurst);
		}
	}
	
	public static Queue<Process> readProcessData() {
		try {
			var fileProcesses = new File("processes_data.csv");
			if (!fileProcesses.exists())
				generateProcessData();
			
			var readingCSV = new Scanner(fileProcesses);
			var processes = readProcessData(readingCSV);
			readingCSV.close();
			
			processes.sort(Process::compareTo);
			return new PriorityQueue<>(processes);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Queue<Process> readProcessData(String filename) {
		try {
			var readingCSV = new Scanner(new File(filename));
			var processes = readProcessData(readingCSV);
			readingCSV.close();
			
			processes.sort(Process::compareTo);
			return new PriorityQueue<>(processes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	private static ArrayList<Process> readProcessData(Scanner reader) {
		// skip the info line
		reader.nextLine();
		var processes = new ArrayList<Process>();
		
		while (reader.hasNextLine()) {
			var line = reader.nextLine();
			var info = line.split(",");
			Process process = new Process(
					Integer.parseInt(info[0]),
					Integer.parseInt(info[1]),
					Integer.parseInt(info[2]),
					Integer.parseInt(info[3])
			);
			processes.add(process);
		}
		
		processes.sort(Process::compareTo);
		return processes;
	}
	
}
