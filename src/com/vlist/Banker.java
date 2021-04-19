package com.vlist;

import java.util.ArrayList;
import java.util.Scanner;

public class Banker {
    ArrayList<Process> processes = new ArrayList<Process>();
    ArrayList<Process> safeSequence = new ArrayList<Process>();
    ArrayList<Resource> workRecord = new ArrayList<Resource>();
    private Resource systemAvailable = new Resource();
    private final int processesCount;

    public Banker() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter system available resource:");
        systemAvailable.setRes(sc);

        System.out.println("Enter processes number:");
        processesCount = sc.nextInt();

        for (int i = 0; i < processesCount; i++) {
            System.out.println("Enter process name:");
            sc.nextLine();
            Process process = new Process(sc.nextLine());

            System.out.println("Enter process MAX matrix:");
            process.setProcessMaximum(sc);

            System.out.println("Enter process ALLO matrix:");
            process.setProcessAllocated(sc);
            systemAvailable.setRes(systemAvailable.minus(process.getAllocated()));

            processes.add(process);
        }

        sc.close();
    }

    public Banker(Resource systemAvailable, ArrayList<Process> processList) {
        this.systemAvailable = systemAvailable;
        this.processes = processList;
        processesCount = processes.size();
    }

    public ArrayList<Process> buildSafeSequence() throws Exception {
        while (!this.processes.isEmpty()) {
            boolean found = false;
            int index = 0;
            while(index != processesCount){
                if(processes.get(index).getNeed().compareTo(systemAvailable) > 0){
                    workRecord.add(new Resource(systemAvailable.getRes()));
                    safeSequence.add(processes.get(index));
                    systemAvailable.setRes(systemAvailable.add(processes.get(index).getAllocated()));

                    processes.remove(index);
                    found = true;
                    break;
                }
                else{
                    index ++;
                }
            }
            if (!found){
                throw new Exception("Can't find safe sequence");
            }

        }
        return safeSequence;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }

    public Resource getSystemAvailable() {
        return systemAvailable;
    }

    public int getProcessesCount() {
        return processesCount;
    }
}
