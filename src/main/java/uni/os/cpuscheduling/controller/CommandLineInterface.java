package uni.os.cpuscheduling.controller;

import uni.os.cpuscheduling.model.OperatingSystem;
import uni.os.cpuscheduling.model.RequestGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class CommandLineInterface {
	private static String[] args;
	private static boolean added_algorithms = false;
	private static ArrayList<String> algorithms = new ArrayList<>(List.of(
			"FIFO", "PSJF", "SJF", "RR", "7", "PP", "P"
	));
	public static boolean verbose = false;
	public static final String help = // todo: add the jarfile name
			"""
					Usage: JARFILE [OPTION]
					\t\t(to execute in commandline)
					
					With no OPTION it uses GUI
					
					\t-g, --generate                        generate new process csv file
					\t-r, --read-csv <FILE>                 read <FILE> if found, to instantiate processes
					\t-n, --number-of-processes <N>         generate <N> processes to simulate
					\t-A, --arrival-range <MIN> <MAX>       set the range for random arrival
					\t-B, --burst-range <MIN> <MAX>         set the range for random burst
					\t-P, --priority-range <MIN> <MAX>      set the range for random priority
					\t-I, --starting-id <N>                 start IDs from <N>
					\t-s, --select-algorithms <ALG>...      only simulate ALG(s) specified
					\t-S, --deselect-algorithms <ALG>...    do not simulate ALG(s) specified
					\t-v, --verbose                         explain what is being done
					\t-h, --help                            display this help and exit
					
					The default values for these options are:
					\t-A 0 ($B * ($N - 1))
					\t-B 0 30
					\t-P 0 255
					\t$B    = max burst time
					\t$N    = number of processes
					\t$ALG  = [FIFO | PSJF | SJF | RR | PP | P]
					If using RR, you should specify the time slice afterwards
					
					Usage of $VAR is not allowed in this version
					""";
	
	public static void launch(String[] args) {
		CommandLineInterface.args = args;
		for (int index = 0; index < args.length; index++) {
			if (args[index].charAt(0) == '-')
				index = handleOption(index);
			else
				throw new NoSuchElementException();
		}
		if (!added_algorithms)
			addAlgorithms();
		OperatingSystem.init();
	}
	
	private static int handleOption(int index) {
		switch (args[index]) {
			case "-g", "--generate" -> RequestGenerator.generateProcessData();
			case "-r", "--read-csv" -> RequestGenerator.filename = args[++index];
			case "-n", "--number-of-processes" -> RequestGenerator.number_of_processes = Integer.parseInt(args[++index]);
			case "-A", "--arrival-range" -> {
				RequestGenerator.min_arrival = Integer.parseInt(args[++index]);
				RequestGenerator.max_arrival = Integer.parseInt(args[++index]);
			}
			case "-B", "--burst-range" -> {
				RequestGenerator.min_burst = Integer.parseInt(args[++index]);
				RequestGenerator.max_burst = Integer.parseInt(args[++index]);
			}
			case "-P", "--priority-range" -> {
				RequestGenerator.min_priority = Integer.parseInt(args[++index]);
				RequestGenerator.max_priority = Integer.parseInt(args[++index]);
			}
			case "-I", "--starting-id" -> RequestGenerator.starting_id = Integer.parseInt(args[++index]);
			case "-s", "--select-algorithms" -> {
				algorithms = new ArrayList<>();
				int i;
				for (i = 1; args[i + index].charAt(0) != '-'; ++i)
					algorithms.add(args[i + index]);
				index += i - 1;
				addAlgorithms();
			}
			case "-S", "--deselect-algorithms" -> {
				int i;
				for (i = 1; args[i + index].charAt(0) != '-'; ++i) {
					var index_of_arg = algorithms.indexOf(args[i + index]);
					algorithms.remove(index_of_arg);
					// remove the default time slice
					if (args[i + index].equals("RR"))
						algorithms.remove(index_of_arg);
				}
				addAlgorithms();
			}
			case "-v", "--verbose" -> verbose = true;
			case "-h", "--help" -> {
				System.out.println(help);
				System.exit(0);
			}
			default -> throw new NoSuchElementException();
		}
		return index;
	}
	
	private static void addAlgorithms() {
		for (int i = 0; i < algorithms.size(); ++i) {
			if (algorithms.get(i).equals("RR")) {
				int time_slice = Integer.parseInt(algorithms.get(i + 1));
				OperatingSystem.addAlgorithm(algorithms.get(i), time_slice);
				i++;
			} else
				OperatingSystem.addAlgorithm(algorithms.get(i));
		}
		added_algorithms = true;
	}
}
