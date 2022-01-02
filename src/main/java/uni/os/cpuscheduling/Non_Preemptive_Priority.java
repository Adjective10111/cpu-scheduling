//package uni.os.cpuscheduling;
//// Java implementation for Priority Scheduling with
////Different Arrival Time priority scheduling
//
//import java.util.*;
//
///// Data Structure
//class ProcessPriority {
//	int at, bt, pri, pno;
//
//	ProcessPriority(int pno, int at, int bt, int pri) {
//		this.pno = pno;
//		this.pri = pri;
//		this.at = at;
//		this.bt = bt;
//	}
//}
//
///// Gantt chart structure
//class GChart {
//	// process number, start time, complete time,
//	// turn around time, waiting time
//	int pno, stime, ctime, wtime, ttime;
//}
//
//// user define comparative method (first arrival first serve,
//// if arrival time same then heigh priority first)
//class MyComparator implements Comparator {
//
//	public int compare(Object o1, Object o2) {
//
//		ProcessPriority p1 = (ProcessPriority) o1;
//		ProcessPriority p2 = (ProcessPriority) o2;
//		if (p1.at < p2.at)
//			return (-1);
//
//		else if (p1.at == p2.at && p1.pri > p2.pri)
//			return (-1);
//
//		else
//			return (1);
//	}
//}
//
//
//// class to find Gantt chart
//public class Non_Preemptive_Priority {
//	void findGc(LinkedList queue) {
//
//		// initial time = 0
//		int time = 0;
//
//		// priority Queue sort data according
//		// to arrival time or priority (ready queue)
//		TreeSet prique = new TreeSet(new MyComparator());
//
//		// link list for store processes data
//		LinkedList result = new LinkedList();
//
//		// process in ready queue from new state queue
//		while (queue.size() > 0)
//			prique.add((ProcessPriority) queue.removeFirst());
//
//		Iterator it = prique.iterator();
//
//		// time set to according to first process
//		time = ((ProcessPriority) prique.first()).at;
//
//		// scheduling process
//		while (it.hasNext()) {
//
//			// dispatcher dispatch the
//			// process ready to running state
//			ProcessPriority obj = (ProcessPriority) it.next();
//
//			GChart gc1 = new GChart();
//			gc1.pno = obj.pno;
//			gc1.stime = time;
//			time += obj.bt;
//			gc1.ctime = time;
//			gc1.ttime = gc1.ctime - obj.at;
//			gc1.wtime = gc1.ttime - obj.bt;
//
//			/// store the exxtreted process
//			result.add(gc1);
//		}
//
//		// create object of output class and call method
//		new ResultOutput(result);
//	}
//}