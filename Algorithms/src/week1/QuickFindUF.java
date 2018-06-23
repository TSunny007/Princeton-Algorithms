package week1;

import java.util.Arrays;
import java.util.HashSet;

public class QuickFindUF implements UF {
    private int[] id;

    public QuickFindUF(int n) {
     id = new int[n];
     for (int i = 0; i < n; i++) {
         id[i] = i;
     }
    }

    @Override
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i=0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    @Override
    public int find(int p) {
        return id[p];
    }

    @Override
    public int count() {
        return new HashSet<>(Arrays.asList(id)).size();
    }
}
