package com.vlist;

import java.util.Scanner;

public class Process {
    private static int globalPid = 1000;
    private String name;
    private Resource maximum;
    private Resource allocated;
    private final int pid;

    public Process(){
        this.pid = globalPid;
        globalPid++;
    }

    public Process(String name, Resource maximum, Resource allocated) {
        this();
        this.name = name;
        this.maximum = maximum;
        this.allocated = allocated;
    }

    public Process(String name) {
        this(name, new Resource(), new Resource());
    }

    public Resource getAllocated() {
        return allocated;
    }

    public Resource getMaximum() {
        return maximum;
    }

    public String getName() {
        return name;
    }

    public Resource getNeed(){
        return maximum.minus(allocated);
    }

    public void setProcessMaximum(Scanner sc) {
        maximum.setRes(sc.nextInt(), sc.nextInt(), sc.nextInt());
    }

    public void setProcessMaximum(int A, int B, int C) {
        maximum.setRes(A,B,C);
    }

    public void setProcessAllocated(Scanner sc) {
        allocated.setRes(sc.nextInt(), sc.nextInt(), sc.nextInt());
    }

    public void setProcessAllocated(int A, int B, int C) {
        maximum.setRes(A,B,C);
    }

    public int getPid() {
        return pid;
    }

    @Override
    public String toString() {
        return name +
                '\t' + maximum.toString() +
                '\t' + allocated.toString();
    }

}
