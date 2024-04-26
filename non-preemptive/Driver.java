package os;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {

	static Scanner input = new Scanner(System.in);
	static PCB allProccesses[]=null;


	static PCB q1[]=null;
	static int q1Size=0;


	static PCB q2[]=null;
	static int q2Size=0;
	

	static final int QT=3;
	static int currentTime=0;

	static String order="";
	static String lastProcess="";

	public static void main(String[] args) {


		int choice =0;
		do {

			System.out.println("Choose:");
			System.out.println("1) start new");
			System.out.println("2) Show details");
			System.out.println("3) exit");

			choice=input.nextInt();

			if(choice==1) {
				lastProcess="";
				System.out.println("Enter number of processes");
				int numOfProcesses=input.nextInt();
				allProccesses=new PCB[numOfProcesses];


				q1=null;
				q1Size=0;

				q2=null;
				q2Size=0;
				

				currentTime=0;

				order="";



				if(numOfProcesses>0)
					for(int i=1 ; i<=numOfProcesses;i++) {
						int pri;
						do {
							System.out.println("P"+i+")Enter priority, 1 or 2");
							pri=input.nextInt();
						}//end do
						while(pri!=1 && pri!=2);

						System.out.println("P"+i+")Enter Arrival time");
						int arrival=input.nextInt();

						System.out.println("P"+i+")Enter cpu burst");
						int burst=input.nextInt();


						PCB p = new PCB(("P"+i),pri,arrival,burst);

						allProccesses[i-1]=p;


					}//end for

				Arrays.sort(allProccesses, (a, b) -> a.arrival - b.arrival);

				q1=new PCB[allProccesses.length];
				q2=new PCB[allProccesses.length];

				while(true) {



					addToQ();
					
					

					if(q1Size!=0) {

						executeQ1();continue;
					}//if size=0

					if(q2Size!=0) {

						executeQ2();continue;
					}//if size=0


					if(allDone())
						break;
					
					else {

						currentTime++;
					}


				}//end while true


			}//end choice 1

			if(choice==2) {
				
				String output="";
				
				
				
				if(allProccesses==null)
					System.out.println("\nSystem is empty\n");
				else {
					
					
					output+="\n\n["+order.replaceAll(",$","")+"]\n----------\n";


					
					int sumTT=0, sumWT=0, sumRT=0;

					for(int i=0 ; i<allProccesses.length; i++) {
						output+=allProccesses[i].toString()+"\n------------\n";
						sumTT+=allProccesses[i].turnAround;
						sumWT+=allProccesses[i].waiting;
						sumRT+=allProccesses[i].response;
						
					}//end for
					
					
					double numOfProcesses=allProccesses.length;
					double avgTT=sumTT/numOfProcesses, avgWT=sumWT/numOfProcesses, avgRT=sumRT/numOfProcesses;
					
					output+="\nAverage Turnaround Time:"+avgTT+"\nAverage Waiting Time:"+avgWT+"\nAverage Response Time:"+avgRT+"\n\n";
					
					System.out.println(output);
					
					
					
					
					

				



				try {
					FileWriter fileWriter = new FileWriter("output.txt");
					PrintWriter printWriter = new PrintWriter(fileWriter);
					printWriter.println(output);
					
					printWriter.close();
					System.out.println("Output written to output.txt");
				} catch (IOException e) {
					System.out.println("An error occurred while writing to file.");
					e.printStackTrace();
				}

				}//end else

			}//end choice 2

		}//end do




		while(choice!=3);
		System.out.println("Bye Bye!");



	}//end main

	

	static void addToQ() {



		for(int i=0 ; i<allProccesses.length ; i++) {
			PCB p = allProccesses[i];


			if (p==null) {

				break;
			}




			if(p.arrival<=currentTime && !p.isInQueue ) {

				if(p.priority==1)
					q1[q1Size++]=p;
				else
					q2[q2Size++]=p;

				p.isInQueue=true;
				p.lastTimeMovedInQueue=currentTime;
				
				
				

			}//end if
		}//end for

	}//end addToQ1






	static void executeQ1() {



		



		PCB currentProcess=q1[0];

		
		//to ensure that new process has priority over processes that the cpu release at the same time
		
		PCB nextProcess=null;
		try {
			nextProcess=q1[1];

			
			if((currentProcess.lastTimeMovedInQueue >=nextProcess.lastTimeMovedInQueue) && (nextProcess.rem == nextProcess.burst) && (currentProcess.rem != currentProcess.burst)) {
				q1[0]=nextProcess;
				q1[1]=currentProcess;
				currentProcess=nextProcess;
			}//end if
				


		}
		catch(Exception e) {}
		
		
		
		
		


		

		if(!lastProcess.equals(currentProcess.processId)) {


			
			lastProcess=currentProcess.processId;
			order+=currentProcess.processId+",";
			//}
		}//end if length 0




		//if it is the first time for the process
		if(currentProcess.burst==currentProcess.rem) {
			currentProcess.start=currentTime;

		}//end if burst


		if(currentProcess.rem<=QT) {

			currentTime+=currentProcess.rem;
			addToQ();
			currentProcess.termination=currentTime;
			currentProcess.turnAround=currentProcess.termination-currentProcess.arrival;
			currentProcess.waiting=currentProcess.turnAround-currentProcess.burst;
			currentProcess.rem=0;
			currentProcess.response=currentProcess.start-currentProcess.arrival;
			remove(q1, 1,0);

		}//end if rem<QT

		else {
			currentProcess.rem=currentProcess.rem-QT;
			currentTime+=QT;
			addToQ();
			currentProcess.lastTimeMovedInQueue=currentTime;
			removeAndAdd(q1, 1);


		}


	}//end execute


	static void executeQ2() {
	
	
	
		

	
		int shortestIndex=findShortest(q2);
		PCB current = q2[shortestIndex];
	
	
		
		if(!lastProcess.equals(current.processId)) {
			
			lastProcess=current.processId;
			order+=current.processId+",";
			
		}//end if length 0
	

	
	
	
	
		//if first time being executed
		if(current.rem==current.burst) {
			current.start=currentTime;
	
		}//end if first time
	
	
	
	

	
		currentTime=currentTime+current.rem;
		
		current.termination=currentTime;
		current.turnAround=current.termination-current.arrival;

		current.waiting=current.turnAround-current.burst;
		
		current.response=current.start-current.arrival;
		current.rem=0;
		remove(q2,2,shortestIndex);

		
	
	
		
	
	
	
	
	
	}//end executeq2

	static void remove(PCB array[], int q, int indexToRemove) {



		int counter = 0;
		if (q == 1) {
			counter = q1Size;
		} else if (q == 2) {
			counter = q2Size;
		}

		

		
		
		
		if (indexToRemove < 0 || indexToRemove >= counter) {
			System.out.println("Invalid index to remove.");
			return;
		}

		
		for (int i = indexToRemove; i < counter - 1; i++) {
			array[i] = array[i + 1];
		}

		if (q == 1) {
			q1Size--;
		} else if (q == 2) {
			q2Size--;
		}
		
		

	}


	static void removeAndAdd(PCB array[], int q) {



		int counter=0;
		if(q==1) {
			counter=q1Size;
		}//end if

		if(q==2) {
			counter=q2Size;
		}//end if

		PCB firstValue = array[0];

		

		if(counter==1) return;

		for (int i = 0; i < counter - 1; i++) {
			array[i] = array[i + 1];
		}



		if(q==1) {
			array[q1Size - 1] = firstValue;
		}//end if

		if(q==2) {
			array[q2Size - 1] = firstValue;
		}//end if
		
		



	}//end add


	static boolean allDone() {

		for(PCB p : allProccesses) {
			if(p.isInQueue==false)
				return false;
		}//end for

		return true;
	}//end check



	static int findShortest(PCB[] q) {
		if(q.length==0)return -1;
		if(q.length==1)return 0;

		int shortest=0;
		int i;
		for(i=1 ; i<q2Size; i++) {
			if(q[i].rem<q[shortest].rem)
				shortest=i;
		}

		return shortest;
	}//end find shortest




}//end class
