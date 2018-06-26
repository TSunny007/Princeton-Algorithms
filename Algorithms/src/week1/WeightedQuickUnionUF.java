package week1;

public class WeightedQuickUnionUF implements UF {
    int[] id;
    int[] weight;
    int count;
    public WeightedQuickUnionUF(int n) {
        id = new int[n];
        weight = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            id[i] = i;
            weight[i] = 1;
        }
    }
    @Override
    public void union(int p, int q) {
        int pid = root(p);
        int qid = root(q);

        // this makes sure that the larger component will always be the parent
        if (pid != qid) {
            if (weight[pid] > weight[qid]) {
                id[qid] = pid;
                weight[pid] += weight[qid];
            } else {
                id[pid] = qid;
                weight[qid] += weight[pid];
            }
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
