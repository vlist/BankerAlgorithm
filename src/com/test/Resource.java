package com.test;

import java.util.Scanner;

public class Resource implements Comparable<Resource> {
    private int[] res;

    public Resource() {
        res = new int[3];
    }

    public Resource(int A, int B, int C) {
        this();
        setRes(A, B, C);
    }

    public Resource(int[] resArray) {
        this();
        setRes(resArray);
    }

    public int[] getRes() {
        return res;
    }

    public void setRes(int A, int B, int C) {
        res[0] = A;
        res[1] = B;
        res[2] = C;
    }

    public void setRes(int[] resArray) {
        System.arraycopy(resArray, 0, res, 0, 3);
    }

    public void setRes(Scanner sc){
        for (int i=0;i<3;i++){
            res[i] = sc.nextInt();
        }
    }

    public void setRes(Resource res){
        this.res = res.getRes();
    }

    public Resource add(Resource _res1, Resource _res2) {
        int[] res1 = _res1.getRes();
        int[] res2 = _res2.getRes();
        int[] addRes = new int[3];

        for (int i = 0; i < 3; i++) {
            addRes[i] = res1[i] + res2[i];
        }
        return new Resource(addRes);
    }

    public Resource add(Resource _res2) {
        int[] res2 = _res2.getRes();
        int[] addRes = new int[3];

        for (int i = 0; i < 3; i++) {
            addRes[i] = res[i] + res2[i];
        }
        return new Resource(addRes);
    }

    public Resource minus(Resource _res1, Resource _res2) {
        int[] res1 = _res1.getRes();
        int[] res2 = _res2.getRes();
        int[] addRes = new int[3];

        for (int i = 0; i < 3; i++) {
            addRes[i] = res1[i] - res2[i];
        }
        return new Resource(addRes);
    }

    public Resource minus(Resource _res2) {
        int[] res2 = _res2.getRes();
        int[] addRes = new int[3];
        for (int i = 0; i < 3; i++) {
            addRes[i] = res[i] - res2[i];
        }
        return new Resource(addRes);
    }

    @Override
    public int compareTo(Resource o) {
        int[] aimRes = o.getRes();
        if (this.res[0] <= aimRes[0] && this.res[1] <= aimRes[1] && this.res[2] <= aimRes[2]){
            return 1;
        }
        else return -1;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i : res) {
            sb.append(i).append(' ');
        }
        return sb.toString();
    }

}
