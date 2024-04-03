
package driverclass;
/**
 *
 * @author Maryam
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class DriverClass {
    ArrayList<ProcessControlBlock> q1 = new ArrayList<>();
    ArrayList<ProcessControlBlock> q2 = new ArrayList<>();
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Choose the desired operation from the following menu:"
                    + "1. Enter process' information"
                    + "2. Report detailed information about each process and different scheduling criteria"
                    + "3. Exit the program");
        
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    processInfo();
                    break;
                    
                case 2:
                    report(); 
                    break;
                    
                case 3:
                    System.out.println("Exiting...");
                    break;
                    
                default:
                    System.out.println("Invalid choice, try again with a valid one.");
            }
        } while (choice != 3);
    }
    
    
    
    //////////////////////methods///////////////////////////
    public static void processInfo(){
        System.out.println("How many proccess you want to add");
        int numOfProcess=input.nextInt();
         for (int i = 1; i <= numOfProcess; i++) {
              System.out.println("Enter inforamtion of process P" + i + ":");
            System.out.println("Priority (1 or 2): ");
            int pri = input.nextInt();
            System.out.println("Arrival Time: ");
            double arrivTime = input.nextDouble();
            System.out.println("CPU Burst Time: ");
            double CPUburst = input.nextDouble();
            
            
            ////not finish yet but ساطعcharge is went off
         }
    }
    
    public static void report(){
//[process ID, priority, arrival time, CPU burst time, starting and termination time, turnaround time,
//waiting time, and the response time]
//In addition, the report should contain information about different
//scheduling criteria, which include [the average turnaround time, the average waiting time, and the average
//response time].
    }
    
    
}
