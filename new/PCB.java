package os;

public class PCB {
	
String processId;
int priority, arrival, burst, start, termination, turnAround, waiting, response, rem;
boolean isInQueue;
int lastTimeMovedInQueue;




public PCB(String processId, int priority, int arrival, int burst) {
	super();
	this.processId = processId;
	this.priority = priority;
	this.arrival = arrival;
	this.burst = burst;
	rem=burst;
	isInQueue=false;
	lastTimeMovedInQueue=0;
}//end const




@Override
public String toString() {
	
	String str="ID:"+processId+
			"\nPriority:"+priority+
			"\nArrival:"+arrival+
			"\nBurst:"+burst+
			"\nStart:"+start+
			"\nTermination:"+termination+
			"\nTurnaround:"+turnAround+
			"\nWating:"+waiting+
			"\nResponse:"+response;
	
	return str;
}







}


