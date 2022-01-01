# cpu-scheduling

OS project:

###### Subject:

Simulate CPU scheduling algorithms

###### Description:

Each process should have these attributes:

- Process ID
- Arrival Time
- Priority
- Burst Time

> Each time unit is equivalent to the time CPU needs to run this code snippet:
> ```
> for (int i = 0, temp = 0; i < 10_000; ++i)
>			if (i % 2 == 0)
>				temp = i / 2;
>			else
>				temp = i * 2;
> ```

This scheduling simulator should be able to simulate these algorithms:

- FIFO/FCFS
- Preemptive SJF
- Non-Preemptive SJF
- RR (with specified time quantum)
- Priority with preemption
- Non-Preemptive priority

In each execution of the program, 100,000 processes should be simulated. In other words, after completion of 100,000 processes, the program should be stopped and these info should be calculated and presented:

- Number of processes: 100,000
- Total time
- Throughput
- CPU utilization
- Average waiting time
- Average turnaround time
- Average response time

All the processes should be generated via a Request Generator, and saved in a file. This file is then used for the main program(the scheduling simulator). For ease of implementation, let waiting state for processes disabled. Also, let the ready queue's length to be 100.
