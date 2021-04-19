package com.vlist;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        Banker banker = new Banker();
        try {
            ArrayList<Process> safe = banker.buildSafeSequence();
            System.out.println("Name\tMax\tAllo");
            for (Process p : safe) {
                System.out.print(p.toString() + " \n");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}

/*
Input:
10 5 7
5
P0
7 5 3
0 1 0
P1
3 2 2
2 0 0
P2
9 0 2
3 0 2
P3
2 2 2
2 1 1
P4
4 3 3
0 0 2
* */
