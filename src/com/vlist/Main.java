package com.vlist;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Banker banker = new Banker();
        T0(banker);
        checkStatus(banker);
    }

    public static void T0(Banker banker){

        try {
            banker.buildSafeSequence();
            ArrayList<Process> safe = banker.getSafeSequence();
            System.out.println("Has Safe Sequence");
            System.out.println("PID\t\tName\tWork\tNeed\tAlloc\tWork+Alloc");

            for (int index = 0; index < safe.size(); index++) {

                String sb = safe.get(index).getPid() + "\t" +
                        safe.get(index).getName() + "\t\t" +
                        banker.getWorkRecord().get(index) + '\t' +
                        safe.get(index).getNeed() + '\t' +
                        safe.get(index).getAllocated() + '\t' +
                        safe.get(index).getAllocated().add(banker.getWorkRecord().get(index)) + '\t';
                System.out.println(sb);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static void checkStatus(Banker banker){
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        while(run){

            System.out.println("Enter PID which will send request");
            int pid = 0;
            pid = sc.nextInt();
            pid -= 1000;

            try{
                banker.RequestReceiver(pid);

                T0(banker);

            }catch(Exception e){
                System.out.println(e.toString());
            }

            System.out.println("Keep input request matrix?[true/false]");
            try {
                run = sc.nextBoolean();
            }
            catch(InputMismatchException e){
                run = true;
            }
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
1001
1 0 2
* */
