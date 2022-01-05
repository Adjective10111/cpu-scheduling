package uni.os.cpuscheduling.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RequestGenerator {
	public static int number_of_processes = 100;
	public static int min_arrival = 0;
	public static int max_arrival = -1;
	public static int min_priority = 0;
	public static int max_priority = 255;
	public static int min_burst = 1;
	public static int max_burst = 30;
	public static int starting_id = 1;
	public static String filename = "processes_data.csv";
	
	public static void generateProcessData() {
		if (max_arrival < 0)
			max_arrival = max_burst * (number_of_processes - 1);
		try {
			var writeCSV = new Formatter(filename);
			generateProcessData(writeCSV);
			writeCSV.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void generateProcessData(Formatter writer) {
		writer.format("%s,%s,%s,%s\n", "ID", "ArrivalTime", "PriorityComparator", "BurstTime");
		for (int counter = 0, process_id = starting_id; counter < number_of_processes; ++process_id, ++counter) {
			writer.format("%d,%d,%d,%d\n",
					process_id, generateInt(min_arrival, max_arrival),
					generateInt(min_priority, max_priority), generateInt(min_burst, max_burst));
		}
	}
	private static int generateInt(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}
	
	public static PriorityQueue<Process> readProcessData() {
		try {
			var fileProcesses = new File(filename);
			if (!fileProcesses.exists()) {
				System.out.println("ERR-404: " + filename + " not found!\n" +
						"Troubleshooter: Generating new processes in " + filename + "...\n");
				generateProcessData();
			}
			
			var readingCSV = new Scanner(fileProcesses);
			var processes = readProcessData(readingCSV);
			readingCSV.close();
			return processes;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	private static PriorityQueue<Process> readProcessData(Scanner reader) {
		// skip the info line
		reader.nextLine();
		var processes = new PriorityQueue<>(new ArrivalComparator());
		
		while (reader.hasNextLine()) {
			var line = reader.nextLine();
			var info = line.split(",");
			var process = new Process(
					Integer.parseInt(info[0]),
					Integer.parseInt(info[1]),
					Integer.parseInt(info[2]),
					Integer.parseInt(info[3])
			);
			processes.add(process);
		}
		return processes;
	}
}
