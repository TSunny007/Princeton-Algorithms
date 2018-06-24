package com.main;

import week1.QuickFindUF;
import week1.QuickUnionUF;
import week1.UF;

public class Main {

    public static void main(String[] args) {
	int N = 10;
	int[][] unions = new int[][] {{4,3},{3,8},{6,5},{9,4},{2,1},{8,9},{5,0},{7,2},{6,1},{1,0},{6,7}};
	UF uf = new QuickFindUF(N);
	for (int[] pair : unions) {
	    int p = pair[0];
	    int q = pair[1];
	    if (!uf.connected(p, q)) {
	        uf.union(p,q);
	        System.out.println(p + " " + q);
        }
    }
        System.out.println(uf.count());
    }
}
