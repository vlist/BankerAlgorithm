package com.vlist;

import java.util.ArrayList;
import java.util.Scanner;

public class Banker {
    private ArrayList<Process> processes = new ArrayList<Process>();
    private ArrayList<Process> safeSequence = new ArrayList<Process>();
    private ArrayList<Resource> workRecord = new ArrayList<Resource>();
    private Resource systemAvailable = new Resource();
    private int processesCount;

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

            System.out.println("Enter process ALLOC matrix:");
            process.setProcessAllocated(sc);
            systemAvailable.setRes(systemAvailable.minus(process.getAllocated()));

            processes.add(process);
        }

    }

    public void buildSafeSequence() throws Exception {
        ArrayList<Process> unchecked = new ArrayList<Process>(processes);

        if (!safeSequence.isEmpty()) {
            for (Process i : unchecked) {
                if (i.getName().equals(safeSequence.get(0).getName())) {
                    unchecked.remove(i);
                    break;
                }
            }
        }

        processesCount = unchecked.size();
        while (!unchecked.isEmpty()) {
            boolean found = false;
            int index = 0;
            while (index != processesCount) {
                if (unchecked.get(index).getNeed().compareTo(systemAvailable) > 0) {
                    workRecord.add(new Resource(systemAvailable.getRes()));
                    safeSequence.add(unchecked.get(index));
                    systemAvailable.setRes(systemAvailable.add(unchecked.get(index).getAllocated()));

                    unchecked.remove(index);
                    found = true;
                    break;
                } else {
                    index++;
                }
            }
            if (!found) {
                throw new RuntimeException("Can't find safe sequence");
            }
        }

    }


    public void RequestReceiver(int index) throws Exception {
        if (!workRecord.isEmpty()) {
            systemAvailable.setRes(workRecord.get(0).getRes());
        }
        safeSequence.clear();
        workRecord.clear();

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter request matrix");
        Resource request = new Resource(sc.nextInt(), sc.nextInt(), sc.nextInt());

        if (request.compareTo(processes.get(index).getNeed()) <= 0) {
            throw new RuntimeException(
                    "Process " + processes.get(index).getName() +
                            "(PID:" + (index + 1000) + ')' +
                            " request more than need");
        }

        if (request.compareTo(systemAvailable) <= 0) {
            throw new RuntimeException(
                    "Process " + processes.get(index).getName() +
                            "(PID:" + (index + 1000) + ')' +
                            "BLOCKED");
        }
        processes.get(index).getAllocated().setRes(processes.get(index).getAllocated().add(request));
        systemAvailable.setRes(systemAvailable.minus(request));

        safeSequence.add(processes.get(index));

        workRecord.add(new Resource(systemAvailable.getRes()));
        systemAvailable.setRes(systemAvailable.add(processes.get(index).getAllocated()));
    }

    public ArrayList<Process> getSafeSequence() {
        return safeSequence;
    }

    public ArrayList<Resource> getWorkRecord() {
        return workRecord;
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
