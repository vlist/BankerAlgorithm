package com.test;

import java.util.Scanner;

public class Process {
    private final String name;
    private final Resource maximum;
    private final Resource allocated;

    public Process(String name, Resource maximum, Resource allocated) {
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

    @Override
    public String toString() {
        return name +
                '\t' + maximum.toString() +
                '\t' + allocated.toString();
    }

}
