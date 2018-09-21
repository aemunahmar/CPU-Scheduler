# First Come First Serve CPU Scheduler

# Instructions:
1.  Open the class FirstComeFirstServe.java and run it.
2.  The program will ask the user to enter the number of processes as well as the burst time and arrival time for each one.
3.  After entering the information, the program should run and return a chart of each process’s number, Arrival Time, Burst Time, Completion Time, Turnaround Time, and Wait Time. It should also return CPU Utilization, Throughput, Average Waiting Time, and Average Turnaround Time.

# Program Explained

The purpose of a CPU Scheduler is to process as many jobs or tasks as possible while maximizing the CPU’s usage of time by choosing which process to run from a list of processes that are ready to execute. There are many algorithms that can be used to schedule processes on a CPU. For this project, the First Come First Serve, or FCFS for short, scheduling algorithm was used. 

The FCFS scheduling algorithm executes processes in FIFO order. This scheduling algorithm is non preemptive which means that the next process will only start execution once the previous process has been fully executed or terminated. 

For this program there is only one class, the FirstComeFirstServe class. This class has all of the components to run the scheduler. The first section is a nested class Process which holds a Process constructor. This constructor holds the variables process number, arrival time, burst time, completion time, turnaround time, and wait time which will be used as identification for each process that will be created later on in the program. 

After the class Process, is the main class where process creation, scheduling, and process related calculations occur. The first section asks the user to input a number of processes. Once the user inputs that information, the second section will be invoked which will create the number of process objects specified by the user. For each process, the user will be asked to input the arrival time and burst time after which it is added to the wait queue. Once all of the processes are added to the wait queue, the scheduler will be checked to see if it is empty. If not, the first process in the wait queue will be executed and the calculations section of the program will run. Once the process has finished execution, that process will be terminated and the next process will be executed. Every time a processes switches states, the context switch counter will increase by 1. This information will then be used to calculate CPU idle time for CPU Utilization later on in the program. This process will repeat until all of the processes in the wait queue have been executed. 

Once all of the processes finish executing, the program will start computing scheduler statistics which includes CPU Utilization, Throughput, Average Wait Time, and Average Turnaround. The calculations are as follows:

CPU Utilization = (total completion time / (total completion time + idle time)) * 100
**Note: idle time = context switch * 0.1**
Throughput = (number of processes / total completion time) * 100
Average Wait Time = total wait time / number of processes
Average Turnaround Time = total turnaround time / number of processes

After all of the statistics are calculated, the processes will be sorted according to process names for display purposes after which a chart of each of the processes’ information will be displayed as well as the statistics that were calculated right before. This is when the CPU Scheduler has officially finished execution and the program terminates. 
