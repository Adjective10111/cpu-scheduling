package uni.os.cpuscheduling.model;

import java.io.File;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class RequestGenerator {
	public static void generateProcessData(){
		try{
			Formatter writeCSV = new Formatter("processes_data.csv");
			for(int i=0; i<=10; i++){
				int randomPriority = ThreadLocalRandom.current().nextInt(0, 255 + 1);
				int randomArrival = ThreadLocalRandom.current().nextInt(0, 3000000 + 1);
				int randomBurst = ThreadLocalRandom.current().nextInt(1, 30 + 1);
				if(i==0){
					writeCSV.format("%s,%s,%s,%s\n","ID", "Priority", "ArrivalTime", "BurstTime");
				}else{
					writeCSV.format("%d,%d,%d,%d\n", i, randomPriority, randomArrival, randomBurst );
				}
			}
			writeCSV.close();
		} catch (Exception e){
			System.out.println("An error has occurred for generating the processes");
		}
	}

	public static Queue<Process> readProcessData() {
		ArrayList<Process> processes = new ArrayList<>();
		try {
			File fileProcesses = new File("processes_data.csv");
			Scanner readingCSV = new Scanner(fileProcesses);
			int counter = 1;
			while (readingCSV.hasNextLine()){
				if(counter != 1){
					String line = readingCSV.nextLine();
					String[] stringProcess = line.split(",");
					Process process = new Process(Integer.parseInt(stringProcess[0]), Integer.parseInt(stringProcess[1]), Integer.parseInt(stringProcess[2]), Integer.parseInt(stringProcess[3]));
					processes.add(process);
				}else{
					readingCSV.nextLine();
				}
				counter++;
			}
			readingCSV.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		processes.sort(Process::compareTo);
		return new PriorityQueue<Process>(processes);
	}


	public static Queue<Process> readProcessData(String filename) {
		ArrayList<Process> processes = new ArrayList<>();
		try {
			File fileProcesses = new File(filename);
			Scanner readingCSV = new Scanner(fileProcesses);
			int counter = 1;
			while (readingCSV.hasNextLine()){
				if(counter != 1){
					String line = readingCSV.nextLine();
					String[] stringProcess = line.split(",");
					Process process = new Process(Integer.parseInt(stringProcess[0]), Integer.parseInt(stringProcess[1]), Integer.parseInt(stringProcess[2]), Integer.parseInt(stringProcess[3]));
					processes.add(process);
				}else{
					readingCSV.nextLine();
				}
				counter++;
			}
			readingCSV.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		processes.sort(Process::compareTo);
		return new PriorityQueue<Process>(processes);
	}
}
