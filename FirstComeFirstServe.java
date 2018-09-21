import java.util.*;

public class FirstComeFirstServe 
{
	/****This section is has a separate class that has variables which will identify each process that will be created 
	 * later on in the program. The process is identified by each of the variables in the constructor of this class****/
	//Process Identification Class
	static class Process
	{
		int processNumber;
		int arrivalTime;
		int burstTime;
		int completionTime;
		int turnAround;
		int waitTime;

		public Process()
		{
			arrivalTime = 0;
			burstTime = 0;
			completionTime = 0;
			turnAround = 0;
			waitTime = 0;
		}
	}
	
	public static void main(String args[])
	{
		/****SCANNER DECLARATION AND USER INPUT****/
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter number of processes: ");
		int numProcesses = scanner.nextInt();
		
		/****ALL DECLARATIONS: VARIABLES, PROCESS, AND QUEUES****/
		//Variable declarations
		float avgWaitTime = 0;
		float avgTurnAround = 0;
		int totalCompletionTime = 0;
		float throughPut = 0;
		double contextSwitch = 0;
		float cpuUtilization;
 
		/****This section creates a process array and temporary process array as well as a wait queue and running queue. 
		 * It also creates the number of processes specified and asks user input for the arrival and burst times for each process.****/
		//Create array of processes
		Process p[] = new Process[numProcesses];
		
		//Temporary process used for sorting process
		Process temp;
		
		//Creating wait queue and running queue
		Queue<Process> waitQueue = new LinkedList<>();
		Queue<Process> runningState = new LinkedList<>();
		
		//Creating the number of processes specified and assigning arrival and burst times for each
		for(int i = 0; i < numProcesses; i++)
		{
			p[i] = new Process();
			p[i].processNumber = i + 1;
			
			System.out.println("Enter arrival time for Process " + (i + 1) + ": ");
 			p[i].arrivalTime = scanner.nextInt();
 			
			System.out.println("Enter burst time for Process " + (i + 1) + ": ");
			p[i].burstTime = scanner.nextInt();
		}
		
		/****This section sorts the incoming processes according to arrival time and adds them to the wait queue****/
		//Sort the incoming processes according to arrival time
		for(int i = 0; i < numProcesses; i++)
		{
			for(int j = 0; j < numProcesses - (i + 1); j++)
			{
				//Compare the process arrival time and switch positions if out of order
				if(p[j].arrivalTime > p[j + 1].arrivalTime)
				{
					temp = p[j];
					p[j] = p[j + 1];
					p[j + 1] = temp;
				}
			}
			
			//Add all processes to waiting queue and list of process array
			waitQueue.add(p[i]);
		}
		
		/****This section handles the calculations for the scheduler and switches processes between wait queue and running queue****/
		//Calculating completion times for each process
		for(int i = 0; i < numProcesses; i++)
		{
			//Position of next process in wait queue
			int j = 1;
			
			//Computing for first process
			if(runningState.isEmpty() || i == 0)
			{	
				runningState.add(p[i]);
				p[i].completionTime = p[i].arrivalTime + p[i].burstTime;
			}
			else //Computing for all other processes
			{		
				if(p[i].arrivalTime > p[i-1].completionTime)
				{
					p[i].completionTime = p[i].arrivalTime + p[i].burstTime;
				}
				else
				{
					p[i].completionTime = p[i-1].completionTime + p[i].burstTime;
				}
			}
			
			//Computing Turnaround for each process
			p[i].turnAround = p[i].completionTime - p[i].arrivalTime;
			
			//Computing Waiting Time for each process
			p[i].waitTime = p[i].turnAround - p[i].burstTime;
			
			//Totaling all process wait times and turnaround times
			avgWaitTime += p[i].waitTime;
			avgTurnAround += p[i].turnAround;     
			
			//Calculating Total Completion Time
			totalCompletionTime += p[i].completionTime;
			
			runningState.remove(p[i]);
			
			if(runningState.isEmpty())
			{
				runningState.add(p[j]);
				contextSwitch += 1;
				j++;
			}
			
			waitQueue.remove(p[i]);
		}
		
		//Calculating CPU Utilization
		double idleTime = contextSwitch * 0.1;
		cpuUtilization = ((float)totalCompletionTime/((float)totalCompletionTime + (float)idleTime)) * 100;

		//Calculating throughput
		throughPut = ((float)numProcesses/(float)totalCompletionTime) * 100;
		
		//Calculating Average Wait Time and Average Turnaround time
		avgWaitTime = avgWaitTime/numProcesses;
		avgTurnAround = avgTurnAround/numProcesses;
		
		/****This section sorts the processes according to the process name for display purposes****/
		//Sorting processes list according to process name
		for(int i = 0; i < numProcesses; i++)
		{
			for(int j = 0; j < numProcesses - (i + 1); j++)
			{
				//Compare the process arrival time and switch positions if out of order
				if(p[j].processNumber > p[j + 1].processNumber)
				{
					temp = p[j];
					p[j] = p[j + 1];
					p[j + 1] = temp;
				}
			}
		}
	
		/****This section displays all of the scheduler information****/
		//Displaying scheduler information for each process
		System.out.println("\n--------------------First Come First Serve Scheduling--------------------");
		System.out.println("Process Number   Arrival   Burst   Completion   Turnaround    Wait");
		
		for(int i = 0; i < numProcesses; i++)
		{
			System.out.println("       " + p[i].processNumber + "\t    " 
					+ p[i].arrivalTime + "\t     " 
					+ p[i].burstTime + "\t\t" 
					+ p[i].completionTime + "\t    " 
					+ p[i].turnAround + "\t\t"  
					+ p[i].waitTime);
		}
		
		//Printing CPU utilization and throughput
		System.out.println("\nCPU Utilization: " + cpuUtilization + "%");
		System.out.println("Throughput: "+ throughPut + "%");
		
		//Print the average waiting time and average turnaround
		System.out.println("Average Waiting Time: " + avgWaitTime + " seconds");
		System.out.println("Average Turnaround Time: " + avgTurnAround + " seconds");
		
		scanner.close();
	}
}
