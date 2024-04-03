
package driverclass;

/**
 *
 * @author Maryam
 */
public class ProcessControlBlock {
    public static int lastAssignedID = 1; //id intially start from 1

    public String processID; 
    public int priority;
    public double arrivalTime;
    public double CPUburst;
    public double startTime;
    public double terminationTime;
    public double turnaroundTime;
    public double waitingTime;
    public double responseTime;
    

    public ProcessControlBlock(int priority, double arrivalTime, double CPUburst) {
        processID="P"+lastAssignedID++;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.CPUburst = CPUburst;
        startTime=-1; //not start yet
        terminationTime=-1; //not end yet
        turnaroundTime=turnaroundTime(); //not sure if this step should b rwitten here  
        waitingTime= waitingTime(); //not sure if this step should b rwitten here
        responseTime=  responseTime(); //not sure if this step should b rwitten here
    }
    
    
    public double turnaroundTime() {
        this.turnaroundTime = this.terminationTime - this.arrivalTime;
        return this.turnaroundTime;
    }
    
    public double waitingTime() {
        this.waitingTime = this.turnaroundTime - this.CPUburst;
        return this.waitingTime;
    }
    
    public double responseTime(){
        this.responseTime = this.startTime - this.arrivalTime;
        return this.responseTime;
    }

    @Override
    public String toString() {
        return "ProcessControlBlock{" + "\nprocessID=" + processID + "\npriority=" + priority + "\narrivalTime=" + arrivalTime + "\nCPUburst=" + CPUburst + "\nstartTime=" + startTime + "\nterminationTime=" + terminationTime + "\nturnaroundTime=" + turnaroundTime + "\nwaitingTime=" + waitingTime + "\nresponseTime=" + responseTime + "\n}";
    }
    
    
    
}
