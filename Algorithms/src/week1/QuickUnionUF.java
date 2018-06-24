package week1;


public class QuickUnionUF implements UF {
    int[] id;
    int count;
    public QuickUnionUF(int n) {
        id = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }
    @Override
    public void union(int p, int q) {
        int pid = root(p);
        int qid = root(q);
        if (pid != qid) {
            id[qid] = root(p);
            count--;
        }
    }

    private int root(int p) {
        while (p != id[p]) {
            p = id[id[p]];
        }
        return p;
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    @Override
    public int find(int p) {
        return root(p);
    }

    @Override
    public int count() {
        return count;
        //return Arrays.stream(id).map(s -> root(s)).boxed().collect(Collectors.toSet()).size();
    }
}
